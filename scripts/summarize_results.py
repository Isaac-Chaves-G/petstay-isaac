"""Publica un resumen legible de los resultados reales, sin cambiar su estado."""
import json
import os
import re
import xml.etree.ElementTree as ET
from pathlib import Path
from select_case import ROOT, normalize_case


def main():
    selected = normalize_case(os.environ.get('INPUT_CASE'))
    expected = {f'TC{i:02d}' for i in range(1, 30)} if selected == 'todos' else {selected}
    cases = []
    for path in (ROOT / 'target/site/serenity').glob('*.json'):
        data = json.loads(path.read_text(encoding='utf-8'))
        if not isinstance(data, dict) or 'testSteps' not in data:
            continue
        case_id = next((tag['name'] for tag in data.get('tags', [])
                        if re.fullmatch(r'TC\d{2}', tag.get('name', ''))), None)
        if case_id:
            rows = (data.get('dataTable') or {}).get('rows', [])
            executions = [row.get('result', 'UNKNOWN') for row in rows] if rows else [data.get('result', 'UNKNOWN')]
            cases.append({'id': case_id, 'name': data['name'], 'result': data.get('result', 'UNKNOWN'),
                          'report': path.name, 'executions': executions})
    cases.sort(key=lambda case: case['id'])
    totals = dict(discovered=0, skipped=0, failures=0, errors=0)
    for path in (ROOT / 'build/test-results/test').glob('TEST-*.xml'):
        suite = ET.parse(path).getroot()
        for key, attribute in [('discovered', 'tests'), ('skipped', 'skipped'), ('failures', 'failures'), ('errors', 'errors')]:
            totals[key] += int(suite.get(attribute, 0))
    totals['executed'] = totals['discovered'] - totals['skipped']
    totals['passed'] = totals['executed'] - totals['failures'] - totals['errors']
    missing = sorted(expected - {c['id'] for c in cases})
    unsuccessful = [c['id'] for c in cases if c['result'] != 'SUCCESS']
    executed = [status for case in cases for status in case['executions']]
    serenity = dict(executed=len(executed), passed=executed.count('SUCCESS'),
                    unsuccessful=sum(status != 'SUCCESS' for status in executed))
    result = dict(selected=selected, serenity=serenity, junit=totals, cases=cases, missing=missing, unsuccessful=unsuccessful)
    out = ROOT / 'target/audit-summary.json'
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
    headline = f"Serenity: {serenity['executed']} ejecuciones; {serenity['passed']} correctas; {serenity['unsuccessful']} sin éxito. Casos sin éxito: {', '.join(unsuccessful) or 'ninguno'}."
    print(headline)
    if os.environ.get('GITHUB_STEP_SUMMARY'):
        lines = ['## Resultados PetStay', '', headline, '',
                 'Los escenarios fuera del filtro aparecen como SKIPPED en JUnit y no se cuentan como ejecutados.', '',
                 '| Caso | Resultado Serenity |', '|---|---|']
        lines.extend(f"| {c['id']} | {c['result']} |" for c in cases)
        if missing:
            lines.extend(['', 'Sin evidencia: ' + ', '.join(missing)])
        lines.extend(['', 'TC14 y TC15 tienen defectos documentados; su etiqueta no convierte fallos en éxitos.'])
        with open(os.environ['GITHUB_STEP_SUMMARY'], 'a', encoding='utf-8') as summary:
            summary.write('\n'.join(lines) + '\n')
        print('::notice title=Resultado PetStay::' + headline)
    if missing or {c['id'] for c in cases} != expected or len(cases) != len({c['id'] for c in cases}):
        raise SystemExit('Reporte incompleto o casos duplicados; revisar target/audit-summary.json.')


if __name__ == '__main__':
    main()

"""Catálogo local y cuerpos de issues derivados de los .feature (sin acceso a red)."""
from pathlib import Path
import json
import re

ROOT = Path(__file__).resolve().parents[1]


def cases():
    result = []
    for path in sorted((ROOT / 'src/test/resources/features').glob('*.feature')):
        text = path.read_text(encoding='utf-8')
        background = re.search(r'  Antecedentes:\n(.*?)(?=\n  @TC|\Z)', text, re.S)
        for match in re.finditer(r'^  (@TC\d+[^\n]*)\n  (?:Escenario|Esquema del escenario): ([^\n]+)\n(.*?)(?=\n  @TC|\Z)', text, re.M | re.S):
            tags, title, steps = match.groups()
            case_id = re.search(r'@TC\d+', tags).group()[1:]
            requirements = re.findall(r'@REQ_\w+', tags)
            issue = re.search(r'@issue:(\d+)', tags)
            result.append(dict(id=case_id, title=title.strip(), tags=tags.split(),
                               requirements=requirements, feature=path.relative_to(ROOT).as_posix(),
                               background=background.group(1).strip() if background else '',
                               steps=steps.strip(), issue=int(issue.group(1)) if issue else None))
    return sorted(result, key=lambda case: case['id'])


def generate(repo=None):
    catalog = cases()
    ids = [case['id'] for case in catalog]
    if len(ids) != len(set(ids)) or set(ids) != {f'TC{i:02d}' for i in range(1, 30)}:
        raise ValueError('El catálogo debe contener exactamente TC01..TC29 sin duplicados')
    docs = ROOT / 'docs'
    (docs / 'issues').mkdir(parents=True, exist_ok=True)
    mapping_path = docs / 'github-mapping.json'
    if mapping_path.exists():
        repo = repo or json.loads(mapping_path.read_text(encoding='utf-8')).get('repository')
    table = ['# Trazabilidad HU-02: PetStay', '',
             'Los identificadores REQ corresponden a los criterios numerados de la historia de usuario.',
             'Los números de issue se incorporan únicamente después de crearlos en GitHub.', '',
             '| Caso | Comportamiento | Requisitos | Issue |', '|---|---|---|---|']
    for case in catalog:
        issue = f"[#{case['issue']}](https://github.com/{repo}/issues/{case['issue']})" if repo and case['issue'] else 'Pendiente de publicar'
        table.append(f"| {case['id']} | {case['title']} | {', '.join(case['requirements'])} | {issue} |")
        code = case['background'] + '\n\n' + case['steps'] if case['background'] else case['steps']
        body = f"# {case['id']}: {case['title']}\n\nHistoria: HU-02.\n\nCriterios: {', '.join(case['requirements'])}.\n\n"
        body += f"Feature: `{case['feature']}`.\n\nTipo y etiquetas: {' '.join(case['tags'])}.\n\n"
        body += f"## Precondiciones, acciones y resultados esperados\n\n```gherkin\n{code}\n```\n\n"
        body += f"## Ejecución\n\n`./gradlew test aggregate -Dcucumber.filter.tags=@{case['id']}`\n\n"
        body += "También se puede aplicar la etiqueta `ejecutar-prueba` a este issue. El workflow debe existir en la rama predeterminada. Para repetir la ejecución, retirar la etiqueta y volverla a añadir.\n\n"
        body += "## Evidencia\n\nReporte Serenity: artefacto del workflow PetStay Serenity BDD. Las pruebas con defectos conocidos mantienen el resultado esperado de la historia; no se omiten.\n"
        (docs / 'issues' / (case['id'] + '.md')).write_text(body, encoding='utf-8')
    (docs / 'TRAZABILIDAD.md').write_text('\n'.join(table) + '\n', encoding='utf-8')
    (docs / 'testcases.json').write_text(json.dumps(catalog, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
    return catalog


if __name__ == '__main__':
    print(f'{len(generate())} casos catalogados; cuerpos de issues y trazabilidad actualizados.')

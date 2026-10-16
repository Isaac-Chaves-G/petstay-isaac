"""Resuelve el caso de Actions antes de convertirlo en un filtro Cucumber."""
import json
import os
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]


def normalize_case(value):
    value = (value or '').strip()
    if value.startswith('@'):
        value = value[1:]
        if not value:
            raise ValueError('Falta el caso después de @.')
    value = value.upper()
    if value in ('', 'TODOS', 'HU02'):
        return 'todos'
    match = re.fullmatch(r'TC(\d{1,2})', value)
    if match and 1 <= int(match[1]) <= 29:
        return f'TC{int(match[1]):02d}'
    raise ValueError('Caso inválido: usa TC01..TC29 (con o sin @, en mayúsculas o minúsculas) o todos.')


def case_for_issue(number, mapping):
    matches = [case_id for case_id, issue in mapping['cases'].items() if issue['number'] == number]
    if len(matches) != 1:
        raise ValueError('El issue no está vinculado a un único caso de prueba en docs/github-mapping.json.')
    return normalize_case(matches[0])


def main():
    case = normalize_case(os.environ.get('INPUT_CASE'))
    if os.environ.get('GITHUB_EVENT_NAME') == 'issues':
        event = json.loads(Path(os.environ['GITHUB_EVENT_PATH']).read_text(encoding='utf-8'))
        mapping = json.loads((ROOT / 'docs/github-mapping.json').read_text(encoding='utf-8'))
        case = case_for_issue(event['issue']['number'], mapping)
    tags = '@HU02' if case == 'todos' else '@' + case
    print(f'Caso seleccionado: {case}; filtro Cucumber: {tags}')
    with open(os.environ['GITHUB_OUTPUT'], 'a', encoding='utf-8') as output:
        output.write(f'case={case}\ntags={tags}\n')


if __name__ == '__main__':
    try:
        main()
    except ValueError as error:
        raise SystemExit(str(error))

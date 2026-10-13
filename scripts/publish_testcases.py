"""Publica casos en un repositorio existente mediante GitHub CLI autenticado.

No crea repositorios, no cambia visibilidad y no hace push. Reutiliza issues por
título exacto y conserva un mapa local para poder continuar después de un error.
"""
import argparse
import json
import re
import subprocess
from pathlib import Path
from catalog import ROOT, generate


def gh(*args):
    return subprocess.run(['gh', *args], check=True, text=True, encoding='utf-8',
                          stdout=subprocess.PIPE).stdout.strip()


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--repo', required=True, help='propietario/repositorio existente')
    parser.add_argument('--project-number', required=True, type=int, help='Número del GitHub Project existente')
    parser.add_argument('--project-owner', help='Propietario del Project; por defecto, propietario del repo')
    args = parser.parse_args()
    if not re.fullmatch(r'[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+', args.repo):
        parser.error('Usa propietario/repositorio')
    owner = args.project_owner or args.repo.split('/')[0]
    gh('auth', 'status')
    gh('repo', 'view', args.repo, '--json', 'nameWithOwner')
    gh('project', 'view', str(args.project_number), '--owner', owner, '--format', 'json')
    gh('project', 'link', str(args.project_number), '--owner', owner, '--repo', args.repo)
    for label, color in [('testcase', '0E8A16'), ('HU02', '1D76DB'), ('ejecutar-prueba', '5319E7')]:
        gh('label', 'create', label, '--repo', args.repo, '--color', color, '--force')
    catalog = generate(args.repo)
    mapping_path = ROOT / 'docs/github-mapping.json'
    mapping = json.loads(mapping_path.read_text(encoding='utf-8')) if mapping_path.exists() else {}
    if mapping.get('repository', args.repo) != args.repo:
        raise SystemExit('El mapa existente pertenece a otro repositorio. No se modificarán sus enlaces.')
    mapping.update(repository=args.repo, project_owner=owner, project_number=args.project_number)
    mapping.setdefault('cases', {})
    existing = json.loads(gh('issue', 'list', '--repo', args.repo, '--state', 'all', '--limit', '1000', '--json', 'number,title,url'))
    by_title = {issue['title']: issue for issue in existing}
    for case in catalog:
        title = f"[{case['id']}] {case['title']}"
        issue = by_title.get(title)
        if issue is None:
            url = gh('issue', 'create', '--repo', args.repo, '--title', title,
                     '--body-file', str(ROOT / 'docs/issues' / (case['id'] + '.md')),
                     '--label', 'testcase', '--label', 'HU02')
            issue = {'url': url, 'number': int(url.rstrip('/').split('/')[-1])}
        mapping['cases'][case['id']] = issue
        mapping_path.write_text(json.dumps(mapping, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
        path = ROOT / case['feature']
        text = path.read_text(encoding='utf-8')
        def add_issue(match):
            tags = re.sub(r'\s+@issue:\d+', '', match.group(0))
            return tags + f" @issue:{issue['number']}"
        text = re.sub(r'^  @' + case['id'] + r'\b[^\n]*', add_issue, text, flags=re.M)
        path.write_text(text, encoding='utf-8')
        gh('project', 'item-add', str(args.project_number), '--owner', owner, '--url', issue['url'])
        print(f"{case['id']} -> {issue['url']}")
    conf = ROOT / 'src/test/resources/serenity.conf'
    text = re.sub(r'^serenity.issue.tracker.url\s*=.*$',
                  'serenity.issue.tracker.url = "https://github.com/' + args.repo + '/issues/{0}"',
                  conf.read_text(encoding='utf-8'), flags=re.M)
    conf.write_text(text, encoding='utf-8')
    generate(args.repo)
    print('Publicación completada. Revisa los cambios locales, haz commit y push para incorporar los tags de issues.')


if __name__ == '__main__':
    main()

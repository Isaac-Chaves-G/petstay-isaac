# Lista de entrega y pendientes externos

| Exigencia del taller | Implementación | Estado |
|---|---|---|
| Al menos 10 escenarios positivos y negativos | 29 casos en cuatro features, 46 ejecuciones contando ejemplos | Ejecutado: 44 pasan, 2 detectan defectos; RESULTADOS.md |
| Serenity BDD y Screenplay | Java, Tasks, Questions, Targets, actor, hooks y suite JUnit Platform | Implementado |
| Código en repositorio GitHub | Isaac-Chaves-G/petstay-isaac, rama main | Publicado |
| Un issue por caso | 29 cuerpos en docs/issues y script de publicación | Creación remota pendiente |
| Project vinculado al repo e issues | Script publish_testcases.py enlaza el Project y agrega los casos | Requiere acceso a GitHub Projects |
| Tags de requisitos e issue | Tags HU02/REQ/TC existentes; issue:N se añade con números reales al publicar | Requisitos listos; issues pendientes |
| GitHub Actions | .github/workflows/ci.yml ejecuta, filtra por caso y conserva evidencias | Configurado; ejecución remota pendiente |
| Ejecución desde TestCase | Evento issues:labeled con ejecutar-prueba | Preparado; validación remota pendiente |
| Sustentación oral | docs/SUSTENTACION.md | Preparada |

Repositorio publicado con la cuenta de Git Bash **Isaac-Chaves-G**: https://github.com/Isaac-Chaves-G/petstay-isaac.

El workflow **Publicar casos de prueba** utiliza la autenticación de GitHub Actions para crear los issues y guardar sus referencias. El Project de la cuenta requiere acceso adicional y sigue pendiente.

El `ci.yml` original citado en Teams/Intu no venía en los ZIP y el estudiante no lo encontró. El workflow actual es una alternativa propia.

No se ha afirmado que la entrega remota esté completada. Las pruebas locales, el workflow preparado y los borradores de issues no sustituyen la evidencia de ejecución en GitHub Actions.

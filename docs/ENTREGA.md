# Lista de entrega y pendientes externos

| Exigencia del taller | Implementación | Estado |
|---|---|---|
| Al menos 10 escenarios positivos y negativos | 29 casos en cuatro features, 46 ejecuciones contando ejemplos | Ejecutado: 44 pasan, 2 detectan defectos; RESULTADOS.md |
| Serenity BDD y Screenplay | Java, Tasks, Questions, Targets, actor, hooks y suite JUnit Platform | Implementado |
| Código en repositorio GitHub | Repositorio Git local en la carpeta del proyecto | Publicación remota pendiente |
| Un issue por caso | 29 cuerpos en docs/issues y script de publicación | Creación remota pendiente |
| Project vinculado al repo e issues | Script publish_testcases.py enlaza el Project y agrega los casos | Requiere acceso a GitHub Projects |
| Tags de requisitos e issue | Tags HU02/REQ/TC existentes; issue:N se añade con números reales al publicar | Requisitos listos; issues pendientes |
| GitHub Actions | .github/workflows/ci.yml ejecuta, filtra por caso y conserva evidencias | Configurado; ejecución remota pendiente |
| Ejecución desde TestCase | Evento issues:labeled con ejecutar-prueba | Preparado; validación remota pendiente |
| Sustentación oral | docs/SUSTENTACION.md | Preparada |

Cuenta de GitHub identificada por Git Credential Manager de Git Bash: **Isaac-Chaves-G**. Nombre propuesto para el repositorio: **petstay-serenity**. No se ha creado un repositorio remoto ni se han inventado URLs de recursos inexistentes.

GitHub CLI no tenía una sesión iniciada al revisar el equipo. El inicio de sesión nuevo se canceló a petición del estudiante. Se requiere un mecanismo autorizado de acceso a la API de GitHub o la creación de los recursos en la web; Git por sí solo permite transferir código a un repositorio existente, pero no crear repositorios, issues o Projects.

El `ci.yml` original citado en Teams/Intu no venía en los ZIP. El estudiante indicó que lo enviará; el workflow actual es una alternativa propia y queda pendiente contrastarlo con el archivo de la profesora.

No se ha afirmado que la entrega remota esté completada. Las pruebas locales, el workflow preparado y los borradores de issues no sustituyen la evidencia de ejecución en GitHub Actions.

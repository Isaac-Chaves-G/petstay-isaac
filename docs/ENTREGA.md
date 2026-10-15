# Lista de entrega y pendientes externos

| Exigencia del taller | Implementación | Estado |
|---|---|---|
| Al menos 10 escenarios positivos y negativos | 29 casos en cuatro features, 46 ejecuciones contando ejemplos | Ejecutado: 44 pasan, 2 detectan defectos; RESULTADOS.md |
| Serenity BDD y Screenplay | Java, Tasks, Questions, Targets, actor, hooks y suite JUnit Platform | Implementado |
| Código en repositorio GitHub | Isaac-Chaves-G/petstay-isaac, rama main | Publicado |
| Un issue por caso | 29 issues reales, #1 a #29, en el repositorio | Publicados y verificados |
| Project vinculado al repo e issues | Script publish_testcases.py enlaza el Project y agrega los casos | Requiere acceso a GitHub Projects |
| Tags de requisitos e issue | Tags HU02/REQ/TC e issue:N; docs/github-mapping.json registra las URLs reales | Los 29 vínculos coinciden con los issues |
| GitHub Actions | .github/workflows/ci.yml ejecuta, filtra por caso y conserva evidencias | Primera ejecución remota finalizada con fallo; artefacto serenity-1 conservado; detalle de fallos remoto por revisar |
| Ejecución desde TestCase | Evento issues:labeled con ejecutar-prueba | Preparado; validación remota pendiente |
| Sustentación oral | docs/SUSTENTACION.md | Preparada |

Repositorio publicado con la cuenta de Git Bash **Isaac-Chaves-G**: https://github.com/Isaac-Chaves-G/petstay-isaac.

El workflow **Publicar casos de prueba** utiliza la autenticación de GitHub Actions para crear los issues y guardar sus referencias. El Project de la cuenta requiere acceso adicional y sigue pendiente.

El `ci.yml` original citado en Teams/Intu no venía en los ZIP y el estudiante no lo encontró. El workflow actual es una alternativa propia.

Publicación de los 29 issues completada correctamente: [workflow de publicación](https://github.com/Isaac-Chaves-G/petstay-isaac/actions/runs/38810800925). [Lista de issues](https://github.com/Isaac-Chaves-G/petstay-isaac/issues). Cada uno contiene precondiciones, pasos, resultados esperados y cómo aplicar `ejecutar-prueba`. El README explica el procedimiento completo.

La primera ejecución remota de la suite está en [Actions](https://github.com/Isaac-Chaves-G/petstay-isaac/actions/runs/38810134760) y conserva el artefacto `serenity-1`. Su estado es fallo; sin inspeccionar ese reporte no se atribuyen automáticamente todos los fallos remotos a los dos bugs confirmados localmente. La ejecución iniciada por una etiqueta aplicada por el estudiante y el Project siguen pendientes de validación.

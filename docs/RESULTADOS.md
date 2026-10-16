# Resultados de ejecución

## Auditoría del 16 de octubre de 2026

Los fallos reportados en GitHub con `Tc01`, `@TC12` y `@TC14` ocurrieron en **Resolver caso solicitado**, antes de ejecutar Cucumber. El selector anterior solo admitía `TC` en mayúsculas y sin `@`. Evidencias remotas: [Tc01](https://github.com/Isaac-Chaves-G/petstay-isaac/actions/runs/38817992286), [@TC12](https://github.com/Isaac-Chaves-G/petstay-isaac/actions/runs/38827584382) y [@TC14](https://github.com/Isaac-Chaves-G/petstay-isaac/actions/runs/38827776960). Esos resultados no prueban defectos de PetStay.

Se corrigió la normalización del selector y su resolución por número real del issue. Cinco pruebas de regresión cubren todos los TC, las variantes reportadas, entradas inválidas y el mapeo de issues. La selección manual y la ejecución desde issues utilizan el mismo selector.

Se revisaron las 29 expectativas contra la historia de usuario. Las comprobaciones del registro ahora comparan cada celda completa; un importe esperado de cero admite tanto la fila oculta como el valor cero visible. Cualquier importe distinto sigue fallando. La verificación final exige `SUCCESS` en Serenity, incluyendo los casos pendientes como resultados no exitosos. El Summary utiliza los resultados de Serenity, incluso cuando JUnit no propaga un fallo de Screenplay.

TC14 y TC15 conservan las expectativas de la historia: **más de siete** noches para descuento y paseo por **cada noche**. No se excluyen de la suite. Las nuevas ejecuciones se documentan a continuación al finalizar; los resultados históricos no sustituyen la validación de esta versión.

## Ejecución inicial local

Ejecución completa contra **https://petstay-aphn.onrender.com**, iniciada el **12 de octubre de 2026 a las 22:08 (America/Bogota)**. Entorno: Windows, Chrome, JDK 21.0.7, Serenity 4.2.32 y Gradle 8.14.3.

Comando: `gradlew.bat clean test aggregate --console=plain`.

| Medida | Resultado |
|---|---:|
| Casos Gherkin distintos | 29 |
| Ejecuciones contando ejemplos | 46 |
| Ejecuciones exitosas | 44 |
| Fallos funcionales | 2 |
| Errores de infraestructura | 0 |
| Ejecuciones omitidas | 0 |
| Casos completos exitosos en Serenity | 27 |
| Casos con fallo en Serenity | 2 |

La ejecución de pruebas duró aproximadamente siete minutos. Gradle terminó con código distinto de cero por los dos defectos detectados; tanto JUnit como el reporte Serenity coinciden. `verifySerenityResults` también detectó ambos fallos. No se deshabilitaron los casos fallidos.

## Defectos reproducidos

| Caso | Regla | Esperado | Observado |
|---|---|---|---|
| TC14 | Descuento solo para más de siete noches | Siete noches, descuento $0, total $280.000 | Descuento $42.000, total $238.000 |
| TC15 | Paseo de $10.000 por noche | Tres noches, paseos $30.000, total $150.000 | Paseos $10.000, total $130.000 |

Detalles de reproducción y propuesta de corrección: [DEFECTOS.md](DEFECTOS.md).

### Evidencia TC14

![Siete noches reciben incorrectamente un descuento](evidencias/TC14.png)

### Evidencia TC15

![Tres noches muestran un cargo único de paseo](evidencias/TC15.png)

## Archivos de evidencia

- [Resumen estructurado de la ejecución](evidencias/resultado-ejecucion.json): identifica los 29 casos y su resultado.
- [Resultados originales de JUnit](evidencias/junit.xml): 46 ejecuciones, dos fallos y ninguna omitida.
- Reporte completo con navegación y capturas: `target/site/serenity/index.html`.
- Los archivos JSON originales de Serenity y las capturas de todos los pasos se conservan junto al reporte completo.

Este resultado histórico corresponde a la aplicación publicada en la fecha de ejecución. Una nueva ejecución depende del estado de PetStay y de cualquier cambio que realice su responsable. Los 29 issues y las ejecuciones de GitHub posteriores pueden consultarse en el repositorio; los enlaces y resultados de la auditoría actual se documentan por separado.

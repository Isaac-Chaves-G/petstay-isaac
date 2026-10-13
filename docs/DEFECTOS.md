# Defectos de PetStay frente a HU-02

La historia de usuario es la fuente del resultado esperado. La automatización no modifica la aplicación de la profesora ni adapta las expectativas a sus defectos. Los resultados y capturas de la ejecución se conservan en `RESULTADOS.md` y en el reporte Serenity.

**Estado:** ambos defectos fueron reproducidos por la suite completa el 12/10/2026. Las capturas confirman los importes observados.

## BUG-01: descuento aplicado a siete noches

- Caso automatizado: **TC14**.
- Requisito: HU-02, Reserva de estadía, criterio 7 (`@REQ_RESERVAS_07`).
- Precondición: dueño autenticado; perro registrado de 10 kg (tamaño pequeño).
- Reproducción: preparar siete noches, entrada dentro de diez días, sin baño ni paseo; calcular total.
- Esperado: alojamiento $280.000, descuento $0, total **$280.000**. El descuento comienza con más de siete noches.
- Comportamiento identificado en el código público de PetStay: la condición usa `noches >= 7`, por lo que aplica $42.000 de descuento y total **$238.000**.
- Impacto: cobro inferior al definido para estadías de siete noches.
- Corrección sugerida al responsable de la aplicación: aplicar el descuento cuando `noches > 7`; mantener una prueba de frontera de siete y otra de ocho noches.

## BUG-02: paseo diario cobrado una sola vez

- Caso automatizado: **TC15**.
- Requisito: HU-02, Reserva de estadía, criterio 6 (`@REQ_RESERVAS_06`).
- Precondición: dueño autenticado; perro registrado de 10 kg.
- Reproducción: preparar tres noches, entrada dentro de diez días, seleccionar paseo diario, sin baño; calcular total.
- Esperado: alojamiento $120.000, paseos 3 × $10.000 = **$30.000**, total **$150.000**.
- Comportamiento identificado en el código público de PetStay: añade una sola tarifa de $10.000 por paseo, total **$130.000**.
- Impacto: cobro incorrecto del servicio para estadías de más de una noche.
- Corrección sugerida al responsable de la aplicación: multiplicar el precio del paseo por las noches, conservando la restricción de especie perro.

## Tratamiento en la entrega

Ambos escenarios tienen `@defecto_conocido` para su identificación, pero **no están deshabilitados ni excluidos de la suite completa o de CI**. La etiqueta no transforma un fallo en éxito. GitHub Actions debe reflejar los fallos y conservar el reporte como evidencia. Un subconjunto de pruebas se puede ejecutar por `@TCxx` o `@smoke`, indicando siempre qué se ejecutó.

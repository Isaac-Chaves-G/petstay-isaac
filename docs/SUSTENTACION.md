# Guía de sustentación

## Recorrido TC24

1. `features/reservas.feature`: describe confirmar una estadía y consultarla.
2. `AccessSteps`: prepara una sesión válida.
3. `PetSteps`: registra la mascota necesaria, sin depender de otro escenario.
4. `BookingSteps`: conecta los pasos de negocio con `PlanStay` y `ConfirmReservation`.
5. `PlanStay`: selecciona la mascota, toma la fecha visible de PetStay, llena fechas y servicios y calcula.
6. `ConfirmReservation`: confirma, espera el código visible y lo recuerda en el actor.
7. `ReservationsPage`: contiene los localizadores de la tabla y de la fila identificada por ese código.
8. `DisplayedMoney`: convierte el importe mostrado a un entero en pesos.
9. Las aserciones verifican mascota, fechas, precio, estado, código y reembolso inicial.
10. El reporter convierte las tareas y verificaciones en documentación viva con capturas.

## Conceptos que debes poder explicar

- **Actor:** representa al dueño. `OnlineCast` le proporciona un navegador.
- **Task:** cumple una intención de negocio, como registrar una mascota.
- **Interaction:** acción pequeña ya provista por Serenity, como `Click` o `Enter`.
- **Target:** identifica un elemento y le da un nombre legible en el reporte.
- **Question:** obtiene información sin cambiar el estado de la aplicación.
- **Ensure:** compara el resultado observado con lo que exige el requisito.
- **Hook:** prepara el actor antes de cada escenario.
- **Runner:** descubre los `.feature`, conecta sus steps y activa el reporter.
- **Issue y tags:** conectan requisito, caso, implementación y evidencia.

## Ejercicios para la demostración

1. Ejecutar únicamente TC24 mediante su etiqueta.
2. Mostrar el reporte y localizar el paso donde se confirma la reserva.
3. Abrir `PetsPage`, encontrar el campo nombre y explicar por qué se usa `#nombreMascota`.
4. Inspeccionar manualmente el campo peso: `[name='peso']` es un localizador por atributo.
5. Explicar por qué el código de reserva se guarda en el actor y no en una variable `static`.
6. Explicar cómo se calcula una entrada dentro de tres días y por qué no se fija una fecha literal.
7. Comparar TC14 y TC15 con la historia de usuario y explicar por qué un reporte rojo puede indicar un defecto real del producto.
8. Seguir un tag `@REQ_RESERVAS_10` hasta TC24, su issue real y el reporte del workflow.

## Cálculos de referencia

- Perro pequeño, dos noches sin servicios: 2 × 40.000 = 80.000.
- Ocho noches con baño: 320.000 − 48.000 + 25.000 = 297.000.
- Siete noches: 280.000, sin descuento; la regla exige **más de siete**.
- Tres noches con paseo diario: 120.000 + 3 × 10.000 = 150.000.
- Cancelación de una reserva de 80.000: 80.000 a tres días, 40.000 a uno/dos días, 0 el mismo día.

## Decisiones de diseño

La consulta de importes no calcula el valor esperado con el código de PetStay: lee lo mostrado. Los valores esperados se especifican en el Gherkin a partir de la historia de usuario. Así se evita copiar un defecto de la aplicación a la prueba.

Las esperas explícitas se limitan a resultados asincrónicos de acceso, registro, confirmación y cancelación. No se usan pausas arbitrarias. Los Targets se agrupan por página para que un cambio de HTML afecte el menor número de archivos posible.

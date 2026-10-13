# Trazabilidad HU-02: PetStay

Los identificadores REQ corresponden a los criterios numerados de la historia de usuario.
Los números de issue se incorporan únicamente después de crearlos en GitHub.

| Caso | Comportamiento | Requisitos | Issue |
|---|---|---|---|
| TC01 | Acceso con credenciales válidas | @REQ_ACCESO_01 | Pendiente de publicar |
| TC02 | Rechazo de credenciales inválidas | @REQ_ACCESO_02 | Pendiente de publicar |
| TC03 | Registro y clasificación por peso | @REQ_MASCOTAS_01, @REQ_MASCOTAS_03, @REQ_MASCOTAS_05 | Pendiente de publicar |
| TC04 | Rechazo de mascota sin vacunas al día | @REQ_MASCOTAS_02 | Pendiente de publicar |
| TC05 | Rechazo de nombre duplicado sin distinguir mayúsculas | @REQ_MASCOTAS_04 | Pendiente de publicar |
| TC06 | Validación de longitud y obligatoriedad del nombre | @REQ_MASCOTAS_01 | Pendiente de publicar |
| TC07 | Rechazo de peso fuera de rango | @REQ_MASCOTAS_01 | Pendiente de publicar |
| TC08 | Rechazo de edad fuera de rango | @REQ_MASCOTAS_01 | Pendiente de publicar |
| TC09 | Aceptación de las edades límite | @REQ_MASCOTAS_01 | Pendiente de publicar |
| TC10 | Registro previo requerido para reservar | @REQ_RESERVAS_01 | Pendiente de publicar |
| TC11 | Tarifa de alojamiento según el tamaño | @REQ_RESERVAS_05, @REQ_RESERVAS_08 | Pendiente de publicar |
| TC12 | Baño como cargo único por estadía | @REQ_RESERVAS_06, @REQ_RESERVAS_08 | Pendiente de publicar |
| TC13 | Descuento de estadía larga aplicado solo al alojamiento | @REQ_RESERVAS_07, @REQ_RESERVAS_08 | Pendiente de publicar |
| TC14 | Siete noches no reciben descuento | @REQ_RESERVAS_07 | Pendiente de publicar |
| TC15 | Paseo diario cobrado por cada noche | @REQ_RESERVAS_06 | Pendiente de publicar |
| TC16 | Rechazo de entrada anterior a hoy | @REQ_RESERVAS_03 | Pendiente de publicar |
| TC17 | Rechazo de salida que no es posterior a la entrada | @REQ_RESERVAS_03 | Pendiente de publicar |
| TC18 | Rechazo de fechas inválidas | @REQ_RESERVAS_02 | Pendiente de publicar |
| TC19 | Rechazo de una estadía de 31 noches | @REQ_RESERVAS_04 | Pendiente de publicar |
| TC20 | Aceptación de una estadía de 30 noches | @REQ_RESERVAS_04 | Pendiente de publicar |
| TC21 | Rechazo de fechas cruzadas para la misma mascota | @REQ_RESERVAS_09 | Pendiente de publicar |
| TC22 | Reservas consecutivas comparten el día de salida y entrada | @REQ_RESERVAS_09 | Pendiente de publicar |
| TC23 | Paseo diario no disponible para gatos | @REQ_RESERVAS_06 | Pendiente de publicar |
| TC24 | Confirmación y consulta de la reserva | @REQ_RESERVAS_10, @REQ_CANCELACION_01 | Pendiente de publicar |
| TC25 | Cancelación con tres días de anticipación y reembolso completo | @REQ_CANCELACION_02, @REQ_CANCELACION_03, @REQ_CANCELACION_04 | Pendiente de publicar |
| TC26 | Cancelación con uno o dos días y reembolso parcial | @REQ_CANCELACION_03, @REQ_CANCELACION_04 | Pendiente de publicar |
| TC27 | Cancelación el mismo día sin reembolso | @REQ_CANCELACION_03, @REQ_CANCELACION_04 | Pendiente de publicar |
| TC28 | Conservar la reserva al rechazar la cancelación | @REQ_CANCELACION_02 | Pendiente de publicar |
| TC29 | Una cancelación libera las fechas para una nueva reserva | @REQ_RESERVAS_09, @REQ_CANCELACION_04 | Pendiente de publicar |

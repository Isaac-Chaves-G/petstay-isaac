# Trazabilidad HU-02: PetStay

Los identificadores REQ corresponden a los criterios numerados de la historia de usuario.
Los números de issue se incorporan únicamente después de crearlos en GitHub.

| Caso | Comportamiento | Requisitos | Issue |
|---|---|---|---|
| TC01 | Acceso con credenciales válidas | @REQ_ACCESO_01 | [#1](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/1) |
| TC02 | Rechazo de credenciales inválidas | @REQ_ACCESO_02 | [#2](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/2) |
| TC03 | Registro y clasificación por peso | @REQ_MASCOTAS_01, @REQ_MASCOTAS_03, @REQ_MASCOTAS_05 | [#3](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/3) |
| TC04 | Rechazo de mascota sin vacunas al día | @REQ_MASCOTAS_02 | [#4](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/4) |
| TC05 | Rechazo de nombre duplicado sin distinguir mayúsculas | @REQ_MASCOTAS_04 | [#5](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/5) |
| TC06 | Validación de longitud y obligatoriedad del nombre | @REQ_MASCOTAS_01 | [#6](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/6) |
| TC07 | Rechazo de peso fuera de rango | @REQ_MASCOTAS_01 | [#7](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/7) |
| TC08 | Rechazo de edad fuera de rango | @REQ_MASCOTAS_01 | [#8](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/8) |
| TC09 | Aceptación de las edades límite | @REQ_MASCOTAS_01 | [#9](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/9) |
| TC10 | Registro previo requerido para reservar | @REQ_RESERVAS_01 | [#10](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/10) |
| TC11 | Tarifa de alojamiento según el tamaño | @REQ_RESERVAS_05, @REQ_RESERVAS_08 | [#11](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/11) |
| TC12 | Baño como cargo único por estadía | @REQ_RESERVAS_06, @REQ_RESERVAS_08 | [#12](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/12) |
| TC13 | Descuento de estadía larga aplicado solo al alojamiento | @REQ_RESERVAS_07, @REQ_RESERVAS_08 | [#13](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/13) |
| TC14 | Siete noches no reciben descuento | @REQ_RESERVAS_07 | [#14](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/14) |
| TC15 | Paseo diario cobrado por cada noche | @REQ_RESERVAS_06 | [#15](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/15) |
| TC16 | Rechazo de entrada anterior a hoy | @REQ_RESERVAS_03 | [#16](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/16) |
| TC17 | Rechazo de salida que no es posterior a la entrada | @REQ_RESERVAS_03 | [#17](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/17) |
| TC18 | Rechazo de fechas inválidas | @REQ_RESERVAS_02 | [#18](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/18) |
| TC19 | Rechazo de una estadía de 31 noches | @REQ_RESERVAS_04 | [#19](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/19) |
| TC20 | Aceptación de una estadía de 30 noches | @REQ_RESERVAS_04 | [#20](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/20) |
| TC21 | Rechazo de fechas cruzadas para la misma mascota | @REQ_RESERVAS_09 | [#21](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/21) |
| TC22 | Reservas consecutivas comparten el día de salida y entrada | @REQ_RESERVAS_09 | [#22](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/22) |
| TC23 | Paseo diario no disponible para gatos | @REQ_RESERVAS_06 | [#23](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/23) |
| TC24 | Confirmación y consulta de la reserva | @REQ_RESERVAS_10, @REQ_CANCELACION_01 | [#24](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/24) |
| TC25 | Cancelación con tres días de anticipación y reembolso completo | @REQ_CANCELACION_02, @REQ_CANCELACION_03, @REQ_CANCELACION_04 | [#25](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/25) |
| TC26 | Cancelación con uno o dos días y reembolso parcial | @REQ_CANCELACION_03, @REQ_CANCELACION_04 | [#26](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/26) |
| TC27 | Cancelación el mismo día sin reembolso | @REQ_CANCELACION_03, @REQ_CANCELACION_04 | [#27](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/27) |
| TC28 | Conservar la reserva al rechazar la cancelación | @REQ_CANCELACION_02 | [#28](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/28) |
| TC29 | Una cancelación libera las fechas para una nueva reserva | @REQ_RESERVAS_09, @REQ_CANCELACION_04 | [#29](https://github.com/Isaac-Chaves-G/petstay-isaac/issues/29) |

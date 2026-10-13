# HU-02: Hospedaje de mascotas en PetStay

**Como** dueño de una mascota
**quiero** registrar a mis mascotas, reservar estadías con servicios adicionales y gestionar mis reservas
**para** que mis mascotas estén bien cuidadas cuando no pueda atenderlas.

## Criterios de aceptación

### Acceso
1. Para usar el hotel hay que iniciar sesión con usuario y contraseña válidos.
2. Si las credenciales son incorrectas, se muestra el mensaje "Usuario o contraseña incorrectos".

### Funcionalidad 1: Registro de mascotas
1. Los campos son:
   - Nombre: obligatorio, entre 2 y 30 caracteres.
   - Especie: perro o gato.
   - Peso: en kg, mayor que 0 y hasta 80, admite decimales.
   - Edad: en años, entre 0 y 25.
   - Vacunas al día: sí o no.
2. Sin vacunas al día no se permite el registro y se muestra "No podemos hospedar mascotas sin vacunas al día".
3. El tamaño se asigna automáticamente según el peso:
   - Pequeño: hasta 10 kg.
   - Mediano: más de 10 kg y menos de 25 kg.
   - Grande: 25 kg o más.
4. No se pueden registrar dos mascotas con el mismo nombre (sin distinguir mayúsculas).
5. La lista de mascotas muestra el nombre, la especie, el peso, la edad y el tamaño.

### Funcionalidad 2: Reserva de estadía
1. Solo se puede reservar para una mascota registrada.
2. Las fechas se escriben con formato `DD/MM/AAAA`.
3. La fecha de entrada no puede ser anterior a hoy. La de salida debe ser posterior a la de entrada.
4. La estadía máxima es de 30 noches.
5. La tarifa por noche depende del tamaño: pequeño $40.000, mediano $55.000, grande $70.000.
6. Los servicios adicionales son dos:
   - Baño y peluquería: $25.000, cargo único por estadía.
   - Paseo diario: $10.000 por noche, solo disponible para perros.
7. Las estadías de **más de 7 noches** tienen 15% de descuento. El descuento aplica solo sobre el alojamiento, no sobre los servicios.
8. Antes de confirmar se muestra un resumen con las noches, el valor del alojamiento, el descuento, los servicios y el total.
9. Una mascota no puede tener dos reservas activas con fechas que se crucen. El día de salida de una reserva puede ser el día de entrada de otra.
10. Al confirmar se genera un código `PS-XXXX` y la reserva queda en estado "Confirmada".

### Funcionalidad 3: Mis reservas y cancelación
1. La lista muestra el código, la mascota, las fechas, el total, el estado y el reembolso de cada reserva.
2. Se puede cancelar una reserva "Confirmada". Antes de cancelar se pide confirmación.
3. El reembolso depende de los días que faltan para la fecha de entrada:
   - 3 días o más: 100%.
   - 1 o 2 días: 50%.
   - El mismo día: 0%.
4. La reserva cancelada pasa a estado "Cancelada" y muestra el valor reembolsado. Ya no se puede volver a cancelar.

Usuario: `dueno` · Contraseña: `huellitas123`

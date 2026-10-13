# language: es
@HU02 @reservas
Característica: Reserva de estadías
  Como dueño quiero conocer el costo del hospedaje y reservar fechas disponibles.

  Antecedentes:
    Dado el dueño ha iniciado sesión en PetStay

  @TC10 @REQ_RESERVAS_01 @negativo
  Escenario: Registro previo requerido para reservar
    Cuando consulta la disponibilidad para reservar sin mascotas
    Entonces debe registrar una mascota antes de reservar

  @TC11 @REQ_RESERVAS_05 @REQ_RESERVAS_08 @positivo
  Esquema del escenario: Tarifa de alojamiento según el tamaño
    Dado tiene registrada a "Toby" de especie "Perro" y peso "<peso>"
    Cuando prepara una estadía para "Toby"
    Y solicita 2 noches con entrada en 10 días, baño "no" y paseo "no"
    Entonces el resumen muestra 2 noches, alojamiento <alojamiento>, descuento 0, baño 0, paseo 0 y total <alojamiento>

    Ejemplos:
      | peso | alojamiento |
      | 10   | 80000       |
      | 15   | 110000      |
      | 25   | 140000      |

  @TC12 @REQ_RESERVAS_06 @REQ_RESERVAS_08 @positivo
  Escenario: Baño como cargo único por estadía
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 2 noches con entrada en 10 días, baño "sí" y paseo "no"
    Entonces el resumen muestra 2 noches, alojamiento 80000, descuento 0, baño 25000, paseo 0 y total 105000

  @TC13 @REQ_RESERVAS_07 @REQ_RESERVAS_08 @positivo @frontera
  Escenario: Descuento de estadía larga aplicado solo al alojamiento
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 8 noches con entrada en 10 días, baño "sí" y paseo "no"
    Entonces el resumen muestra 8 noches, alojamiento 320000, descuento 48000, baño 25000, paseo 0 y total 297000

  @TC14 @REQ_RESERVAS_07 @positivo @frontera @defecto_conocido
  Escenario: Siete noches no reciben descuento
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 7 noches con entrada en 10 días, baño "no" y paseo "no"
    Entonces el resumen muestra 7 noches, alojamiento 280000, descuento 0, baño 0, paseo 0 y total 280000

  @TC15 @REQ_RESERVAS_06 @positivo @defecto_conocido
  Escenario: Paseo diario cobrado por cada noche
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 3 noches con entrada en 10 días, baño "no" y paseo "sí"
    Entonces el resumen muestra 3 noches, alojamiento 120000, descuento 0, baño 0, paseo 30000 y total 150000

  @TC16 @REQ_RESERVAS_03 @negativo
  Escenario: Rechazo de entrada anterior a hoy
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 2 noches con entrada en -1 días, baño "no" y paseo "no"
    Entonces el campo de reserva "entrada" indica "La fecha de entrada no puede ser anterior a hoy"

  @TC17 @REQ_RESERVAS_03 @negativo @frontera
  Esquema del escenario: Rechazo de salida que no es posterior a la entrada
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita <noches> noches con entrada en 10 días, baño "no" y paseo "no"
    Entonces el campo de reserva "salida" indica "La fecha de salida debe ser posterior a la de entrada"

    Ejemplos:
      | noches |
      | 0      |
      | -1     |

  @TC18 @REQ_RESERVAS_02 @negativo
  Esquema del escenario: Rechazo de fechas inválidas
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita entrada "<entrada>" y salida "10/01/2099"
    Entonces el campo de reserva "entrada" indica "Usa el formato DD/MM/AAAA con una fecha válida"

    Ejemplos:
      | entrada    |
      | 2099-01-01 |
      | 32/01/2099 |

  @TC19 @REQ_RESERVAS_04 @negativo @frontera
  Escenario: Rechazo de una estadía de 31 noches
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 31 noches con entrada en 10 días, baño "no" y paseo "no"
    Entonces el campo de reserva "salida" indica "La estadía máxima es de 30 noches"

  @TC20 @REQ_RESERVAS_04 @positivo @frontera
  Escenario: Aceptación de una estadía de 30 noches
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 30 noches con entrada en 10 días, baño "no" y paseo "no"
    Entonces el resumen muestra 30 noches, alojamiento 1200000, descuento 180000, baño 0, paseo 0 y total 1020000

  @TC21 @REQ_RESERVAS_09 @negativo
  Escenario: Rechazo de fechas cruzadas para la misma mascota
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Y tiene una reserva de 3 noches para "Toby" con entrada en 10 días
    Cuando prepara una estadía para "Toby"
    Y solicita 2 noches con entrada en 11 días, baño "no" y paseo "no"
    Entonces la reserva es rechazada con "Esta mascota ya tiene una reserva activa en esas fechas"
    Cuando consulta sus reservas
    Entonces hay 1 reservas en la lista

  @TC22 @REQ_RESERVAS_09 @positivo @frontera
  Escenario: Reservas consecutivas comparten el día de salida y entrada
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Y tiene una reserva de 3 noches para "Toby" con entrada en 10 días
    Cuando prepara una estadía para "Toby"
    Y solicita 2 noches con entrada en 13 días, baño "no" y paseo "no"
    Y confirma la estadía
    Y consulta sus reservas
    Entonces su reserva aparece confirmada por 80000 pesos con código válido y los datos solicitados
    Y hay 2 reservas en la lista

  @TC23 @REQ_RESERVAS_06 @negativo
  Escenario: Paseo diario no disponible para gatos
    Dado tiene registrada a "Luna" de especie "Gato" y peso "5"
    Cuando prepara una estadía para "Luna"
    Entonces el paseo diario no está disponible

  @TC24 @REQ_RESERVAS_10 @REQ_CANCELACION_01 @positivo @smoke
  Escenario: Confirmación y consulta de la reserva
    Dado tiene registrada a "Toby" de especie "Perro" y peso "10"
    Cuando prepara una estadía para "Toby"
    Y solicita 2 noches con entrada en 10 días, baño "no" y paseo "no"
    Y confirma la estadía
    Y consulta sus reservas
    Entonces su reserva aparece confirmada por 80000 pesos con código válido y los datos solicitados
    Y hay 1 reservas en la lista

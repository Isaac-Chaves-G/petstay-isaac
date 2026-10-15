# language: es
@HU02 @cancelaciones
Característica: Cancelación y reembolso
  Como dueño quiero cancelar mis reservas y recibir el reembolso que corresponda.

  Antecedentes:
    Dado el dueño ha iniciado sesión en PetStay
    Y tiene registrada a "Toby" de especie "Perro" y peso "10"

  @TC25 @REQ_CANCELACION_02 @REQ_CANCELACION_03 @REQ_CANCELACION_04 @positivo @frontera @smoke @issue:25
  Escenario: Cancelación con tres días de anticipación y reembolso completo
    Dado tiene una reserva de 2 noches para "Toby" con entrada en 3 días
    Cuando solicita cancelar su reserva
    Entonces se le pide confirmar con un reembolso del 100 por ciento
    Cuando acepta la cancelación
    Entonces la reserva queda cancelada con reembolso de 80000 pesos y no admite otra cancelación

  @TC26 @REQ_CANCELACION_03 @REQ_CANCELACION_04 @positivo @frontera @issue:26
  Esquema del escenario: Cancelación con uno o dos días y reembolso parcial
    Dado tiene una reserva de 2 noches para "Toby" con entrada en <dias> días
    Cuando solicita cancelar su reserva
    Entonces se le pide confirmar con un reembolso del 50 por ciento
    Cuando acepta la cancelación
    Entonces la reserva queda cancelada con reembolso de 40000 pesos y no admite otra cancelación

    Ejemplos:
      | dias |
      | 1    |
      | 2    |

  @TC27 @REQ_CANCELACION_03 @REQ_CANCELACION_04 @positivo @frontera @issue:27
  Escenario: Cancelación el mismo día sin reembolso
    Dado tiene una reserva de 2 noches para "Toby" con entrada en 0 días
    Cuando solicita cancelar su reserva
    Entonces se le pide confirmar con un reembolso del 0 por ciento
    Cuando acepta la cancelación
    Entonces la reserva queda cancelada con reembolso de 0 pesos y no admite otra cancelación

  @TC28 @REQ_CANCELACION_02 @positivo @issue:28
  Escenario: Conservar la reserva al rechazar la cancelación
    Dado tiene una reserva de 2 noches para "Toby" con entrada en 3 días
    Cuando solicita cancelar su reserva
    Entonces se le pide confirmar con un reembolso del 100 por ciento
    Cuando decide conservar la reserva
    Entonces su reserva aparece confirmada por 80000 pesos con código válido y los datos solicitados

  @TC29 @REQ_RESERVAS_09 @REQ_CANCELACION_04 @positivo @issue:29
  Escenario: Una cancelación libera las fechas para una nueva reserva
    Dado tiene una reserva de 2 noches para "Toby" con entrada en 3 días
    Cuando solicita cancelar su reserva
    Y acepta la cancelación
    Entonces la reserva queda cancelada con reembolso de 80000 pesos y no admite otra cancelación
    Cuando prepara una estadía para "Toby"
    Y solicita 2 noches con entrada en 3 días, baño "no" y paseo "no"
    Y confirma la estadía
    Y consulta sus reservas
    Entonces su reserva aparece confirmada por 80000 pesos con código válido y los datos solicitados
    Y hay 2 reservas en la lista

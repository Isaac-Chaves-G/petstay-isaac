# language: es
@HU02 @acceso
Característica: Acceso al hotel PetStay
  Como dueño quiero acceder con mis credenciales para gestionar el hospedaje de mis mascotas.

  @TC01 @REQ_ACCESO_01 @smoke @positivo @issue:1
  Escenario: Acceso con credenciales válidas
    Dado el dueño está en el acceso de PetStay
    Cuando inicia sesión con usuario "dueno" y contraseña "huellitas123"
    Entonces puede gestionar sus mascotas

  @TC02 @REQ_ACCESO_02 @negativo @issue:2
  Esquema del escenario: Rechazo de credenciales inválidas
    Dado el dueño está en el acceso de PetStay
    Cuando inicia sesión con usuario "<usuario>" y contraseña "<contraseña>"
    Entonces el acceso es rechazado con "Usuario o contraseña incorrectos"

    Ejemplos:
      | usuario | contraseña    |
      | dueno   | incorrecta    |
      | ajeno   | huellitas123  |

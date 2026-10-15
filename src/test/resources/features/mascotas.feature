# language: es
@HU02 @mascotas
Característica: Registro de mascotas
  Como dueño quiero registrar mascotas aptas para hospedaje y conocer su tamaño.

  Antecedentes:
    Dado el dueño ha iniciado sesión en PetStay

  @TC03 @REQ_MASCOTAS_01 @REQ_MASCOTAS_03 @REQ_MASCOTAS_05 @positivo @frontera @smoke @issue:3
  Esquema del escenario: Registro y clasificación por peso
    Cuando registra una mascota con estos datos:
      | nombre | especie   | peso   | edad | vacunas |
      | Luna   | <especie> | <peso> | 3    | sí      |
    Entonces la mascota "Luna" aparece como "<especie>" con peso "<visible>", edad "3" y tamaño "<tamaño>"
    Y hay 1 mascotas registradas

    Ejemplos:
      | especie | peso | visible | tamaño  |
      | Gato    | 0.1  | 0,1     | Pequeño |
      | Perro   | 10   | 10      | Pequeño |
      | Perro   | 10.1 | 10,1    | Mediano |
      | Perro   | 24.9 | 24,9    | Mediano |
      | Perro   | 25   | 25      | Grande  |
      | Perro   | 80   | 80      | Grande  |

  @TC04 @REQ_MASCOTAS_02 @negativo @issue:4
  Escenario: Rechazo de mascota sin vacunas al día
    Cuando registra una mascota con estos datos:
      | nombre | especie | peso | edad | vacunas |
      | Toby   | Perro   | 8    | 2    | no      |
    Entonces el registro es rechazado con "No podemos hospedar mascotas sin vacunas al día"
    Y hay 0 mascotas registradas

  @TC05 @REQ_MASCOTAS_04 @negativo @issue:5
  Escenario: Rechazo de nombre duplicado sin distinguir mayúsculas
    Dado tiene registrada a "Luna" de especie "Gato" y peso "5"
    Cuando registra una mascota con estos datos:
      | nombre | especie | peso | edad | vacunas |
      | LUNA   | Perro   | 8    | 2    | sí      |
    Entonces el campo de mascota "nombre" indica "Ya existe una mascota registrada con ese nombre"
    Y hay 1 mascotas registradas

  @TC06 @REQ_MASCOTAS_01 @negativo @frontera @issue:6
  Esquema del escenario: Validación de longitud y obligatoriedad del nombre
    Cuando registra una mascota con estos datos:
      | nombre   | especie | peso | edad | vacunas |
      | <nombre> | Perro   | 8    | 2    | sí      |
    Entonces el campo de mascota "nombre" indica "<mensaje>"
    Y hay 0 mascotas registradas

    Ejemplos:
      | nombre                          | mensaje                                      |
      |                                 | Este campo es obligatorio                    |
      | A                               | El nombre debe tener entre 2 y 30 caracteres  |
      | ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE | El nombre debe tener entre 2 y 30 caracteres  |

  @TC07 @REQ_MASCOTAS_01 @negativo @frontera @issue:7
  Esquema del escenario: Rechazo de peso fuera de rango
    Cuando registra una mascota con estos datos:
      | nombre | especie | peso   | edad | vacunas |
      | Toby   | Perro   | <peso> | 2    | sí      |
    Entonces el campo de mascota "peso" indica "El peso debe ser mayor que 0 y máximo 80 kg"
    Y hay 0 mascotas registradas

    Ejemplos:
      | peso |
      | 0    |
      | 80.1 |
      | -1   |

  @TC08 @REQ_MASCOTAS_01 @negativo @frontera @issue:8
  Esquema del escenario: Rechazo de edad fuera de rango
    Cuando registra una mascota con estos datos:
      | nombre | especie | peso | edad   | vacunas |
      | Toby   | Perro   | 8    | <edad> | sí      |
    Entonces el campo de mascota "edad" indica "La edad debe estar entre 0 y 25 años"
    Y hay 0 mascotas registradas

    Ejemplos:
      | edad |
      | -1   |
      | 26   |

  @TC09 @REQ_MASCOTAS_01 @positivo @frontera @issue:9
  Esquema del escenario: Aceptación de las edades límite
    Cuando registra una mascota con estos datos:
      | nombre | especie | peso | edad   | vacunas |
      | Toby   | Perro   | 8    | <edad> | sí      |
    Entonces la mascota "Toby" aparece como "Perro" con peso "8", edad "<edad>" y tamaño "Pequeño"
    Y hay 1 mascotas registradas

    Ejemplos:
      | edad |
      | 0    |
      | 25   |

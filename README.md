# Taller de automatización Front-End: PetStay

Automatización de la historia **HU-02: Hospedaje de mascotas** con Java, Serenity BDD, Cucumber y Screenplay. Incluye 29 casos identificados de TC01 a TC29; los esquemas de escenario ejecutan varios conjuntos de datos.

## Aplicación y requisitos

- Aplicación de práctica: https://petstay-aphn.onrender.com
- Credenciales públicas del ejercicio: `dueno` / `huellitas123`.
- JDK 17 o 21, Chrome y conexión a Internet. La validación local utiliza JDK 21.
- Gradle 8.14.3 se descarga mediante el wrapper incluido. Selenium Manager resuelve el driver.
- Python 3 y GitHub CLI son necesarios solo para preparar/publicar el catálogo de issues, no para las pruebas.

## Ejecutar en Windows (PowerShell)

Abrir una terminal **dentro de esta carpeta**, donde está `build.gradle`:

```powershell
# Si JAVA_HOME no está definido o apunta a una instalación incorrecta:
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'

# Todos los escenarios, incluidos los que detectan defectos:
.\gradlew.bat clean test aggregate

# Solo un caso:
.\gradlew.bat clean test aggregate '-Dcucumber.filter.tags=@TC24'

# Recorrido básico: acceso, registro, reserva y cancelación:
.\gradlew.bat clean test aggregate '-Dcucumber.filter.tags=@smoke'
```

En Linux/macOS se usa `bash ./gradlew` en lugar de `.\gradlew.bat`.

El reporte principal está en **`target/site/serenity/index.html`**. Incluye pasos, aserciones y capturas. El reporte de Gradle está en `build/reports/tests/test/index.html`. `clean` retira los reportes de ejecuciones anteriores para no mezclar evidencias. El reporte corresponde al último filtro ejecutado.

Una prueba negativa **pasa** cuando la aplicación rechaza correctamente un dato inválido. Una prueba que encuentra un defecto funcional **falla**: no se cambia la expectativa para ocultar el defecto. La suite completa incluye TC14 y TC15; ver `docs/DEFECTOS.md` y `docs/RESULTADOS.md`.

## Organización

```text
src/test/resources/features/          Escenarios de negocio en español
src/test/resources/serenity.conf      Navegador, capturas y reportes
src/test/java/com/icesi/petstay/
  runners/                           Suite JUnit Platform
  hooks/                             Escena y actor por escenario
  ui/                                Localizadores verificados en PetStay
  models/                            Datos de mascota
  tasks/                             Acciones de negocio
  questions/                         Lectura de importes y cantidades
  stepdefinitions/                   Conexión Gherkin-Java y expectativas
.github/workflows/ci.yml              Ejecución en GitHub Actions
docs/                                Historia, casos, trazabilidad y evidencias
scripts/                             Catálogo y publicación de test cases
```

Flujo: **Feature → Step Definition → Actor → Task → Target**. Las Questions consultan el resultado y `Ensure` comprueba que coincide con la historia de usuario. Se utilizan `Task.where`, `OnlineCast` y localizadores agrupados por página. No hay `Thread.sleep`, XPath absoluto ni datos de escenarios en variables estáticas.

Cada escenario recibe un navegador gestionado por Serenity. Los datos de PetStay viven en el almacenamiento local del perfil del navegador; las pruebas usan perfiles de automatización y crean sus precondiciones desde la interfaz. No utilizan la sesión personal del usuario ni necesitan reiniciar datos de otros usuarios. Las fechas válidas se calculan desde **la fecha que muestra PetStay**, evitando discrepancias entre la zona horaria de Windows y la del runner Linux.

## Versiones y adaptación del material

Se conserva Serenity **4.2.32**, que aparece en el `build.gradle` entregado por la profesora, y se alinean todos sus módulos. Cucumber **7.22.2**, JUnit **5.13.0** y Selenium transitivo **4.33.0** corresponden a las versiones declaradas por el POM oficial de Serenity 4.2.32. No se mezclan JUnit 4 y 5 ni se fuerzan varias versiones de Selenium.

La guía técnica actualizada propone el estilo de Screenplay aplicado aquí. El nombre del reporter se adapta a la versión elegida: en Serenity 4.2.32 es `io.cucumber.core.plugin.SerenityReporterParallel`, comprobado en el JAR. La ruta `net.serenitybdd.cucumber.core.plugin...` de la guía corresponde a otra generación y no existe en este JAR. El runner utiliza la suite JUnit Platform.

Se incluye `verifySerenityResults`, ejecutado después de generar el reporte. En esta combinación algunas fallas de tareas anidadas pueden quedar registradas por Serenity sin propagarse a JUnit Platform. La verificación lee los resultados reales de Serenity y hace fallar Gradle si encuentra errores o fallos; también rechaza una ejecución sin resultados. No omite defectos ni convierte fallos en éxitos.

El ZIP del ejemplo contenía scripts `gradlew` pero no los archivos completos del wrapper. Este proyecto incorpora el wrapper oficial y verifica la distribución de Gradle con SHA-256. Los materiales originales permanecen fuera del proyecto, sin modificaciones.

Referencias técnicas: [POM oficial de Serenity](https://repo.maven.apache.org/maven2/net/serenity-bdd/serenity-bdd/4.2.32/serenity-bdd-4.2.32.pom), [Screenplay](https://serenity-bdd.github.io/docs/screenplay/screenplay_fundamentals), [GitHub Actions y eventos de issues](https://docs.github.com/en/actions/reference/workflows-and-actions/events-that-trigger-workflows#issues).

## GitHub, TestCases y trazabilidad

Ver `docs/TRAZABILIDAD.md`. Cada caso tiene su etiqueta `@TCxx` y etiquetas `@REQ_...`; todos pertenecen a `@HU02`. Los números reales `@issue:N` se incorporan al publicar los casos. No se usan números de issue ficticios.

1. Publicar este proyecto en el repositorio del estudiante y mantener `main` como rama predeterminada.
2. Crear un GitHub Project para el taller, o usar uno existente, y obtener su número.
3. Con GitHub CLI autenticado y acceso al repositorio y Project, ejecutar:

   ```powershell
   python scripts/publish_testcases.py --repo PROPIETARIO/REPOSITORIO --project-number NUMERO
   ```

   El script vincula el Project al repo, crea/reutiliza los 29 issues, los añade al Project, registra sus URLs, añade tags `@issue:N` a los escenarios y configura enlaces del reporte. Usa archivos para los cuerpos de los issues. No crea repositorios ni realiza `push` automáticamente.

4. Revisar los cambios generados, hacer commit y push. Verificar en GitHub que cada caso aparece en el Project y que sus tags coinciden con el número del issue.
5. Revisar Actions → **PetStay Serenity BDD**. El workflow ejecuta la suite en push y pull request; desde **Run workflow** se puede seleccionar `TC01`..`TC29` o `todos`.
6. Para ejecutar desde un TestCase, aplicar `ejecutar-prueba` al issue `[TCxx] ...`. La ejecución aparecerá en Actions. Para repetir, retirar la etiqueta y volverla a añadir. El workflow debe estar en la rama predeterminada.
7. Descargar el artefacto `serenity-N` para revisar reporte y capturas, incluso cuando falle una prueba.

El archivo de pipeline citado por el enunciado (`ci.yml` en Teams/Intu) no estaba incluido en los materiales locales. El workflow de este proyecto es una implementación equivalente propia; debe compararse con ese archivo si la profesora exige utilizarlo literalmente. GitHub Actions, los issues y el Project solo se consideran completados después de verificar su ejecución/publicación remota.

## Sustentación

La guía para explicar y modificar el proyecto está en `docs/SUSTENTACION.md`. El catálogo reproducible se actualiza con `python scripts/catalog.py`.

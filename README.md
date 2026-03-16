# Sistema de Gestión de Empleados (Ejemplo)

Descripción
-----------
Proyecto de ejemplo que gestiona una lista simple de empleados. Muestra cómo
encapsular datos de empleados, mantener una colección, aplicar aumentos de
salario y realizar validaciones básicas.

Autor y versión
----------------
- Autor: Antonio Guillén Illán
- Versión: 1.0.0
- Fecha: 2026-03-16

Estructura del repositorio
--------------------------
- `Empleado.java` — clase que representa a un empleado (nombre, cargo, salario) con validaciones.
- `Empleados.java` — gestor simple que mantiene una colección (`lista`) de empleados y operaciones sobre ella.
- `Cargos.java` — enum con los cargos profesionales permitidos (DESARROLLADOR, DISENADORA, GERENTE).
- `Constantes.java` — constantes usadas en la aplicación (mensajes, formatos, convenio salarial, etc.).
- `SistemaGestionEmpleados.java` — clase con el `main` que muestra uso del sistema.
- `EmpleadoTest.java` — runner independiente (sin JUnit) que ejecuta los casos de prueba definidos en `TESTING.md`.
- `TESTING.md` — análisis de caja negra con casos válidos y no válidos para los atributos de `Empleado`.
- `pom.xml` — archivo Maven para ejecutar tests con JUnit 5 (opcional).
- `README-TESTS.md` — instrucciones para ejecutar tests con Maven.

Explicación del código
----------------------
- `Empleado` valida en su constructor y setters que:
  - `nombre` no sea nulo, no esté vacío y contenga al menos dos palabras.
  - `cargo` pertenezca al enum `Cargos.Cargo`.
  - `salario` no sea negativo ni inferior al `CONVENIO_SALARIAL` definido en `Constantes`.
- `Empleados` gestiona un array público `lista` de `Empleado` y ofrece operaciones:
  - `darAltaEmpleado(Empleado)` — añade un empleado, ampliando el array si es necesario.
  - `aumentarSalario(double porcentaje)` — aplica un aumento porcentual a todos los empleados dados de alta.
  - `mostrarListado()` — imprime la lista por consola.
- `SistemaGestionEmpleados.main` crea tres empleados de ejemplo, solicita por consola
  un porcentaje de aumento (admite coma decimal), aplica el aumento y muestra el resultado.

Ejecución
---------
Compilar y ejecutar la aplicación principal:

```powershell
cd 'd:\Examen Entornos'
javac *.java
java SistemaGestionEmpleados
```

Al ejecutar se pedirá el porcentaje de aumento; introduce `10` o `10,5` y verás la lista de empleados con los salarios actualizados.

Pruebas
-------
Hay dos formas de ejecutar las pruebas definidas:

- Runner independiente (sin JUnit):

```powershell
cd 'd:\Examen Entornos'
javac *.java
java EmpleadoTest
```

  Este runner ejecuta los casos válidos y no válidos del `TESTING.md` y muestra un resumen (Passed/Failed).

- Usando Maven y JUnit 5 (opcional):
  - Requiere Maven instalado. Ejecuta `mvn test` en la raíz del proyecto. `pom.xml` ya está incluido para facilitar la ejecución con JUnit 5.
 
  - Al no haber instalado nada esos archivos de Test contendrán errores de ejecución



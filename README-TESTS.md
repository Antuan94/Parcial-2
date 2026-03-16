Instrucciones para ejecutar tests JUnit 5

Requisitos:
- Maven 3.x
- JDK 11 o superior

Pasos:
1. Abrir PowerShell en la carpeta del proyecto (d:\Examen Entornos)
2. Ejecutar:

mvn test

Esto descargará las dependencias de JUnit y ejecutará los tests definidos en `test/EmpleadoTest.java`.

Nota: Si no deseas usar Maven puedes compilar y ejecutar tests manualmente añadiendo los jars de JUnit 5 al classpath.

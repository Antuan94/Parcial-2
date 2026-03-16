# TESTING — Análisis de caja negra para la clase Empleado

Fecha: 2026-03-16

Objetivo
-------
Realizar un análisis de caja negra sobre los tres atributos de la clase `Empleado`: `nombre`, `cargo` y `salario`. Defino criterios de aceptación y casos de prueba (válidos y no válidos) para verificar comportamientos esperados.

Supuestos
---------
- El proyecto usa textos en español.
- El atributo `cargo` debe corresponder a uno de los valores definidos en `Cargos.Cargo` (DESARROLLADOR, DISENADORA, GERENTE).
- Convenio salarial mínimo asumido para las pruebas: 15000.00 (moneda local por año). Este valor es una asunción razonable para el ejercicio y se puede ajustar si hay un valor oficial distinto.

Criterios generales de aceptación
--------------------------------
- `nombre`: no vacío, al menos dos palabras (nombre + apellido), sin solo espacios en blanco.
- `cargo`: valor perteneciente al enum `Cargos.Cargo`.
- `salario`: no vacío, numérico, >= 0 y >= CONVENIO (15000.00).

1) Análisis de parámetros
-------------------------

| Parámetro | Tipo esperado | Rango / formato | Criterio de aceptación |
|---|---:|---|---|
| nombre | String | Texto Unicode, mínimo 2 palabras separadas por espacio | No vacío; tras trim contiene al menos dos tokens separados por espacio (p. ej. "Juan Pérez") |
| cargo | Cargos.Cargo (enum) | Uno de: DESARROLLADOR, DISENADORA, GERENTE | Coincide con una constante del enum (comparación de valor) |
| salario | double (numérico) | Decimal positivo o cero, razonablemente acotado | No null; >= 0; >= CONVENIO (15000.00) |

2) Casos válidos (casos positivos)
---------------------------------

| ID | nombre | cargo | salario | Descripción | Resultado esperado |
|---:|---|---|---:|---|---|
| V1 | "Juan Pérez" | DESARROLLADOR | 50000.00 | Caso típico correcto | Aceptado (creación OK) |
| V2 | "María López" | DISENADORA | 15000.00 | Límite convenio exacto | Aceptado (creación OK) |
| V3 | "Ana María García" | GERENTE | 60000.50 | Nombre con 3 tokens y salario decimal | Aceptado (creación OK) |
| V4 | " José  Martínez " | DESARROLLADOR | 20000 | Nombre con espacios externos y doble espacio interno | Aceptado tras trim y normalización (2 tokens) |
| V5 | "Óscar Núñez" | GERENTE | 15000.01 | Salario justo por encima del convenio | Aceptado |
| V6 | "J. de la Fuente" | DISENADORA | 30000 | Nombres compuestos con puntos/partículas | Aceptado si tras split hay >=2 tokens |

3) Casos no válidos (casos negativos)
-----------------------------------

| ID | nombre | cargo | salario | Descripción | Error esperado |
|---:|---|---|---:|---|---|
| N1 | "" | DESARROLLADOR | 50000.00 | Nombre vacío | Rechazo: nombre inválido (vacío) |
| N2 | "Juan" | DESARROLLADOR | 50000.00 | Solo una palabra en nombre | Rechazo: nombre inválido (menos de 2 palabras) |
| N3 | "   " | DISENADORA | 45000.00 | Solo espacios en nombre | Rechazo: nombre inválido (trim->vacío) |
| N4 | "María Pérez" | null | 40000.00 | Cargo nulo | Rechazo: cargo inválido (no corresponde al enum) |
| N5 | "María Pérez" | "Interno" (no existe) | 40000.00 | Cargo fuera del enum | Rechazo: cargo inválido |
| N6 | "María Pérez" | DISENADORA | -100.00 | Salario negativo | Rechazo: salario inválido (negativo) |
| N7 | "María Pérez" | DISENADORA | 0.00 | Salario cero por debajo de convenio | Rechazo: salario por debajo del convenio (0 < 15000) |
| N8 | "María Pérez" | DISENADORA | 14999.99 | Salario ligeramente por debajo del convenio | Rechazo: salario por debajo del convenio |
| N9 | null | GERENTE | 20000.00 | Nombre nulo | Rechazo: nombre inválido (null) |
| N10 | "Juan Pérez" | GERENTE | "abc" | Salario no numérico | Rechazo: formato salario inválido |

Observaciones y recomendaciones
--------------------------------
- Implementar validaciones en la construcción o en un validador externo antes de crear/aceptar instancias de `Empleado`.
- Para `nombre` se sugiere: aplicar `trim()`, sustituir múltiples espacios por uno y contar tokens separados por espacios en blanco (aceptar hyphens y apóstrofes como parte del token si procede).
- Para `cargo` ofrecer método de parsing seguro (`Cargos.Cargo.fromString(...)`) que devuelva `Optional<Cargo>` o lance excepción controlada.
- Para `salario` mantener `CONVENIO` centralizado en `Constantes` o en una clase de configuración para que las pruebas usen el valor real del convenio.
- Añadir pruebas automáticas (unitarias) que cubran todas las filas de las tablas anteriores; incluir tests de frontera (igual a convenio, justo por encima/por debajo).

Fin del documento.

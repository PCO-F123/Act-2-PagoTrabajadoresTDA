# Pago a Trabajadores - TDA Trabajador

**Autor:** Francisco Hernández Hernández

## Descripción

Aplicación de escritorio para Windows, desarrollada en Java (Swing), que registra el nombre, número de empleado, salario por hora y las horas trabajadas de un empleado de lunes a viernes. Las horas se guardan en un arreglo de tamaño fijo dentro de un Tipo de Dato Abstracto (TDA) llamado `Trabajador`.

Al presionar los botones de la interfaz, la aplicación obtiene:

- El total de horas trabajadas en la semana, calculado mediante un **método recursivo**.
- El sueldo bruto, aplicando pago doble a las horas que excedan la jornada normal de 48 horas semanales.
- El descuento de IMSS (5% fijo sobre el sueldo bruto).
- El sueldo neto final.
- Si el trabajador es de confianza, un bono fijo en lugar de pago de horas extra.

## Conceptos aplicados

- **TDA y encapsulamiento:** la clase `Trabajador` mantiene sus atributos privados (nombre, número de empleado, salario por hora y arreglo de horas) y solo se accede a ellos mediante métodos.
- **Herencia:** la clase `TrabajadorConfianza` hereda de `Trabajador` y sobrescribe el cálculo del sueldo neto: no cobra horas extra, pero suma un bono fijo.
- **Constructores con validaciones:** 4 constructores (por default, completo, parcial y copia). El salario por hora, el bono y las horas no pueden ser negativos; el arreglo de horas debe tener exactamente 5 posiciones (lunes a viernes).
- **Recursividad:** el método `sumarHoras` tiene como caso base cuando el índice llega al tamaño del arreglo (retorna 0), y avanza sumando la posición actual más el resultado de la llamada con el siguiente índice (`i + 1`).

## Estructura del proyecto

- `Trabajador.java` — TDA base: atributos, constructores, y toda la lógica de cálculo del sueldo.
- `TrabajadorConfianza.java` — hereda de `Trabajador`, agrega el atributo `bonoConfianza` y sobrescribe el cálculo del sueldo neto.
- `PagoTrabajadoresApp.java` — interfaz gráfica (Swing) que captura los datos y muestra los resultados.

La clase `Trabajador` contiene 13 métodos:

- **Acceso (5):** `getnombre`, `getnumeroEmpleado`, `getsalarioPorHora`, `gethorasPorDia`, `setsalarioPorHora`.
- **Dominio (7):** `sumarHoras` (recursivo), `calcularTotalHoras`, `calcularHorasNormales`, `calcularHorasExtras`, `calcularSueldoBruto`, `calcularDescuento`, `calcularSueldoNeto`.
- **Representación (1):** `toString`.

## Cálculo del pago

| Horas trabajadas en la semana | Cómo se paga |
|---|---|
| Hasta 48 horas | Salario por hora × horas trabajadas |
| Más de 48 horas | Las primeras 48 horas se pagan normal; las horas que exceden 48 se pagan al doble |

Si el trabajador es de **confianza**, no aplica el pago doble por horas extra: en su lugar recibe un **bono fijo** que se suma directo a su sueldo neto.

## Requisitos para ejecutar la app

- NetBeans IDE (versión reciente).
- JDK 8 o superior.
- No requiere librerías externas: usa únicamente `javax.swing` y `java.awt`, incluidas en el JDK.

## Cómo ejecutar la aplicación paso a paso

1. Clonar el repositorio, o descargar el proyecto como ZIP desde el botón verde **Code** de este repositorio y descomprimirlo.
2. Abrir NetBeans.
3. Ir a **File > Open Project**, buscar la carpeta del proyecto y seleccionarla.
4. Esperar a que NetBeans termine de indexar el proyecto (barra de progreso abajo).
5. En el panel **Projects**, dar clic derecho sobre `PagoTrabajadoresApp.java` y seleccionar **Run File** (o usar **Shift + F6**).
6. Se abrirá la ventana de la aplicación.

## Cómo usar la aplicación

1. Escribir el nombre del trabajador y su número de empleado.
2. Escribir el salario por hora.
3. Escribir las horas trabajadas de lunes a viernes.
4. Si el trabajador es de confianza, marcar la casilla **"Es trabajador de confianza"** e ingresar el monto del bono.
5. Presionar el botón **Horas** para ver el total de horas trabajadas.
6. Presionar el botón **Sueldo Bruto** para ver el sueldo antes de descuentos y el monto del descuento de IMSS.
7. Presionar el botón **Sueldo Neto** para ver el sueldo final a pagar.

## Validaciones

- El salario por hora, el bono de confianza y las horas capturadas no pueden guardarse como valores negativos: el TDA los ajusta a 0 si llegan a ser inválidos.
- Si algún campo numérico se deja vacío o con texto no numérico, la aplicación lo toma como 0 en ese cálculo, sin detener el programa.

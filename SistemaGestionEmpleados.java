import java.util.Scanner;

/**
 * Aplicación principal para la gestión de empleados (ejemplo didáctico).
 *
 * Esta clase contiene únicamente el método {@code main} que muestra cómo usar
 * la clase {@link Empleados} y las constantes en {@link Constantes} para
 * interactuar con el usuario.
 */
public class SistemaGestionEmpleados {

    /**
     * Punto de entrada de la aplicación.
     *
     * Usa {@link Empleados} para dar de alta 3 empleados, solicita al usuario
     * un porcentaje de aumento, aplica el aumento y muestra la lista resultante.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        // Crear contenedor de empleados y dar de alta los 3 empleados del ejemplo
        Empleados empleados = new Empleados(3);
        empleados.darAltaEmpleado(new Empleado("Juan", Cargos.Cargo.DESARROLLADOR, 50000));
        empleados.darAltaEmpleado(new Empleado("María", Cargos.Cargo.DISENADORA, 45000));
        empleados.darAltaEmpleado(new Empleado("Pedro", Cargos.Cargo.GERENTE, 60000));

        Scanner scanner = new Scanner(System.in);
        double porcentaje = 0;
        System.out.print(Constantes.MSG_INTRO_PORCENTAJE);
        String linea = scanner.nextLine().trim();
        if (!linea.isEmpty()) {
            // permitir coma decimal y usar punto para parsear
            linea = linea.replace(',', '.');
            try {
                porcentaje = Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println(Constantes.MSG_ENTRADA_INVALIDA);
                porcentaje = 0;
            }
        }

        empleados.aumentarSalario(porcentaje);

        System.out.println(Constantes.MSG_LISTA_EMPLEADOS);
        empleados.mostrarListado();

        scanner.close();
    }

}
import java.util.Objects;

/**
 * Contenedor y gestor simple de empleados.
 *
 * Esta clase mantiene internamente un array de {@link Empleado} accesible
 * públicamente como {@code lista} (por conveniencia de uso en el ejemplo).
 * Proporciona operaciones para dar de alta empleados, aumentar salario y
 * mostrar el listado.
 */
public class Empleados {

    /**
     * Array público que contiene las referencias a empleados dados de alta.
     * Se expone como {@code empleados.lista} en el ejemplo. Para un diseño
     * más robusto, considerar cambiarlo a {@code private} y exponer métodos de acceso.
     */
    public Empleado[] lista;
    private int count;

    /**
     * Crea un contenedor con capacidad inicial especificada.
     *
     * @param capacidadInicial capacidad inicial del array (si <=0 se usa {@link Constantes#DEFAULT_CAPACITY})
     */
    public Empleados(int capacidadInicial) {
        if (capacidadInicial <= 0) {
            capacidadInicial = Constantes.DEFAULT_CAPACITY;
        }
        this.lista = new Empleado[capacidadInicial];
        this.count = 0;
    }

    /**
     * Crea un contenedor con capacidad por defecto.
     */
    public Empleados() {
        this(Constantes.DEFAULT_CAPACITY);
    }

    /**
     * Añade un empleado al contenedor.
     *
     * @param empleado empleado a añadir (no puede ser {@code null})
     * @throws NullPointerException si {@code empleado} es {@code null}
     */
    public void darAltaEmpleado(Empleado empleado) {
        Objects.requireNonNull(empleado, Constantes.ERR_EMPLEADO_NULL);
        if (count >= lista.length) {
            // ampliar capacidad duplicando el tamaño
            Empleado[] nuevo = new Empleado[lista.length * 2];
            System.arraycopy(lista, 0, nuevo, 0, lista.length);
            lista = nuevo;
        }
        lista[count++] = empleado;
    }

    /**
     * Aumenta el salario de todos los empleados dados de alta en un porcentaje.
     *
     * @param porcentaje porcentaje de aumento (por ejemplo 10 para 10%)
     */
    public void aumentarSalario(double porcentaje) {
        for (int i = 0; i < count; i++) {
            Empleado e = lista[i];
            if (e != null) {
                double nuevoSalario = e.getSalario() * (1 + porcentaje / 100);
                e.setSalario(nuevoSalario);
            }
        }
    }

    /**
     * Imprime por consola el listado de empleados dados de alta.
     */
    public void mostrarListado() {
        for (int i = 0; i < count; i++) {
            Empleado e = lista[i];
            if (e != null) {
                System.out.println(e);
            }
        }
    }

    /**
     * Devuelve el número de empleados dados de alta.
     *
     * @return número de empleados
     */
    public int size() {
        return count;
    }
}


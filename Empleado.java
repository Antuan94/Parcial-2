import java.util.Locale;


/**
 * Representa un empleado de la empresa.
 *
 * Contiene los datos básicos: nombre, cargo (enum {@link Cargos.Cargo}) y salario.
 * Se incluyen validaciones mínimas en constructor y setters para asegurar
 * que los atributos cumplen los criterios de negocio (ver TESTING.md).
 */
class Empleado {
    private String nombre;
    private Cargos.Cargo cargo;
    private double salario;

    public Empleado(String nombre, Cargos.Cargo cargo, double salario) {
    validateNombre(nombre);
    validateCargo(cargo);
    validateSalario(salario);
    this.nombre = normalizeNombre(nombre);
    this.cargo = cargo;
    this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
    validateNombre(nombre);
    this.nombre = normalizeNombre(nombre);
    }

    public Cargos.Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargos.Cargo cargo) {
    validateCargo(cargo);
    this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        validateSalario(salario);
        this.salario = salario;
    }

    /**
     * Validaciones internas usadas por constructor y setters.
     * Estas validaciones lanzan {@link IllegalArgumentException} cuando
     * los datos no cumplen los criterios definidos.
     */
    private void validateNombre(String nombre) {
        if (nombre == null) throw new IllegalArgumentException("nombre no puede ser null");
        String t = nombre.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("nombre no puede estar vacío");
        // Normalizar múltiples espacios y contar tokens
        String singleSpaced = t.replaceAll("\\s+", " ");
        String[] tokens = singleSpaced.split(" ");
        if (tokens.length < 2) throw new IllegalArgumentException("nombre debe contener al menos dos palabras");
    }

    private String normalizeNombre(String nombre) {
        return nombre.trim().replaceAll("\\s+", " ");
    }

    private void validateCargo(Cargos.Cargo cargo) {
        if (cargo == null) throw new IllegalArgumentException("cargo inválido (null)");
    }

    private void validateSalario(double salario) {
        if (Double.isNaN(salario)) throw new IllegalArgumentException("salario inválido (NaN)");
        if (salario < 0) throw new IllegalArgumentException("salario no puede ser negativo");
        if (salario < Constantes.CONVENIO_SALARIAL) throw new IllegalArgumentException("salario por debajo del convenio");
    }

    @Override
    public String toString() {
    return "Empleado{" +
        "nombre='" + nombre + '\'' +
        ", cargo='" + (cargo != null ? cargo.getDisplayName() : "") + '\'' +
        ", salario=" + String.format(Locale.US, Constantes.FORMATO_SALARIO, salario) +
        '}';
    }
}
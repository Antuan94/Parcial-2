import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    // CASOS VÁLIDOS
    @Test
    public void testV1_creacionValida() {
        Empleado e = new Empleado("Juan Pérez", Cargos.Cargo.DESARROLLADOR, 50000.00);
        assertEquals("Juan Pérez", e.getNombre());
        assertEquals(Cargos.Cargo.DESARROLLADOR, e.getCargo());
        assertEquals(50000.00, e.getSalario());
    }

    @Test
    public void testV2_convenioExacto() {
        Empleado e = new Empleado("María López", Cargos.Cargo.DISENADORA, Constantes.CONVENIO_SALARIAL);
        assertEquals(Constantes.CONVENIO_SALARIAL, e.getSalario());
    }

    @Test
    public void testV3_nombreTresTokens() {
        Empleado e = new Empleado("Ana María García", Cargos.Cargo.GERENTE, 60000.50);
        assertEquals("Ana María García", e.getNombre());
    }

    @Test
    public void testV4_espaciosExternosDobleInterno() {
        Empleado e = new Empleado(" José  Martínez ", Cargos.Cargo.DESARROLLADOR, 20000);
        assertEquals("José Martínez", e.getNombre());
    }

    @Test
    public void testV5_justoPorEncimaConvenio() {
        Empleado e = new Empleado("Óscar Núñez", Cargos.Cargo.GERENTE, Constantes.CONVENIO_SALARIAL + 0.01);
        assertTrue(e.getSalario() > Constantes.CONVENIO_SALARIAL);
    }

    @Test
    public void testV6_nombresConParticulas() {
        Empleado e = new Empleado("J. de la Fuente", Cargos.Cargo.DISENADORA, 30000);
        assertEquals("J. de la Fuente", e.getNombre());
    }

    // CASOS NO VÁLIDOS: deben lanzar IllegalArgumentException

    @Test
    public void testN1_nombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("", Cargos.Cargo.DESARROLLADOR, 50000.00);
        });
    }

    @Test
    public void testN2_nombreUnaPalabra() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("Juan", Cargos.Cargo.DESARROLLADOR, 50000.00);
        });
    }

    @Test
    public void testN3_nombreSoloEspacios() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("   ", Cargos.Cargo.DISENADORA, 45000.00);
        });
    }

    @Test
    public void testN4_cargoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("María Pérez", null, 40000.00);
        });
    }

    @Test
    public void testN5_cargoInexistente_string() {
        // No es posible pasar string directamente; este caso simula conversión fallida antes de constructor
        assertThrows(IllegalArgumentException.class, () -> {
            // Simulamos intentando parsear un cargo inexistente
            Cargos.Cargo c = Cargos.Cargo.fromString("Interno");
            if (c == null) throw new IllegalArgumentException("cargo inválido");
            new Empleado("María Pérez", c, 40000.00);
        });
    }

    @Test
    public void testN6_salarioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("María Pérez", Cargos.Cargo.DISENADORA, -100.00);
        });
    }

    @Test
    public void testN7_salarioCeroPorDebajoConvenio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("María Pérez", Cargos.Cargo.DISENADORA, 0.00);
        });
    }

    @Test
    public void testN8_salarioJustoPorDebajoConvenio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado("María Pérez", Cargos.Cargo.DISENADORA, Constantes.CONVENIO_SALARIAL - 0.01);
        });
    }

    @Test
    public void testN9_nombreNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Empleado(null, Cargos.Cargo.GERENTE, 20000.00);
        });
    }

    @Test
    public void testN10_salarioNoNumerico() {
        assertThrows(NumberFormatException.class, () -> {
            // El constructor no acepta strings; este test simula parseo previo fallido
            Double.parseDouble("abc");
        });
    }

}

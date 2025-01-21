import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngresosTest {

    @Test
    void testRegistrarIngresoNomina() {
        Ingresos ingresos = new Ingresos();
        assertTrue(ingresos.registrarIngreso("nomina", 500.0));
        assertEquals(425.0, ingresos.getTotalIngresos());
    }

    @Test
    void testRegistrarIngresoVentas() {
        Ingresos ingresos = new Ingresos();
        assertTrue(ingresos.registrarIngreso("ventas", 300.0));
        assertEquals(300.0, ingresos.getTotalIngresos());
    }

    @Test
    void testRegistrarIngresoNegativo() {
        Ingresos ingresos = new Ingresos();
        assertFalse(ingresos.registrarIngreso("ventas", -100.0));
        assertEquals(0.0, ingresos.getTotalIngresos());
    }

    @Test
    void testSetTotalIngresos() {
        Ingresos ingresos = new Ingresos();
        ingresos.setTotalIngresos(1000.0);
        assertEquals(1000.0, ingresos.getTotalIngresos());
    }

    @Test
    void testMostrarIngresos() {
        Ingresos ingresos = new Ingresos();
        ingresos.registrarIngreso("nomina", 200.0);
        ingresos.mostrarIngresos();
    }
}

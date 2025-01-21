import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GastosTest {

    @Test
    void testRegistrarGastoValido() {
        Gastos gastos = new Gastos();
        boolean resultado = gastos.registrarGasto("Compra de libros", 50.0, 100.0);
        assertTrue(resultado);
        assertEquals(50.0, gastos.getTotalGastos());
    }

    @Test
    void testRegistrarGastoExcesoSaldo() {
        Gastos gastos = new Gastos();
        boolean resultado = gastos.registrarGasto("Compra de muebles", 150.0, 100.0);
        assertFalse(resultado);
        assertEquals(0.0, gastos.getTotalGastos());
    }

    @Test
    void testRegistrarGastoNegativo() {
        Gastos gastos = new Gastos();

        boolean resultado = gastos.registrarGasto("Compra de café", -20.0, 100.0);
        assertFalse(resultado);
        assertEquals(0.0, gastos.getTotalGastos());
    }

    @Test
    void testSetTotalGastos() {
        Gastos gastos = new Gastos();

        gastos.setTotalGastos(300.0);
        assertEquals(300.0, gastos.getTotalGastos());  // Verificamos que el setter funcione correctamente
    }

    @Test
    void testMostrarGastos() {
        Gastos gastos = new Gastos();

        gastos.registrarGasto("Cena", 30.0, 100.0);
        gastos.mostrarGastos();
    }
}

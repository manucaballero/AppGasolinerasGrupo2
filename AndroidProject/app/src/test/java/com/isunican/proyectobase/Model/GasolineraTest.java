package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Model.Vehiculo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GasolineraTest {

    Gasolinera g1;
    Gasolinera g2;
    private Vehiculo mockVehiculo;
    private Descuento mockDescuento1;
    private Descuento mockDescuento2;
    private Descuento mockDescuento3;
    /**
     * Inicialización de variables
     */
    @Before
    public void setUp(){

        g1 = new Gasolinera(1000,"SANTANDER","SANTANDER", "Av Valdecilla", 1.299,1.359,"AVIA","43.45741814","-3.82677519");
        g2 = new Gasolinera(1100,"SANTANDER","SANTANDER", "Av Valdecilla", 1.399,0.939,"AVIA","43.45741814","-3.82677519");

        mockVehiculo = mock(Vehiculo.class);

        //Comportamiento del mock
        when(mockVehiculo.getDeposito()).thenReturn(50.0);
        when(mockVehiculo.getConsumoMedio()).thenReturn(20.0);

        mockDescuento1 = mock(Descuento.class);
        mockDescuento2 = mock(Descuento.class);
        mockDescuento3 = mock(Descuento.class);
        //Comportamiento de los mock de la clase Descuento
        when(mockDescuento1.getPorcentaje()).thenReturn(10);
        when(mockDescuento2.getPorcentaje()).thenReturn(15);
        when(mockDescuento3.getPorcentaje()).thenReturn(25);

    }

    /**
     * Método que comprueba el correcto funcionamiento del método calculaPrecioFinal
     * de la clase Gasolinera, el cual deberá calcular el precio del gasóleo A y la gasolina 95
     * en función de si tiene o no descuento la gasolinera.
     *
     * @author Bárbara Martínez Carcedo
     */
    @Test
    public void calcularPrecioFinalTest() {
        g1.setDescuento(new Descuento("cod", "descripcion", 10));
        g1.calculaPrecioFinal(mockVehiculo);


        //Gasolinera con descuento
        assertEquals(g1.getGasoleoAConDescuento(), Gasolinera.round(g1.getMultiplicadorCostePorLitro() * g1.getGasoleoA() * 0.9, 3), 0.0);
        assertEquals(g1.getGasolina95ConDescuento(), Gasolinera.round(g1.getMultiplicadorCostePorLitro() * g1.getGasolina95() * 0.9, 3), 0.0);

        g2.calculaPrecioFinal(mockVehiculo);

        //Gasolinera sin descuento
        assertEquals(g2.getGasolina95ConDescuento(), Gasolinera.round(g2.getMultiplicadorCostePorLitro() * g2.getGasolina95(), 3), 0.0);
        assertEquals(g2.getGasoleoAConDescuento(), Gasolinera.round(g2.getMultiplicadorCostePorLitro() * g2.getGasoleoA(), 3), 0.0);

    }

    /*
        Método que comprueba que al añadir un descuento a una gasolinera se añade correctamente, teniendo en cuenta que
        o bien la gasolinera no tiene descuentos, o bien el descuento nuevo a introducir es mayor que el que ya existía
        previamente.

        @author Miguel Casamichana Bolado
     */
    @Test
    public void setDescuentoTest(){
        g1.setDescuento(mockDescuento1);
        assertEquals(10, g1.getDescuento().getPorcentaje());
        //Se comprueba ahora que al añadir un descuento superior se cambia adecuadamente
        g1.setDescuento(mockDescuento2);
        assertEquals(15, g1.getDescuento().getPorcentaje());
        //Se comprueba ahora que al añadir un descuento inferior no se cambia el descuento actual
        g1.setDescuento(mockDescuento1);
        assertNotEquals(10, g1.getDescuento().getPorcentaje());
        assertEquals(15, g1.getDescuento().getPorcentaje());

    }
    /**
     *  Método que comprueba que al eliminar el descuento de una gasolinera, esta se queda sin descuentos activos
     *  @author Manuel Caballero Rabago
     */
    @Test
    public void removeDescuentoTest(){
        g1.setDescuento(mockDescuento3);
        assertEquals(25,g1.getDescuento().getPorcentaje());
        g1.removeDescuento();
        assertNull(g1.getDescuento());
    }

}

package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Model.Vehiculo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GasolineraTest {

    Gasolinera g1;
    Gasolinera g2;
    private Vehiculo mockVehiculo;

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
        g1.setDescuento(new Descuento("cod", "descripcion", 10.0));
        g1.calculaPrecioFinal(mockVehiculo);

        //Gasolinera con descuento
        assertEquals(g1.getGasoleoAConDescuento(), Gasolinera.round(g1.getMultiplicadorCostePorLitro() * g1.getGasoleoA() * 0.9, 3), 0.0);
        assertEquals(g1.getGasolina95ConDescuento(), Gasolinera.round(g1.getMultiplicadorCostePorLitro() * g1.getGasolina95() * 0.9, 3), 0.0);

        g2.calculaPrecioFinal(mockVehiculo);

        //Gasolinera sin descuento
        assertEquals(g2.getGasolina95ConDescuento(), Gasolinera.round(g2.getMultiplicadorCostePorLitro() * g2.getGasolina95(), 3), 0.0);
        assertEquals(g2.getGasoleoAConDescuento(), Gasolinera.round(g2.getMultiplicadorCostePorLitro() * g2.getGasoleoA(), 3), 0.0);

    }



}

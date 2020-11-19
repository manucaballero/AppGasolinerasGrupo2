package com.isunican.proyectobase.Model;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class GasolineraTest {


    Gasolinera g1;
    Gasolinera g2;
    //Definición del objeto mock
    private Vehiculo mockVehiculo;

    /**
     * Inicialización de variables
     */
    @Before
    public void setUp(){

        g1 = new Gasolinera(1000,"SANTANDER","SANTANDER", "Av Valdecilla", 1.299,1.359,"AVIA","43.45741814","-3.82677519");
        g2 = new Gasolinera(1100,"SANTANDER","SANTANDER", "Av Valdecilla", 1.399,0.939,"AVIA","43.45741814","-3.82677519");

        mockVehiculo = new Vehiculo("Mercedes CLK");
        mockVehiculo.setConsumoMedio(20.0);
        mockVehiculo.setDeposito(50.0);


    }

    /**
     * Método que comprueba el correcto funcionamiento del método calculaPrecioFinal
     * de la clase Gasolinera, el cual deberá calcular el precio del gasóleo A y la gasolina 95
     * en función de si tiene o no descuento la gasolinera.
     */
    @Test
    public void calcularPrecioFinalTest() {
        g1.setTieneDescuento(true);
        g1.calculaPrecioFinal(mockVehiculo);

        //Gasolinera con descuento
        assertTrue(g1.getGasoleoAConDescuento() == Gasolinera.round(g1.getMultiplicadorCostePorLitro() * g1.getGasoleoA() * 0.9, 3));
        assertTrue(g1.getGasolina95ConDescuento() == Gasolinera.round(g1.getMultiplicadorCostePorLitro() * g1.getGasolina95() * 0.9, 3));

        g2.calculaPrecioFinal(mockVehiculo);

        //Gasolinera sin descuento
        assertTrue(g2.getGasolina95ConDescuento() == Gasolinera.round(g2.getMultiplicadorCostePorLitro()*g2.getGasolina95() , 3));
        assertTrue(g2.getGasoleoAConDescuento() == Gasolinera.round(g2.getMultiplicadorCostePorLitro()*g2.getGasoleoA(), 3));

    }

}

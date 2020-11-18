package com.isunican.proyectobase.Presenter.Model;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GasolineraTest {


    Gasolinera g1;
    Gasolinera g2;
    Vehiculo vehiculo;

    /**
     * Inicialización de variables
     */
    @Before
    public void setUp(){
        PresenterGasolineras sut = new PresenterGasolineras();
        sut.cargaDatosDummy();
        g1 = sut.getGasolineras().get(0);
        g2 = sut.getGasolineras().get(1);
        vehiculo = new Vehiculo("Mercedes CLK");
        vehiculo.setConsumoMedio(20);
        vehiculo.setDeposito(50);

    }

    /**
     * Método que comprueba el correcto funcionamiento del método calculaPrecioFinal
     * de la clase Gasolinera, el cual deberá calcular el precio del gasóleo A y la gasolina 95
     * en función de si tiene o no descuento la gasolinera.
     */

    @Test
    public void calcularPrecioFinalTest() {
        g1.setTieneDescuento(true);
        g1.calculaPrecioFinal(vehiculo);

        //Gasolinera con descuento
        assertTrue(g1.getGasoleoAConDescuento() == Gasolinera.round((vehiculo.getDeposito() * g1.getGasoleoA() + g1.getDistanciaEnKm() * vehiculo.getConsumoMedio() * g1.getGasoleoA()) / vehiculo.getDeposito() * 0.9, 3));
        assertTrue(g1.getGasolina95ConDescuento() == Gasolinera.round((vehiculo.getDeposito() * g1.getGasolina95() + g1.getDistanciaEnKm() * vehiculo.getConsumoMedio() * g1.getGasolina95()) / vehiculo.getDeposito() * 0.9, 3));

        g2.calculaPrecioFinal(vehiculo);

        //Gasolinera sin descuento
        assertTrue(g2.getGasoleoAConDescuento() == Gasolinera.round((vehiculo.getDeposito() * g2.getGasoleoA() + g2.getDistanciaEnKm() * vehiculo.getConsumoMedio() * g2.getGasoleoA()) / vehiculo.getDeposito(), 3));
        assertTrue(g2.getGasolina95ConDescuento() == Gasolinera.round((vehiculo.getDeposito() * g2.getGasolina95() + g2.getDistanciaEnKm() * vehiculo.getConsumoMedio() * g2.getGasolina95()) / vehiculo.getDeposito(), 3));

    }
}

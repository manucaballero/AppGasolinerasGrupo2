package com.isunican.proyectobase;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GasolineraTest {

    Gasolinera g1;
    Gasolinera g2;
    double precio;

    @Before
    public void setUp(){
        PresenterGasolineras sut = new PresenterGasolineras();
        sut.cargaDatosDummy();
        g1 = sut.getGasolineras().get(0);
        g2 = sut.getGasolineras().get(1);
        precio = g1.getGasoleoA();

    }

    @Test
    public void calcularPrecioFinalTest(){
        g1.setTieneDescuento(true);
        /*
        g1.calculaPrecioFinal();

        //Gasolinera con descuento
        assertTrue(g1.getGasoleoAConDescuento() == Gasolinera.round((50*g1.getGasoleoA()+g1.getDistanciaEnKm()*6/100*g1.getGasoleoA())/50*0.9,3));

        g2.setTieneDescuento(false);
        g2.calculaPrecioFinal();

        //Gasolinera sin descuento
        assertTrue(g2.getGasoleoA() == Gasolinera.round((50*g2.getGasoleoA()+g2.getDistanciaEnKm()*6/100*g2.getGasoleoA())/50,3));

         */
    }


}

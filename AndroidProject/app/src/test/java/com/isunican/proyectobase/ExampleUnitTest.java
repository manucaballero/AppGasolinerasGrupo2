package com.isunican.proyectobase;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    PresenterGasolineras pg;
    Gasolinera g1;
    double precio;

    @Before
    public void setUp(){
        pg = new PresenterGasolineras();
        pg.cargaDatosDummy();

        g1 = pg.getGasolineras().get(0);
        precio = g1.getGasoleoA();

    }

    
    @Test
    public void ordenaListaTest(){
        pg.ordenaLista();
        List<Gasolinera> lista = lista = pg.getGasolineras();

        assertTrue(lista.get(0).getGasoleoAConDescuento() <= lista.get(1).getGasoleoAConDescuento());
        assertTrue(lista.get(1).getGasoleoAConDescuento() <= lista.get(2).getGasoleoAConDescuento());
        assertTrue(lista.get(2).getGasoleoAConDescuento() <= lista.get(3).getGasoleoAConDescuento());
        assertTrue(lista.get(3).getGasoleoAConDescuento() <= lista.get(4).getGasoleoAConDescuento());
    }

    @Test
    public void calcularPrecioFinal(){
        g1.calculaPrecioFinal();
        assertTrue(g1.getGasoleoA()==Gasolinera.round((g1.getDEPOSITO()*precio+g1.getDistanciaEnKm()*6/100*precio)/g1.getDEPOSITO(),3));
    }

}
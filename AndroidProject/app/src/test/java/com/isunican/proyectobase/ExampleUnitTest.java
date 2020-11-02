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

        assertTrue(lista.get(0).getGasoleoA() <= lista.get(1).getGasoleoA());
        assertTrue(lista.get(1).getGasoleoA() <= lista.get(2).getGasoleoA());
        assertTrue(lista.get(2).getGasoleoA() <= lista.get(3).getGasoleoA());
        assertTrue(lista.get(3).getGasoleoA() <= lista.get(4).getGasoleoA());
    }

    @Test
    public void calcularPrecioFinal(){
        g1.calculaPrecioFinal();
        assertTrue(g1.getGasoleoA() == precio*0.9+g1.getDistanciaEnKm()*6/100);
    }

}
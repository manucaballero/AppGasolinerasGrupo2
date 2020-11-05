package com.isunican.proyectobase;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Utilities.RemoteFetch;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PruebasUnitariasTest {

    PresenterGasolineras pg;
    Gasolinera g1;
    double precio;
    List<Gasolinera> lista;


    @Before
    public void setUp(){
        pg = new PresenterGasolineras();
        pg.cargaDatosDummy();
        lista = pg.getGasolineras();
        g1 = pg.getGasolineras().get(0);
        precio = g1.getGasoleoA();


    }

    @Test
    public void cargaDatosDummyTest(){
        assertEquals(lista.get(0).getIdeess(), 1000);
        assertEquals(lista.get(1).getIdeess(), 1053);
        assertEquals(lista.get(2).getIdeess(), 420);
        assertEquals(lista.get(3).getIdeess(), 9564);
        assertEquals(lista.get(4).getIdeess(), 1025);
    }
    
    @Test
    public void ordenaListaTest(){
        pg.ordenaLista();
        List<Gasolinera> lista = lista = pg.getGasolineras();

        //Gasolineras ordenadas correctamente por precio
        assertTrue(lista.get(0).getGasoleoA() <= lista.get(1).getGasoleoA());
        assertTrue(lista.get(1).getGasoleoA() <= lista.get(2).getGasoleoA());
        assertTrue(lista.get(2).getGasoleoA() <= lista.get(3).getGasoleoA());
        assertTrue(lista.get(3).getGasoleoA() <= lista.get(4).getGasoleoA());

        //Gasolineras no estan ordenadas por precio

    }

    @Test
    public void calcularPrecioFinalTest(){
        g1.calculaPrecioFinal();
        assertTrue(g1.getGasoleoA() == Gasolinera.round(precio*0.9+g1.getDistanciaEnKm()*6/100,4));
    }



}
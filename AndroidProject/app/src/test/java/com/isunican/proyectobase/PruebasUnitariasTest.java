package com.isunican.proyectobase;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
//import com.isunican.proyectobase.Utilities.RemoteFetch;
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

    /**
     * Metodo que comprueba que el metodo cargaDatosDummy de la clase presenter
     * funciona adecuadamente
     */
    @Test
    public void cargaDatosDummyTest(){
        assertEquals(lista.get(0).getIdeess(), 1000);
        assertEquals(lista.get(1).getIdeess(), 1053);
        assertEquals(lista.get(2).getIdeess(), 420);
        assertEquals(lista.get(3).getIdeess(), 9564);
        assertEquals(lista.get(4).getIdeess(), 1025);
    }

    /**
     * Metodo que comprueba que el metodo ordenaLista de la clase presenter
     * funciona adecuadamente
     */
    @Test
    public void ordenaListaTest(){
        pg.ordenaLista();
        List<Gasolinera> lista = lista = pg.getGasolineras();

        //Gasolineras ordenadas correctamente por precio
        assertTrue(lista.get(0).getGasoleoAConDescuento() <= lista.get(0).getGasoleoAConDescuento());
        assertTrue(lista.get(1).getGasoleoAConDescuento() <= lista.get(1).getGasoleoAConDescuento());
        assertTrue(lista.get(2).getGasoleoAConDescuento() <= lista.get(2).getGasoleoAConDescuento());
        assertTrue(lista.get(3).getGasoleoAConDescuento() <= lista.get(3).getGasoleoAConDescuento());

    }

    /**
     * Metodo que se actualiza el precio correctamente de la gasolinaA cuando se aplica un descuento
     */
    @Test
    public void calcularPrecioFinalTest(){
        g1.calculaPrecioFinal();
        assertTrue(g1.getGasoleoA() == Gasolinera.round((g1.getDEPOSITO()*precio+g1.getDistanciaEnKm()*6/100*precio)/g1.getDEPOSITO(),3));
    }
}
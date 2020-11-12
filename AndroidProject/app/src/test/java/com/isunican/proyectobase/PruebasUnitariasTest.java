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

    PresenterGasolineras sut;
    Gasolinera g1;
    Gasolinera g2;
    double precio;
    //lista desordenada
    List<Gasolinera> lista;
    //lista desordenada con gasoleoA = 0
    List<Gasolinera> l;


    @Before
    public void setUp(){
        sut = new PresenterGasolineras();
        sut.cargaDatosDummy();
        lista = sut.getGasolineras();
        g1 = sut.getGasolineras().get(0);
        g2 = sut.getGasolineras().get(1);
        precio = g1.getGasoleoA();
        l = new ArrayList<>();
        l.add(new Gasolinera(1001,"SANTANDER","SANTANDER", "Av Valdecilla", 0,1.359,"AVIA","43.45741814","-3.82677519"));
        l.add(new Gasolinera(1052,"SANTANDER","SANTANDER", "Plaza Matias Montero", 0,1.349,"CAMPSA","43.25741814","-3.84477519"));
        l.add(new Gasolinera(421,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        l.add(new Gasolinera(9544,"SANTANDER","SANTANDER", "Av Parayas", 0,1.269,"EASYGAS","43.40741814","-3.92677519"));
        l.add(new Gasolinera(1015,"SANTANDER","SANTANDER", "Calle el Empalme", 0,1.319,"CARREFOUR","43.42741814","-3.02677519"));

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
        sut.ordenaLista();
        List<Gasolinera> lista = lista = sut.getGasolineras();

        //Gasolineras ordenadas correctamente por precio
        assertTrue(lista.get(0).getGasoleoAConDescuento() <= lista.get(1).getGasoleoAConDescuento());
        assertTrue(lista.get(1).getGasoleoAConDescuento() <= lista.get(2).getGasoleoAConDescuento());
        assertTrue(lista.get(2).getGasoleoAConDescuento() <= lista.get(3).getGasoleoAConDescuento());
        assertTrue(lista.get(3).getGasoleoAConDescuento() <= lista.get(4).getGasoleoAConDescuento());

        sut.setGasolineras(l);
        sut.ordenaLista();

        //Gasolineras en el mismo orden que en la entrada
        assertTrue(lista.get(0).getGasoleoAConDescuento() == l.get(0).getGasoleoAConDescuento());
        assertTrue(lista.get(1).getGasoleoAConDescuento() == l.get(1).getGasoleoAConDescuento());
        assertTrue(lista.get(2).getGasoleoAConDescuento() == l.get(2).getGasoleoAConDescuento());
        assertTrue(lista.get(3).getGasoleoAConDescuento() == l.get(3).getGasoleoAConDescuento());
        assertTrue(lista.get(4).getGasoleoAConDescuento() == l.get(4).getGasoleoAConDescuento());

    }

    @Test
    public void calcularPrecioFinalTest(){
        g1.setTieneDescuento(true);
        g1.calculaPrecioFinal();

        //Gasolinera con descuento
        assertTrue(g1.getGasoleoAConDescuento() == Gasolinera.round((50*g1.getGasoleoA()+g1.getDistanciaEnKm()*6/100*g1.getGasoleoA())/50*0.9,3));

        g2.setTieneDescuento(false);
        g2.calculaPrecioFinal();

        //Gasolinera sin descuento
        assertTrue(g2.getGasoleoA() == Gasolinera.round((50*g2.getGasoleoA()+g2.getDistanciaEnKm()*6/100*g2.getGasoleoA())/50,3));
    }


}
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
public class PresenterGasolinerasTest {

    PresenterGasolineras sut;

    //lista desordenada
    List<Gasolinera> lista;
    //lista desordenada con gasoleoA = 0
    List<Gasolinera> l;


    @Before
    public void setUp(){
        sut = new PresenterGasolineras();
        sut.cargaDatosDummy();
        lista = sut.getGasolineras();

        l = new ArrayList<>();
        l.add(new Gasolinera(1001,"SANTANDER","SANTANDER", "Av Valdecilla", 0,1.359,"AVIA","43.45741814","-3.82677519"));
        l.add(new Gasolinera(1052,"SANTANDER","SANTANDER", "Plaza Matias Montero", 0,1.349,"CAMPSA","43.25741814","-3.84477519"));
        l.add(new Gasolinera(421,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        l.add(new Gasolinera(9544,"SANTANDER","SANTANDER", "Av Parayas", 0,1.269,"EASYGAS","43.40741814","-3.92677519"));
        l.add(new Gasolinera(1015,"SANTANDER","SANTANDER", "Calle el Empalme", 0,1.319,"CARREFOUR","43.42741814","-3.02677519"));

    }

    @Test
    public void cargaDatosDummyTest(){
        assertEquals(1000,lista.get(0).getIdeess());
        assertEquals(1053, lista.get(1).getIdeess() );
        assertEquals(420, lista.get(2).getIdeess());
        assertEquals(9564, lista.get(3).getIdeess());
        assertEquals(1025, lista.get(4).getIdeess());
    }
    
    @Test
    public void ordenaListaTest(){
        sut.ordenaLista();
        List<Gasolinera> lista = lista = sut.getGasolineras();

        //UTD.1a
        assertEquals(true, lista.get(0).getGasoleoAConDescuento() <= lista.get(1).getGasoleoAConDescuento());
        assertEquals(true, lista.get(1).getGasoleoAConDescuento() <= lista.get(2).getGasoleoAConDescuento());
        assertEquals(true, lista.get(2).getGasoleoAConDescuento() <= lista.get(3).getGasoleoAConDescuento());
        assertEquals(true, lista.get(3).getGasoleoAConDescuento() <= lista.get(4).getGasoleoAConDescuento());

        sut.setGasolineras(l);
        sut.ordenaLista();

        //UTD.1b
        assertEquals(lista.get(0).getGasoleoAConDescuento(), l.get(0).getGasoleoAConDescuento(), 0.0);
        assertEquals(lista.get(1).getGasoleoAConDescuento(), l.get(1).getGasoleoAConDescuento(), 0.0);
        assertEquals(lista.get(2).getGasoleoAConDescuento(), l.get(2).getGasoleoAConDescuento(), 0.0);
        assertEquals(lista.get(3).getGasoleoAConDescuento(), l.get(3).getGasoleoAConDescuento(), 0.0);
        assertEquals(lista.get(4).getGasoleoAConDescuento(), l.get(4).getGasoleoAConDescuento(), 0.0);

        l.clear();

        //UTD.1d
        assertEquals(0, l.size());

    }

}
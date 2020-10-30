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
    List<Gasolinera> listaOrdenada;
    List<Gasolinera> lista;

    @Before
    public void setUp(){
        pg = new PresenterGasolineras();
        pg.cargaDatosDummy();
        listaOrdenada = new ArrayList<>();
        lista = pg.getGasolineras();
        listaOrdenada.add(lista.get(3));
        listaOrdenada.add(lista.get(2));
        listaOrdenada.add(lista.get(4));
        listaOrdenada.add(lista.get(1));
        listaOrdenada.add(lista.get(0));

    }
    @Test
    public void ordenaListaTest(){
        pg.ordenaLista();
        List<Gasolinera> gasolinerasOrdenadas = pg.getGasolineras();
        assertTrue(gasolinerasOrdenadas.get(0).equals(listaOrdenada.get(0)));
        assertTrue(gasolinerasOrdenadas.get(1).equals(listaOrdenada.get(1)));
        assertTrue(gasolinerasOrdenadas.get(2).equals(listaOrdenada.get(2)));
        assertTrue(gasolinerasOrdenadas.get(3).equals(listaOrdenada.get(3)));
        assertTrue(gasolinerasOrdenadas.get(4).equals(listaOrdenada.get(4)));
    }


}
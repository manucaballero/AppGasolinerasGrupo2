package com.isunican.proyectobase;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;



import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    private PresenterGasolineras mockPresenter;
    private MainActivity.CargaDatosGasolinerasTask sut;

    @Before
    public void setUp(){

        /*
        Gasolinera g1 = new Gasolinera(1000,SANTANDER,SANTANDER, "Av Valdecilla", 1.299,1.359,"AVIA");
        Gasolinera g2 = new Gasolinera(1053,SANTANDER,SANTANDER, "Plaza Matias Montero", 1.270,1.349,"CAMPSA"));
        Gasolinera g3 = new Gasolinera(420,SANTANDER,SANTANDER, "Area Arrabal Puerto de Raos", 1.249,1.279,"E.E.S.S. MAS, S.L.");
         */
        List<Gasolinera> lista;
        /*
        lista.add(g1);
        lista.add(g2);
        lista.add(g3);

        listaOrdenada.add(g3);
        listaOrdenada.add(g2);
        listaOrdenada.add(g1);

         */

        List<Gasolinera> listaOrdenada = new ArrayList<>();
        mockPresenter = mock(PresenterGasolineras.class);
        mockPresenter.cargaDatosDummy();
        lista = mockPresenter.getGasolineras();
        listaOrdenada.add(lista.get(3));
        listaOrdenada.add(lista.get(2));
        listaOrdenada.add(lista.get(4));
        listaOrdenada.add(lista.get(1));
        listaOrdenada.add(lista.get(0));

        //Comportamiento mock
        when(mockPresenter.ordenaLista()).thenReturn(listaOrdenada);

    }
    @Test
    public void ordenaListaTest(){


    }


}
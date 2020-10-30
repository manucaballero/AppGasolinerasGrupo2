package com.isunican.proyectobase;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrdenaListaITest {

    private PresenterGasolineras mockPresenter;
    private MainActivity sut;
    private List<Gasolinera> listaOrdenada;
    List<Gasolinera> lista;

    @Before
    public void setUp(){
        mockPresenter = mock(PresenterGasolineras.class);
        mockPresenter.cargaDatosDummy();
        lista = mockPresenter.getGasolineras();

        listaOrdenada.add(lista.get(3));
        listaOrdenada.add(lista.get(2));
        listaOrdenada.add(lista.get(4));
        listaOrdenada.add(lista.get(1));
        listaOrdenada.add(lista.get(0));

        when(mockPresenter.ordenaLista()).thenReturn(listaOrdenada);
    }

    @Test
    public void ordenaListaIT(){
        sut = new MainActivity();

    }
}

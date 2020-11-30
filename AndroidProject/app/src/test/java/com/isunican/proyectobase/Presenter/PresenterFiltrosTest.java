package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.IFiltro;
import com.isunican.proyectobase.Model.Vehiculo;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que compruba el funcionamiento de los métodos de la clase PresenterFiltros.
 *
 * @author Daniel Sánchez
 */
public class PresenterFiltrosTest {
    PresenterFiltros sut;
    IFiltro f1;
    IFiltro f2;
    private List<IFiltro> listaFiltros;

    /**
     * Inicializamos los atributos necesarios antes de la ejecución de cada método.
     */
    @Before
    public void setUp(){
        sut = new PresenterFiltros();
        listaFiltros = new ArrayList<>();
    }
}

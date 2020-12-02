package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.ConDescuentoFiltro;
import com.isunican.proyectobase.Model.DieselFiltro;
import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Model.IFiltro;
import com.isunican.proyectobase.Model.Vehiculo;

import org.junit.Before;
import org.junit.Test;

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
    List<Gasolinera> listaGas;

    /**
     * Inicializamos los atributos necesarios antes de la ejecución de cada método.
     */
    @Before
    public void setUp(){
        sut = new PresenterFiltros();
        listaFiltros = new ArrayList<>();
        f1 = new ConDescuentoFiltro();
        f2 = new DieselFiltro();
        listaFiltros.add(f1);
        listaFiltros.add(f2);
        PresenterGasolineras pg = new PresenterGasolineras();
        pg.cargaDatosDummy();
        listaGas = pg.getGasolineras();
    }

    @Test
    public void eliminaFiltrosTest(){
        for(IFiltro f : listaFiltros)
            f.ordena(listaGas);
    }
}

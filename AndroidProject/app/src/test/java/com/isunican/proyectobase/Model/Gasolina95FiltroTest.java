package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Gasolina95FiltroTest {

    PresenterGasolineras presenterGasolineras;
    List<Gasolinera> listaGaso;
    Gasolinera g1;
    Gasolinera g2;
    Gasolinera g3;
    IFiltro filtro;

    /**
     * Inicializamos las variables necesarias
     */
    @Before
    public void setUp(){
        presenterGasolineras = new PresenterGasolineras();
        presenterGasolineras.cargaDatosDummy();
        listaGaso = presenterGasolineras.getGasolineras();
        filtro = new Gasolina95Filtro();

    }

    /**
     * Comprobamos el funcionamiento del metodo ordena del filtro Gasolina95,
     * el cual ordena las gasolineras de menor a mayor precio de la gasolina 95
     */
    @Test
    public void ordenaGasolina95Test(){
        filtro.ordena(listaGaso);
        g1 = listaGaso.get(0);
        g2 = listaGaso.get(1);
        g3 = listaGaso.get(2);
        Assert.assertTrue(g1.getGasolina95ConDescuento()<=g2.getGasolina95ConDescuento());
        Assert.assertTrue(g2.getGasolina95ConDescuento()<=g3.getGasolina95ConDescuento());

    }
}

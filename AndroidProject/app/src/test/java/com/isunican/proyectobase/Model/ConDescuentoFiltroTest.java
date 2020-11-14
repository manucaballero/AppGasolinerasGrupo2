package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ConDescuentoFiltroTest {

    PresenterGasolineras presenterGasolineras;
    List<Gasolinera> listaGaso;
    Gasolinera g1;
    Gasolinera g2;
    IFiltro filtro;

    /**
     * Inicializamos las variables necesarias
     */
    @Before
    public void setUp(){
        presenterGasolineras = new PresenterGasolineras();
        presenterGasolineras.cargaDatosDummy();
        listaGaso = presenterGasolineras.getGasolineras();
        filtro = new ConDescuentoFiltro();

    }

    /**
     * Comprobamos el funcionamiento del metodo ordena del filtro ConDescuento,
     * el cual solo muestra las gasolineras que tienen descuento
     */
    @Test
    public void ordenaConDescuentoTest(){
        filtro.ordena(listaGaso);
        g1 = listaGaso.get(0);
        //(maximo-minimo) para generar un numero aleatorio de los que estan en la lista
        g2 = listaGaso.get((int)Math.random()*(listaGaso.size()-0));
        Assert.assertTrue(g1.getTieneDescuento());
        Assert.assertTrue(g2.getTieneDescuento());

    }



}

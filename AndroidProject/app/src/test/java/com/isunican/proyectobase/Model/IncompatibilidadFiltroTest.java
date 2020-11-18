package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IncompatibilidadFiltroTest {

    PresenterGasolineras presenterGasolineras;
    List<Gasolinera> listaGasolineras;
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
        listaGasolineras = presenterGasolineras.getGasolineras();

    }

    /**
     * Comprobamos que el uso de más de un filtro al mismo tiempo
     * funciona de la forma esperada, ordenando de menor a mayor
     * según el último combustible seleccionado y filtrando también
     * en función de si las gasolineras tienen descuento.
     */
    @Test
    public void incompatibilidadesTest(){
        //Se filtra por gasolina
        filtro = new Gasolina95Filtro();
        filtro.ordena(listaGasolineras);

        //Se filtra por gasoleo, se debe cambiar el filtro
        filtro = new DieselFiltro();
        filtro.ordena(listaGasolineras);

        //Se filtra seleccionando solo aquellas con descuento
        filtro = new ConDescuentoFiltro();
        filtro.ordena(listaGasolineras);

        g1=listaGasolineras.get(0);
        g2=listaGasolineras.get(1);

        //Solo debe haber dos gasolineras con descuento
        assertEquals(2, listaGasolineras.size());

        //Orden correcto según el último filtro aplicado
        assertTrue(g1.getGasoleoAConDescuento()<=g2.getGasoleoAConDescuento());

        //Las gasolineras seleccionadas tienen descuento
        assertTrue(g1.getTieneDescuento());
        assertTrue(g2.getTieneDescuento());

    }
}

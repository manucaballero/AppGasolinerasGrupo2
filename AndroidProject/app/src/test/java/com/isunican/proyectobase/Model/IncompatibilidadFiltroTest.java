package com.isunican.proyectobase.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Comprobamos el uso de distintos filtros al mismo tiempo.
 * @author Aarón Rodríguez Solar
 */
public class IncompatibilidadFiltroTest {

    List<Gasolinera> listaGasolineras;
    Gasolinera g1;
    Gasolinera g2;
    Gasolinera g3;
    IFiltro sut;


    /**
     * Inicializamos las variables necesarias
     */
    @Before
    public void setUp(){
        listaGasolineras=new ArrayList<Gasolinera>();
        listaGasolineras.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,1.359,"CEPSA","43.45741814","-3.82677519"));
        listaGasolineras.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,1.349,"CAMPSA","43.25741814","-3.84477519"));
        listaGasolineras.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,1.279,"CEPSA","43.45741814","-3.82677519"));
        listaGasolineras.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,1.269,"EASYGAS","43.40741814","-3.92677519"));
        listaGasolineras.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,1.319,"CEPSA","43.42741814","-3.02677519"));
        Descuento desAux = new Descuento("codigo", "Descripción", 10.0);
        for(Gasolinera g:listaGasolineras){
            if(g.getRotulo().equals("CEPSA")){
                g.setDescuento(desAux); //Descuento del 10%
            }
        }
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
        sut = new Gasolina95Filtro();
        sut.ordena(listaGasolineras);

        //Se filtra por gasoleo, se debe cambiar el filtro
        sut = new DieselFiltro();
        sut.ordena(listaGasolineras);

        //Se filtra seleccionando solo aquellas con descuento
        sut = new ConDescuentoFiltro();
        sut.ordena(listaGasolineras);

        g1=listaGasolineras.get(0);
        g2=listaGasolineras.get(1);
        g3=listaGasolineras.get(2);

        //Solo debe haber dos gasolineras con descuento
        assertEquals(3, listaGasolineras.size());

        //Orden correcto según el último filtro aplicado
        assertTrue(g1.getGasoleoAConDescuento()<=g2.getGasoleoAConDescuento());
        assertTrue(g2.getGasoleoAConDescuento()<=g3.getGasoleoAConDescuento());

        //Las gasolineras seleccionadas tienen descuento
        assertTrue(g1.getTieneDescuento());
        assertTrue(g2.getTieneDescuento());
        assertTrue(g3.getTieneDescuento());

    }
}

package com.isunican.proyectobase.Model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Comprobamos el correcto funcionamiento del filtro sin descuento.
 * @author Ruben Calleja
 */
public class SinDescuentoFiltroTest {

    List<Gasolinera> listaGaso;
    List<Gasolinera> listaGaso0;
    Gasolinera g1;
    Gasolinera g2;
    Gasolinera g3;
    IFiltro sut;

    /**
     * Inicializamos las variables necesarias
     */
    @Before
    public void setUp(){
        sut = new SinDescuentoFiltro();

        listaGaso = new ArrayList<Gasolinera>();
        listaGaso0 = new ArrayList<Gasolinera>();

        listaGaso.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,1.349,"AVIA","43.45741814","-3.82677519"));
        listaGaso.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,1.349,"CEPSA","43.25741814","-3.84477519"));
        listaGaso.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        listaGaso.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,1.269,"EASYGAS","43.40741814","-3.92677519"));
        listaGaso.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,1.319,"CEPSA","43.42741814","-3.02677519"));

        listaGaso0.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,0,"CEPSA","43.45741814","-3.82677519"));
        listaGaso0.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,0,"CEPSA","43.25741814","-3.84477519"));
        listaGaso0.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,0,"CEPSA","43.45741814","-3.82677519"));
        listaGaso0.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,0,"CEPSA","43.40741814","-3.92677519"));
        listaGaso0.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,0,"CEPSA","43.42741814","-3.02677519"));
        //Descuento por defecto del 10%
        Descuento descuento = new Descuento("codigo", "Descripción", 10);
        //Introducimos el descuento en las gasolineras que sean de CEPSA
        for(Gasolinera g:listaGaso){
            if(g.getRotulo().equals("CEPSA")){
                g.setDescuento(descuento);
            }
        }
        for(Gasolinera g:listaGaso0){
            if(g.getRotulo().equals("CEPSA")){
                g.setDescuento(descuento);
            }
        }
    }

    /**
     * Comprobamos el funcionamiento del metodo ordena del filtro SinDescuento,
     * el cual solo muestra las gasolineras que no tienen descuento
     */
    @Test
    public void ordenaSinDescuentoTest(){
        //Comprobamos con tres gasolineras sin descuento, por tanto probamos que no tienen descuento las que hay y que son 3
        sut.ordena(listaGaso);
        g1 = listaGaso.get(0);
        g2 = listaGaso.get(1);
        g3 = listaGaso.get(2);
        Assert.assertFalse(g1.getTieneDescuento());
        Assert.assertFalse(g2.getTieneDescuento());
        Assert.assertFalse(g3.getTieneDescuento());
        Assert.assertEquals(3, listaGaso.size());

        //Comprobamos sin ninguna gasolinera sin descuento por tanto la lista estará vacía
        sut.ordena(listaGaso0);
        Assert.assertEquals(0, listaGaso0.size());

        //Comprobamos el caso con la lista vacía
        listaGaso.clear();
        sut.ordena(listaGaso);
        Assert.assertEquals(0, listaGaso.size());

    }

}

package com.isunican.proyectobase.Model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Comprobamos el correcto funcionamiento del filtro gasolina95
 * @author Ruben Calleja
 */

public class Gasolina95FiltroTest {

    List<Gasolinera> listaGaso;
    List<Gasolinera> listaGaso0;
    Gasolinera g1;
    Gasolinera g2;
    Gasolinera g3;
    Gasolinera g4;
    Gasolinera g5;
    IFiltro sut;

    /**
     * Inicializamos las variables necesarias
     */
    @Before
    public void setUp(){
        sut = new Gasolina95Filtro();

        listaGaso = new ArrayList<Gasolinera>();
        listaGaso0 = new ArrayList<Gasolinera>();

        listaGaso.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,1.349,"AVIA","43.45741814","-3.82677519"));
        listaGaso.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,1.349,"CEPSA","43.25741814","-3.84477519"));
        listaGaso.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        listaGaso.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,1.269,"EASYGAS","43.40741814","-3.92677519"));
        listaGaso.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,1.319,"CARREFOUR","43.42741814","-3.02677519"));

        listaGaso0.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,0,"AVIA","43.45741814","-3.82677519"));
        listaGaso0.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,0,"CEPSA","43.25741814","-3.84477519"));
        listaGaso0.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,0,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        listaGaso0.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,0,"EASYGAS","43.40741814","-3.92677519"));
        listaGaso0.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,0,"CARREFOUR","43.42741814","-3.02677519"));



    }

    /**
     * Comprobamos el funcionamiento del metodo ordena del filtro gasolina95,
     * el cual ordena las gasolineras de menor a mayor precio de la gasolina 95
     */
    @Test
    public void ordenaGasolina95Test(){
        //Comprobamos que se ordenan bien si tienen valor

        sut.ordena(listaGaso);

        g1 = listaGaso.get(0);
        g2 = listaGaso.get(1);
        g3 = listaGaso.get(2);
        g4 = listaGaso.get(3);
        g5 = listaGaso.get(4);

        Assert.assertTrue(g1.getGasolina95ConDescuento()<=g2.getGasolina95ConDescuento());
        Assert.assertTrue(g2.getGasolina95ConDescuento()<=g3.getGasolina95ConDescuento());
        Assert.assertTrue(g3.getGasolina95ConDescuento()<=g4.getGasolina95ConDescuento());
        Assert.assertTrue(g4.getGasolina95ConDescuento()<=g5.getGasolina95ConDescuento());

        //Comprobamos que en caso de empate si una tiene descuento se pone primero
        if(g4.getGasolina95ConDescuento() == g5.getGasolina95ConDescuento()){
            Assert.assertTrue(g4.getTieneDescuento());
        }

        //Comprobamos que se ordenan bien si su valor es 0
        sut.ordena(listaGaso0);
        g1 = listaGaso0.get(0);
        g2 = listaGaso0.get(1);
        g3 = listaGaso0.get(2);
        g4 = listaGaso0.get(3);
        g5 = listaGaso0.get(4);

        Assert.assertTrue(g1.getGasolina95ConDescuento()<=g2.getGasolina95ConDescuento());
        Assert.assertTrue(g2.getGasolina95ConDescuento()<=g3.getGasolina95ConDescuento());
        Assert.assertTrue(g3.getGasolina95ConDescuento()<=g4.getGasolina95ConDescuento());
        Assert.assertTrue(g4.getGasolina95ConDescuento()<=g5.getGasolina95ConDescuento());

        //Comprobamos el caso con la lista vacía
        listaGaso.clear();
        sut.ordena(listaGaso);
        Assert.assertTrue(listaGaso.size()==0);

    }
}

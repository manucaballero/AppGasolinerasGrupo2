package com.isunican.proyectobase.Model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Comprobamos el correcto funcionamiento del filtro gasolina95
 * @author Rubén Calleja
 */

public class Gasolina95FiltroTest {

    List<Gasolinera> listaGaso;
    List<Gasolinera> listaGaso0;
    List<Gasolinera> listaGasoNoDes;
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
        listaGasoNoDes = new ArrayList<Gasolinera>();

        listaGaso.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,1.349,"AVIA","43.45741814","-3.82677519"));
        listaGaso.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,1.360,"CEPSA","43.25741814","-3.84477519"));
        listaGaso.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        listaGaso.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,1.269,"EASYGAS","43.40741814","-3.92677519"));
        listaGaso.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,1.319,"CARREFOUR","43.42741814","-3.02677519"));

        listaGaso0.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,0,"AVIA","43.45741814","-3.82677519"));
        listaGaso0.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,0,"CEPSA","43.25741814","-3.84477519"));
        listaGaso0.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,0,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        listaGaso0.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,0,"EASYGAS","43.40741814","-3.92677519"));
        listaGaso0.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,0,"CARREFOUR","43.42741814","-3.02677519"));

        listaGasoNoDes.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,0,"AVIA","43.45741814","-3.82677519"));
        listaGasoNoDes.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,0,"CAMPSA","43.25741814","-3.84477519"));
        listaGasoNoDes.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,0,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        listaGasoNoDes.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,0,"EASYGAS","43.40741814","-3.92677519"));
        listaGasoNoDes.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,0,"CARREFOUR","43.42741814","-3.02677519"));



    }

    /**
     * Comprobamos el funcionamiento del método ordena del filtro gasolina95,
     * el cual ordena las gasolineras de menor a mayor precio de la gasolina 95
     *
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

        assertEquals(true, g1.getGasolina95ConDescuento() <= g2.getGasolina95ConDescuento());
        assertEquals(true, g2.getGasolina95ConDescuento() <= g3.getGasolina95ConDescuento());
        assertEquals(true, g3.getGasolina95ConDescuento() <= g4.getGasolina95ConDescuento());
        assertEquals(true, g4.getGasolina95ConDescuento() <= g5.getGasolina95ConDescuento());

        //caso con descuento y mismo precio
        sut.ordena(listaGaso0);

        g1 = listaGaso0.get(0);
        g2 = listaGaso0.get(1);
        g3 = listaGaso0.get(2);
        g4 = listaGaso0.get(3);
        g5 = listaGaso0.get(4);

        //Comprobamos que en caso de empate si una tiene descuento se pone primero, todas estan a 0, pero como una tiene descuento está la primera
        assertEquals(true, g1.getTieneDescuento());
        assertEquals(g1, listaGaso0.get(0));
        assertEquals(g2, listaGaso0.get(1));
        assertEquals(g3, listaGaso0.get(2));
        assertEquals(g4, listaGaso0.get(3));
        assertEquals(g5, listaGaso0.get(4));



        //Comprobamos que si todas tienen valor 0 y ninguna tiene descuento se quedan como están
        g1 = listaGasoNoDes.get(0);
        g2 = listaGasoNoDes.get(1);
        g3 = listaGasoNoDes.get(2);
        g4 = listaGasoNoDes.get(3);
        g5 = listaGasoNoDes.get(4);
        sut.ordena(listaGasoNoDes);


        assertEquals(g1, listaGasoNoDes.get(0));
        assertEquals(g2, listaGasoNoDes.get(1));
        assertEquals(g3, listaGasoNoDes.get(2));
        assertEquals(g4, listaGasoNoDes.get(3));
        assertEquals(g5, listaGasoNoDes.get(4));

        //Comprobamos el caso con la lista vacía
        listaGaso.clear();
        sut.ordena(listaGaso);
        assertEquals(0, listaGaso.size());

    }
}

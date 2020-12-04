package com.isunican.proyectobase.Model;

import androidx.annotation.Nullable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Bárbara Martínez Carcedo
 */
public class DieselFiltroTest {

    IFiltro sut;
    //lista desordenada
    List<Gasolinera> gasolineraList;
    //lista desordenada con gasóleo A == 0
    List<Gasolinera> l;

    Gasolinera g1;
    Gasolinera g2;
    Gasolinera g3;
    Gasolinera g4;
    Gasolinera g5;


    /**
     * Inicialización de variables
     */
    @Before
    public void setUp(){
        sut = new DieselFiltro();
        l = new ArrayList<>();
        gasolineraList = new ArrayList<>();

        gasolineraList.add(new Gasolinera(1101,"SANTANDER","SANTANDER", "Av Valdecilla", 0.352,1.359,"AVIA","43.45741814","-3.82677519"));
        gasolineraList.add(new Gasolinera(1152,"SANTANDER","SANTANDER", "Plaza Matias Montero", 1.034,1.349,"CAMPSA","43.25741814","-3.84477519"));
        gasolineraList.add(new Gasolinera(411,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0.977,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        gasolineraList.add(new Gasolinera(9144,"SANTANDER","SANTANDER", "Av Parayas", 1.543,1.269,"EASYGAS","43.40741814","-3.92677519"));
        gasolineraList.add(new Gasolinera(1115,"SANTANDER","SANTANDER", "Calle el Empalme", 1.254,1.319,"CARREFOUR","43.42741814","-3.02677519"));


        l.add(new Gasolinera(1001,"SANTANDER","SANTANDER", "Av Valdecilla", 0,1.359,"AVIA","43.45741814","-3.82677519"));
        l.add(new Gasolinera(1052,"SANTANDER","SANTANDER", "Plaza Matias Montero", 0,1.349,"CAMPSA","43.25741814","-3.84477519"));
        l.add(new Gasolinera(421,"SANTANDER","SANTANDER", "Area Arrabal Puerto de Raos", 0,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        l.add(new Gasolinera(9544,"SANTANDER","SANTANDER", "Av Parayas", 0,1.269,"EASYGAS","43.40741814","-3.92677519"));
        l.add(new Gasolinera(1015,"SANTANDER","SANTANDER", "Calle el Empalme", 0,1.319,"CARREFOUR","43.42741814","-3.02677519"));
    }

    /**
     * Método test que comprueba que el método ordena() de la clase DieselFiltro funciona
     * adecuadamente.
     *
     */
    @Test
    public void ordenaGasoleATest(){

        //lista con gasolineras sin descuento, gasoleoA!=null y ninguna con el mismo precio del gasóleo A.
        sut.ordena(gasolineraList);

        g1 = gasolineraList.get(0);
        g2 = gasolineraList.get(1);
        g3 = gasolineraList.get(2);
        g4 = gasolineraList.get(3);
        g5 = gasolineraList.get(4);

        assertEquals(true, g1.getGasoleoAConDescuento() <= g2.getGasoleoAConDescuento());
        assertEquals(true, g2.getGasoleoAConDescuento() <= g3.getGasoleoAConDescuento());
        assertEquals(true, g3.getGasoleoAConDescuento() <= g4.getGasoleoAConDescuento());
        assertEquals(true, g4.getGasoleoAConDescuento() <= g5.getGasoleoAConDescuento());

        //lista con gasolineras, y una de ellas con descuento, gasoleoA!=null y ninguna con los precios del gasóleo A igual.
        gasolineraList.get(4).setTieneDescuento(true);
        sut.ordena(gasolineraList);

        assertEquals(g1, gasolineraList.get(0));
        assertEquals(g2, gasolineraList.get(1));
        assertEquals(g3, gasolineraList.get(2));
        assertEquals(g4, gasolineraList.get(3));
        assertEquals(g5, gasolineraList.get(4));


        //lista con gasolineras sin descuento y gasoleoA==0
        g1 = l.get(0);
        g2 = l.get(1);
        g3 = l.get(2);
        g4 = l.get(3);
        g5 = l.get(4);

        sut.ordena(l);

        assertEquals(g1, l.get(0));
        assertEquals(g2, l.get(1));
        assertEquals(g3, l.get(2));
        assertEquals(g4, l.get(3));
        assertEquals(g5, l.get(4));



        //Lista de gasolineras con todos los precios iguales, y una de ellas con descuento.
        g5.setTieneDescuento(true);
        sut.ordena(l);
        assertEquals(g5, l.get(0));
        assertEquals(g1, l.get(1));
        assertEquals(g2, l.get(2));
        assertEquals(g3, l.get(3));
        assertEquals(g4, l.get(4));


        //lista vacía
        l.clear();
        sut.ordena(l);
        assertEquals(0, l.size());

    }


}

package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Descuento;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterDescuentosTest {
    //Presenter sobre el que se realizarán las pruebas
    private PresenterDescuentos sut;
    //Lista auxiliar de descuentos
    private List<Descuento> listaAux;

    @Before
    public void setUp(){
        listaAux = new ArrayList<Descuento>();
        sut = new PresenterDescuentos();
        listaAux.add(new Descuento("codigo1", "Se aplicará un descuento del 10%", 10));
        listaAux.add(new Descuento("codigo2", "Se aplicará un descuento del 15%", 15));
        listaAux.add(new Descuento("codigo3", "Se aplicará un descuento del 20%", 20));
        listaAux.add(new Descuento("codigo4", "Se aplicará un descuento del 25%", 25));
        listaAux.add(new Descuento("codigo5", "Se aplicará un descuento del 30%", 30));
    }

    /*
        @author Miguel Casamichana Bolado

        Se carga la lista del presenter comprobando que sea true (devuelve true si está cargada correctamente)
        y se comprueba con la lista auxiliar que cada elemento introducido es el esperado. Además, se comprueba
        también su tamaño (en este caso 5).
     */
    @Test
    public void cargaDatosDummyTest(){
        List<Descuento> lista;
        assertTrue(sut.cargaDatosDummy());
        lista = sut.getDescuentos();
        for(int i=0; i<listaAux.size();i++){
            assertEquals(lista.get(i),listaAux.get(i));
        }
        assertEquals(5, lista.size());
    }
}

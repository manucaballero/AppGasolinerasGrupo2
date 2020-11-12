package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import java.util.Comparator;
import java.util.Iterator;

public class ConDescuentoFiltro implements IDescuentoFiltro {

    String nombre = "conDescuento";

    @Override
    public void ordena(PresenterGasolineras presenterGasolineras) {
        Iterator i = presenterGasolineras.getGasolineras().iterator();

        while(i.hasNext()){
            Gasolinera g = (Gasolinera) i.next();
            if(!g.getTieneDescuento()){
                i.remove();
            }
        }
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}


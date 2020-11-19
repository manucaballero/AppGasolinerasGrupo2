package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Filtro que muestra solo las gasolineras que tienen un descuento activo
 */
public class ConDescuentoFiltro implements IDescuentoFiltro {

    String nombre = "Con Descuento";

    @Override
    public void ordena(List<Gasolinera> listaGasolineras) {
        Iterator i = listaGasolineras.iterator();

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


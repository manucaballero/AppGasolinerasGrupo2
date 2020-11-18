package com.isunican.proyectobase.Model;

import java.util.Iterator;
import java.util.List;

/**
 * Filtro que muestra solo las gasolineras que no tienen un descuento activo
 */
public class SinDescuentoFiltro implements IDescuentoFiltro {

    String nombre = "sinDescuento";

    @Override
    public void ordena(List<Gasolinera> listaGasolineras) {
        Iterator<Gasolinera> i = listaGasolineras.iterator();

        while(i.hasNext()){
            Gasolinera g = i.next();
            if(g.getTieneDescuento()){
                i.remove();
            }
        }

    }

    @Override
    public String getNombre() {
        return null;
    }
}

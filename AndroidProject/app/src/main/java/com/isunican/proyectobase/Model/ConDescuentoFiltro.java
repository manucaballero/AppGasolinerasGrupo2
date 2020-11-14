package com.isunican.proyectobase.Model;


import java.util.Iterator;
import java.util.List;

/**
 * Filtro que muestra solo las gasolineras que tienen un descuento activo
 */
public class ConDescuentoFiltro implements IDescuentoFiltro {

    String nombre = "conDescuento";

    @Override
    public void ordena(List<Gasolinera> listaGasolineras) {
        Iterator<Gasolinera> i = listaGasolineras.iterator();

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


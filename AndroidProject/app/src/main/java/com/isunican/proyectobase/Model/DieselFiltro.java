package com.isunican.proyectobase.Model;


import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

/**
 * Filtro que muestra las gasolineras ordenadas de menor a mayor precio del diesel
 */
public class DieselFiltro implements ICombustibleFiltro{

    String nombre = "diesel";

    @Override
    public void ordena(List<Gasolinera> listaGasolineras) {
        sort(listaGasolineras,new ComparadorGasolinerasDiesel());
    }

    @Override
    public String getNombre() {
        return nombre;
    }


}

/**
 * Clase para comparar dos gasolineras en función del precio del diesel
 */
class ComparadorGasolinerasDiesel implements Comparator<Gasolinera>{
    @Override
    public int compare(Gasolinera g1, Gasolinera g2) {
        double resta = g1.getGasoleoAConDescuento() - g2.getGasoleoAConDescuento();
        if (resta == 0){
            return 0;
        }else {
            if(resta < 0){
                return -1;
            }else{
                return 1;
            }
        }

    }
}

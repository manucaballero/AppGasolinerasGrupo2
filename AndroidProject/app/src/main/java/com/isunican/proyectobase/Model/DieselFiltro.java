package com.isunican.proyectobase.Model;


import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

/**
 * Filtro que muestra las gasolineras ordenadas de menor a mayor precio del diesel
 */
public class DieselFiltro implements ICombustibleFiltro{

    String nombre = "GasóleoA";

    @Override
    public void ordena(List<Gasolinera> listaGasolineras) {
        sort(listaGasolineras,new Comparador("GasoleoA"));
    }

    @Override
    public String getNombre() {
        return nombre;
    }


}


package com.isunican.proyectobase.Model;


import java.util.Collections;
import java.util.List;

/**
 * Filtro que muestra las gasolineras ordenadas de menor a mayor precio de la gasolina95
 */
public class Gasolina95Filtro implements ICombustibleFiltro{

    String nombre = "Gasolina 95";

    @Override
    public void ordena(List<Gasolinera> listaGasolineras) {
        Collections.sort(listaGasolineras, new Comparador("Gasolina95"));
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}

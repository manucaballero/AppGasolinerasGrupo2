package com.isunican.proyectobase.Model;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Filtro que muestra las gasolineras ordenadas de menor a mayor precio de la gasolina95
 */
public class Gasolina95Filtro implements ICombustibleFiltro{

    String nombre = "gasolina";

    @Override
    public void ordena(List<Gasolinera> listaGasolineras) {
        Collections.sort(listaGasolineras, new ComparadorGasolinerasGasolina());
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
/**
 * Clase para comparar dos gasolineras en función del precio de la gasolina95
 */
class ComparadorGasolinerasGasolina implements Comparator<Gasolinera> {
    @Override
    public int compare(Gasolinera g1, Gasolinera g2) {
        double resta = g1.getGasolina95ConDescuento() - g2.getGasolina95ConDescuento();
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

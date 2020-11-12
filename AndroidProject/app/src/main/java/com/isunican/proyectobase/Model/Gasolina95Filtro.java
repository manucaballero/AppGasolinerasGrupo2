package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import java.util.Comparator;

public class Gasolina95Filtro implements ICombustibleFiltro{

    String nombre = "gasolina";

    @Override
    public void ordena(PresenterGasolineras presenterGasolineras) {

    }

    @Override
    public String getNombre() {
        return nombre;
    }
}

class comparadorGasolinerasGasolina implements Comparator<Gasolinera> {
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

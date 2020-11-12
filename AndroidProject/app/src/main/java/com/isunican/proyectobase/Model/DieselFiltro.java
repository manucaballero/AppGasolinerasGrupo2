package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.*;

public class DieselFiltro implements ICombustibleFiltro{

    String nombre = "diesel";

    @Override
    public void ordena(PresenterGasolineras presenterGasolineras) {
        sort(presenterGasolineras.getGasolineras(),new comparadorGasolinerasDiesel());
    }

    @Override
    public String getNombre() {
        return nombre;
    }


}

class comparadorGasolinerasDiesel implements Comparator<Gasolinera>{
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

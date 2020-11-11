package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class PresenterVehiculos {
    private List<Vehiculo> listVehiculos;

    public PresenterVehiculos(){
        this.listVehiculos=new ArrayList<Vehiculo>();
    }

    /**
     * TO DO
     * @return
     */
    public boolean cargaDatosVehiculos(){

        Vehiculo v1= new Vehiculo("Mercedes-Benz Clase A 180 CDI");
        Vehiculo v2=new Vehiculo("Tesla model S P100D");
        listVehiculos.add(v1);
        listVehiculos.add(v2);
        return true;

    }

    public List<Vehiculo> getVehiculos(){
        return listVehiculos;
    }


}

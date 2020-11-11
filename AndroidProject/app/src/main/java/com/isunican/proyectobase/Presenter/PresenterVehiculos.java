package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class PresenterVehiculos {
    private List<Vehiculo> listVehiculos;

    public PresenterVehiculos(){
        this.listVehiculos=new ArrayList<Vehiculo>();
        Vehiculo v1= new Vehiculo("Mercedes-Benz Clase A 180 CDI");
        Vehiculo v2=new Vehiculo("Tesla model S P100D");
        v1.setMatricula("1234ABC");
        //v2.setMatricula("9876ZYX");

        v1.setAnotaciones("Familiar");
        v2.setAnotaciones("Trabajo");

        listVehiculos.add(v1);
        listVehiculos.add(v2);

    }

    /**
     * TO DO
     * Añadir los vehiculos almacenados en un fichero
     * @return
     */
    public boolean cargaDatosVehiculos(){


        return true;

    }

    public List<Vehiculo> getVehiculos(){
        return listVehiculos;
    }


}

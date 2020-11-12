package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class PresenterVehiculos {
    private List<Vehiculo> listVehiculos;

    public PresenterVehiculos(){
        this.listVehiculos=new ArrayList<Vehiculo>();
    }

    public boolean cargaDatosVehiculos(){
        return false;
    }
}

package com.isunican.proyectobase.Presenter;

import com.google.gson.Gson;
import com.isunican.proyectobase.Model.Vehiculo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PresenterVehiculos {

    private List<Vehiculo> listVehiculos;

    //Vehiculo seleccionado por el usuario. De este vehiculo se utilizará el desposito y consumo medio.
    private static Vehiculo vehiculoSeleccionado;

    public PresenterVehiculos(){
        this.listVehiculos=new ArrayList<Vehiculo>();
        Vehiculo v1= new Vehiculo("Mercedes-Benz Clase A 180 CDI");
        Vehiculo v2=new Vehiculo("Tesla model S P100D");
        v1.setMatricula("1234ABC");
        //v2.setMatricula("9876ZYX");

        v1.setAnotaciones("Familiar");
        v2.setAnotaciones("Trabajo");

        v1.setDeposito(60);
        v1.setConsumoMedio(6.4);

        setVehiculoSeleccionado(v1);

        listVehiculos.add(v1);
        listVehiculos.add(v2);

    }

    /**
     * TO DO
     * Añadir los vehiculos almacenados en un fichero
     * @return
     */
    public boolean cargaDatosVehiculos(){

        File file = new File("vehiculos.json");
        boolean exists = file.exists();

        String data="";

        if (exists){
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String st;
                while ((st = in.readLine()) != null)
                    data+=st;

                Gson gson= new Gson();
                listVehiculos = gson.fromJson(data, List.class);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;

    }

    public List<Vehiculo> getVehiculos(){
        return listVehiculos;
    }

    public static Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    public static void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        PresenterVehiculos.vehiculoSeleccionado = vehiculoSeleccionado;
    }

}

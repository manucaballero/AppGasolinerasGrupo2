package com.isunican.proyectobase.Presenter;

import android.content.Context;
import android.util.Log;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Views.FormActivity;
import com.isunican.proyectobase.Views.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.Normalizer;
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
        try {

            BufferedReader in = new BufferedReader(new FileReader(MainActivity.vehiculos));

            String linea=in.readLine();
            Vehiculo v = null;
            while(linea.equals("---")){
                new Vehiculo(linea); //modelo
                v.setDeposito(Double.parseDouble(in.readLine()));//capacidad
                v.setConsumoMedio(Double.parseDouble(in.readLine()));//c medio
                v.setMatricula(in.readLine());//maticula
                v.setAnotaciones(in.readLine());//nota
                listVehiculos.add(v);
                linea = in.readLine();
            }
            Log.d("Veh", "Tras bucle");
            in.close();

        } catch(Exception e) {
            e.printStackTrace();
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

    public void guardaVehiculo(Vehiculo v){

        listVehiculos.add(v);

        String output ="";

        for (Vehiculo v1:listVehiculos) {
            output += "---\n" + v.getModelo()+ "\n"+ v.getDeposito() + "\n"+ v.getConsumoMedio() + "\n"+ v.getMatricula()
                    + "\n"+ v.getAnotaciones()+ "\n";
        }

        try {
            FileWriter fw = new FileWriter(MainActivity.vehiculos);
            PrintWriter out = new PrintWriter(fw);
            out.println(output);
            out.close();
            Log.d("Prueba", output);
        }

        catch(IOException e) {
            System.out.println("Error al escribir");
        }

    }

}

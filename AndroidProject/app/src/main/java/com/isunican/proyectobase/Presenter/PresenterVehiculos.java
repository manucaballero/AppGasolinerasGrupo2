package com.isunican.proyectobase.Presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.LongDef;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Views.FormActivity;
import com.isunican.proyectobase.Views.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
        //listVehiculos.add(v2);

    }

    /**
     * TO DO
     * Añadir los vehiculos almacenados en un fichero
     * @return
     */
    public boolean cargaDatosVehiculos(Context context){

        List<Vehiculo> aux = new ArrayList<Vehiculo>();
        try {
            File tempFile = new File(context.getFilesDir()+"/vehiculos.txt");
            boolean exists = tempFile.exists();

            if (exists){
                BufferedReader in = new BufferedReader(new FileReader(context.getFilesDir()+"/vehiculos.txt"));

                String linea=in.readLine();
                Vehiculo v;

                while(linea.equals("---") && !linea.equals("-fin-")){
                    v = new Vehiculo(in.readLine()); //modelo
                    v.setDeposito(Double.parseDouble(in.readLine()));//capacidad
                    v.setConsumoMedio(Double.parseDouble(in.readLine()));//c medio
                    v.setMatricula(in.readLine());//maticula
                    v.setAnotaciones(in.readLine());//nota
                    aux.add(v);
                    linea = in.readLine();
                }
                listVehiculos = aux;
                in.close();
            }


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

    public void guardaVehiculo(Vehiculo v, Context context){

        File tempFile = new File(context.getFilesDir()+"/vehiculos.txt");
        boolean exists = tempFile.exists();

        if (exists)
            tempFile.delete();

        listVehiculos.add(v);

        String output ="";

        for (Vehiculo v1:listVehiculos) {
            output += "---\n" + v1.getModelo()+ "\n"+ v1.getDeposito() + "\n"+ v1.getConsumoMedio() + "\n"+ v1.getMatricula()
                    + "\n"+ v1.getAnotaciones()+ "\n";
        }

        output+="-fin-";

        try {
            File f = new File(context.getFilesDir() + "/vehiculos.txt");
            FileWriter fw = new FileWriter(f);
            fw.append(output);
            fw.close();
        }

        catch(IOException e) {
            e.printStackTrace();
        }

    }

}

package com.isunican.proyectobase.Presenter;

import android.content.Context;

import com.isunican.proyectobase.Model.Vehiculo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public boolean guardaVehiculo(Vehiculo v, Context context){

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
            fw.write(output);
            fw.close();
            return true;
        }

        catch(IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void guardaVehiculoSeleccionado(Vehiculo v, Context context){

        String output = v.getModelo() + "\n" + v.getMatricula() + "\n" + v.getAnotaciones();

        try {
            File f = new File(context.getFilesDir() + "/vehiculoSeleccionado.txt");
            FileWriter fw = new FileWriter(f);
            fw.write(output);
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public boolean cargaVehiculoSeleccionado(Context context){
        List<Vehiculo> aux = new ArrayList<Vehiculo>();

        try {
            File tempFile = new File(context.getFilesDir()+"/vehiculoSeleccionado.txt");
            boolean exists = tempFile.exists();

            if (exists){
                BufferedReader in = new BufferedReader(new FileReader(context.getFilesDir()+"/vehiculoSeleccionado.txt"));

                String modelo = in.readLine(); //modelo
                String matricula = in.readLine();//maticula
                String anotacion = in.readLine();//nota

                for(Vehiculo v : listVehiculos){
                    if(v.getModelo().equals(modelo)){
                        aux.add(v);
                    }
                }
                if(aux.size() == 1)
                    setVehiculoSeleccionado(aux.get(0));
                else {
                    for(Vehiculo v : aux){
                        if(v.getAnotaciones().equals(anotacion) && v.getMatricula().equals(matricula)){
                            setVehiculoSeleccionado(v);
                        }
                    }
                }

                in.close();

            }else{
                setVehiculoSeleccionado(listVehiculos.get(0));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void borra(Context c){
        File f = new File(c.getFilesDir()+"/vehiculoSeleccionado.txt");
        File f1 = new File(c.getFilesDir()+"/vehiculos.txt");
        f.delete();
        f1.delete();
        setVehiculoSeleccionado(listVehiculos.get(0));
    }
}

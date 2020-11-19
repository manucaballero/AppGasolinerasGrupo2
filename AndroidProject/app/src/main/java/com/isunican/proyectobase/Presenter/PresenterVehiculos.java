package com.isunican.proyectobase.Presenter;

import android.content.Context;
import android.util.Log;

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
    private static final String VEHICULO_TXT = "/vehiculos.txt";

    private static final String VEHICULO_SELECCIONADO_TXT = "/vehiculoSeleccionado.txt";

    //Vehiculo seleccionado por el usuario. De este vehiculo se utilizará el desposito y consumo medio.
    private static Vehiculo vehiculoSeleccionado;

    public PresenterVehiculos(){
        this.listVehiculos=new ArrayList<>();
        Vehiculo v1= new Vehiculo("Mercedes-Benz Clase A 180 CDI");
        Vehiculo v2=new Vehiculo("Tesla model S P100D");
        v1.setMatricula("1234ABC");

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
    public boolean cargaDatosVehiculos(Context context) {

        List<Vehiculo> aux = new ArrayList<Vehiculo>();
        BufferedReader in = null;

        try {
            File tempFile = new File(context.getFilesDir()+VEHICULO_TXT);
            boolean exists = tempFile.exists();

            if (exists){
                in = new BufferedReader(new FileReader(context.getFilesDir()+VEHICULO_TXT));

                String linea=in.readLine();
                Vehiculo v;

                while(linea.equals("---") && !linea.equals("-fin-")){
                    v = new Vehiculo(in.readLine()); //modelo
                    v.setDeposito(Double.parseDouble(in.readLine()));//capacidad
                    v.setConsumoMedio(Double.parseDouble(in.readLine()));//c medio
                    v.setMatricula(in.readLine());//matricula
                    v.setAnotaciones(in.readLine());//nota
                    aux.add(v);
                    linea = in.readLine();
                }
                listVehiculos = aux;
                in.close();

            }

        } catch(Exception e) {
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
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

    public boolean guardaVehiculo(Vehiculo v, Context context) {

        listVehiculos.add(v);
        StringBuilder bld = new StringBuilder();
        String output ="";

        for (Vehiculo v1:listVehiculos) {
            bld.append( "---\n" + v1.getModelo()+ "\n"+ v1.getDeposito() + "\n"+ v1.getConsumoMedio() + "\n"+ v1.getMatricula()
                    + "\n"+ v1.getAnotaciones()+ "\n");
        }

        bld.append("-fin-");
        FileWriter fw = null;
        output = bld.toString();
        try {
            File f = new File(context.getFilesDir() + VEHICULO_TXT);
            fw = new FileWriter(f);
            fw.write(output);
            return true;
        }

        catch(IOException e) {
            return false;
        } finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }



    }

    public static void guardaVehiculoSeleccionado(Vehiculo v, Context context) {

        String output = v.getModelo() + "\n" + v.getMatricula() + "\n" + v.getAnotaciones();
        FileWriter fw = null;

        try {
            File f = new File(context.getFilesDir() + VEHICULO_SELECCIONADO_TXT );
            fw = new FileWriter(f);
            fw.write(output);
        } catch(IOException e) {
        } finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }

    }

    public boolean cargaVehiculoSeleccionado(Context context) {
        List<Vehiculo> aux = new ArrayList<Vehiculo>();
        BufferedReader in = null;
        try {
            File tempFile = new File(context.getFilesDir()+VEHICULO_SELECCIONADO_TXT );
            boolean exists = tempFile.exists();
            if (exists){
                in = new BufferedReader(new FileReader(context.getFilesDir()+VEHICULO_SELECCIONADO_TXT ));

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
            }else{
                setVehiculoSeleccionado(listVehiculos.get(0));
            }

        } catch(Exception e) {
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        return true;
    }

    public void borra(Context c){
        File f = new File(c.getFilesDir()+VEHICULO_SELECCIONADO_TXT );
        File f1 = new File(c.getFilesDir()+VEHICULO_TXT);
        if(f.delete() && f1.delete()){
            setVehiculoSeleccionado(listVehiculos.get(0));
        }
    }
}

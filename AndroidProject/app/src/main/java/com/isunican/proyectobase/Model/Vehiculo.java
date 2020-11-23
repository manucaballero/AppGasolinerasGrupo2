package com.isunican.proyectobase.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehiculo implements Parcelable {
    private String modelo;
    private String anotaciones;
    private String matricula;

    private double deposito;        //En litros
    private double consumoMedio;    //En L/Km

    public Vehiculo(String modelo){
        this.modelo=modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getDeposito() {
        return deposito;
    }

    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }

    public double getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(double consumoMedio){this.consumoMedio=consumoMedio; }



    /**
     * interfaz Parcelable
     *
     * Métodos necesarios para implementar la interfaz Parcelable
     * que nos permitirá pasar objetos del tipo Vehiculo
     * directamente entre actividades utilizando intents
     * Se enviarían utilizando putExtra
     * myIntent.putExtra("id", objeto vehiculo);
     * y recibiéndolos con
     * Vehiculo v = getIntent().getExtras().getParcelable("id")
     */
    protected Vehiculo(Parcel in) {
        modelo = in.readString();
        matricula = in.readString();
        anotaciones = in.readString();
        deposito = in.readDouble();
        consumoMedio = in.readDouble();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(modelo);
        dest.writeString(matricula);
        dest.writeString(anotaciones);
        dest.writeDouble(deposito);
        dest.writeDouble(consumoMedio);
    }

    public static final Parcelable.Creator<Vehiculo> CREATOR = new Parcelable.Creator<Vehiculo>() {
        @Override
        public Vehiculo createFromParcel(Parcel in) {
            return new Vehiculo(in);
        }

        @Override
        public Vehiculo[] newArray(int size) {
            return new Vehiculo[size];
        }
    };

    @Override
    public boolean equals(Object o){
        boolean aux = false;

        if(o==this){
            aux = true;
        }
        if(!(o instanceof Vehiculo)){
            aux = false;
        }
        if (o != null && ((Vehiculo) o).modelo.equals(this.modelo)
                && ((Vehiculo) o).anotaciones.equals(this.anotaciones)
                && ((Vehiculo) o).matricula.equals(this.matricula)) {

            aux = true;
        }
        return aux;

    }

    @Override
    public int hashCode(){
        return -1;
    }
}

package com.isunican.proyectobase.Model;

import android.os.Parcel;
import android.os.Parcelable;

/*
------------------------------------------------------------------
    Clase que almacena la informacion de una gasolinera
    Implementa la interfaz Parceable, que permite que luego podamos
    pasar objetos de este tipo entre activities a traves de una llamada intent
------------------------------------------------------------------
*/

public class Gasolinera implements Parcelable {
    private int ideess;
    private String localidad;
    private String provincia;
    private String direccion;
    private String rotulo;
    private Posicion posicion;

    //Guardarán el precio con descuento y consumo.
    private double gasoleoA;
    private double gasolina95;

    //Precio sin el descuento pero contando con el consumo hasta la gasolinera.
    private double gasoleoAConDescuento;
    private double gasolina95ConDescuento;

    private double distanciaEnKm;
    private boolean tieneDescuento;
    private static final double DEPOSITO = 50;


    /**
     * Constructor, getters y setters
     */
    public Gasolinera (int ideess, String localidad, String provincia, String direccion, double gasoleoA, double gasolina95, String rotulo,String latitud, String longitud ){
        this.ideess = ideess;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.gasoleoA = gasoleoA;
        this.gasolina95 = gasolina95;
        this.rotulo = rotulo;
        this.tieneDescuento=false;

        this.posicion = new Posicion(Double.parseDouble(latitud.replace(",",".")),Double.parseDouble(longitud.replace(",",".")));

        if(rotulo.equals("CEPSA")){
            setTieneDescuento(true);
        }
    }

    /**
     * Método que calcula el precio por litro final que tendrá cada tipo de combustible
     * teniendo en cuenta el descuento disponible en la gasolinera y el consumo de conducir
     * hasta ella.
     */
    public void calculaPrecioFinal(Vehiculo v){

        if(this.getTieneDescuento()){
            this.gasoleoAConDescuento=round((v.getDeposito()*gasoleoA+distanciaEnKm*v.getConsumoMedio()*gasoleoA)/v.getDeposito()*0.9,3);
            this.gasolina95ConDescuento=round((v.getDeposito()*gasolina95+distanciaEnKm*v.getConsumoMedio()*gasolina95)/v.getDeposito()*0.9,3);
        }else{
            this.gasoleoAConDescuento=round((v.getDeposito()*gasoleoA+distanciaEnKm*v.getConsumoMedio()*gasoleoA)/v.getDeposito(),3);
            this.gasolina95ConDescuento=round((v.getDeposito()*gasolina95+distanciaEnKm*v.getConsumoMedio()*gasolina95)/v.getDeposito(),3);
        }
        this.gasoleoA=round((v.getDeposito()*gasoleoA+distanciaEnKm*v.getConsumoMedio()*gasoleoA)/v.getDeposito(),3);
        this.gasolina95=round((v.getDeposito()*gasolina95+distanciaEnKm*v.getConsumoMedio()*gasolina95)/v.getDeposito(),3);

    }

    public boolean getTieneDescuento(){
        return tieneDescuento;
    }

    public void setTieneDescuento(boolean tieneDescuento){
        this.tieneDescuento=tieneDescuento;
    }

    /**
     * Método que redondea un número double.
     * @param value valor a redondear
     * @param places número de decimales deseados
     * @return valor introducido redondeado
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }



    public int getIdeess() { return ideess; }
    public void setIdeess(int ideess) { this.ideess = ideess; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getGasoleoA() { return gasoleoA; }
    public void setGasoleoA(double gasoleoA) { this.gasoleoA = gasoleoA; }

    public String getRotulo() { return rotulo; }
    public void setRotulo(String rotulo) { this.rotulo = rotulo; }

    public double getGasolina95() { return gasolina95; }
    public void setGasolina95(double gasolina95) { this.gasolina95 = gasolina95; }

    public double getDistanciaEnKm(){return distanciaEnKm;}

    public Posicion getPosicion(){ return posicion; }

    public double getGasoleoAConDescuento(){return gasoleoAConDescuento;}
    public void setGasoleoAConDescuento(double gasoleoA) { this.gasoleoAConDescuento = gasoleoA; }

    public double getGasolina95ConDescuento(){return gasolina95ConDescuento;}
    public void setGasolina95ConDescuento(double gasolina95) { this.gasolina95ConDescuento = gasolina95; }

    public void setDistanciaEnKm(double distanciaEnKm){
        this.distanciaEnKm = distanciaEnKm;
    }
    public double getDEPOSITO(){ return  DEPOSITO;}
    /**
     * toString
     *
     * Redefine el método toString para obtener los datos
     * de una Gasolinera en formato texto
     *
     * @param
     * @return String
     */
    @Override
    public String toString(){
        String textoGasolineras = "";
        textoGasolineras +=
                getRotulo() + "\n"+
                        getDireccion() + "\n" +
                        getLocalidad() + "\n" +
                        "Precio diesel: " + getGasoleoA() + " " + "\n" +
                        "Precio gasolina 95: " + getGasolina95() + " " + "\n\n";

        return textoGasolineras;
    }


    /**
     * interfaz Parcelable
     *
     * Métodos necesarios para implementar la interfaz Parcelable
     * que nos permitirá pasar objetos del tipo Gasolinera
     * directamente entre actividades utilizando intents
     * Se enviarían utilizando putExtra
     * myIntent.putExtra("id", objeto gasolinera);
     * y recibiéndolos con
     * Gasolinera g = getIntent().getExtras().getParcelable("id")
     */
    protected Gasolinera(Parcel in) {
        ideess = in.readInt();
        localidad = in.readString();
        provincia = in.readString();
        direccion = in.readString();
        gasoleoA = in.readDouble();
        gasolina95 = in.readDouble();
        rotulo = in.readString();
        posicion = new Posicion(Double.parseDouble(in.readString().replace(",",".")),Double.parseDouble(in.readString().replace(",",".")));
        setTieneDescuento(false);
        if(rotulo.equals("CEPSA")){
            setTieneDescuento(true);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ideess);
        dest.writeString(localidad);
        dest.writeString(provincia);
        dest.writeString(direccion);
        dest.writeDouble(gasoleoA);
        dest.writeDouble(gasolina95);
        dest.writeString(rotulo);
        dest.writeString(String.valueOf(posicion.getLatitud()));
        dest.writeString(String.valueOf(posicion.getLongitud()));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Gasolinera> CREATOR = new Parcelable.Creator<Gasolinera>() {
        @Override
        public Gasolinera createFromParcel(Parcel in) {
            return new Gasolinera(in);
        }

        @Override
        public Gasolinera[] newArray(int size) {
            return new Gasolinera[size];
        }
    };



}
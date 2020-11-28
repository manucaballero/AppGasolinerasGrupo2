package com.isunican.proyectobase.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Descuento implements Parcelable {

    private String codigo;
    private String descripcion;
    private int porcentaje;

    public Descuento(String codigo, String descripcion, int porcentaje){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() { return this.descripcion; }
    public void setDescripcion(String descripcion)  { this.descripcion = descripcion; }

    public int getPorcentaje() { return porcentaje; }
    public void setPorcentaje(int porcentaje) { this.porcentaje = porcentaje; }

    public String getCodigo() { return this.codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    /**
     * interfaz Parcelable
     *
     * Métodos necesarios para implementar la interfaz Parcelable
     * que nos permitirá pasar objetos del tipo Descuento
     * directamente entre actividades utilizando intents
     * Se enviarían utilizando putExtra
     * myIntent.putExtra("id", objeto descuento);
     * y recibiéndolos con
     * Descuento d = getIntent().getExtras().getParcelable("id")
     */
    protected Descuento(Parcel in) {
        codigo = in.readString();
        descripcion = in.readString();
        porcentaje = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo);
        dest.writeString(descripcion);
        dest.writeDouble(porcentaje);
    }

    public static final Parcelable.Creator<Descuento> CREATOR = new Parcelable.Creator<Descuento>() {
        @Override
        public Descuento createFromParcel(Parcel in) {
            return new Descuento(in);
        }

        @Override
        public Descuento[] newArray(int size) { return new Descuento[size]; }
    };

    @Override
    public boolean equals(Object o){
        boolean aux = false;

        if(o==this){
            aux = true;
        }
        if(!(o instanceof Descuento)){
            aux = false;
        }
        if (o != null && ((Descuento) o).codigo.equals(this.codigo)
                && ((Descuento) o).descripcion.equals(this.descripcion)
                && ((Descuento) o).porcentaje==this.porcentaje) {

            aux = true;
        }
        return aux;

    }

    @Override
    public int hashCode(){
        return -1;
    }
}

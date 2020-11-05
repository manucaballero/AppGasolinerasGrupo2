package com.isunican.proyectobase.Model;

public class Posicion {
    private double Latitud;
    private double Longitud;
    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }


    public Posicion(double latitud, double longitud)
    {
        Latitud = latitud;
        Longitud = longitud;
    }
}

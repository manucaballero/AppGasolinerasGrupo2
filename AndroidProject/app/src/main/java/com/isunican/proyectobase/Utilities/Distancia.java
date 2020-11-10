package com.isunican.proyectobase.Utilities;

import com.isunican.proyectobase.Model.Posicion;

public class Distancia {
    private static final  double RADIO_TIERRA_KM = 6378.0F;

    private Distancia(){}

    public static double distanciaKm(Posicion posOrigen, Posicion posDestino) {
        double difLatitud = Math.toRadians((posDestino.getLatitud() - posOrigen.getLatitud()));
        double difLongitud = Math.toRadians((posDestino.getLongitud() -posOrigen.getLongitud()));


        double a = Math.pow(Math.sin(difLatitud/2), 2) +
                Math.cos(Math.toRadians(posOrigen.getLatitud()))*
                        Math.cos(Math.toRadians(posDestino.getLatitud()))*
                        Math.sin(Math.pow(difLongitud/2, 2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA_KM *c;
    }
}

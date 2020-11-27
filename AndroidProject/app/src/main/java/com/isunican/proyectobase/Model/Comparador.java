package com.isunican.proyectobase.Model;

import java.util.Comparator;

/**
 * Clase para comparar dos gasolineras en función del precio del diesel
 */
class Comparador implements Comparator<Gasolinera> {

    private String combustible;

    public Comparador(String comb){ this.combustible=comb; }

    @Override
    public int compare(Gasolinera g1, Gasolinera g2) {

        double resta=0;

        if(combustible.equals("Gasolina95"))
            resta = g1.getGasolina95() - g2.getGasolina95();
        else if(combustible.equals("GasoleoA"))
            resta = g1.getGasoleoAConDescuento() - g2.getGasoleoAConDescuento();

        if (resta == 0) {
            if (!g1.getTieneDescuento() && !g2.getTieneDescuento()) {
                return 0;
            } else if (g1.getTieneDescuento()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if (resta < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

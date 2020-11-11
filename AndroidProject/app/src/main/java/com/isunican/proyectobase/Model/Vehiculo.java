package com.isunican.proyectobase.Model;

public class Vehiculo {
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
}

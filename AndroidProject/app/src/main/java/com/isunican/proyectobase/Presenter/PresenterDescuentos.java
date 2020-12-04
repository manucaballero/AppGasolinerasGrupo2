package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Descuento;

import java.util.ArrayList;
import java.util.List;

public class PresenterDescuentos {
    private List<Descuento> listDescuentos;


    public PresenterDescuentos(){
        listDescuentos = new ArrayList<>();
    }




    public List<Descuento> getDescuentos(){
        return listDescuentos;
    }
    public boolean cargaDatosDummy(){
        this.listDescuentos.add(new Descuento("codigo1", "Se aplicará un descuento del 10%", 10));
        this.listDescuentos.add(new Descuento("codigo2", "Se aplicará un descuento del 15%", 15));
        this.listDescuentos.add(new Descuento("codigo3", "Se aplicará un descuento del 20%", 20));
        this.listDescuentos.add(new Descuento("codigo4", "Se aplicará un descuento del 25%", 25));
        this.listDescuentos.add(new Descuento("codigo5", "Se aplicará un descuento del 30%", 30));
        return true;
    }
}

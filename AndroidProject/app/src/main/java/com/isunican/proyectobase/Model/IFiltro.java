package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

public interface IFiltro {
    String nombre=null;
    void ordena(PresenterGasolineras presenterGasolineras);
    public String getNombre();

}

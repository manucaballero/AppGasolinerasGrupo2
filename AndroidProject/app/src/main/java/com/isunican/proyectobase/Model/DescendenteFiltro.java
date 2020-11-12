package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

public class DescendenteFiltro implements IPrecioFiltro {

    String nombre = "descendente";

    @Override
    public void ordena(PresenterGasolineras presenterGasolineras) {

    }

    @Override
    public String getNombre() {
        return nombre;
    }
}

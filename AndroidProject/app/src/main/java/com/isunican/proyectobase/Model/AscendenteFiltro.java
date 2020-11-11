package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

public class AscendenteFiltro implements IPrecioFiltro {


    String nombre = "ascendente";
    @Override
    public void ordena(PresenterGasolineras presenterGasolineras) {

    }

    @Override
    public String getNombre() {
        return nombre;
    }
}



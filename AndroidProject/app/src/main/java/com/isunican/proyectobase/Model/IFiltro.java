package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

/**
 * Interfaz genérica compartida por todos los filtros, define los métodos a implementar por el resto
 */
public interface IFiltro {
    String nombre=null;
    void ordena(PresenterGasolineras presenterGasolineras);
    public String getNombre();

}

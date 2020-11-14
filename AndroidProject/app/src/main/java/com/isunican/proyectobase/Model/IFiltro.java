package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import java.util.List;

/**
 * Interfaz genérica compartida por todos los filtros, define los métodos a implementar por el resto
 */
public interface IFiltro {
    String nombre=null;
    void ordena(List<Gasolinera> listaGasolineras);
    public String getNombre();

}

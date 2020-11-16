package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Vehiculo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Clase que compruba el funcionamiento de los métodos de la clase presenter.
 */
public class presenterVehiculosTest {

    PresenterVehiculos presenterVehiculos;
    Vehiculo v1;
    List<Vehiculo> listaVehiculos;

    /**
     * Inicializamos los atributos necesarios antes de la ejecución de cada método.
     */
    @Before
    public void setUp(){
        presenterVehiculos = new PresenterVehiculos();
        presenterVehiculos.cargaDatosVehiculosDummy();
        listaVehiculos = presenterVehiculos.getVehiculos();
        v1=listaVehiculos.get(0);
    }

    /**
     * Comprueba el funcionamiento del método getVehiculoSeleccionado y setVehiculoSeleccionado.
     * Que obtiene bien el vehiculo seleccionado y que le asigan bien.
     */
    @Test
    public void getSetVehiculoSeleccionadoTest(){
        presenterVehiculos.setVehiculoSeleccionado(v1);
        Assert.assertTrue(PresenterVehiculos.getVehiculoSeleccionado().getModelo()==v1.getModelo());

    }

    /**
     * Comprueba el funcionamiento del método getVehiculos. Es decir que devuelve bien la lista de vehiculos actual.
     */
    @Test
    public void getVehiculosTest(){
        Assert.assertTrue(listaVehiculos.size() == 2);
    }

}

package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Vehiculo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que compruba el funcionamiento de los métodos de la clase presenter.
 *
 * @author Rubén Calleja
 */
public class presenterVehiculosTest {

    PresenterVehiculos sut;
    Vehiculo v1;
    List<Vehiculo> listaVehiculos;

    /**
     * Inicializamos los atributos necesarios antes de la ejecución de cada método.
     */
    @Before
    public void setUp(){

        sut = new PresenterVehiculos();
        listaVehiculos = new ArrayList<Vehiculo>();
        listaVehiculos.add(new Vehiculo("BMW m8"));
        listaVehiculos.add(new Vehiculo("BMW m3"));
    }

    /**
     * Comprueba el funcionamiento del método getVehiculoSeleccionado y setVehiculoSeleccionado.
     * Que obtiene bien el vehiculo seleccionado y que le asigan bien.
     */
    @Test
    public void getSetVehiculoSeleccionadoTest(){
        sut.setVehiculoSeleccionado(null);
        Assert.assertNull(sut.getVehiculoSeleccionado());
        v1=listaVehiculos.get(0);
        sut.setVehiculoSeleccionado(v1);
        Assert.assertSame(sut.getVehiculoSeleccionado().getModelo(), v1.getModelo());

    }

    /**
     * Comprueba el funcionamiento del método getVehiculos. Es decir que devuelve bien la lista de vehiculos actual.
     */
    @Test
    public void getVehiculosTest(){
        Assert.assertEquals(2, listaVehiculos.size());
    }

}

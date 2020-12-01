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
public class PresenterVehiculosTest {

    PresenterVehiculos sut;
    Vehiculo v1;
    Vehiculo v2;
    List<Vehiculo> listaVehiculos;

    /**
     * Inicializamos los atributos necesarios antes de la ejecución de cada método.
     */
    @Before
    public void setUp(){
        sut = new PresenterVehiculos();
        listaVehiculos = new ArrayList<Vehiculo>();
        listaVehiculos.add(new Vehiculo("BMW m3"));
        listaVehiculos.add(new Vehiculo("Tesla model S"));
    }

    /**
     * Comprueba el funcionamiento del método getVehiculoSeleccionado y setVehiculoSeleccionado.
     * Que obtiene bien el vehiculo seleccionado y que le asigan bien.
     */
    @Test
    public void getSetVehiculoSeleccionadoTest(){
        //Comprobamos con el vehiculo seleccionado a null
        sut.setVehiculoSeleccionado(null);
        Assert.assertNull(sut.getVehiculoSeleccionado());

        //Comprobamos con el primer vehículo
        v1=listaVehiculos.get(0);
        sut.setVehiculoSeleccionado(v1);
        Assert.assertEquals(sut.getVehiculoSeleccionado().getModelo(), v1.getModelo());

        //Comprobamos con el segundo vehículo
        v2=listaVehiculos.get(1);
        sut.setVehiculoSeleccionado(v2);
        Assert.assertEquals(sut.getVehiculoSeleccionado().getModelo(), v2.getModelo());


    }

    /**
     * Comprueba el funcionamiento del método getVehiculos. Es decir que devuelve bien la lista de vehiculos actual.
     */
    @Test
    public void getVehiculosTest(){

        //Porque al crear el presenter siemre tiene uno por defecto
        Assert.assertEquals(1, sut.getVehiculos().size());

        //Comprobamos a añadir uno más
        sut.getVehiculos().add(new Vehiculo("BMW m3"));
        Assert.assertEquals(2, sut.getVehiculos().size());

        //Comprobamos añadir varios a la vez
        sut.getVehiculos().add(new Vehiculo("BMW m4"));
        sut.getVehiculos().add(new Vehiculo("BMW x5"));
        sut.getVehiculos().add(new Vehiculo("BMW m8"));
        Assert.assertEquals(5, sut.getVehiculos().size());

        //Comprobamos a eliminar todos
        sut.getVehiculos().clear();
        Assert.assertEquals(0, sut.getVehiculos().size());

    }

}

package com.isunican.proyectobase;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.Views.FormActivity;
import com.isunican.proyectobase.Views.MisVehiculosActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Comprobamos el correcto funcionamiento del método de añadir vehiculos.
 * @author Daniel Sánchez
 */
@RunWith(JUnit4.class)
public class AnhadeVehiculoITest {

    @Rule
    public ActivityTestRule<FormActivity> formularioActivityTestRule = new ActivityTestRule<>(FormActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    PresenterVehiculos pv;

    /**
     * Inicializamos las variables necesarias
     */
    @Before
    public void setUp(){
        pv = formularioActivityTestRule.getActivity().presenterVehiculos;
    }


    @Test
    public void anhadeVehiculoTest(){

        int exitCode=0;

        //Añade dos vehiculos distintos
        exitCode = formularioActivityTestRule.getActivity().anhadeVehiculo
                ("Modelo1","GasoleoA", "Nuevo", "50", "5");
        Assert.assertEquals(0, exitCode);

        exitCode = formularioActivityTestRule.getActivity().anhadeVehiculo
                ("Modelo2","GasoleoA", "Nuevo", "50", "5");
        Assert.assertEquals(0, exitCode);
        Assert.assertEquals(3, pv.getVehiculos().size());

        //Añade un vehiculo con mismo modelo pero diferente anotacion
        exitCode = formularioActivityTestRule.getActivity().anhadeVehiculo
                ("Modelo2","GasoleoA", "Nota", "50", "5");
        Assert.assertEquals(0, exitCode);
        Assert.assertEquals(4, pv.getVehiculos().size());

        //Añade vehiculo identico al anterior
        exitCode = formularioActivityTestRule.getActivity().anhadeVehiculo
                ("Modelo2","Gasolina95", "Nota", "50", "5");
        Assert.assertEquals(1, exitCode);
        Assert.assertEquals(4, pv.getVehiculos().size());

        //Añade vehiculo con los campos vacios
        exitCode = formularioActivityTestRule.getActivity().anhadeVehiculo
                ("","", "", "", "");
        Assert.assertEquals(2, exitCode);
        Assert.assertEquals(4, pv.getVehiculos().size());

    }

}

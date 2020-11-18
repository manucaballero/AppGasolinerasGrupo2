package com.isunican.proyectobase;

import android.widget.ListAdapter;

import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.Views.FormActivity;
import com.isunican.proyectobase.Views.MisVehiculosActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class AnhadirVehiculoITest {

    @Rule
    public ActivityTestRule<MisVehiculosActivity> mActivityTestRule = new ActivityTestRule<>(MisVehiculosActivity.class);

    ListAdapter adapter = mActivityTestRule.getActivity().adapter;
    PresenterVehiculos pv;
    Vehiculo v1;
    Vehiculo v2;
    Vehiculo v3;
    Vehiculo v4;

    @Before
    public void setUp(){
        pv=new PresenterVehiculos();

        v1=new Vehiculo("BMW m8");
        v1.setMatricula("1234ABC");
        v1.setDeposito(68);
        v1.setConsumoMedio(11);
        v1.setAnotaciones("625cv");

        v2=new Vehiculo("BMW m3");
        v2.setDeposito(50);
        v2.setMatricula("4321DEF");
        v2.setConsumoMedio(8);

        v3=new Vehiculo("BMW m3");
        v3.setMatricula("123");
        v3.setConsumoMedio(8);
        v3.setDeposito(50);

        v4=new Vehiculo("BMW i8");
        v4.setMatricula("1234ABC");

    }

    @Test
    public void anhadeVehiculoTest(){
        assertTrue(pv.guardaVehiculo(v1));
        assertTrue(pv.guardaVehiculo(v2));
        assertFalse(pv.guardaVehiculo(v3));
        assertFalse(pv.guardaVehiculo(v4));

        pv.cargaDatosVehiculos();

        List<Vehiculo> vehiculos=pv.getVehiculos();

        Vehiculo vehiculo1;
        Vehiculo vehiculo2;

        vehiculo1= (Vehiculo) adapter.getItem(1);
        vehiculo2= (Vehiculo) adapter.getItem(2);


        assertEquals(vehiculo1.getModelo(),"BMW m8");
        assertEquals(vehiculo1.getMatricula(),"1234ABC");
        assertEquals(vehiculo1.getDeposito(),68);
        assertEquals(vehiculo1.getConsumoMedio(),8);
        assertEquals(vehiculo1.getAnotaciones(),"625cv");

        assertEquals(vehiculo2.getModelo(),"BMW m3");
        assertEquals(vehiculo2.getMatricula(),"4321DEF");
        assertEquals(vehiculo1.getDeposito(),50);
        assertEquals(vehiculo2.getConsumoMedio(),8);


    }
}

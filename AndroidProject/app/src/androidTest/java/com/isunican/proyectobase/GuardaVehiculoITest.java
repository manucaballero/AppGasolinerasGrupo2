package com.isunican.proyectobase;

import android.widget.ListAdapter;

import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.Views.MisVehiculosActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class GuardaVehiculoITest {

    @Rule
    public ActivityTestRule<MisVehiculosActivity> vehiculosActivityTestRule = new ActivityTestRule<>(MisVehiculosActivity.class);

    ListAdapter adapter;
    PresenterVehiculos pv;
    Vehiculo v1;
    Vehiculo v2;

    /**
     * Se crean los objetos necesarios
     */
    @Before
    public void setUp(){
        adapter = vehiculosActivityTestRule.getActivity().adapter;
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
        v2.setAnotaciones("Nota");

    }

    /**
     * Se comprueba que los vehiculos se guardan correctamente, y que,
     * poseriormente se cargan en la lista de vehiculos de forma correcta,
     * con los datos de los vehiculos esperados.
     */
    @Test
    public void guardaVehiculoTest(){

        //Se guardan en el fichero los vehiculos creados.
        assertTrue(pv.guardaVehiculo(v1, vehiculosActivityTestRule.getActivity().getBaseContext()));
        assertTrue(pv.guardaVehiculo(v2, vehiculosActivityTestRule.getActivity().getBaseContext()));

        //Se cargan del fichero
        pv.cargaDatosVehiculos(vehiculosActivityTestRule.getActivity().getBaseContext());
        pv.cargaVehiculoSeleccionado(vehiculosActivityTestRule.getActivity().getBaseContext());

        Vehiculo vehiculo1;
        Vehiculo vehiculo2;

        //Se obtienen los vehiculos del adapter (en la posicion 0 se encuentra un vehiculo introducido de ejemplo)
        vehiculo1= (Vehiculo) adapter.getItem(1);
        vehiculo2= (Vehiculo) adapter.getItem(2);

        //Se comprueba que los datos obtenidos corresponten con los esperados
        //Vehiculo 1

        assertEquals("BMW m8", vehiculo1.getModelo());
        assertEquals("1234ABC", vehiculo1.getMatricula());
        assertEquals(68, vehiculo1.getDeposito(),0);
        assertEquals(11, vehiculo1.getConsumoMedio(), 0);
        assertEquals("625cv", vehiculo1.getAnotaciones());
        //Vehiculo 2
        assertEquals("BMW m3", vehiculo2.getModelo());
        assertEquals("4321DEF", vehiculo2.getMatricula());
        assertEquals(50, vehiculo2.getDeposito(),0);
        assertEquals(8, vehiculo2.getConsumoMedio(),0);




    }
}
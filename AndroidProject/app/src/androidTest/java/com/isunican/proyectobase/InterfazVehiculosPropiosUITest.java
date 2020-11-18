package com.isunican.proyectobase;

import android.Manifest;
import android.widget.ArrayAdapter;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Views.MisVehiculosActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class InterfazVehiculosPropiosUITest {
    @Rule
    public ActivityTestRule<MisVehiculosActivity> mVehiculosActivity = new ActivityTestRule<>(MisVehiculosActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);
    @Test
    public void VehiculosPropiosUITest(){

        ArrayAdapter<Vehiculo> adapter = mVehiculosActivity.getActivity().adapter;

        for(int i=0; i<adapter.getCount(); i++){
            DataInteraction d = onData(anything()).inAdapterView(withId(R.id.listViewVehiculos)).atPosition(i);
            Vehiculo v = adapter.getItem(i);
            //Revisamos que el modelo y las anotaciones concuerden con las de la lista de vehiculos
            d.onChildView(withId(R.id.textViewModelo)).check(matches(withText(v.getModelo())));
            d.onChildView(withId(R.id.textViewAnotacion)).check(matches(withText(v.getAnotaciones())));
            //Consumo medio y deposito no aparecen en esta interfaz
        }
        Vehiculo vehiculo1 = adapter.getItem(0);
        //Revisamos la matricula y si esta seleccionado el primer vehiculo de la lista
        DataInteraction d = onData(anything()).inAdapterView(withId(R.id.listViewVehiculos)).atPosition(0);
        d.onChildView(withId(R.id.textViewMatricula)).check(matches(withText(vehiculo1.getMatricula())));
        d.onChildView(withId(R.id.textViewSeleccionado)).check(matches(withText("Seleccionado")));

        //Revisamos la matricula y si esta seleccionado el segundo vehiculo de la lista, ambos deben de estar invisible
        //ya que no tiene rellenada la matricula y no esta seleccionado.
        //Este vehiculo finalmente no aparece
        //d = onData(anything()).inAdapterView(withId(R.id.listViewVehiculos)).atPosition(1);
        //d.onChildView(withId(R.id.textViewMatricula)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        //d.onChildView(withId(R.id.textViewSeleccionado)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

}

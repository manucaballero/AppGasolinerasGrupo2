package com.isunican.proyectobase;

import android.Manifest;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
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

/**
 * @author Manuel Caballero, modificado por Daniel Sánchez
 */
//@RunWith(AndroidJUnit4.class)
public class InterfazVehiculosPropiosUITest {
    @Rule
    public ActivityTestRule<MisVehiculosActivity> mVehiculosActivity = new ActivityTestRule<>(MisVehiculosActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);
    /*
    @Test
    public void VehiculosPropiosUITest(){

        ArrayAdapter<Vehiculo> adapter = mVehiculosActivity.getActivity().adapter;
        Vehiculo seleccionado = PresenterVehiculos.getVehiculoSeleccionado();
        Vehiculo v;
        DataInteraction d;
        for(int i=0; i<adapter.getCount(); i++){
            d = onData(anything()).inAdapterView(withId(R.id.listViewVehiculos)).atPosition(i);
            v = adapter.getItem(i);

            //Revisamos que el modelo, las anotaciones y la matricula concuerden con las de la lista de vehiculos
            d.onChildView(withId(R.id.textViewModelo)).check(matches(withText(v.getModelo())));
            d.onChildView(withId(R.id.textViewAnotacion)).check(matches(withText(v.getAnotaciones())));
            d.onChildView(withId(R.id.textViewCombustible)).check(matches(withText(v.getCombustible())));

            
            //Si el vehiculo actual es el seleccionado revisamos que este dato aparezca en la interfaz
            if(v.equals(seleccionado)){
                d.onChildView(withId(R.id.textViewSeleccionado)).check(matches(withText("Seleccionado")));
            }else{
                d.onChildView(withId(R.id.textViewSeleccionado)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
            }
        }
    }
     */
}

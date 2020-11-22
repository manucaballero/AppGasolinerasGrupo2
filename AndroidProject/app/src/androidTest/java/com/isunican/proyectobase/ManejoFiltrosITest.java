package com.isunican.proyectobase;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Model.Gasolina95Filtro;
import com.isunican.proyectobase.Model.IFiltro;
import com.isunican.proyectobase.Model.SinDescuentoFiltro;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * @author Miguel Casamichana
 */
@RunWith(AndroidJUnit4.class)
public class ManejoFiltrosITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    /*
        @author Miguel Casamichana Bolado

        Prueba de interfaz en la que se comprueba que se muestran correctamente por pantalla
        el nombre de los filtros activos deseados.
     */
    @Test
    public void mostrarFiltrosActivosTest() {

        List<IFiltro> lista = mActivityTestRule.getActivity().listaFiltros;

        for(int i = 0; i< lista.size(); i++){
            IFiltro filtro = lista.get(i);
            DataInteraction d = onData(anything()).inAdapterView(withId(R.id.recyclerViewFiltros)).atPosition(i);
            d.onChildView(withId(R.id.txtNombreFiltro)).check(matches(withText(filtro.getNombre())));
        }

    }

    /**
     * Comprobamos que el botón reset de los filtros funciona correctamente,
     * para ello le pulsamos sin ningún filtro y comprobamos que la lista de filtros añadidos esta vacía,
     * también comprobamos que tras añadir varios filtros si le pulsamos, la lista queda vacía.
     *
     * @author Ruben Calleja
     *
     */

/*
>>>>>>> 124c8e20efd2ef798376006956ab35e7c2e1323a
    @Test
    public void botonResetTest(){
        List<IFiltro> lista = mActivityTestRule.getActivity().listaFiltros;

        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.buttonReset)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        Assert.assertTrue(lista.size()==0);

        lista.add(new SinDescuentoFiltro());
        lista.add(new Gasolina95Filtro());
        onView(withId(R.id.buttonReset)).perform(click());
        Assert.assertTrue(lista.size()==0);

    }

     */


}
package com.isunican.proyectobase;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.AdapterDataLoaderAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class OrdenaListaITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    double precio1, precio2, precio3;


    @Test
    public void useAppContext() {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {}

        ListAdapter adapter = mActivityTestRule.getActivity().adapter;

        Gasolinera g1 = (Gasolinera) adapter.getItem(0);
        Gasolinera g2 = (Gasolinera) adapter.getItem(1);
        Gasolinera g3 = (Gasolinera) adapter.getItem(2);

        //Se comprueban los datos en la interfaz de la primera gasolinera (ordenada por precio)
        //onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0).perform(click());
        DataInteraction v1 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0);
        //v1.onChildView(withId(R.id.txtLocalidad)).check(matches(withText(g1.getLocalidad())));
        v1.onChildView(withId(R.id.textViewDireccion)).check(matches(withText(g1.getDireccion())));
        v1.onChildView(withId(R.id.textViewRotulo)).check(matches(withText(g1.getRotulo())));
        v1.onChildView(withId(R.id.textViewGasoleoA)).check(matches(withText(Double.toString(g1.getGasoleoA())+"€")));
        v1.onChildView(withId(R.id.textViewGasolina95)).check(matches(withText(Double.toString(g1.getGasolina95())+"€")));
        //Espresso.pressBack();

        //Se comprueban los datos en la interfaz de la segunda gasolinera (ordenada por precio)
        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(1);
        onView(withId(R.id.txtLocalidad)).check(matches(withText(g2.getLocalidad())));
        onView(withId(R.id.txtDireccion)).check(matches(withText(g2.getDireccion())));
        onView(withId(R.id.txtNombre)).check(matches(withText(g2.getRotulo())));
        onView(withId(R.id.txtPrecioDiesel)).check(matches(withText(Double.toString(g2.getGasoleoA()))));
        onView(withId(R.id.txtPrecioGasolina95)).check(matches(withText(Double.toString(g2.getGasolina95()))));
        //Espresso.pressBack();

        //Se comprueban los datos en la interfaz de la tercera gasolinera (ordenada por precio)
        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(2);
        onView(withId(R.id.txtLocalidad)).check(matches(withText(g3.getLocalidad())));
        onView(withId(R.id.txtDireccion)).check(matches(withText(g3.getDireccion())));
        onView(withId(R.id.txtNombre)).check(matches(withText(g3.getRotulo())));
        onView(withId(R.id.txtPrecioDiesel)).check(matches(withText(Double.toString(g3.getGasoleoA()))));
        onView(withId(R.id.txtPrecioGasolina95)).check(matches(withText(Double.toString(g3.getGasolina95()))));

        Assert.assertTrue(g1.getGasoleoA() < g2.getGasoleoA());
        Assert.assertTrue(g2.getGasoleoA() < g3.getGasoleoA());



    }
}

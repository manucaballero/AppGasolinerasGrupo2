package com.isunican.proyectobase;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ExecutionList;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalcularGasolineraMasBarataITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void detalleITest() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
        ListAdapter adapter = mActivityTestRule.getActivity().adapter;
        Gasolinera g = (Gasolinera) adapter.getItem(0);

        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0).perform(click());
        onView(withId(R.id.txtLocalidad)).check(matches(withText(g.getLocalidad())));
        onView(withId(R.id.txtDireccion)).check(matches(withText(g.getDireccion())));
        onView(withId(R.id.txtNombre)).check(matches(withText(g.getRotulo())));
        onView(withId(R.id.txtPrecioDiesel)).check(matches(withText(Double.toString(g.getGasoleoA()))));
        onView(withId(R.id.txtPrecioGasolina95)).check(matches(withText(Double.toString(g.getGasolina95()))));
    }

    @Test
    public void ordenaListaITest(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {}

        ListAdapter adapter = mActivityTestRule.getActivity().adapter;

        Gasolinera g1 = (Gasolinera) adapter.getItem(0);
        Gasolinera g2 = (Gasolinera) adapter.getItem(1);
        Gasolinera g3 = (Gasolinera) adapter.getItem(2);


        //Se comprueban los datos en la interfaz de la primera gasolinera (ordenada por precio)
        DataInteraction v1 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0);
        v1.onChildView(withId(R.id.textViewDireccion)).check(matches(withText(g1.getDireccion())));
        v1.onChildView(withId(R.id.textViewRotulo)).check(matches(withText(g1.getRotulo())));
        v1.onChildView(withId(R.id.textViewGasoleoA)).check(matches(withText(" "+Double.toString(g1.getGasoleoA())+"€")));
        v1.onChildView(withId(R.id.textViewGasolina95)).check(matches(withText(" "+Double.toString(g1.getGasolina95())+"€")));


        //Se comprueban los datos en la interfaz de la segunda gasolinera (ordenada por precio)
        DataInteraction v2 = Espresso.onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(1);
        v2.onChildView(withId(R.id.textViewDireccion)).check(matches(withText(g2.getDireccion())));
        v2.onChildView(withId(R.id.textViewRotulo)).check(matches(withText(g2.getRotulo())));
        v2.onChildView(withId(R.id.textViewGasoleoA)).check(matches(withText(" "+Double.toString(g2.getGasoleoA())+"€")));
        v2.onChildView(withId(R.id.textViewGasolina95)).check(matches(withText(" "+Double.toString(g2.getGasolina95())+"€")));


        //Se comprueban los datos en la interfaz de la tercera gasolinera (ordenada por precio)
        DataInteraction v3 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(2);
        v3.onChildView(withId(R.id.textViewDireccion)).check(matches(withText(g3.getDireccion())));
        v3.onChildView(withId(R.id.textViewRotulo)).check(matches(withText(g3.getRotulo())));
        v3.onChildView(withId(R.id.textViewGasoleoA)).check(matches(withText(" "+Double.toString(g3.getGasoleoA())+"€")));
        v3.onChildView(withId(R.id.textViewGasolina95)).check(matches(withText(" "+Double.toString(g3.getGasolina95())+"€")));

        Assert.assertTrue(g1.getGasoleoA() < g2.getGasoleoA());
        Assert.assertTrue(g2.getGasoleoA() < g3.getGasoleoA());

    }
}

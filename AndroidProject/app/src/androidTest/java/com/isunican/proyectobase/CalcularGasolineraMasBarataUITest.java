package com.isunican.proyectobase;

import android.widget.ListAdapter;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalcularGasolineraMasBarataUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    /**
     * Metodo que comprueba que los datos mostrados en las listas de la vista principal
     * son los correctos
     */
    /*

    @Test
    public void compruebaListaUITest(){
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
        v1.onChildView(withId(R.id.textViewGasoleoA)).check(matches(withText(" "+Double.toString(g1.getGasoleoAConDescuento())+"€")));
        v1.onChildView(withId(R.id.textViewGasolina95)).check(matches(withText(" "+Double.toString(g1.getGasolina95ConDescuento())+"€")));

        //Se comprueban los datos en la interfaz de la segunda gasolinera (ordenada por precio)
        DataInteraction v2 = Espresso.onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(1);
        v2.onChildView(withId(R.id.textViewDireccion)).check(matches(withText(g2.getDireccion())));
        v2.onChildView(withId(R.id.textViewRotulo)).check(matches(withText(g2.getRotulo())));
        v2.onChildView(withId(R.id.textViewGasoleoA)).check(matches(withText(" "+Double.toString(g2.getGasoleoAConDescuento())+"€")));
        v2.onChildView(withId(R.id.textViewGasolina95)).check(matches(withText(" "+Double.toString(g2.getGasolina95ConDescuento())+"€")));

        //Se comprueban los datos en la interfaz de la tercera gasolinera (ordenada por precio)
        DataInteraction v3 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(2);
        v3.onChildView(withId(R.id.textViewDireccion)).check(matches(withText(g3.getDireccion())));
        v3.onChildView(withId(R.id.textViewRotulo)).check(matches(withText(g3.getRotulo())));
        v3.onChildView(withId(R.id.textViewGasoleoA)).check(matches(withText(" "+Double.toString(g3.getGasoleoAConDescuento())+"€")));
        v3.onChildView(withId(R.id.textViewGasolina95)).check(matches(withText(" "+Double.toString(g3.getGasolina95ConDescuento())+"€")));

        //Se comprueba que las gasolineras están ordenadas de menor a mayor según el gasóleoA
        Assert.assertTrue(g1.getGasoleoAConDescuento() < g2.getGasoleoAConDescuento());
        Assert.assertTrue(g2.getGasoleoAConDescuento() < g3.getGasoleoAConDescuento());


    }*/

    /*
    @Test
    public void listaCargadaTest() {

        try {
            Thread.sleep(4000);
        } catch (Exception e) {
        }

        ListAdapter adapter = mActivityTestRule.getActivity().adapter;
        
        Assert.assertNotEquals(adapter.getCount(), 0);
    }

     */
}

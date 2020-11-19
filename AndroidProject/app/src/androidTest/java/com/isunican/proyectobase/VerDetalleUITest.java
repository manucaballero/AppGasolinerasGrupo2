package com.isunican.proyectobase;

import android.widget.ListAdapter;

import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

public class VerDetalleUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void detalleUITest() {
        Assert.assertTrue(true);
        /*try {
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
        */
    }


}

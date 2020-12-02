package com.isunican.proyectobase;

import android.Manifest;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DesaplicarFiltrosUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    RecyclerView mRecyclerView;
    MainActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
        mRecyclerView = mActivity.findViewById(R.id.recyclerViewFiltros);
    }

    @Test
    public void desaplicarFiltrosTest(){
        //Se pulsa el botón filtrar
        onView(withId(R.id.buttonFiltrar)).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Se elige filtrar por gasoleo A y con descuentos
        onView(withId(R.id.checkBoxGasoleoA)).perform(click());
        onView(withId(R.id.checkBoxDescuentoSi)).perform(click());
        onView(withId(R.id.buttonApply)).perform(click());

        //Se comprueba que los filtros que se muestran aplicados son 2
        int itemCount = mRecyclerView.getAdapter().getItemCount();
        Assert.assertEquals(itemCount,2);

        //Se compreba que la lista de filtros se muestra
        onView(withId(R.id.recyclerViewFiltros)).check(matches(isDisplayed()));

        //Se pulsa el primero, el que se quiere eliminar
        onView(withId(R.id.recyclerViewFiltros))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Se muestra un popup para confirmar que se quiere eliminar, se pulsa aceptar
        onView(withId(R.id.buttonSiBorrarFiltro)).perform(click());

        //Se comprueba que los filtros que se muestran aplicados son 1
        itemCount = mRecyclerView.getAdapter().getItemCount();
        Assert.assertEquals(itemCount,1);

        //Se comprueba que la lista de filtros se muestra
        onView(withId(R.id.recyclerViewFiltros)).check(matches(isDisplayed()));


        /*
        Ahora compruebo si al pulsar No Borrar en el pop-up, se mantiene el filtro
         */

        //Se pulsa el primero, el que se quiere eliminar
        onView(withId(R.id.recyclerViewFiltros))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Se muestra un popup para confirmar que se quiere eliminar, se pulsa aceptar
        onView(withId(R.id.buttonNoBorrarFiltro)).perform(click());

        //Se comprueba que los filtros que se muestran aplicados son 1 (la misma cantidad que antes)
        itemCount = mRecyclerView.getAdapter().getItemCount();
        Assert.assertEquals(itemCount,1);

        //Se comprueba que la lista de filtros se muestra
        onView(withId(R.id.recyclerViewFiltros)).check(matches(isDisplayed()));
    }
}





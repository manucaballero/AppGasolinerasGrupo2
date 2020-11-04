package com.isunican.proyectobase;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;

import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class ResaltarDescuentosTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    
    @Test
    public void resaltarTest(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
        ListAdapter adapter = mActivityTestRule.getActivity().adapter;
        int i = 0;
        int j = 0;
        //Buscamos una gasolinera con descuento
        while((Gasolinera) !adapter.getItem(i).tieneDescuento()){
            i++;
        }
        Gasolinera gDescuento = (Gasolinera) adapter.getItem(i);
        //Buscamos otra gasolinera sin descuento
        while((Gasolinera) adapter.getItem(i).tieneDescuento()){
            i++;
        }
        Gasolinera gSinDescuento = (Gasolinera) adapter.getItem(j);

        DataInteraction v1 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i);
        DataInteraction v2 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(j);

        v1.onChildView(withId(R.id.txtPrecioGasolina95).matches(withId(R.id.listViewGasolineras).)
        v1.onChildView(withId(R.id.textViewRotulo)).check(matches(withText(g1.getRotulo())));

        Assert.assertTrue();
        Assert.assertTrue();
        Assert.assertTrue();
        Assert.assertTrue();





    }
}

package com.isunican.proyectobase;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        /*ListView lv = (ListView) mActivityTestRule.getActivity().findViewById(R.id.listViewGasolineras);
        ListAdapter adapter = lv.getAdapter();*/

        //ListView lv = mActivityTestRule.getActivity().listViewGasolineras;
        //ListAdapter adapter = mActivityTestRule.getActivity().adapter;

        //ListAdapter adapter = mActivityTestRule.getActivity().adapter;  //poner adapter publico?

        PresenterGasolineras presenterGasolineras = mActivityTestRule.getActivity().presenterGasolineras;
        List<Gasolinera> lista = presenterGasolineras.getGasolineras();

        int tamnaho = lista.size();

        Assert.assertTrue(tamnaho == 0);
        /*
        for (int i = 0; i < lista.size(); i++) {
            Log.d("barbara", lista.get(i).getProvincia());
        }

         */

        /*
        Gasolinera g1 = presenterGasolineras.getGasolineras().get(0);
        Gasolinera g2 = presenterGasolineras.getGasolineras().get(1);
        Gasolinera g3 = presenterGasolineras.getGasolineras().get(2);

         /*
        Gasolinera g1 = (Gasolinera) adapter.getItem(0);
        Gasolinera g2 = (Gasolinera) adapter.getItem(1);
        Gasolinera g3 = (Gasolinera) adapter.getItem(2);
          */

        /*
        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0).perform(click());
        precio1 = R.id.txtPrecioDIesel;
        Espresso.pressBack();
        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(1).perform(click());
        precio2 = R.id.txtPrecioDIesel;
        Espresso.pressBack();
        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(2).perform(click());
        precio3 = R.id.txtPrecioDIesel;
         */
        /*
        Assert.assertTrue(g1.getGasoleoA() < g2.getGasoleoA());
        Assert.assertTrue(g2.getGasoleoA() < g3.getGasoleoA());
        */


    }
}

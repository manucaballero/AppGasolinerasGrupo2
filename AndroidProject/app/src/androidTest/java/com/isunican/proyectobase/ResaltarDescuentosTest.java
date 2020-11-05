package com.isunican.proyectobase;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Checks;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class ResaltarDescuentosTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    /*
    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    @Test
    public void internetTest(){
        Assert.assertEquals(netIsAvailable(),true);

        try {
            Assert.assertEquals(netIsAvailable(),false);
            Assert.fail("Error de conexión");
        }catch(Exception e){}
    }*/

    /*
        Se comprueba que la lista se cargue correctamente en el presenter
     */
    @Test
    public void listaCargadaTest(){
        PresenterGasolineras presenterGasolineras = new PresenterGasolineras();
        presenterGasolineras.cargaDatosGasolineras();
        Assert.assertNotEquals(presenterGasolineras.getGasolineras().size(),0);
    }

    /*
        Se comprueba que se muestre por la interfaz el color esperado de background de cada view y el color
        de letra esperado de los precios de cada una de las views que correspondan a las gasolineras con
        descuentos.
     */
    @Test
    public void resaltarTest() {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
        ListAdapter adapter = mActivityTestRule.getActivity().adapter;
        //Obtenemos la lista de vistas
        ListView lv = mActivityTestRule.getActivity().findViewById(R.id.listViewGasolineras);

        int i = 0;
        int j = 2;
        //Cogemos las vistas que queremos (correspondientes a cada elemento de la listview de la interfaz principal
        View v1 = lv.getChildAt(i);
        View v2 = lv.getChildAt(j);
        //Obtenemos los colores de background
        ColorDrawable cBck1 = (ColorDrawable) v1.getBackground();
        ColorDrawable cBck2 = (ColorDrawable) v2.getBackground();
        //Comparamos con los valores esperados
        Assert.assertEquals(cBck1.getColor(), 0xfffffd82);
        Assert.assertEquals(cBck2.getColor(), Color.WHITE);
        //Obtenemos las textview que queremos observar
        TextView gasolinaCon = v1.findViewById(R.id.textViewGasolina95);
        TextView gasoleoCon = v1.findViewById(R.id.textViewGasoleoA);
        TextView gasolinaSin = v2.findViewById(R.id.textViewGasolina95);
        TextView gasoleoSin = v2.findViewById(R.id.textViewGasoleoA);
        //Comparamos con los valores esperados
        Assert.assertEquals(gasolinaCon.getCurrentTextColor(),Color.RED);
        Assert.assertEquals(gasoleoCon.getCurrentTextColor(),Color.RED);
        Assert.assertEquals(gasolinaSin.getCurrentTextColor(),Color.BLACK);
        Assert.assertEquals(gasoleoSin.getCurrentTextColor(),Color.BLACK);

    }
}

package com.isunican.proyectobase;

import android.Manifest;

import android.content.Intent;
import android.widget.ListAdapter;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;


import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Views.MainActivity;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Probamos la interfaz ver descuentos
 *
 * @author Rubén Calleja
 */
public class BotonVerDescuentoUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    private Boolean encontrada;
    private int i;
    private ListAdapter adapter;
    Gasolinera g;

    @Before
    public void setUp() {
        encontrada = false;
        i = 0;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        //Eliminamos el pop-up de añadir vehiculo por primera vez
        if (mActivityTestRule.getActivity().myIntentPop != null) {
            onView(withId(R.id.buttonMasTarde)).check(matches(isDisplayed()));
            onView(withId(R.id.buttonMasTarde)).perform(click());
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
        adapter = mActivityTestRule.getActivity().adapter;
        g = null;
    }

    /**
     * Comprobamos que el boton de mostrar descuento no se muestra en las gasolineras que no tienen descuento.
     */
    @Test
    public void botonVerDescuentoGasolineraSinDescuentoUITest() {
        //Recorremos las gasolineras hasta encontrar una que no tenga descuento para probar que está el botón.
        while (i < adapter.getCount() && !encontrada) {
            g = (Gasolinera) adapter.getItem(i);
            if (!g.getTieneDescuento()) {
                //Comprobamos que el botón ver descuento esta gone, es decir que no se ve.
                onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i).perform(click());
                onView(withId(R.id.buttonVerDescuento)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
                encontrada = true;
            }
            i++;
        }

    }

    /**
     * Comprobamos que el boton de mostrar descuento se muestra en las gasolineras que tienen descuento
     * y además comprobamos que al pulsar el botón se muestre el pop-up y que el codigo de descuento mostrado
     * es el adecuado.
     */
    @Test
    public void botonVerDescuentoGasolineraConDescuentoUITest() {
        //Recorremos las gasolineras hasta encontrar una que tenga descuento para probar que está el botón.
        while (i < adapter.getCount() && !encontrada) {
            Gasolinera g = (Gasolinera) adapter.getItem(i);
            if (g.getTieneDescuento()) {
                //Obtenemos el código de descuento de la gasolinera con descuento y accedemos a su vista de detalle
                String codDes = g.getDescuento().getCodigo();
                onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i).perform(click());
                onView(withId(R.id.buttonVerDescuento)).check(matches(isDisplayed()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                //Comprobamos que al clicar en el botón sale el pop-up y pone bien el codigo de descuento
                onView(withId(R.id.buttonVerDescuento)).perform(click());
                onView(withId(R.id.txtDescuento)).check(matches(isDisplayed()));
                onView(withId(R.id.txtDescuento)).check(matches(withText(codDes)));
                onView(withId(R.id.buttonAceptarDialog)).perform(click());
                encontrada = true;

            }
            i++;
        }
    }
}

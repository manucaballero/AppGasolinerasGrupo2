package com.isunican.proyectobase;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        //assertEquals("com.isunican.proyectobase", appContext.getPackageName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
        PresenterGasolineras presenterGasolineras = mActivityTestRule.getActivity().getPresenter();
        Gasolinera g = presenterGasolineras.getGasolineras().get(0);

        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0).perform(click());
        onView(withId(R.id.txtLocalidad)).check(matches(withText(g.getLocalidad())));
        onView(withId(R.id.txtDireccion)).check(matches(withText(g.getDireccion())));
        onView(withId(R.id.txtNombre)).check(matches(withText(g.getRotulo())));
        onView(withId(R.id.txtPrecioDiesel)).check(matches(withText(Double.toString(g.getGasoleoA()))));
        onView(withId(R.id.txtPrecioGasolina95)).check(matches(withText(Double.toString(g.getGasolina95()))));




    }
}

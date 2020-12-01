package com.isunican.proyectobase;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;


import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

public class PopUpAnhadirVehiculoPorPrimeraVezUITest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    /**
     * @author Miguel Casamichana Bolado
     */
    @Test
    public void mostrarPopUpTest(){
        //onView(ViewMatchers.withId(R.id.txtAceptar)).perform(click());
        /*Button aux = view.findViewById(R.id.buttonMasTarde);
        if(aux.isClickable()) aux.performClick();*/
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(mActivityTestRule.getActivity().myIntentPop!=null) onView(withId(R.id.buttonMasTarde)).perform(click());

        openActionBarOverflowOrOptionsMenu(mActivityTestRule.getActivity().getApplicationContext());
        onView(withText("Actualizar")).perform(click());
        onView(withId(R.id.texViewAnhadir)).check(matches(isDisplayed()));
    }
}

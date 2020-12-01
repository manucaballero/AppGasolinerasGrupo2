package com.isunican.proyectobase;

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
        openActionBarOverflowOrOptionsMenu(mActivityTestRule.getActivity().getApplicationContext());
        try {
            Thread.sleep(121000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Actualizar")).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.texViewAnhadir)).check(matches(isDisplayed()));
    }
}

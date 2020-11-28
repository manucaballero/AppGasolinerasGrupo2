package com.isunican.proyectobase;

import android.Manifest;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Model.Descuento;
import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Views.DetailActivity;
import com.isunican.proyectobase.Views.ListaDescuentosActivity;
import com.isunican.proyectobase.Views.MainActivity;


import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;


public class ListaDescuentosUITest {
    @Rule
    public ActivityTestRule<DetailActivity> mActivityTestRule = new ActivityTestRule<>(DetailActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    @Test
    public void ListaDescuentosUITest() {

        onView(withId(R.id.buttonVerDescuento)).perform(ViewActions.click());
        onView(withId(R.id.))

        DataInteraction dataInteraction;
        Button b;




    }
}

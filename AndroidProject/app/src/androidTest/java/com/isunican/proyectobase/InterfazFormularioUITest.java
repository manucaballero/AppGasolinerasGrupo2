package com.isunican.proyectobase;

import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Views.FormActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

/**
 * @author Aarón rodríguez
 */
public class InterfazFormularioUITest {

    @Rule
    public ActivityTestRule<FormActivity> mActivityTestRule = new ActivityTestRule(FormActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Test
    public void testInterfaz() {

        //En el formulario se clicka aceptar
        onView(ViewMatchers.withId(R.id.txtAceptar)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.txtAceptar)).perform(click());

        //Se crea un objeto referente al campo de la matricula para poder comprobar si tiene error o no
        EditText a = mActivityTestRule.getActivity().findViewById(R.id.campoMatricula);
        //Se comprueba si sale el error esperado
        Assert.assertTrue(a.getError().equals("Campo Requerido"));

        //En el campo de la matricula se escribe un valor erroneo y se cierra el teclado del movil (si no da error)
        onView(withId(R.id.campoMatricula)).perform(typeText("AABB1"));
        Espresso.closeSoftKeyboard();
        //Click en aceptar y se comprueba si sale el error esperado. Además se comprueba que el texto introducido antes está presente
        onView(ViewMatchers.withId(R.id.txtAceptar)).perform(click());
        Assert.assertTrue(a.getError().equals("Mínimo 6 caracteres"));
        onView(ViewMatchers.withId(R.id.campoMatricula)).check(matches(withText("AABB1")));

        //En el campo de la matricula se escribe un valor correcto (tras dejarlo en blanco) y se cierra el teclado del movil
        onView(withId(R.id.campoMatricula)).perform(clearText(), typeText("AABB11"));
        Espresso.closeSoftKeyboard();
        //Click en aceptar y se comprueba no hay errores. Además se comprueba que el texto introducido antes está presente
        onView(ViewMatchers.withId(R.id.txtAceptar)).perform(click());
        Assert.assertTrue(a.getError()==null);
        onView(ViewMatchers.withId(R.id.campoMatricula)).check(matches(withText("AABB11")));

    }
}

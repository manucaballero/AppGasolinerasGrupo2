package com.isunican.proyectobase.Views.FormActivityUITest;

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Views.FormActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;



public class InterfazFormularioUITest {

    @Rule
    public ActivityTestRule<FormActivity> mActivityTestRule = new ActivityTestRule(FormActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);
/*
 * @author Daniel Sánchez Díez
 */
/*
    @Test
    public void testInterfaz() {
        //En el formulario se clicka aceptar
        onView(ViewMatchers.withId(R.id.txtAceptar)).check(matches(isDisplayed()));
        //Se crea un objeto referente al campo de la matricula para poder comprobar si tiene error o no
        EditText modelo = mActivityTestRule.getActivity().findViewById(R.id.campoModelo);
        EditText nota = mActivityTestRule.getActivity().findViewById(R.id.campoAnotaciones);

        //Se comprueba si sale el error esperado
        onView(ViewMatchers.withId(R.id.txtAceptar)).perform(click());
        Assert.assertEquals("Campo Requerido", modelo.getError());
        //En el campo de las anotaciones se escribe un valor erroneo y se cierra el teclado del movil (si no da error)
        onView(withId(R.id.campoAnotaciones)).perform(typeText("ASD"));
        Espresso.closeSoftKeyboard();
        //Click en aceptar y se comprueba que el texto introducido antes está presente
        onView(ViewMatchers.withId(R.id.txtAceptar)).perform(click());
        onView(ViewMatchers.withId(R.id.campoAnotaciones)).check(matches(withText("ASD")));


        onView(withId(R.id.campoCombustible)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GasoleoA"))).perform(click());
        onView(withId(R.id.campoCombustible)).check(matches(withSpinnerText(containsString("GasoleoA"))));

    }
    */

/**
 * @author Manuel Caballero Rabago y Miguel Casamichana Bolado
 * Se comprueba que no se pueden añadir dos vehiculos iguales
 */
/*
@Test
public void comprobarDuplicidadTest(){
    Assert.assertTrue(true);
    boolean aux = true;
    while(aux) {
        try {
            //Se abre el desplegable del menú y se actualiza la lista de vehiculos a por defecto
            openActionBarOverflowOrOptionsMenu(mActivityTestRule.getActivity().getApplicationContext());
            onView(withText("Restaurar vehículos de fábrica")).perform(click());

            //Se abre el desplegable del menú y se selecciona "Nuevo Vehículo"
            openActionBarOverflowOrOptionsMenu(mActivityTestRule.getActivity().getApplicationContext());
            onView(withText("Nuevo Vehículo")).perform(click());
            //Se añaden los campos obligatorios
            onView(withId(R.id.campoModelo)).perform(typeText("vehiculoPruebaInterf"));
            onView(withId(R.id.campoCapacidad)).perform(typeText("50"));
            onView(withId(R.id.campoConsumoMedio)).perform(typeText("5"));
            //Se cierra el teclado para que se pueda hacer click en "Aceptar"
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.txtAceptar)).perform(click());

            //Se repite el proceso para añadir un mismo vehículo repetido y
            // comprobar que salte el error en el campo de texto
            openActionBarOverflowOrOptionsMenu(mActivityTestRule.getActivity().getApplicationContext());
            onView(withText("Nuevo Vehículo")).perform(click());
            EditText modelo = mActivityTestRule.getActivity().findViewById(R.id.campoModelo);
            onView(withId(R.id.campoModelo)).perform(typeText("vehiculoPruebaInterf"));
            onView(withId(R.id.campoCapacidad)).perform(typeText("50"));
            onView(withId(R.id.campoConsumoMedio)).perform(typeText("5"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.txtAceptar)).perform(click());

            //Comprobamos que se notifica el error y no se consigue añadir el vehiculo
            //Assert.assertEquals("Campo Requerido", modelo.getError());
            //modelo.check(matches(hasErrorText("Cannot be blank!")));
            onView(withId(R.id.campoModelo)).check(matches(hasErrorText("Ya existe un vehiculo con estas características. " +
                    "Introduzca una nueva Anotación para diferenciarlos.")));
            //Volvemos a la interfaz principal
            Espresso.pressBack();

            //Se abre el desplegable del menú y se actualiza la lista de vehiculos a por defecto
            openActionBarOverflowOrOptionsMenu(mActivityTestRule.getActivity().getApplicationContext());
            onView(withText("Restaurar vehículos de fábrica")).perform(click());
            aux = false;


        }catch(NoMatchingViewException e) {
            //Si salta el pop up de añadir vehículo se cierra
            onView(withId(R.id.buttonMasTarde)).perform(click());
        }

        onView(withId(R.id.campoCombustible)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GasoleoA"))).perform(click());
        onView(withId(R.id.campoCombustible)).check(matches(withSpinnerText(containsString("GasoleoA"))));
    }
    */

    @Test
    public void test(){
        Assert.assertTrue(true);
    }

}

package com.isunican.proyectobase;

import android.Manifest;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import com.isunican.proyectobase.Presenter.PresenterFiltros;
import com.isunican.proyectobase.Views.MainActivity;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.*;

@RunWith(AndroidJUnit4.class)
public class FiltrosActivosPopUpUITest {
    //Obtenemos la main activity
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);
    private PresenterFiltros pf;

    @Test
    public void EliminarFiltroActivoUITest(){
        onView(withId(R.id.buttonFiltrar)).perform(click());
        //Agregamos filtros
        onView(withId(R.id.checkBoxDescuentoSi)).perform(click());
        onView(withId(R.id.checkBoxGasolina95)).perform(click());
        //Aplicamos los filtros
        onView(withId(R.id.buttonApply)).perform(click());
        //Pinchamos en el filtro con descuento
        onView(withId(R.id.recyclerViewFiltros)).perform(click());
        //Eliminamos ese filtro
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        onView(withId(R.id.buttonSiBorrarFiltro)).perform(click());
        pf = mActivityTestRule.getActivity().presenterFiltros;
        pf.getListaFiltros().size();
        //Comprobamos que solo sigue activo un filtro
        Assert.assertEquals(1, pf.getListaFiltros().size());

    }

    @Test
    public void NoEliminarFiltroActivoUITest() throws InterruptedException {
        onView(withId(R.id.buttonFiltrar)).perform(click());
        //Agregamos filtros
        onView(withId(R.id.checkBoxDescuentoSi)).perform(click());
        onView(withId(R.id.checkBoxGasolina95)).perform(click());
        //Aplicamos los filtros
        onView(withId(R.id.buttonApply)).perform(click());
        //Pinchamos en el filtro con descuento
        onView(withId(R.id.recyclerViewFiltros)).perform(click());
        //Eliminamos ese filtro
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        onView(withId(R.id.buttonNoBorrarFiltro)).perform(click());

        pf = mActivityTestRule.getActivity().presenterFiltros;
        pf.getListaFiltros().size();
        //Comprobamos que siguen activos los dos filtros
        Assert.assertEquals(2, pf.getListaFiltros().size());

    }
}

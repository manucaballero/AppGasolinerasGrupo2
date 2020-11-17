package com.isunican.proyectobase;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Model.IFiltro;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class FiltrosITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    /*
        @author Miguel Casamichana Bolado

        Prueba de interfaz en la que se comprueba que se muestran correctamente por pantalla
        el nombre de los filtros activos deseados.
     */
    @Test
    public void mostrarFiltrosActivosTest() {

        //List<View> lv = mActivityTestRule.getActivity().findViewById(R.id.recyclerViewFiltros);
        ArrayList<IFiltro> lista = mActivityTestRule.getActivity().listaFiltros;

        for(int i = 0; i< lista.size(); i++){
            IFiltro filtro = lista.get(i);
            DataInteraction d = onData(anything()).inAdapterView(withId(R.id.recyclerViewFiltros)).atPosition(i);
            d.onChildView(withId(R.id.txtNombreFiltro)).check(matches(withText(filtro.getNombre())));
            //Assert.assertEquals(lv.get(i)., );
        }




    }


}
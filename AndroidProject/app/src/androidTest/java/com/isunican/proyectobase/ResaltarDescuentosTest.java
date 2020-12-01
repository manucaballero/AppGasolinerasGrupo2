package com.isunican.proyectobase;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.Views.MainActivity;
import com.isunican.proyectobase.Views.MisVehiculosActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class ResaltarDescuentosTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Before
    public void setUp(){

        try{
            Thread.sleep(2000);
        }catch(Exception e){}
    }

    /*
        Se comprueba que la lista se cargue correctamente en la interfaz
     */
    @Test
    public void listaCargadaTest(){
        ListAdapter adapter = mActivityTestRule.getActivity().adapter;
        Assert.assertNotEquals(adapter.getCount(), 0);
    }

        /*
        Se comprueba que se muestre por la interfaz el color esperado de background de cada view y el color
        de letra esperado de los precios de cada una de las views que correspondan a las gasolineras con
        descuentos.
     */
    @Test
    public void resaltarTest() {

        if(mActivityTestRule.getActivity().myIntentPop!=null) onView(withId(R.id.buttonMasTarde)).perform(click());

        //Obtenemos la lista de vistas
        ListView lv = mActivityTestRule.getActivity().findViewById(R.id.listViewGasolineras);

        onView(withId(R.id.buttonFiltrar)).perform(click());
        onView(withId(R.id.checkBoxDescuentoSi)).perform(click());

        onView(withId(R.id.buttonApply)).perform(click());
        if(mActivityTestRule.getActivity().myIntentPop!=null) onView(withId(R.id.buttonMasTarde)).perform(click());

        View v1 = lv.getChildAt(0);

        onView(withId(R.id.buttonReset)).perform(click());
        if(mActivityTestRule.getActivity().myIntentPop!=null) onView(withId(R.id.buttonMasTarde)).perform(click());

        onView(withId(R.id.buttonFiltrar)).perform(click());
        onView(withId(R.id.checkBoxDescuentoNo)).perform(click());

        onView(withId(R.id.buttonApply)).perform(click());
        if(mActivityTestRule.getActivity().myIntentPop!=null) onView(withId(R.id.buttonMasTarde)).perform(click());


        View v2 = lv.getChildAt(0);
        
        //Obtenemos los colores de background
        ColorDrawable cBck1 = (ColorDrawable) v1.getBackground();
        ColorDrawable cBck2 = (ColorDrawable) v2.getBackground();
        //Comparamos con los valores esperados
        Assert.assertEquals(0xfffffd82, cBck1.getColor());
        Assert.assertEquals(Color.WHITE, cBck2.getColor());
        //Obtenemos las textview que queremos observar
        TextView gasolinaCon = v1.findViewById(R.id.textViewGasolina95);
        TextView gasoleoCon = v1.findViewById(R.id.textViewGasoleoA);
        TextView gasolinaSin = v2.findViewById(R.id.textViewGasolina95);
        TextView gasoleoSin = v2.findViewById(R.id.textViewGasoleoA);
        //Comparamos con los valores esperados
        Assert.assertEquals(Color.RED,gasolinaCon.getCurrentTextColor());
        Assert.assertEquals(Color.RED,gasoleoCon.getCurrentTextColor());
        Assert.assertEquals(Color.BLACK,gasolinaSin.getCurrentTextColor());
        Assert.assertEquals(Color.BLACK,gasoleoSin.getCurrentTextColor());

    }
}

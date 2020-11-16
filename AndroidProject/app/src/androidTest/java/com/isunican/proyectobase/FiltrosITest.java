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

import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

        ListView lv = mActivityTestRule.getActivity().findViewById(R.id.listViewGasolineras);

        ListAdapter adapter = mActivityTestRule.getActivity().adapter;
        int i = 0;
        int j = 0;

        Gasolinera gDesc=(Gasolinera)adapter.getItem(0);
        while(!gDesc.getTieneDescuento()){
            i++;
            gDesc=(Gasolinera)adapter.getItem(i);
        }
        Gasolinera gSin=(Gasolinera)adapter.getItem(0);
        while(!gSin.getTieneDescuento()){
            j++;
            gSin=(Gasolinera)adapter.getItem(j);
        }

        //Cogemos las vistas que queremos (correspondientes a cada elemento de la listview de la interfaz principal
        View v1 = lv.getChildAt(i);
        View v2 = lv.getChildAt(j);

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
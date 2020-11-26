package com.isunican.proyectobase.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.R;

public class PopUpBorrarFiltroActivity extends AppCompatActivity {
    Button borrarFiltro;
    Button cerrar;
    boolean bgasoleoA = false;
    boolean bgasolina95 = false;
    boolean bdescuentoSi = false;
    boolean bDescuentoNo =false;
    //Filtro marcado

    public static String getGasoleoA(){
        return "gasoleoA";
    }
    public static String getGasolina95(){
        return "gasolina95";
    }
    public static String getDescuentoSi(){
        return "descuentoSI";
    }
    public static String getDescuentoNo(){
        return "descuentoNo";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_borrar_filtro);
        borrarFiltro = findViewById(R.id.buttonSiBorrarFiltro);
        cerrar= findViewById(R.id.buttonNoBorrarFiltro);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);
        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;
        getWindow().setLayout((int) (ancho * 0.9), (int) (alto * 0.5));

        borrarFiltro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Aqui va el codigo para eliminar el filtro
                borraFiltro();
                Intent intent = new Intent();
                intent.putExtra(getGasoleoA(), bgasoleoA);
                intent.putExtra(getGasolina95(),bgasolina95);
                intent.putExtra(getDescuentoSi(),bdescuentoSi);
                intent.putExtra(getDescuentoNo(),bDescuentoNo);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        Intent data = getIntent();
        bgasoleoA = data.getBooleanExtra("GasoleoA", false);
        bgasolina95 = data.getBooleanExtra("Gasolina95", false);
        bdescuentoSi = data.getBooleanExtra("DescuentoSI", false);
        bDescuentoNo = data.getBooleanExtra("DescuentoNo", false);

        //Filtro marcado = getFiltroMarcado
        //El requestCode tiene que ser distinto a 10 para asi en el onActivityResult meter en el if
        //un || aprovechando que los data que devuelve la actividad FilterActivity son los mismos que la nuestra
        //y asi evitamos codigo duplicado

    }
    public void borraFiltro(){
        //if infernal que busca el filtro marcado y lo cambia de true a false;
    }
}

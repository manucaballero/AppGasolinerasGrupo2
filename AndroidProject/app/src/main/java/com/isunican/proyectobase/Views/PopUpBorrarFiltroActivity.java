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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_borrar_filtro);
        borrarFiltro = findViewById(R.id.buttonSiBorrarFiltro);
        cerrar = findViewById(R.id.buttonNoBorrarFiltro);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);
        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;
        getWindow().setLayout((int) (ancho * 0.9), (int) (alto * 0.5));

        borrarFiltro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
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


    }

}


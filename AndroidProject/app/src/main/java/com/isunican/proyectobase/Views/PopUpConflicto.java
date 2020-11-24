package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.isunican.proyectobase.R;

public class PopUpConflicto extends AppCompatActivity {

    Button boton;
    Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_conflicto);
        boton = findViewById(R.id.buttonAceptarConflicto);
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(PopUpConflicto.this, FilterActivity.class);
                PopUpConflicto.this.startActivity(myIntent);
            }
        });


        cerrar= findViewById(R.id.buttonCancelarConflicto);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho * 0.9), (int) (alto * 0.6));

    }
}
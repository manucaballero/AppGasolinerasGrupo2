package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.isunican.proyectobase.R;


public class PopUpPrimerVehiculoActivity extends AppCompatActivity {

    Button boton;
    Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_prime_vehiculo);
        boton = findViewById(R.id.buttonAnhadir);
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent1 = new Intent(PopUpPrimerVehiculoActivity.this, FormActivity.class);
                //myIntent.putExtra("Lista", listaVehiculos)
                PopUpPrimerVehiculoActivity.this.startActivity(myIntent1);
            }
        });


        cerrar= findViewById(R.id.buttonMasTarde);
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

        getWindow().setLayout((int) (ancho * 0.9), (int) (alto * 0.5));


    }


}
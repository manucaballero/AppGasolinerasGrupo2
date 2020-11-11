package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.isunican.proyectobase.R;


public class PopUpPV extends AppCompatActivity {

    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_p_v);
        boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent1 = new Intent(PopUpPV.this, FormActivity.class);
                //myIntent.putExtra("Lista", listaVehiculos)
                PopUpPV.this.startActivity(myIntent1);
            }
        });

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho * 0.9), (int) (alto * 0.3));

    }


}
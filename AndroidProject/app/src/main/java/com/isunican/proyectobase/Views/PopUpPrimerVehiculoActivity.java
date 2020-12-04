package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 1000;
        params.width = 700;
        this.getWindow().setAttributes(params);


    }


}
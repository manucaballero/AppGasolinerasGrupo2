package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
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

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 1300;
        params.width = 800;
        this.getWindow().setAttributes(params);

    }
}
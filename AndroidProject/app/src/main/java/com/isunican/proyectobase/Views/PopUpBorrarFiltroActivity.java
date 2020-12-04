package com.isunican.proyectobase.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 1000;
        params.width = 700;
        this.getWindow().setAttributes(params);

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


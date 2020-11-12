package com.isunican.proyectobase.Views;

import com.isunican.proyectobase.R;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/*
------------------------------------------------------------------
    Vista de formulario de creacion de vehiculo


------------------------------------------------------------------
*/
public class FormActivity extends AppCompatActivity {

    //TODO declarar todos los textviews etc
    EditText campoMatricula;
    EditText campoModelo;
    EditText campoCapacidad;
    EditText campoAnotaciones;
    EditText campoConsumomedio;
    TextView txtAceptar;

    /**
     * onCreate
     *
     * Crea los elementos que conforman la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_vehiculo_form);

        // muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);


        //COGER LOS DATOS DE TODOS LOS CAMPOS DE TEXTO Y CREAR UN NUEVO VEHICULO Y AÑADIRLO AL PRESENTER
        //ADEMAS ALMACENAR DATOS DE FORMA EXTERNA
        //PARA EL BOTON DE ACEPTAR PONER UN SETONCLICKLISTENER
        txtAceptar = findViewById(R.id.txtAceptar);
        campoMatricula = findViewById(R.id.campoMatricula);
        campoModelo = findViewById(R.id.campoModelo);
        campoCapacidad = findViewById(R.id.campoCapacidad);
        campoConsumomedio = findViewById(R.id.campoConsumoMedio);
        campoAnotaciones= findViewById(R.id.campoAnotaciones);

    }
}
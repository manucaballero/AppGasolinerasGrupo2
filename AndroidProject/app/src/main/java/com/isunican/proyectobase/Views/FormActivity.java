package com.isunican.proyectobase.Views;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/*
------------------------------------------------------------------
    Vista de formulario de creacion de vehiculo


------------------------------------------------------------------
*/
public class FormActivity extends AppCompatActivity implements View.OnClickListener{

    EditText campoMatricula;
    EditText campoModelo;
    EditText campoCapacidad;
    EditText campoAnotaciones;
    EditText campoConsumomedio;
    TextView txtAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_vehiculo_form);

        // muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);

        //ADEMAS ALMACENAR DATOS DE FORMA EXTERNA Y AÑADIRLO AL PRESENTER

        txtAceptar = findViewById(R.id.txtAceptar);
        campoMatricula = findViewById(R.id.campoMatricula);
        campoModelo = findViewById(R.id.campoModelo);
        campoCapacidad = findViewById(R.id.campoCapacidad);
        campoConsumomedio = findViewById(R.id.campoConsumoMedio);
        campoAnotaciones= findViewById(R.id.campoAnotaciones);

        txtAceptar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Toast toast = null;
        Vehiculo v1 = null;

        if(v.getId()==R.id.txtAceptar){

            String matricula = campoMatricula.getText().toString();
            String modelo = campoModelo.getText().toString();
            String anotacion = campoAnotaciones.getText().toString();
            String capacidadtxt = campoCapacidad.getText().toString();
            String consumoMediotxt = campoConsumomedio.getText().toString();

            if(!modelo.equals("") && !capacidadtxt.equals("")){
                v1 = new Vehiculo(modelo);
                v1.setDeposito(Double.parseDouble(capacidadtxt));

                if(!matricula.equals(""))
                    v1.setMatricula(matricula);

                if(!anotacion.equals(""))
                    v1.setAnotaciones(anotacion);

                if(!consumoMediotxt.equals(""))
                    v1.setConsumoMedio(Double.parseDouble(consumoMediotxt));

                //TODO AÑADIRLO A LA LUSTA Y ESCRIBIRLO
                toast = Toast.makeText(getApplicationContext(), "Vehiculo añadido con exito", Toast.LENGTH_LONG);
                toast.show();

                Intent myIntent1 = new Intent(FormActivity.this, MainActivity.class);
                FormActivity.this.startActivity(myIntent1);

            }else {
                toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_LONG);
                toast.show();
                campoModelo.setText("Campo Requerido");
                campoModelo.setTextColor(Color.RED);
                campoCapacidad.setText("50");
                campoCapacidad.setTextColor(Color.RED);
                campoAnotaciones.setText("");
                campoConsumomedio.setText("");
                campoMatricula.setText("");
            }

            //listaVehiculos.add(v1);

        }
        //guardaDatos();

    }
/*
    public void guardaDatos(){

        String output="";

        for (Vehiculo v: listaVehiculos) {
            output = v1.getMatricula() + "/" + v1.getModelo() + "/" + v1.getDeposito() + "/" + v1.getConsumoMedio() + "/" + v1.getAnotaciones()+"\n";
        }
        //TODO AÑADIR EL VEHICULO A LA LISTA
    }*/
}
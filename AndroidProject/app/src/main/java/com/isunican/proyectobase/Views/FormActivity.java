package com.isunican.proyectobase.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.R;


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
    Button txtAceptar;
    private static final String CAMPO_REQUERIDO = "Campo Requerido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_vehiculo_form);

        // muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);


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


        if(v.getId()==R.id.txtAceptar){

            String matricula = campoMatricula.getText().toString();
            String modelo = campoModelo.getText().toString();
            String anotacion = campoAnotaciones.getText().toString();
            String capacidadtxt = campoCapacidad.getText().toString();
            String consumoMediotxt = campoConsumomedio.getText().toString();

            if(!modelo.equals("") && !capacidadtxt.equals("") && matricula.length()>5){
                Vehiculo v1 = new Vehiculo(modelo);
                v1.setDeposito(Double.parseDouble(capacidadtxt));
                v1.setMatricula(matricula);

                if(anotacion.equals("")){
                    v1.setAnotaciones("Coche nuevo");
                }else{
                    v1.setAnotaciones(anotacion);
                }

                if(consumoMediotxt.equals("")){
                    v1.setConsumoMedio(5);
                }else{
                    v1.setConsumoMedio(Double.parseDouble(consumoMediotxt));
                }

                toast = Toast.makeText(getApplicationContext(), "Vehiculo añadido con exito", Toast.LENGTH_LONG);
                toast.show();
                //TODO Algo aqui no funciona porque no se actualiza la lista
                // Se deberá llamar a un método que guarde el vehículo en el fichero

                Intent myIntent = new Intent(FormActivity.this, MisVehiculosActivity.class);
                FormActivity.this.startActivity(myIntent);

            }else {
                toast = Toast.makeText(getApplicationContext(), "No se ha podido crear el vehiculo", Toast.LENGTH_LONG);
                toast.show();

                if(matricula.length()==0)
                    campoMatricula.setError("Campo Requerido");
                else if(matricula.length()<6)
                    campoMatricula.setError("Mínimo 6 caracteres");
                campoModelo.setError("Campo Requerido");
                campoCapacidad.setError("Campo Requerido");

            }

        }


    }

}
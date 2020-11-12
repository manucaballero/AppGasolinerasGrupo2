package com.isunican.proyectobase.Views;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
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
    public PresenterVehiculos presenterVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_vehiculo_form);

        this.presenterVehiculos = new PresenterVehiculos();

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
                presenterVehiculos.getVehiculos().add(v1);

                Intent myIntent = new Intent(FormActivity.this, MisVehiculosActivity.class);
                FormActivity.this.startActivity(myIntent);

            }else {
                toast = Toast.makeText(getApplicationContext(), "No se ha podido crear el vehiculo", Toast.LENGTH_LONG);
                toast.show();
                campoModelo.setText("Campo Requerido");
                campoModelo.setTextColor(Color.RED);
                campoCapacidad.setText("50");
                campoCapacidad.setTextColor(Color.RED);
                campoAnotaciones.setText("");
                campoConsumomedio.setText("");
                campoMatricula.setText("Campo Requerido");
                campoMatricula.setTextColor(Color.RED);
            }

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
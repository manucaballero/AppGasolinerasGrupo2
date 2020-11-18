package com.isunican.proyectobase.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.R;

import java.util.ArrayList;
import java.util.List;


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

    PresenterVehiculos presenterVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_vehiculo_form);

        this.presenterVehiculos = new PresenterVehiculos();
        presenterVehiculos.cargaDatosVehiculos(FormActivity.this);

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

        if(v.getId()==R.id.txtAceptar)
            anhadeVehiculo();

    }

    private void anhadeVehiculo() {

        Toast toast;

        String matricula = campoMatricula.getText().toString();
        String modelo = campoModelo.getText().toString();
        String anotacion = campoAnotaciones.getText().toString();
        String capacidadtxt = campoCapacidad.getText().toString();
        String consumoMediotxt = campoConsumomedio.getText().toString();


        if(!modelo.equals("") && !capacidadtxt.equals("") && !consumoMediotxt.equals("")){

            Vehiculo v1 = new Vehiculo(modelo);
            v1.setDeposito(Double.parseDouble(capacidadtxt));
            v1.setConsumoMedio(Double.parseDouble(consumoMediotxt));
            v1.setAnotaciones(anotacion);
            v1.setMatricula(matricula);

            List<Vehiculo> aux = new ArrayList<Vehiculo>();
            for(Vehiculo v : presenterVehiculos.getVehiculos()){
                if(v.getModelo().equals(modelo)){
                    aux.add(v);
                }
            }

            if(aux.size() == 0){
                presenterVehiculos.guardaVehiculo(v1, FormActivity.this);

                toast = Toast.makeText(getApplicationContext(), "Vehiculo añadido con exito", Toast.LENGTH_LONG);
                toast.show();

                Intent myIntent = new Intent(FormActivity.this, MisVehiculosActivity.class);
                FormActivity.this.startActivity(myIntent);
            } else {
                for(Vehiculo v : aux){

                    if(v.getAnotaciones().equals(anotacion) && v.getMatricula().equals(matricula)){
                        campoModelo.setError("Ya existe un vehiculo con estas características. Introduzca una nueva Matrícula o Anotación para diferenciarlos.");
                        aux.clear();
                    }else{
                        presenterVehiculos.guardaVehiculo(v1, FormActivity.this);

                        toast = Toast.makeText(getApplicationContext(), "Vehiculo añadido con exito", Toast.LENGTH_LONG);
                        toast.show();

                        Intent myIntent = new Intent(FormActivity.this, MisVehiculosActivity.class);
                        FormActivity.this.startActivity(myIntent);
                    }
                }
            }


        }else {
            toast = Toast.makeText(getApplicationContext(), "No se ha podido crear el vehiculo", Toast.LENGTH_LONG);
            toast.show();

            if(matricula.length()!=0 && matricula.length()<6)
                campoMatricula.setError("Mínimo 6 caracteres");
            if(modelo.length()==0)
                campoModelo.setError("Campo Requerido");
            if(capacidadtxt.length()==0)
                campoCapacidad.setError("Campo Requerido");
            if(consumoMediotxt.length()==0)
                campoConsumomedio.setError("Campo Requerido");

        }

    }
}
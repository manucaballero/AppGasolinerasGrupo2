package com.isunican.proyectobase.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
public class FormActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner campoCombustible;
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
        campoCombustible = (Spinner) findViewById(R.id.campoCombustible);
        campoModelo = findViewById(R.id.campoModelo);
        campoCapacidad = findViewById(R.id.campoCapacidad);
        campoConsumomedio = findViewById(R.id.campoConsumoMedio);
        campoAnotaciones= findViewById(R.id.campoAnotaciones);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_combustible, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoCombustible.setAdapter(adapter);

        txtAceptar.setOnClickListener(this);
        campoCombustible.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.txtAceptar)
            anhadeVehiculo();

    }

    private void anhadeVehiculo() {

        Toast toast;

        String combustible = campoCombustible.getSelectedItem().toString();
        String modelo = campoModelo.getText().toString();
        String anotacion = campoAnotaciones.getText().toString();
        String capacidadtxt = campoCapacidad.getText().toString();
        String consumoMediotxt = campoConsumomedio.getText().toString();


        if(!modelo.equals("") && !capacidadtxt.equals("") && !consumoMediotxt.equals("")){

            Vehiculo v1 = new Vehiculo(modelo);
            v1.setDeposito(Double.parseDouble(capacidadtxt));
            v1.setConsumoMedio(Double.parseDouble(consumoMediotxt));
            v1.setAnotaciones(anotacion);
            v1.setCombustible(combustible);

            boolean igual=false;

            for(Vehiculo v : presenterVehiculos.getVehiculos()){
                if(v.getModelo().equals(modelo) && v.getAnotaciones().equals(anotacion))
                    igual=true;
            }

            if(igual)
                campoModelo.setError("Ya existe un vehiculo con estas características. Introduzca una nueva Anotación para diferenciarlos.");
            else{
                presenterVehiculos.guardaVehiculo(v1, FormActivity.this);

                toast = Toast.makeText(getApplicationContext(), "Vehiculo añadido con exito", Toast.LENGTH_LONG);
                toast.show();

                Intent myIntent = new Intent(FormActivity.this, MainActivity.class);
                FormActivity.this.startActivity(myIntent);
            }

        }else {
            toast = Toast.makeText(getApplicationContext(), "No se ha podido crear el vehiculo", Toast.LENGTH_LONG);
            toast.show();

            if(combustible.length()==0)
                ((TextView)campoCombustible.getSelectedView()).setError(CAMPO_REQUERIDO);
            if(modelo.length()==0)
                campoModelo.setError(CAMPO_REQUERIDO);
            if(capacidadtxt.length()==0)
                campoCapacidad.setError(CAMPO_REQUERIDO);
            if(consumoMediotxt.length()==0)
                campoConsumomedio.setError(CAMPO_REQUERIDO);

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.isunican.proyectobase.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.R;

public class DetailVehiculoActivity extends AppCompatActivity {

    Vehiculo vehiculo;

    TextView textViewModelo;
    TextView textViewCombustible;
    TextView textViewAnotacion;
    TextView textViewDeposito;
    TextView textViewConsumoMedio;
    TextView textViewCombustibleLabel;

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
        setContentView(R.layout.activity_detail_vehiculo);

        // muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);


        // captura el TextView
        // obtiene el objeto Gasolinera a mostrar
        // y lo introduce en el TextView convertido a cadena de texto
        textViewModelo = findViewById(R.id.textViewModelo);
        textViewAnotacion = findViewById(R.id.textViewAnotacion);
        textViewCombustible = findViewById(R.id.textViewCombustible);
        textViewDeposito = findViewById(R.id.textViewDeposito);
        textViewConsumoMedio = findViewById(R.id.textViewConsumoMedio);
        textViewCombustibleLabel= findViewById(R.id.textViewCombustibleLabel);

        vehiculo = getIntent().getExtras().getParcelable(getResources().getString(R.string.pasoDatosVehiculo));

        textViewModelo.setText(vehiculo.getModelo());
        textViewCombustible.setText(vehiculo.getCombustible());
        textViewAnotacion.setText(vehiculo.getAnotaciones());
        textViewDeposito.setText(Double.toString(vehiculo.getDeposito()));
        textViewConsumoMedio.setText(Double.toString(vehiculo.getConsumoMedio()));

        Button buttonSeleccionado=findViewById(R.id.buttonSeleccionado);

        buttonSeleccionado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PresenterVehiculos.setVehiculoSeleccionado(vehiculo);
                PresenterVehiculos.guardaVehiculoSeleccionado(vehiculo, DetailVehiculoActivity.this);
                Toast.makeText(DetailVehiculoActivity.this, "Vehículo seleccionado", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(DetailVehiculoActivity.this, MisVehiculosActivity.class);
                DetailVehiculoActivity.this.startActivity(myIntent);
            }
        });



    }
}

package com.isunican.proyectobase.Views;

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
    TextView textViewMatricula;
    TextView textViewAnotacion;
    TextView textViewDeposito;
    TextView textViewConsumoMedio;
    TextView textViewMatriculaLabel;

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
        textViewMatricula = findViewById(R.id.textViewMatricula);
        textViewDeposito = findViewById(R.id.textViewDeposito);
        textViewConsumoMedio = findViewById(R.id.textViewConsumoMedio);
        textViewMatriculaLabel= findViewById(R.id.textViewMatriculaLabel);

        vehiculo = getIntent().getExtras().getParcelable(getResources().getString(R.string.pasoDatosVehiculo));

        textViewModelo.setText(vehiculo.getModelo());
        textViewMatricula.setText(vehiculo.getMatricula());
        textViewAnotacion.setText(vehiculo.getAnotaciones());
        textViewDeposito.setText(Double.toString(vehiculo.getDeposito()));
        textViewConsumoMedio.setText(Double.toString(vehiculo.getConsumoMedio()));

        Button buttonSeleccionado=findViewById(R.id.buttonSeleccionado);

        if(vehiculo.getMatricula()==null){
            textViewMatriculaLabel.setVisibility(View.INVISIBLE);
        }

        buttonSeleccionado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PresenterVehiculos.setVehiculoSeleccionado(vehiculo);
                Toast.makeText(DetailVehiculoActivity.this, "Vehículo seleccionado", Toast.LENGTH_SHORT).show();
            }
        });



    }
}

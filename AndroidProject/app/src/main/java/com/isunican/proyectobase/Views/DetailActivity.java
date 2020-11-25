package com.isunican.proyectobase.Views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.R;


/*
------------------------------------------------------------------
    Vista de detalle

    Presenta datos de detalle de una Gasolinera concreta.
    La gasolinera a mostrar se le pasa directamente a la actividad
    en la llamada por intent (usando putExtra / getExtra)
    Para ello Gasolinera implementa la interfaz Parcelable
------------------------------------------------------------------
*/
public class DetailActivity extends AppCompatActivity {

    TextView txtNombre;
    TextView txtDireccion;
    TextView txtLocalidad;
    TextView txtDieselPrecio;
    TextView txtGasolina95Precio;
    TextView txtMaps;
    TextView txtDieselPrecioConDescuento;
    TextView txtDieselConDescuento;
    TextView txtGasolina95ConDescuento;
    TextView txtPrecioGasolina95ConDescuento;
    Button buttonVerDescuento;
    TextView txtDiesel;
    TextView txtGasolina95;


    ImageView imgLogo;
    //logo

    Gasolinera g;

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
        setContentView(R.layout.activity_detail);

        // muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);


        // captura el TextView
        // obtiene el objeto Gasolinera a mostrar
        // y lo introduce en el TextView convertido a cadena de texto
        imgLogo = findViewById(R.id.imgLogo);
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtLocalidad = findViewById(R.id.txtLocalidad);
        txtMaps = findViewById(R.id.txtMaps);
        txtDieselPrecio = findViewById(R.id.txtPrecioDiesel);
        txtGasolina95Precio = findViewById(R.id.txtPrecioGasolina95);
        buttonVerDescuento = findViewById(R.id.buttonVerDescuento);
        txtDiesel = findViewById(R.id.txtDiesel);
        txtGasolina95 = findViewById(R.id.txtGasolina95);

        txtDieselPrecioConDescuento=findViewById(R.id.txtPrecioDieselConDescuento);
        txtDieselConDescuento=findViewById(R.id.textDieselConDescuento);

        txtGasolina95ConDescuento=findViewById(R.id.txtGasolina95ConDescuento);
        txtPrecioGasolina95ConDescuento=findViewById(R.id.txtPrecioGasolina95ConDescuento);



        g = getIntent().getExtras().getParcelable(getResources().getString(R.string.pasoDatosGasolinera));


        //Si lo llamas aqui aunque no hagas nada va bien
        //Este hace falta
        g.calculaPrecioFinal(PresenterVehiculos.getVehiculoSeleccionado());


        txtNombre.setText(g.getRotulo());
        txtDireccion.setText(g.getDireccion());
        txtLocalidad.setText(g.getLocalidad());



        //Precio Sin el Descuento
        //Diesel

        txtDieselPrecio.setText(Double.toString(g.getGasoleoA()));
        //Gasolina95
        txtGasolina95Precio.setText(Double.toString(g.getGasolina95()));


        //Precio Con el Descuento
        //Diesel
        txtDieselPrecioConDescuento.setText(Double.toString(g.getGasoleoAConDescuento()));
        txtDieselPrecioConDescuento.setTextColor(Color.RED);
        txtDieselPrecioConDescuento.setTextSize(20);
        txtDieselConDescuento.setTextSize(20);

        //Gasolina 95

        txtPrecioGasolina95ConDescuento.setText(Double.toString(g.getGasolina95ConDescuento()));
        txtPrecioGasolina95ConDescuento.setTextColor(Color.RED);
        txtPrecioGasolina95ConDescuento.setTextSize(20);
        txtGasolina95ConDescuento.setTextSize(20);

        //Si no tiene descuento se oculta esa línea
        if(!g.getTieneDescuento()) {
            txtDieselPrecioConDescuento.setVisibility(View.GONE);
            txtDiesel.setTextSize(20);
            txtDieselPrecio.setTextSize(25);
            txtDieselConDescuento.setVisibility(View.GONE);
            txtPrecioGasolina95ConDescuento.setVisibility(View.GONE);

            txtGasolina95ConDescuento.setVisibility(View.GONE);
            txtGasolina95.setTextSize(20);
            txtGasolina95Precio.setTextSize(25);
            buttonVerDescuento.setVisibility(View.GONE);
        }



        int imageID = getResources().getIdentifier(g.getRotulo().toLowerCase(),
                "drawable", getPackageName());

        if (imageID == 0 || TextUtils.isDigitsOnly(g.getRotulo().toLowerCase())) {
            imageID = getResources().getIdentifier(getResources().getString(R.string.pordefecto),
                    "drawable", getPackageName());
        }
        imgLogo.setImageResource(imageID);


        final AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);

        builder.setMessage("hola")
                .setTitle("Descuento proporcionado");

        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        Button buttonAceptarDialog = dialog.getButton(Dialog.BUTTON_NEUTRAL);

                buttonVerDescuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }
}
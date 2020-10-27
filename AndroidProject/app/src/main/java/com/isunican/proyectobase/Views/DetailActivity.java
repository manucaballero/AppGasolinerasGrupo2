package com.isunican.proyectobase.Views;

import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Model.*;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


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

    TextView txtNombre, txtDireccion, txtLocalidad, txtDieselPrecio, txtGasolina95Precio, txtMasInfo, txtMaps;
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
        txtDieselPrecio = findViewById(R.id.txtPrecioDIesel);
        txtGasolina95Precio = findViewById(R.id.txtPrecioGasolina95);
        txtMasInfo = findViewById(R.id.txtMasInfo);

        g = getIntent().getExtras().getParcelable(getResources().getString(R.string.pasoDatosGasolinera));

        txtNombre.setText(g.getRotulo());
        txtDireccion.setText(g.getDireccion());
        txtLocalidad.setText(g.getLocalidad());
        //txtMapsç
        txtDieselPrecio.setText(Double.toString(g.getGasoleoA()));
        txtGasolina95Precio.setText(Double.toString(g.getGasolina95()));
        int imageID = getResources().getIdentifier(g.getRotulo().toLowerCase(),
                "drawable", getPackageName());

        if (imageID == 0 || TextUtils.isDigitsOnly(g.getRotulo().toLowerCase())) {
            imageID = getResources().getIdentifier(getResources().getString(R.string.pordefecto),
                    "drawable", getPackageName());
        }
        imgLogo.setImageResource(imageID);

    }
}
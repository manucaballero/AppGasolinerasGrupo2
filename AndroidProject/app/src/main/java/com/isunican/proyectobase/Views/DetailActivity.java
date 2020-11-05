package com.isunican.proyectobase.Views;

import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Model.*;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
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

    TextView txtNombre, txtDireccion, txtLocalidad, txtDieselPrecio,txtDieselPrecioConDescuento,txtDieselConDescuento,
            txtGasolina95Precio,txtGasolina95ConDescuento,txtPrecioGasolina95ConDescuento, txtMasInfo, txtMaps;
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
        //txtMasInfo = findViewById(R.id.txtMasInfo);


        txtDieselPrecioConDescuento=findViewById(R.id.txtPrecioDieselConDescuento);
        txtDieselConDescuento=findViewById(R.id.textDieselConDescuento);

        txtGasolina95ConDescuento=findViewById(R.id.txtGasolina95ConDescuento);
        txtPrecioGasolina95ConDescuento=findViewById(R.id.txtPrecioGasolina95ConDescuento);



        g = getIntent().getExtras().getParcelable(getResources().getString(R.string.pasoDatosGasolinera));


        //Si lo llamas aqui aunque no hagas nada va bien
        //g.setDistanciaEnKm(0);
        //Este hace falta
        g.calculaPrecioFinal();


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
            txtDieselPrecioConDescuento.setVisibility(View.INVISIBLE);
            txtDieselConDescuento.setVisibility(View.INVISIBLE);
            txtPrecioGasolina95ConDescuento.setVisibility(View.INVISIBLE);
            txtGasolina95ConDescuento.setVisibility(View.INVISIBLE);
        }



        int imageID = getResources().getIdentifier(g.getRotulo().toLowerCase(),
                "drawable", getPackageName());

        if (imageID == 0 || TextUtils.isDigitsOnly(g.getRotulo().toLowerCase())) {
            imageID = getResources().getIdentifier(getResources().getString(R.string.pordefecto),
                    "drawable", getPackageName());
        }
        imgLogo.setImageResource(imageID);

    }
}
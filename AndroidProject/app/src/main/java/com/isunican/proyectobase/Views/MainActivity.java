package com.isunican.proyectobase.Views;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.isunican.proyectobase.Model.*;
import com.isunican.proyectobase.Presenter.*;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Utilities.Distancia;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


/*
------------------------------------------------------------------
    Vista principal

    Presenta los datos de las gasolineras en formato lista.

------------------------------------------------------------------
*/
public class MainActivity extends AppCompatActivity {

    private PresenterGasolineras presenterGasolineras;


    private PresenterVehiculos presenterVehiculos;


    // Vista de lista y adaptador para cargar datos en ella
    private ListView listViewGasolineras;
    private RecyclerView recyclerViewFiltros;
    public ArrayAdapter<Gasolinera> adapter;
    private Button filter;
    private Button reset;
    private IFiltro filtroGasoleA;
    private IFiltro filtroGasolina95;
    private IFiltro descuentoSiFiltro;
    private IFiltro descuentoNoFiltro;

    private boolean gasoleoA;
    private boolean gasolina95;
    private boolean descuentoSi;
    private boolean descuentoNo;
    private AdapterFiltros adapterFiltros;

    // Barra de progreso circular para mostar progeso de carga
    ProgressBar progressBar;

    // Swipe and refresh (para recargar la lista con un swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    public List<IFiltro> listaFiltros= new ArrayList<IFiltro>();

    private static final int PERMISSION_REQUEST = 100;
    private static final int REQUEST_CHECK_SETTINGS = 101;
    private FusedLocationProviderClient mFusedLocationClient;

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
        setContentView(R.layout.activity_main);

        this.presenterGasolineras = new PresenterGasolineras();

        this.presenterVehiculos= new PresenterVehiculos();
        //presenterVehiculos.borra(MainActivity.this);
        presenterVehiculos.cargaDatosVehiculos(MainActivity.this);
        presenterVehiculos.cargaVehiculoSeleccionado(MainActivity.this);


        // Obtenemos la vista de la lista
        listViewGasolineras = findViewById(R.id.listViewGasolineras);
        recyclerViewFiltros = findViewById(R.id.recyclerViewFiltros);

        recyclerViewFiltros.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        filtroGasoleA = new DieselFiltro();
        filtroGasolina95 = new Gasolina95Filtro();
        descuentoSiFiltro = new ConDescuentoFiltro();
        descuentoNoFiltro = new SinDescuentoFiltro();

        filter = findViewById(R.id.button2);
        reset = findViewById(R.id.buttonReset);

        // Barra de progreso
        // https://materialdoc.com/components/progress/
        progressBar = new ProgressBar(MainActivity.this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        LinearLayout layout = findViewById(R.id.activity_precio_gasolina);
        layout.addView(progressBar, params);

        // Muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);

        // Swipe and refresh
        // Al hacer swipe en la lista, lanza la tarea asíncrona de carga de datos
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onRefresh() {
                new CargaDatosGasolinerasTask(MainActivity.this).execute();
            }
        });

        if (!checkPermissionLocation()) {
            requestPermission();
        }
        //cargarSpinner();
        // Al terminar de inicializar todas las variables
        // se lanza una tarea para cargar los datos de las gasolineras
        // Esto se ha de hacer en segundo plano definiendo una tarea asíncrona
        new CargaDatosGasolinerasTask(this).execute();

    }


    /**
     * Menú action bar
     *
     * Redefine métodos para el uso de un menú de tipo action bar.
     *
     * onCreateOptionsMenu
     * Carga las opciones del menú a partir del fichero de recursos menu/menu.xml
     *
     * onOptionsItemSelected
     * Define las respuestas a las distintas opciones del menú
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemActualizar) {
            mSwipeRefreshLayout.setRefreshing(true);
            new CargaDatosGasolinerasTask(this).execute();
        } else if(item.getItemId()==R.id.itemMisVehiculos) {
            Intent myIntent = new Intent(MainActivity.this, MisVehiculosActivity.class);
            MainActivity.this.startActivity(myIntent);
        }else if (item.getItemId() == R.id.itemNuevoVehiculo) {
            Intent myIntent = new Intent(MainActivity.this, FormActivity.class);
            MainActivity.this.startActivity(myIntent);
        }else if (item.getItemId() == R.id.itemInfo) {
            Intent myIntent = new Intent(MainActivity.this, InfoActivity.class);
            MainActivity.this.startActivity(myIntent);
        }else if (item.getItemId() == R.id.itemFabrica) {
            presenterVehiculos.borra(MainActivity.this);
            Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
            MainActivity.this.startActivity(myIntent);
        }

        return true;
    }




    /**
     * CargaDatosGasolinerasTask
     *
     * Tarea asincrona para obtener los datos de las gasolineras
     * en segundo plano.
     *
     * Redefinimos varios métodos que se ejecutan en el siguiente orden:
     * onPreExecute: activamos el dialogo de progreso
     * doInBackground: solicitamos que el presenter cargue los datos
     * onPostExecute: desactiva el dialogo de progreso,
     *    muestra las gasolineras en formato lista (a partir de un adapter)
     *    y define la acción al realizar al seleccionar alguna de ellas
     *
     * http://www.sgoliver.net/blog/tareas-en-segundo-plano-en-android-i-thread-y-asynctask/
     */
    public class CargaDatosGasolinerasTask extends AsyncTask<Void, Void, Boolean> {


        Activity activity;

        /**
         * Constructor de la tarea asincrona
         * @param activity
         */
        public CargaDatosGasolinerasTask(Activity activity) {
            this.activity = activity;
        }

        /**
         * onPreExecute
         * @deprecated deprecated method
         * Metodo ejecutado de forma previa a la ejecucion de la tarea definida en el metodo doInBackground()
         * Muestra un diálogo de progreso
         */
        @Override @Deprecated
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar
        }

        /**
         * doInBackground
         *
         * Tarea ejecutada en segundo plano
         * Llama al presenter para que lance el método de carga de los datos de las gasolineras
         * @param params
         * @return boolean
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            return presenterGasolineras.cargaDatosGasolineras();
        }

        /**
         * onPostExecute
         * @deprecated deprecated method
         * Se ejecuta al finalizar doInBackground
         * Oculta el diálogo de progreso.
         * Muestra en una lista los datos de las gasolineras cargadas,
         * creando un adapter y pasándoselo a la lista.
         * Define el manejo de la selección de los elementos de la lista,
         * lanzando con una intent una actividad de detalle
         * a la que pasamos un objeto Gasolinera
         *
         * @param res
         */
        @Override @Deprecated
        protected void onPostExecute(Boolean res) {
            Toast toast = null;

            // Si el progressDialog estaba activado, lo oculta
            progressBar.setVisibility(View.GONE);     // To Hide ProgressBar
            mSwipeRefreshLayout.setRefreshing(false);

            // Si se ha obtenido resultado en la tarea en segundo plano
            if (Boolean.TRUE.equals(res)) {
                // Definimos el array adapter
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                LocationRequest mLocationRequest = new LocationRequest();
                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                        .addLocationRequest(mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY));
                Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(MainActivity.this).checkLocationSettings(builder.build());
                result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                        try {
                            task.getResult(ApiException.class);
                            // All location settings are satisfied. The client can initialize location
                            // requests here.
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                requestPermission();
                            }
                            mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                                @Override
                                public void onComplete(@NonNull Task<Location> task)
                                {
                                    Location location = task.getResult();
                                    //Cuando el usuario tiene la ubicacion activada
                                    if (location != null) {
                                        Posicion posUsuario = new Posicion(location.getLatitude(), location.getLongitude());

                                        for(Gasolinera g:presenterGasolineras.getGasolineras()){
                                            g.setDistanciaEnKm(Distancia.distanciaKm(posUsuario,g.getPosicion()));
                                            g.calculaPrecioFinal(PresenterVehiculos.getVehiculoSeleccionado());

                                        }
                                        if (descuentoSi) {
                                            if (hayFiltro((IDescuentoFiltro.class)) == -1) {
                                                listaFiltros.add(descuentoSiFiltro);
                                            }
                                            descuentoSiFiltro.ordena(presenterGasolineras.getGasolineras());
                                        }

                                        if (descuentoNo) {
                                            if (hayFiltro((IDescuentoFiltro.class)) == -1) {
                                                listaFiltros.add(descuentoNoFiltro);
                                            }
                                            descuentoNoFiltro.ordena(presenterGasolineras.getGasolineras());
                                        }

                                        if (gasoleoA) {
                                            if (hayFiltro((ICombustibleFiltro.class)) == -1) {
                                                listaFiltros.add(filtroGasoleA);
                                            }
                                            filtroGasoleA.ordena(presenterGasolineras.getGasolineras());
                                        }

                                        if (gasolina95) {
                                            if (hayFiltro((ICombustibleFiltro.class)) == -1) {
                                                listaFiltros.add(filtroGasolina95);
                                            }
                                            filtroGasolina95.ordena(presenterGasolineras.getGasolineras());
                                        }
                                    }

                                    adapter = new GasolineraArrayAdapter(activity, 0, presenterGasolineras.getGasolineras());
                                    listViewGasolineras.setAdapter(adapter);
                                    adapterFiltros.notifyDataSetChanged();
                                    Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datosConUbicacion), Toast.LENGTH_LONG);
                                    toast.show();
                                    }
                            });

                        } catch (ApiException exception) {
                            switch (exception.getStatusCode()) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied. But could be fixed by showing the
                                    // user a dialog.
                                    try {
                                        // Cast to a resolvable exception.
                                        ResolvableApiException resolvable = (ResolvableApiException) exception;
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        resolvable.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                                    } catch (IntentSender.SendIntentException|ClassCastException e) {
                                        // Ignore the error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied. However, we have no way to fix the
                                    // settings so we won't show the dialog.
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });


                for(Gasolinera g:presenterGasolineras.getGasolineras()){
                    g.calculaPrecioFinal(PresenterVehiculos.getVehiculoSeleccionado());
                }


                adapter = new GasolineraArrayAdapter(activity, 0, presenterGasolineras.getGasolineras());

                if(descuentoSi){
                    if (hayFiltro((IDescuentoFiltro.class) ) == -1) {
                        listaFiltros.add(descuentoSiFiltro);
                    }
                    descuentoSiFiltro.ordena(presenterGasolineras.getGasolineras());
                }

                if(descuentoNo){
                    if (hayFiltro((IDescuentoFiltro.class) ) == -1) {
                        listaFiltros.add(descuentoNoFiltro);
                    }
                    descuentoNoFiltro.ordena(presenterGasolineras.getGasolineras());
                }

                if(gasoleoA){
                    if (hayFiltro((ICombustibleFiltro.class) ) == -1) {
                        listaFiltros.add(filtroGasoleA);
                    }
                    filtroGasoleA.ordena(presenterGasolineras.getGasolineras());
                }

                if(gasolina95){
                    if (hayFiltro((ICombustibleFiltro.class) ) == -1) {
                        listaFiltros.add(filtroGasolina95);
                    }
                    filtroGasolina95.ordena(presenterGasolineras.getGasolineras());
                }


                adapter = new GasolineraArrayAdapter(activity, 0, presenterGasolineras.getGasolineras());
                adapterFiltros = new AdapterFiltros(MainActivity.this, listaFiltros);
                recyclerViewFiltros.setAdapter(adapterFiltros);
                adapterFiltros.notifyDataSetChanged();

                // Cargamos los datos en la lista
                if (!presenterGasolineras.getGasolineras().isEmpty()) {
                    // datos obtenidos con exito
                    listViewGasolineras.setAdapter(adapter);
                    toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_exito), Toast.LENGTH_LONG);
                    if(presenterVehiculos.getVehiculos().size()<=1){
                        Intent myIntent = new Intent(MainActivity.this, PopUpPrimerVehiculoActivity.class);
                        MainActivity.this.startActivity(myIntent);
                    }
                } else {
                    // los datos estan siendo actualizados en el servidor, por lo que no son actualmente accesibles
                    // sucede en torno a las :00 y :30 de cada hora
                    toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_accesibles), Toast.LENGTH_LONG);
                }
            } else {
                Intent myIntent = new Intent(MainActivity.this, NoDatosActivity.class);
                MainActivity.this.startActivity(myIntent);
                // error en la obtencion de datos desde el servidor
                toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_LONG);
            }

            // Muestra el mensaje del resultado de la operación en un toast
            if (toast != null) {
                toast.show();
            }

            /*
             * Define el manejo de los eventos de click sobre elementos de la lista
             * En este caso, al pulsar un elemento se lanzará una actividad con una vista de detalle
             * a la que le pasamos el objeto Gasolinera sobre el que se pulsó, para que en el
             * destino tenga todos los datos que necesita para mostrar.
             * Para poder pasar un objeto Gasolinera mediante una intent con putExtra / getExtra,
             * hemos tenido que hacer que el objeto Gasolinera implemente la interfaz Parcelable
             */
            listViewGasolineras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                    /* Obtengo el elemento directamente de su posicion,
                     * ya que es la misma que ocupa en la lista
                     */

                    Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.pasoDatosGasolinera),
                            presenterGasolineras.getGasolineras().get(position));
                    MainActivity.this.startActivity(myIntent);

                }
            });

            filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                    intent.putExtra("GasoleoA", gasoleoA);
                    intent.putExtra("Gasolina95",gasolina95);
                    intent.putExtra("DescuentoSI",descuentoSi);
                    intent.putExtra("DescuentoNo",descuentoNo);
                    setResult(Activity.RESULT_OK, intent);
                    MainActivity.this.startActivityForResult(intent, 10);
                }
            });
            /*
                On click para el botón Reset en el cual se desactivan todos los filtros
             */
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gasoleoA = false;
                    gasolina95 = false;
                    descuentoSi = false;
                    descuentoNo = false;
                    listaFiltros.clear();
                    adapterFiltros.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(true);
                    new CargaDatosGasolinerasTask(MainActivity.this).execute();
                }
            });





        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && resultCode == Activity.RESULT_OK && data != null){
            gasoleoA = data.getBooleanExtra(FilterActivity.getGasoleoA(), false);
            gasolina95 = data.getBooleanExtra(FilterActivity.getGasolina95(), false);
            descuentoNo = data.getBooleanExtra(FilterActivity.getDescuentoNo(), false);
            descuentoSi = data.getBooleanExtra(FilterActivity.getDescuentoSi(), false);
            new CargaDatosGasolinerasTask(MainActivity.this).execute();
        }
    }
    /*
        Método auxiliar que retorna -1 si no hay un filtro del tipo pasado como parámetro
        en el ArrayList de listaFiltros
     */
    public int hayFiltro(Class<? extends IFiltro> tipo){
        for(int i=0; i<listaFiltros.size();i++){
            if(tipo.isAssignableFrom(listaFiltros.get(i).getClass())){
                return i;
            }
        }
        return -1;
    }




    /*
    ------------------------------------------------------------------
        GasolineraArrayAdapter

        Adaptador para inyectar los datos de las gasolineras
        en el listview del layout principal de la aplicacion
    ------------------------------------------------------------------
    */
    public class GasolineraArrayAdapter extends ArrayAdapter<Gasolinera> {

        private Context context;
        private List<Gasolinera> listaGasolineras;

        // Constructor
        public GasolineraArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
            super(context, resource, objects);
            this.context = context;
            this.listaGasolineras = objects;
        }

        // Llamado al renderizar la lista
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtiene el elemento que se está mostrando
            Gasolinera gasolinera = listaGasolineras.get(position);

            // Indica el layout a usar en cada elemento de la lista
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_gasolinera, null);
            // Asocia las variables de dicho layout
            ImageView logo = view.findViewById(R.id.imageViewLogo);
            TextView rotulo = view.findViewById(R.id.textViewRotulo);
            TextView direccion = view.findViewById(R.id.textViewDireccion);
            TextView TextViewGasoleoA = view.findViewById(R.id.textViewGasoleoA);
            TextView TextViewGasolina95 = view.findViewById(R.id.textViewGasolina95);

            view.setBackgroundColor(Color.WHITE);
            TextViewGasoleoA.setTextColor(Color.BLACK);
            TextViewGasolina95.setTextColor(Color.BLACK);

            if (gasolinera.getTieneDescuento()) {
                view.setBackgroundColor(0xfffffd82);
                TextViewGasoleoA.setTextColor(Color.RED);
                TextViewGasolina95.setTextColor(Color.RED);
            }
            // Y carga los datos del item
            rotulo.setText(gasolinera.getRotulo());
            direccion.setText(gasolinera.getDireccion());
            if (gasolinera.getTieneDescuento()) {
                TextViewGasoleoA.setText(" " + gasolinera.getGasoleoAConDescuento() + getResources().getString(R.string.moneda));
                TextViewGasolina95.setText(" " + gasolinera.getGasolina95ConDescuento() + getResources().getString(R.string.moneda));
            } else {
                TextViewGasoleoA.setText(" " + gasolinera.getGasoleoA() + getResources().getString(R.string.moneda));
                TextViewGasolina95.setText(" " + gasolinera.getGasolina95() + getResources().getString(R.string.moneda));
            }

            // carga icono
            cargaIcono(gasolinera, logo);


            // Si las dimensiones de la pantalla son menores
            // reducimos el texto de las etiquetas para que se vea correctamente
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels < 720) {
                TextView tv = view.findViewById(R.id.textViewGasoleoALabel);
                RelativeLayout.LayoutParams params = ((RelativeLayout.LayoutParams) tv.getLayoutParams());
                params.setMargins(15, 0, 0, 0);
                tv.setTextSize(11);
                TextView tmp;
                tmp = view.findViewById(R.id.textViewGasolina95Label);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.textViewGasoleoA);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.textViewGasolina95);
                tmp.setTextSize(11);
            }

            return view;
        }

        private void cargaIcono(Gasolinera gasolinera, ImageView logo) {
            String rotuleImageID = gasolinera.getRotulo().toLowerCase();

            // Tengo que protegerme ante el caso en el que el rotulo solo tiene digitos.
            // En ese caso getIdentifier devuelve esos digitos en vez de 0.
            int imageID = context.getResources().getIdentifier(rotuleImageID,
                    "drawable", context.getPackageName());

            if (imageID == 0 || TextUtils.isDigitsOnly(rotuleImageID)) {
                imageID = context.getResources().getIdentifier(getResources().getString(R.string.pordefecto),
                        "drawable", context.getPackageName());
            }
            logo.setImageResource(imageID);
        }
    }

    private boolean checkPermissionLocation() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},PERMISSION_REQUEST );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST && grantResults.length > 0){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(MainActivity.this, "Permisos concedidos, reinicie la app", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(MainActivity.this, "Permisos no concedidos, la app no funcionara correctamente", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
        }
    }
}

class ViewHolderJr extends RecyclerView.ViewHolder{

    TextView nombreFiltro;

    public ViewHolderJr(@NonNull View itemView) {
        super(itemView);
        nombreFiltro = itemView.findViewById(R.id.txtNombreFiltro);
    }


}

class AdapterFiltros extends RecyclerView.Adapter<ViewHolderJr>{
    private List<IFiltro> lista;
    private LayoutInflater inflater;

    public AdapterFiltros(Context context, List<IFiltro> lista){
        this.lista = lista;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolderJr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_filtro_activo, parent, false);
        return new ViewHolderJr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJr holder, int position) {
        IFiltro filtro = lista.get(position);
        holder.nombreFiltro.setText(filtro.getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
package com.isunican.proyectobase.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isunican.proyectobase.Model.Descuento;
import com.isunican.proyectobase.Presenter.PresenterDescuentos;
import com.isunican.proyectobase.R;

import java.util.List;

public class ListaDescuentosActivity extends AppCompatActivity {

    RecyclerView recyclerViewDescuentos;
    AdapterDescuentos adapterDescuentos;
    PresenterDescuentos presenterDescuentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_descuentos);

        recyclerViewDescuentos = findViewById(R.id.recyclerViewDescuentos);
        recyclerViewDescuentos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        presenterDescuentos = new PresenterDescuentos();
        presenterDescuentos.cargaDatosDummy();


        adapterDescuentos = new AdapterDescuentos(ListaDescuentosActivity.this,presenterDescuentos.getDescuentos());
        recyclerViewDescuentos.setAdapter(adapterDescuentos);
        adapterDescuentos.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.itemMisVehiculos) {
            Intent myIntent = new Intent(ListaDescuentosActivity.this, MisVehiculosActivity.class);
            ListaDescuentosActivity.this.startActivity(myIntent);
        }else if (item.getItemId() == R.id.itemNuevoVehiculo) {
            Intent myIntent = new Intent(ListaDescuentosActivity.this, FormActivity.class);
            ListaDescuentosActivity.this.startActivity(myIntent);
        }else if (item.getItemId() == R.id.itemInfo) {
            Intent myIntent = new Intent(ListaDescuentosActivity.this, InfoActivity.class);
            ListaDescuentosActivity.this.startActivity(myIntent);
        }else if(item.getItemId()==R.id.itemGasolineras) {
            Intent myIntent = new Intent(ListaDescuentosActivity.this, MainActivity.class);
            ListaDescuentosActivity.this.startActivity(myIntent);
        }else if (item.getItemId() == R.id.itemDescuentos){
            Intent myIntent = new Intent(ListaDescuentosActivity.this, ListaDescuentosActivity.class);
            ListaDescuentosActivity.this.startActivity(myIntent);
        }

        return true;
    }

}

class ViewHolderDesc extends RecyclerView.ViewHolder{

    TextView codigoDescuento;
    TextView porcentajeDescuento;

    public ViewHolderDesc(@NonNull View itemView) {
        super(itemView);
        codigoDescuento = itemView.findViewById(R.id.textViewCodDescuento);
        porcentajeDescuento = itemView.findViewById(R.id.PorcentajeDescuento);
    }


}

class AdapterDescuentos extends RecyclerView.Adapter<ViewHolderDesc> {
    private List<Descuento> lista;
    private LayoutInflater inflater;

    public AdapterDescuentos(Context context, List<Descuento> lista) {
        this.lista = lista;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolderDesc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_descuento, parent, false);
        return new ViewHolderDesc(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDesc holder, int position) {
        String codigo = lista.get(position).getCodigo();
        holder.codigoDescuento.setText(codigo);
        int descuento = lista.get(position).getPorcentaje();
        holder.porcentajeDescuento.setText("-"+Integer.toString(descuento)+"%");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
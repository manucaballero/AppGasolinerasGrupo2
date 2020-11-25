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
import com.isunican.proyectobase.R;

import java.util.ArrayList;
import java.util.List;

public class ListaDescuentosActivity extends AppCompatActivity {

    List listaDescuentos;
    RecyclerView recyclerViewDescuentos;
    AdapterDescuentos adapterDescuentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_descuentos);

        listaDescuentos = new ArrayList<String>();
        listaDescuentos.add("Descuento 1");


        recyclerViewDescuentos = findViewById(R.id.recyclerViewDescuentos);

        recyclerViewDescuentos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        adapterDescuentos = new AdapterDescuentos(ListaDescuentosActivity.this,listaDescuentos);
        recyclerViewDescuentos.setAdapter(adapterDescuentos);
        adapterDescuentos.notifyDataSetChanged();
        int i = adapterDescuentos.getItemCount();

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
        /*}else if (item.getItemId() == R.id.itemFabrica) {
            presenterVehiculos.borra(ListaDescuentosActivity.this);
            Intent myIntent = new Intent(ListaDescuentosActivity.this, MainActivity.class);
            ListaDescuentosActivity.this.startActivity(myIntent);*/
        }else if (item.getItemId() == R.id.itemDescuentos){
            Intent myIntent = new Intent(ListaDescuentosActivity.this, ListaDescuentosActivity.class);
            ListaDescuentosActivity.this.startActivity(myIntent);
        }

        return true;
    }

}

class ViewHolderDesc extends RecyclerView.ViewHolder{

    TextView nombreDescuento;

    public ViewHolderDesc(@NonNull View itemView) {
        super(itemView);
        nombreDescuento = itemView.findViewById(R.id.textViewNombreDescuento);
    }


}

class AdapterDescuentos extends RecyclerView.Adapter<ViewHolderDesc> {
    private List<String> lista;
    private LayoutInflater inflater;

    public AdapterDescuentos(Context context, List<String> lista) {
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
        String des = lista.get(position);
        holder.nombreDescuento.setText(des);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
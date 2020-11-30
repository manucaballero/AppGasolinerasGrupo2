package com.isunican.proyectobase.Views;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isunican.proyectobase.Model.IFiltro;
import com.isunican.proyectobase.R;

import java.util.List;

public class AdapterFiltros extends RecyclerView.Adapter<ViewHolderJr>{
    private final List<IFiltro> lista;
    private final LayoutInflater inflater;
    Activity act;

    public AdapterFiltros(Activity act, List<IFiltro> lista){
        this.lista = lista;
        this.inflater = LayoutInflater.from(act);
        this.act = act;
    }

    @NonNull
    @Override
    public ViewHolderJr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_filtro_activo, parent, false);
        return new ViewHolderJr(view, act);
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

package com.example.practicaley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.articuloModel;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolderArticulos> {

    List<articuloModel> listaArticulos;

    public AdaptadorArticulos(List<articuloModel> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    @Override
    public ViewHolderArticulos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pasilloconsulta, null, false);
        return new ViewHolderArticulos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderArticulos holder, int position) {
        holder.nombreArticulo.setText(listaArticulos.get(position).getNombreArticulo());
        holder.cantidad.setText(listaArticulos.get(position).cantidad);
        holder.load.setText(listaArticulos.get(position).load);
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    public class ViewHolderArticulos extends RecyclerView.ViewHolder {

        TextView nombreArticulo, cantidad, load, reserva;

        public ViewHolderArticulos(View itemView) {
            super(itemView);
            nombreArticulo = (TextView) itemView.findViewById(R.id.tvNombreArticuloConsulta);
            cantidad = (TextView) itemView.findViewById(R.id.tvCantidadConsultaPasillo);
            load = (TextView) itemView.findViewById(R.id.tvLoadConsultaPasillo);
            reserva = (TextView) itemView.findViewById(R.id.tvReservaConsultaPasillo);
        }
    }


}

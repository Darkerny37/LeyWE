package com.example.practicaley.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaley.R;

import java.util.List;

import Model.articuloModel;
import retrofit2.Callback;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolderArticulos> {

    List<articuloModel> listaArticulos;
    Context context;

    public AdaptadorArticulos(Context context, List<articuloModel> listaArticulos) {
        this.context = context;
        this.listaArticulos = listaArticulos;
    }

    @Override
    public ViewHolderArticulos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_pasilloconsulta, parent, false);
        return new AdaptadorArticulos.ViewHolderArticulos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderArticulos holder, int position) {
        holder.nombreArticulo.setText(listaArticulos.get(position).getNombreArticulo());
        holder.cantidad.setText(Long.toString(listaArticulos.get(position).getCantidad()));
        holder.load.setText(Integer.toString(listaArticulos.get(position).getLoad()));

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

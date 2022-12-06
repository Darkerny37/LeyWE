package com.example.practicaley.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaley.R;

import java.util.List;

import Model.reservaModel;

public class AdaptadorReservas
        extends RecyclerView.Adapter<AdaptadorReservas.ViewHolderReservas>
            implements View.OnClickListener{

    List<reservaModel> listaReservas;
    Context context;

    private View.OnClickListener listener;

    public AdaptadorReservas(Context context, List<reservaModel> listaReservas){
        this.context = context;
        this.listaReservas = listaReservas;
    }

    @Override
    public ViewHolderReservas onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_ubicacion, parent, false);

        view.setOnClickListener(this);

        return new AdaptadorReservas.ViewHolderReservas(view);//view?
    }

    @Override
    public void onBindViewHolder( AdaptadorReservas.ViewHolderReservas holder, int position) {
        holder.reserva.setText(listaReservas.get(position).nombreReserva);
    }

    @Override
    public int getItemCount() { return listaReservas.size(); }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderReservas extends RecyclerView.ViewHolder{

        TextView reserva;

        public ViewHolderReservas(View itemView) {
            super(itemView);
            reserva = (TextView) itemView.findViewById(R.id.tvReservaUbicacion);
        }



    }
}

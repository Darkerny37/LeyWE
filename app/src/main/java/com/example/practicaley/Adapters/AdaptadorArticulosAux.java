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

import Interfaces.serviceRetrofit;
import Model.articuloAuxModel;
import Model.articuloModel;
import Model.loadModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorArticulosAux extends RecyclerView.Adapter<AdaptadorArticulosAux.ViewHolderArticulosAux>{

    List<articuloAuxModel> listaArticulosAux;
    Context context;

    public AdaptadorArticulosAux(Context context, List<articuloAuxModel> listaArticulosAux) {
        this.context = context;
        this.listaArticulosAux = listaArticulosAux;
    }

    @Override
    public ViewHolderArticulosAux onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_levantararticulo, parent, false);
        return new AdaptadorArticulosAux.ViewHolderArticulosAux(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderArticulosAux holder, int position) {
        holder.nombreArticulo.setText(listaArticulosAux.get(position).getNombreArticuloAux());
        holder.cantidad.setText(Integer.toString(listaArticulosAux.get(position).getCantidad()));
        holder.numSerie.setText(listaArticulosAux.get(position).getNumeroSerie());

        //holder.consultarLoad(listaArticulosAux.get(position).getLoad());

    }

    @Override
    public int getItemCount() {
        return listaArticulosAux.size();
    }

    public class ViewHolderArticulosAux extends RecyclerView.ViewHolder {

        TextView nombreArticulo, cantidad, load, numSerie;

        public ViewHolderArticulosAux(View itemView) {
            super(itemView);
            nombreArticulo = (TextView) itemView.findViewById(R.id.tvNombreArticuloConsulta);
            cantidad = (TextView) itemView.findViewById(R.id.tvCantidadLevantar);
            load = (TextView) itemView.findViewById(R.id.tvLoadLevantar);
            numSerie = (TextView) itemView.findViewById(R.id.tvNumSerieLevantar);
        }

        private void consultarLoad(int id){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://leybodega.000webhostapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                    .build();

            serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

            Call<loadModel> call = loadServicio.consultaLoadId(id);

            call.enqueue(new Callback<loadModel>() {
                @Override
                public void onResponse(Call<loadModel> call, Response<loadModel> response) {
                    if(response.body().idLoad!= 0){
                        load.setText(response.body().codigoBarras);
                    }
                }

                @Override
                public void onFailure(Call <loadModel> call, Throwable t) {

                }
            });
        }


    }
}

package com.example.practicaley.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaley.R;

import java.util.List;

import Interfaces.serviceRetrofit;
import Model.loadModel;
import Model.tareaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorTareas
        extends RecyclerView.Adapter<AdaptadorTareas.ViewHolderTareas>
            implements  View.OnClickListener{

    List<tareaModel> listaTareas;
    Context context;

    private View.OnClickListener  listener;

    public AdaptadorTareas(Context context, List<tareaModel> listaTareas) {
        this.context = context;
        this.listaTareas = listaTareas;
    }

    @Override
    public ViewHolderTareas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_tareaconsulta, parent, false);

        // para el setOnClick
        view.setOnClickListener(this);
        //fin

        return new AdaptadorTareas.ViewHolderTareas(view);
    }


    @Override
    public void onBindViewHolder(AdaptadorTareas.ViewHolderTareas holder, int position) {
        holder.tvDescTareas.setText(listaTareas.get(position).descripcion);
        holder.consultarLoad(listaTareas.get(position).load);
        //holder.tvLoadTareas.setText(listaTareas.get(position).load);
        //Obtener el nombre de load 1. Consulta || Crear un nuevo modelo
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    // para el setOnClickListener
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }
    //fin


    public class ViewHolderTareas extends RecyclerView.ViewHolder {

        TextView tvDescTareas, tvLoadTareas;

        public ViewHolderTareas(View itemView) {
            super(itemView);
            tvDescTareas = (TextView) itemView.findViewById(R.id.tvDescTarea);
            tvLoadTareas = (TextView) itemView.findViewById(R.id.tvLoadTareas);
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
                    if(response.body().idLoad != 0){
                        tvLoadTareas.setText(response.body().codigoBarras);
                    }
                }

                @Override
                public void onFailure(Call <loadModel> call, Throwable t) {

                }
            });
        }

    }


}


package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Interfaces.serviceRetrofit;
import Model.articuloModel;
import Model.loadModel;
import Model.pasilloModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarAR extends AppCompatActivity {
    private Button aCancelar, aAceptar;
    public loadModel load = new loadModel();
    public TextView idArticulo, nomArticulo, txtvload, reserva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_ar);

        //Labels
        idArticulo = (TextView)findViewById(R.id.txtvIDArticulo);
         nomArticulo = (TextView)findViewById(R.id.txtvNombre);
         txtvload = (TextView)findViewById(R.id.txtvLoad);
         reserva = (TextView)findViewById(R.id.txtvReserva);


        aCancelar = findViewById(R.id.aCancelar);
        aAceptar = findViewById(R.id.aAceptar);

        consultaArticuloporLoad(getIntent().getExtras().getString("idLoad"));

        aCancelar.setOnClickListener(view -> startActivity(new Intent(AgregarAR.this,AgregarCodigo.class)));
        aAceptar.setOnClickListener(view -> startActivity(new Intent(AgregarAR.this, MenuAdmin.class)));
    }

    private void consultaArticuloporLoad(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit articuloServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call<articuloModel> call = articuloServicio.consultaPorLoad(Long.parseLong(id));

        call.enqueue(new Callback<articuloModel>() {
            @Override
            public void onResponse(Call<articuloModel> call, Response<articuloModel> response) {
                idArticulo.setText(response.body().idArticulo);
                nomArticulo.setText(response.body().nombreArticulo);
                txtvload.setText(response.body().load);
                reserva.setText(getIntent().getExtras().getString("idReserva"));
            }

            @Override
            public void onFailure(Call<articuloModel> call, Throwable t) {

            }
        });
    }
}

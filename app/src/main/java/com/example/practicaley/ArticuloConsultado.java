package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Interfaces.serviceRetrofit;
import Model.articuloModel;
import Model.loadModel;
import Model.pasilloModel;
import Model.reservaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticuloConsultado extends AppCompatActivity {
    private Button acAceptar;
    public articuloModel articulo = new articuloModel();
    public TextView reservas, pasillo;
    public Integer load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articulo_consultado);

        //Labels
        TextView idArt = (TextView)findViewById(R.id.txtidArticulo);
        TextView nombreArt = (TextView) findViewById(R.id.txtnombreArticulo);
        TextView cantArt = (TextView) findViewById(R.id.txtCantidad);
        TextView infoArt = (TextView) findViewById(R.id.txtInfo);
        reservas = (TextView) findViewById(R.id.textView8);
        pasillo = (TextView) findViewById(R.id.textView10);

        idArt.setText(String.valueOf(getIntent().getExtras().getInt("idArticulo")));
        nombreArt.setText(getIntent().getExtras().getString("nombreArticulo"));
        cantArt.setText(String.valueOf(getIntent().getExtras().getInt("cantidad")));
        infoArt.setText(getIntent().getExtras().getString("numeroSerie"));
        if(getIntent().hasExtra("load"))
            load = Integer.valueOf(getIntent().getExtras().getString("load"));

        if(load != null){
            //Hay load por ende se rellenan los campos
            consultarLoad(load);
        }else {
            reservas.setText("Sin reserva");
            pasillo.setText("Sin pasillo");
        }
        acAceptar = findViewById(R.id.acAceptar);

        acAceptar.setOnClickListener(view -> startActivity(new Intent(ArticuloConsultado.this, MenuAdmin.class)));
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
                    //llamar reserva
                    consultarReserva(response.body().reserva);
                }
            }

            @Override
            public void onFailure(Call <loadModel> call, Throwable t) {

            }
        });
    }

    private void consultarReserva(int id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit reservaServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call <reservaModel> call = reservaServicio.consultaReserva(id);

        call.enqueue(new Callback <reservaModel>() {
            @Override
            public void onResponse(Call<reservaModel> call, Response<reservaModel> response) {
                if(response.body().idReserva!= 0){
                    reservas.setText(response.body().nombreReserva);
                    consultaPasillo(response.body().pasillo);
                }
            }

            @Override
            public void onFailure(Call <reservaModel> call, Throwable t) {

            }
        });
    }

    private void consultaPasillo(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit pasilloServicio = retrofit.create(serviceRetrofit.class);

        Call<pasilloModel> call = pasilloServicio.consultaPasilloUnoId(id);

        call.enqueue(new Callback<pasilloModel>() {
            @Override
            public void onResponse(Call<pasilloModel> call, Response<pasilloModel> response) {
                if (response.isSuccessful()){
                    if (response.body().idPasillo != 0){   // body() respuesta del request que viene del servicio
                        //Activity para poner el codigo de barras
                        pasillo.setText(response.body().codigoBarras);

                    }else{
                        Toast.makeText(getApplicationContext(), "Pasillo no valido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<pasilloModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

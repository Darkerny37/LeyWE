package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;

import Interfaces.serviceRetrofit;
import Model.articuloModel;
import Model.loadModel;
import Model.msgModel;
import Model.msgModelEliminarArticulo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EliminarER extends AppCompatActivity {
    private Button btnCanE, btnAcE;
    public TextView nomArticulo, idArticulo, txtvload, cantidad;
    public String numSerie,loadCdgBarras;
    public int idArticuloParEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_er);

        //Labels
        nomArticulo = (TextView)findViewById(R.id.txtvNombreEC);
        idArticulo = (TextView)findViewById(R.id.txtvCodBarrasArtEC);
        txtvload = (TextView)findViewById(R.id.txtvLoadEC);
        cantidad = (TextView)findViewById(R.id.txtvCantidadEC);


        btnCanE = findViewById(R.id.btnCanE);
        btnAcE = findViewById(R.id.btnAcE);

        obtenerLoadConReserva(getIntent().getExtras().getInt("idReserva"));

        btnCanE.setOnClickListener(view -> startActivity(new Intent(EliminarER.this,EliminarCodigo.class)));
        btnAcE.setOnClickListener( view -> {

               eliminarArticulo(idArticuloParEliminar);


        });

    }

    private void obtenerLoadConReserva(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);

        Call <List<loadModel>> call = loadServicio.consultaLoadPorReserva(id);

        call.enqueue(new Callback <List<loadModel>>() {
            @Override
            public void onResponse(Call<List<loadModel>> call, Response<List<loadModel>> response) {
                if (response.isSuccessful()){
                    List<loadModel> lst = response.body();

                    txtvload.setText(lst.get(0).codigoBarras);
                    consultaArticuloporLoad(lst.get(0).idLoad);

                    //consultaArticuloporLoad(response.body().idLoad);
                }
            }

            @Override
            public void onFailure(Call<List<loadModel>> call, Throwable t) {
                Toast.makeText(EliminarER.this, "Error"+ t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void consultaArticuloporLoad(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit articuloServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call <List<articuloModel>> call = articuloServicio.consultaPorLoad(id);

        call.enqueue(new Callback <List<articuloModel>>() {
            @Override
            public void onResponse(Call<List<articuloModel>> call, Response<List<articuloModel>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() != 0) {
                        List<articuloModel> lst = response.body();

                        Toast.makeText(getApplicationContext(), lst.get(0).nombreArticulo, Toast.LENGTH_LONG).show();

                        idArticulo.setText(lst.get(0).numeroSerie);
                        nomArticulo.setText(lst.get(0).nombreArticulo);
                        cantidad.setText(Integer.toString(lst.get(0).cantidad));
                        idArticuloParEliminar = lst.get(0).idArticulo;
                    }else{
                        Toast.makeText(getApplicationContext(), "No existen articulos con ese load", Toast.LENGTH_LONG).show();
                    }
            }else {
                    Toast.makeText(getApplicationContext(), "Problema en la ruta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call <List<articuloModel>> call, Throwable t) {

            }
        });
    }

    private void eliminarArticulo(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit articuloServicio = retrofit.create(serviceRetrofit.class);

        Call <msgModelEliminarArticulo> call = articuloServicio.eliminarArticulo(id);

        call.enqueue(new Callback<msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo> call, Response<msgModelEliminarArticulo> response) {
                if(response.isSuccessful()){
                    if(!response.body().message.equals("")){
                        if(response.body().message.equals("Se elimino correctamente")){
                            Toast.makeText(EliminarER.this, response.body().message, Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(EliminarER.this,MenuAdmin.class));
                        }
                    }
                }else
                {
                    Toast.makeText(EliminarER.this, "Error"+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<msgModelEliminarArticulo> call, Throwable t) {
                Toast.makeText(EliminarER.this, "Error"+ t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
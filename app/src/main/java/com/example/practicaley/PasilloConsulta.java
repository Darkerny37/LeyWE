package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Interfaces.serviceRetrofit;
import Model.articuloModel;
import Model.reservaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PasilloConsulta extends AppCompatActivity {
    private Button btnPR;
    public int idPasillo;

    // Para el Recycler View
    ArrayList<articuloModel> listaArticulos;
    RecyclerView recyclerArticulos;
    // fin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasillo_consulta);

        listaArticulos = new ArrayList<>();
        recyclerArticulos = (RecyclerView) findViewById(R.id.listRV);
        recyclerArticulos.setLayoutManager(new LinearLayoutManager(this));

        llenarArticulos();

        AdaptadorArticulos adapter = new AdaptadorArticulos(listaArticulos);
        recyclerArticulos.setAdapter(adapter);
        
        /*
        //Traer parametro de mi intent (consulta p codigo)
        String nombrePasillo = getIntent().getExtras().getString("nombrePasillo");
        idPasillo = getIntent().getExtras().getInt("idPasillo");

        TextView nombre = findViewById(R.id.txtNombrePasillo);
        nombre.setText(nombrePasillo);

        btnPR = findViewById(R.id.btnPR);
        //Llenar la lista 1. Traer en una consulta las reservas (reserva)
        //Toast.makeText(getApplicationContext(), "El pasillo tiene el id: " + idPasillo, Toast.LENGTH_LONG).show();

        consultaReservaPorPasillo(idPasillo);
        btnPR.setOnClickListener(view -> startActivity(new Intent(PasilloConsulta.this,MenuAdmin.class)));
        
         */

    }

    private void llenarArticulos() {
        //listaArticulos.add(new articuloModel();
    }

    
    
    /*
    private void consultaReservaPorPasillo(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit reservaServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call<List<reservaModel>> call = reservaServicio.consultaReservaPasillo(id);

        call.enqueue(new Callback<List<reservaModel>>() {
            @Override
            public void onResponse(Call<List<reservaModel>> call, Response<List<reservaModel>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() != 0){

                        List<reservaModel> lst = response.body();

                        Toast.makeText(getApplicationContext(), lst.get(0).nombreReserva, Toast.LENGTH_LONG).show();

                        //Llenar la lista en el recycle view se pone el onclick se pasa el id de la reserva que selecciono
                        //reservaList = response.body();
                        //mAdatper = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reservaList);
                        //listv.setAdapter(mAdatper);
                    }else{
                        Toast.makeText(getApplicationContext(), "Pasillo no valido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<reservaModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    */

}
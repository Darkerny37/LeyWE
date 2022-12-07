package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaley.Adapters.AdaptadorArticulosAux;

import java.util.List;

import Interfaces.serviceRetrofit;
import Model.articuloAuxModel;
import Model.pasilloModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LevantarArticulos extends AppCompatActivity{
    private Button botonLevantarArticulo, botonLevantarInventario;
    private ListView ListaLev;
    public RecyclerView rvArticulosAux;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levantar_articulos);

        botonLevantarArticulo = findViewById(R.id.botonLevantarArticulo);
        botonLevantarInventario = findViewById(R.id.botonLevantarInventario);
        rvArticulosAux = findViewById(R.id.ListaLev);

        id = getIntent().getExtras().getInt("idPasillo");
        consultaPasillo(id);
        consultaArticusloAux(id);

        botonLevantarArticulo.setOnClickListener(view -> {
            Intent i = new Intent(LevantarArticulos.this, LevNuevoArticulo.class);
            i.putExtra("idPasillo", id);
            startActivity(i);
        });
        botonLevantarInventario.setOnClickListener(view -> startActivity(new Intent(LevantarArticulos.this,MenuRecibidor.class)));
    }


    private void consultaArticusloAux(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit pasilloServicio = retrofit.create(serviceRetrofit.class);

        Call<List<articuloAuxModel>> call = pasilloServicio.consultarArticulosAuxPorPasillo(id);

        call.enqueue(new Callback<List<articuloAuxModel>>() {
            @Override
            public void onResponse(Call<List<articuloAuxModel>>call, Response<List<articuloAuxModel>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() != 0){   // body() respuesta del request que viene del servicio
                        //Consulta articulos aux
                        AdaptadorArticulosAux adapter = new AdaptadorArticulosAux(getApplicationContext(), response.body());
                        rvArticulosAux.setAdapter(adapter);
                        rvArticulosAux.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    }else{
                        Toast.makeText(getApplicationContext(), "No hay articulos contados aun", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<articuloAuxModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
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
                        botonLevantarArticulo.setText("Agregar inventario pasillo: " + response.body().nombrePasillo);
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

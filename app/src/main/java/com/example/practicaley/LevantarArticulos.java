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
import Model.articuloModel;
import Model.msgModelEliminarArticulo;
import Model.pasilloModel;
import Model.reporteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LevantarArticulos extends AppCompatActivity{
    private Button botonLevantarArticulo, botonLevantarInventario;
    private ListView ListaLev;
    public RecyclerView rvArticulosAux;
    public int id, i;
    public List<articuloAuxModel> articuloAuxModels; public List<articuloModel> articuloModels;
    public reporteModel reporte = new reporteModel();
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levantar_articulos);

        botonLevantarArticulo = findViewById(R.id.botonLevantarArticulo);
        botonLevantarInventario = findViewById(R.id.botonLevantarInventario);
        rvArticulosAux = findViewById(R.id.ListaLev);

        id = getIntent().getExtras().getInt("idPasillo");

        consultaPasillo(id);
        consultaArticulosAux(id);

        botonLevantarArticulo.setOnClickListener(view -> {
            Intent i = new Intent(LevantarArticulos.this, LevNuevoArticulo.class);
            i.putExtra("idPasillo", id);
            startActivity(i);
        });
        botonLevantarInventario.setOnClickListener(view -> {
            //Traer ArticuloAuxModel
            // ArticuloModel
            consultaListaArticulosAux();

            //startActivity(new Intent(LevantarArticulos.this,MenuRecibidor.class));
        });
    }


    private void consultaArticulosAux(int id){
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

    private void consultaListaArticulosAux(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit pasilloServicio = retrofit.create(serviceRetrofit.class);

        Call<List<articuloAuxModel>> call = pasilloServicio.consultaAriculosAuxConsultar();

        call.enqueue(new Callback<List<articuloAuxModel>>() {
            @Override
            public void onResponse(Call<List<articuloAuxModel>>call, Response<List<articuloAuxModel>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() != 0){   // body() respuesta del request que viene del servicio
                        //Consulta articulos aux
                        articuloAuxModels = response.body();
                        Toast.makeText(getApplicationContext(), "Pase por consulta lista articulo aux", Toast.LENGTH_LONG).show();
                        //Consulta por nombre
                        consulta(articuloAuxModels);
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

    private void consultaArticulosPorNombre(List<articuloAuxModel> models){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit pasilloServicio = retrofit.create(serviceRetrofit.class);

            Call<articuloModel> call = pasilloServicio.consultaArticulosPorNombre("Axe");

            call.enqueue(new Callback<articuloModel>() {
                @Override
                public void onResponse(Call<articuloModel>call, Response<articuloModel> response) {
                    if (response.isSuccessful()){
                        if (response.body().idArticulo != 0){   // body() respuesta del request que viene del servicio
                            //Consulta articulos aux
                            articuloModels.add(response.body());
                            //Consulta por nombre
                        }else{
                            Toast.makeText(getApplicationContext(), "No hay articulos contados aun", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<articuloModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
                }
            });


    }

    private void consulta(List<articuloAuxModel> models){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit consultaCdgService = retrofit.create(serviceRetrofit.class);

            Call<articuloModel> call = consultaCdgService.consultaPorNumSerie(Long.parseLong(models.get(0).numeroSerie));

            call.enqueue(new Callback<articuloModel>() {
                 @Override
                 public void onResponse(Call<articuloModel> call, Response<articuloModel> response) {
                     if(response.isSuccessful()){
                         if(response.body().idArticulo != 0){
                             //articuloModels.add(response.body());
                             //Insert del
                             reporte.cantidadSistema = response.body().cantidad;
                             reporte.articulo = response.body().idArticulo;
                             reporte.cantidadFisica = articuloAuxModels.get(0).cantidad;
                             reporte.usuario = 2;
                             agregarReporte(reporte);
                             //Toast.makeText(getApplicationContext(), "Jalo", Toast.LENGTH_LONG).show();

                         }else {
                             Toast.makeText(getApplicationContext(), "Articulo no valido", Toast.LENGTH_LONG).show();

                         }

                     }else{
                         Toast.makeText(getApplicationContext(), "Problemaaa" + response.code(),Toast.LENGTH_LONG).show();
                     }
                 }

                 @Override
                 public void onFailure(Call<articuloModel> call, Throwable t) {
                     Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
                 }
             }
            );

    }


    private void agregarReporte(reporteModel rep){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit pasilloServicio = retrofit.create(serviceRetrofit.class);

        Call<msgModelEliminarArticulo> call = pasilloServicio.agregarReporte(rep);

        call.enqueue(new Callback<msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo>call, Response<msgModelEliminarArticulo> response) {
                if (response.isSuccessful()){
                    if (!response.body().message.equals("")){   // body() respuesta del request que viene del servicio
                        Toast.makeText(getApplicationContext(), "Estoy en agregar reporte", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(), "No hay articulos contados aun", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<msgModelEliminarArticulo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
            }
        });


    }



}

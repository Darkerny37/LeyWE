package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.example.practicaley.Adapters.AdaptadorTareas;

import java.util.List;

import Interfaces.serviceRetrofit;
import Model.pasilloModel;
import Model.tareaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TareasMontacarga extends AppCompatActivity {
    private Button btnRegLis;
    public RecyclerView RVTareas;
    public List<tareaModel> tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montacarga_tareas);

        btnRegLis = findViewById(R.id.btnRegLis);
        RVTareas = findViewById(R.id.listU);
        consultarTarea();

        //Lenar tareas
        btnRegLis.setOnClickListener(view -> startActivity(new Intent(TareasMontacarga.this,ReservaNewUbicacion.class)));

    }

    private void consultarTarea(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);

        Call<List<tareaModel>> call = loadServicio.consultarTareas();

        call.enqueue(new Callback<List<tareaModel>>() {
            @Override
            public void onResponse(Call<List<tareaModel>> call, Response<List<tareaModel>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() != 0){   // body() respuesta del request que viene del servicio
                        tareas = response.body();

                        AdaptadorTareas adapter = new AdaptadorTareas(getApplicationContext(), tareas);
                        RVTareas.setAdapter(adapter);
                        RVTareas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        //para setOnClickListener
                        adapter.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                /*

                                Intent i = new Intent(TareasMontacarga.this, AgregarTareaReserva.class);
                                i.putExtra("idTarea", id);
                                startActivity(i);
                                */
                                //startActivity(new Intent(TareasMontacarga.this, ReservaNewUbicacion.class));
                                int itemPosition = RVTareas.getChildLayoutPosition(view);
                                int id = tareas.get(itemPosition).idTarea;
                                String desc = tareas.get(itemPosition).descripcion;

                                Intent i = new Intent(TareasMontacarga.this, ReservaNewUbicacion.class);
                                i.putExtra("idTarea", id);
                                i.putExtra("descripcionTarea", desc);

                                startActivity(i);
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(), "No hay tareas", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<tareaModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
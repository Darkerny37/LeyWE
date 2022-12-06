package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicaley.Adapters.AdaptadorReservas;
import com.example.practicaley.Adapters.AdaptadorTareas;

import java.util.List;

import Interfaces.serviceRetrofit;
import Model.reservaModel;
import Model.tareaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservaNewUbicacion extends AppCompatActivity {
    private Button btnUbLis;
    private  TextView reserva;
    public int idTarea; public String desc;
    public RecyclerView rvReservas;
    public  List<reservaModel> responseReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_newub);
        idTarea= getIntent().getExtras().getInt("idTarea");
        desc = getIntent().getExtras().getString("descripcionTarea");

        rvReservas = (RecyclerView) findViewById(R.id.listU);
        consultarReservas();
        btnUbLis = findViewById(R.id.btnUbLis);

        btnUbLis.setOnClickListener(view -> startActivity(new Intent(ReservaNewUbicacion.this,ConfirmarUbicacion.class)));

    }

    private void consultarReservas()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit reservaServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call<List<reservaModel>> call = reservaServicio.consultaReservas();

        call.enqueue(new Callback<List<reservaModel>>() {
            @Override
            public void onResponse(Call<List<reservaModel>> call, Response<List<reservaModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().size() !=0){
                        responseReservas =response.body();
                        //Mandar a llamar el adaptador
                        AdaptadorReservas adapter = new AdaptadorReservas(getApplicationContext(), responseReservas);
                        rvReservas.setAdapter(adapter);
                        rvReservas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        //para setOnClickListener
                        adapter.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                int itemPosition = rvReservas.getChildLayoutPosition(view);
                                int idReserva = responseReservas.get(itemPosition).idReserva;
                                String nombreReserva = responseReservas.get(itemPosition).nombreReserva;

                                Intent i = new Intent(ReservaNewUbicacion.this, ReservaNewUbicacion.class);
                                i.putExtra("idReserva", idReserva);
                                i.putExtra("NombreReserva", nombreReserva);
                                i.putExtra("descripcionTarea", desc);
                                i.putExtra("idTarea", idTarea);
                                i.putExtra("idPasillo", responseReservas.get(itemPosition).pasillo);
                                startActivity(i);

                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(), "No hay reservas disponibles", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call <List<reservaModel>> call, Throwable t) {

            }
        });
    }



}
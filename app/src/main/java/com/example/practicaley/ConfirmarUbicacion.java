package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Interfaces.serviceRetrofit;
import Model.loadModel;
import Model.msgModelEliminarArticulo;
import Model.pasilloModel;
import Model.tareaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfirmarUbicacion extends AppCompatActivity {
    private Button btnCan, btnAc;
    public TextView pasillo, reserva, tarea;
    public tareaModel tareaM = new tareaModel();
    public int idTareaExtra, idLoadExtra, idReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_ub);

        btnCan = findViewById(R.id.btnCan);
        btnAc = findViewById(R.id.btnAc);
        pasillo = findViewById(R.id.tvPasilloUbi);
        reserva = findViewById(R.id.tvReservaUbi);
        tarea = findViewById(R.id.tvTareaUbi);

        reserva.setText(getIntent().getExtras().getString("NombreReserva"));
        tarea.setText(getIntent().getExtras().getString("descripcionTarea"));

        tareaM.descripcion = getIntent().getExtras().getString("descripcionTarea");
        idTareaExtra = getIntent().getExtras().getInt("idTarea");
        idLoadExtra = getIntent().getExtras().getInt("idLoad");
        idReserva = getIntent().getExtras().getInt("idReserva");

        consultaPasillo(getIntent().getExtras().getInt("idPasillo"));

        btnCan.setOnClickListener(view -> startActivity(new Intent(ConfirmarUbicacion.this,ReservaNewUbicacion.class)));
        btnAc.setOnClickListener(view -> {
            //Enviar tarea
            tareaM.load=idLoadExtra;
            enviarTarea(idTareaExtra, tareaM);
            //guardar reserva con el load que tiene la tarea
            //obtenerLoadConReserva(getIntent().getExtras().getInt("idReserva"));
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
                    if(response.body().size() !=0 ){
                        Toast.makeText(getApplicationContext(), "load con reserva valido", Toast.LENGTH_LONG).show();

                        List<loadModel> lst = response.body();

                        tareaM.load=lst.get(0).idLoad;

                        enviarTarea(idTareaExtra, tareaM);
                    }else{
                        Toast.makeText(getApplicationContext(), "load con reserva no valido", Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Problema " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<loadModel>> call, Throwable t) {
                Toast.makeText(ConfirmarUbicacion.this, "Error"+ t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void enviarTarea(int id, tareaModel tarea){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit servicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call<msgModelEliminarArticulo> call = servicio.modificarTareaLoad(id, tarea);

        call.enqueue(new Callback<msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo> call, Response<msgModelEliminarArticulo> response) {
                if(response.isSuccessful()){
                    if(!response.body().message.equals("")){
                        if(response.body().message.equals("Se editaron los registros correctamente"))
                        {
                            Toast.makeText(getApplicationContext(), "Se agrego tarea a load", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ConfirmarUbicacion.this,MenuMontacarga.class));
                            consultarLoad(idLoadExtra);
                        }
                    }else{
                        //Redirigir atras
                        Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ConfirmarUbicacion.this,ReservaNewUbicacion.class));
                    }
                }
            }

            @Override
            public void onFailure(Call <msgModelEliminarArticulo> call, Throwable t) {

            }
        });
    }

    private void consultarLoad(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call <loadModel> call = loadServicio.consultaLoadId(id);

        call.enqueue(new Callback <loadModel>() {
            @Override
            public void onResponse(Call<loadModel> call, Response<loadModel> response) {
                if(response.isSuccessful()){
                    if(response.body().idLoad!= 0){
                        loadModel loadTemp = new loadModel();
                        loadTemp.usuario = 2;
                        loadTemp.reserva = idReserva;
                        loadTemp.codigoBarras = response.body().codigoBarras;

                        editarLoad(idLoadExtra, loadTemp);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problema " + response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call <loadModel> call, Throwable t) {

            }
        });
    }

    private void editarLoad(int id, loadModel load){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);

        Call<msgModelEliminarArticulo>call = loadServicio.modificarLoad(id, load);

        call.enqueue(new Callback <msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo>call, Response<msgModelEliminarArticulo> response) {
                if (response.isSuccessful()){
                    if(!response.body().message.equals("")){
                        if(response.body().message.equals("Se editaron los registros correctamente"))
                            Toast.makeText(getApplicationContext(), "Se agrego reserva a load", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "load con reserva no valido", Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Problema " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<msgModelEliminarArticulo> call, Throwable t) {
                Toast.makeText(ConfirmarUbicacion.this, "Error"+ t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Interfaces.serviceRetrofit;
import Model.articuloModel;
import Model.loadModel;
import Model.msgModel;
import Model.pasilloModel;
import Model.reservaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarAR extends AppCompatActivity {
    private Button aCancelar, aAceptar;
    public loadModel load = new loadModel();
    public TextView idArticulo, nomArticulo, txtvload, reserva;
    public EditText valor;
    public articuloModel articulo = new articuloModel();
    public String numSerie, loadCdgBarras;
    public int cantTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_ar);

        //Edit text
        valor = (EditText) findViewById(R.id.TextInputEditText);
        //Labels
        idArticulo = (TextView)findViewById(R.id.txtvIDArticulo);
        nomArticulo = (TextView)findViewById(R.id.txtvNombre);
        txtvload = (TextView)findViewById(R.id.txtvLoad);
        reserva = (TextView)findViewById(R.id.txtvReserva);


        aCancelar = findViewById(R.id.aCancelar);
        aAceptar = findViewById(R.id.aAceptar);

        consultaArticuloporLoad(getIntent().getExtras().getInt("idLoad"));

        aCancelar.setOnClickListener(view -> startActivity(new Intent(AgregarAR.this,AgregarCodigo.class)));
        aAceptar.setOnClickListener(view ->
        {
            if(!valor.getText().equals("")){
                Toast.makeText(getApplicationContext(), valor.getText(), Toast.LENGTH_LONG).show();
                articulo.nombreArticulo = nomArticulo.getText().toString();
                articulo.numeroSerie = numSerie;
                articulo.cantidad = Integer.parseInt(valor.getText().toString()) + cantTemp;
                articulo.load = Integer.parseInt(loadCdgBarras);


                agregar(Integer.parseInt(idArticulo.getText().toString()), articulo);
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
                if(response.isSuccessful()) {
                    if (response.body().size() != 0) {
                        List<articuloModel> lst = response.body();


                        idArticulo.setText(Integer.toString(lst.get(0).idArticulo));
                        nomArticulo.setText(lst.get(0).nombreArticulo);
                        numSerie = lst.get(0).numeroSerie;
                        cantTemp = lst.get(0).cantidad;
                        consultarLoad(Integer.toString(lst.get(0).load));
                        consultarReserva(Integer.toString(getIntent().getExtras().getInt("idReserva")));
                        loadCdgBarras = Integer.toString(lst.get(0).load);
                        //reserva.setText(Integer.toString(getIntent().getExtras().getInt("idReserva")));
                    } else {
                        Toast.makeText(getApplicationContext(), "No existen articulos con ese load", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problema en la ruta", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call <List<articuloModel>> call, Throwable t) {

            }
        });
    }

    private void agregar(int id, articuloModel articulo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit articuloServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call <msgModel> call = articuloServicio.agregarArticuloAdmin(id, articulo);

        call.enqueue(new Callback <msgModel>() {
            @Override
            public void onResponse(Call<msgModel> call, Response<msgModel> response) {
                if(response.isSuccessful()){
                    if(response.body().message != ""){
                        if(response.body().message.equals("Se editaron los registros correctamente")){
                            startActivity(new Intent(AgregarAR.this, MenuAdmin.class));
                            Toast.makeText(getApplicationContext(), "valores enviados bien", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), response.body().message ,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Respuesta mal",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call <msgModel> call, Throwable t) {

            }
        });
    }

    private void consultarLoad(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call <loadModel> call = loadServicio.consultaLoadId(Integer.parseInt(id));

        call.enqueue(new Callback <loadModel>() {
            @Override
            public void onResponse(Call<loadModel> call, Response<loadModel> response) {
                if(response.body().idLoad!= 0){
                    txtvload.setText(response.body().codigoBarras);
                }
            }

            @Override
            public void onFailure(Call <loadModel> call, Throwable t) {

            }
        });
    }

    private void consultarReserva(String id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit reservaServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call <reservaModel> call = reservaServicio.consultaReserva(Long.parseLong(id));

        call.enqueue(new Callback <reservaModel>() {
            @Override
            public void onResponse(Call<reservaModel> call, Response<reservaModel> response) {
                if(response.body().idReserva!= 0){
                    reserva.setText(response.body().nombreReserva);
                }
            }

            @Override
            public void onFailure(Call <reservaModel> call, Throwable t) {

            }
        });
    }

}

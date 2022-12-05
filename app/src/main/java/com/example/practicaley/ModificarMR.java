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
import Model.msgModelEliminarArticulo;
import Model.reservaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarMR extends AppCompatActivity {
    private Button mCancelar, mAceptar;
    public TextView nomArticulo, txtvload, reserva, cantidadAct;
    public EditText valor;
    public articuloModel articulo = new articuloModel();
    public int cantidadMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_mr);

        //EditText
        valor = (EditText)findViewById(R.id.TextInputEditText);
        //Labels
        nomArticulo = (TextView)findViewById(R.id.txtvNomArticuloMR);
        txtvload = (TextView)findViewById(R.id.txtvLoadMR);
        reserva = (TextView)findViewById(R.id.txtvReservaMR);
        cantidadAct = (TextView)findViewById(R.id.txtvCanActMR);


        mCancelar = findViewById(R.id.mCancelar);
        mAceptar = findViewById(R.id.mAceptar);

        obtenerLoadConReserva(getIntent().getExtras().getInt("idReserva"));

        mCancelar.setOnClickListener(view -> startActivity(new Intent(ModificarMR.this,ModificarCodigo.class)));

        mAceptar.setOnClickListener(view -> {
            if(!valor.getText().equals("")){
                Toast.makeText(getApplicationContext(), valor.getText(), Toast.LENGTH_LONG).show();
                articulo.cantidad = Integer.parseInt(valor.getText().toString());


                modificar(articulo.idArticulo, articulo);
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
                    List<loadModel> lst = response.body();

                    txtvload.setText(lst.get(0).codigoBarras);
                    consultaArticuloPorLoad(lst.get(0).idLoad);
                    consultarReserva(lst.get(0).codigoBarras);

                    //consultaArticuloporLoad(response.body().idLoad);
                }
            }

            @Override
            public void onFailure(Call<List<loadModel>> call, Throwable t) {
                Toast.makeText(ModificarMR.this, "Error"+ t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void consultaArticuloPorLoad(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit articuloServicio = retrofit.create(serviceRetrofit.class);

        Call<List<articuloModel>> call = articuloServicio.consultaPorLoad(id);

        call.enqueue(new Callback<List<articuloModel>>() {
            @Override
            public void onResponse(Call<List<articuloModel>> call, Response<List<articuloModel>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size() != 0){
                    List<articuloModel> lst = response.body();


                    nomArticulo.setText(lst.get(0).nombreArticulo);
                    cantidadAct.setText(Integer.toString(lst.get(0).cantidad));
                    Toast.makeText(getApplicationContext(), lst.get(0).nombreArticulo, Toast.LENGTH_LONG).show();
                    articulo.idArticulo = lst.get(0).idArticulo;
                    articulo.nombreArticulo = lst.get(0).nombreArticulo;
                    articulo.load = lst.get(0).load;
                    articulo.numeroSerie = lst.get(0).numeroSerie;

                    //articulo.cantidad = cantidadMod;//
                    cantidadMod = articulo.cantidad;

                    consultarReserva(Integer.toString(getIntent().getExtras().getInt("idReserva")));
                }else{
                        Toast.makeText(getApplicationContext(), "No existen articulos con ese load", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problema en la ruta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<articuloModel>> call, Throwable t) {

            }
        });
    }

    private void modificar(int id, articuloModel articulo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit articuloServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call <msgModelEliminarArticulo> call = articuloServicio.modificarArticuloAdmin(id, articulo);

        call.enqueue(new Callback<msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo> call, Response<msgModelEliminarArticulo> response) {
                if (response.isSuccessful()){
                    if (response.body().message != ""){
                        if (response.body().message.equals("Se editaron los registros correctamente")){
                            startActivity(new Intent(ModificarMR.this, MenuAdmin.class));
                            Toast.makeText(getApplicationContext(),"valores enviados bien", Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<msgModelEliminarArticulo> call, Throwable t) {

            }
        });
    }

    private void consultarReserva(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit reservaServicio = retrofit.create(serviceRetrofit.class);// serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

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

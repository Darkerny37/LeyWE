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
import Model.msgModelEliminarArticulo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SurtirDom extends AppCompatActivity {
    private Button sdCancelar, sdAceptar;
    public TextView nombre, numeroSerie, load;
    private EditText editCodigoArCon1;
    public articuloModel articulo = new articuloModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surtir_dom);

        editCodigoArCon1 = findViewById(R.id.TextInputEditText);
        sdCancelar = findViewById(R.id.sdCancelar);
        sdAceptar = findViewById(R.id.sdAceptar);

        nombre = findViewById(R.id.nombreArticulo);
        numeroSerie = findViewById(R.id.NumeroSerie);
        load = findViewById(R.id.numeroSerieLoad);

        articulo.idArticulo = getIntent().getExtras().getInt("idArticulo");
        articulo.nombreArticulo = getIntent().getExtras().getString("nombreArticulo");
        articulo.load = getIntent().getExtras().getInt("load");
        articulo.numeroSerie = getIntent().getExtras().getString("numeroSerie");
        articulo.cantidad = getIntent().getExtras().getInt("cantidad");

        nombre.setText(articulo.nombreArticulo);
        numeroSerie.setText(articulo.numeroSerie);


        consultarLoad(articulo.load);
        sdCancelar.setOnClickListener(view -> startActivity(new Intent(SurtirDom.this,MontacargaSCodigo.class)));
        sdAceptar.setOnClickListener(view -> {

            if(!editCodigoArCon1.getText().toString().equals("")){
                //Modificar articulo
                articulo.cantidad = articulo.cantidad - Integer.parseInt(editCodigoArCon1.getText().toString());
                if(articulo.cantidad < 0){
                    articulo.cantidad = 0;
                    articulo.load = null;
                }
                modificar(articulo.idArticulo, articulo);
                startActivity(new Intent(SurtirDom.this, MenuMontacarga.class));

            }else
            {
                Toast.makeText(getApplicationContext(), "Favor de llenar el campo" ,Toast.LENGTH_LONG).show();
            }
        });
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
                if(response.isSuccessful()){
                    if(response.body().idLoad!= 0){
                        load.setText(response.body().codigoBarras);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call <loadModel> call, Throwable t) {

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
                            Toast.makeText(getApplicationContext(),"Articulo actualizado", Toast.LENGTH_LONG).show();
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


}

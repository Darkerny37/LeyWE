package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Interfaces.serviceRetrofit;
import Model.articuloAuxModel;
import Model.msgModelEliminarArticulo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContarAR extends AppCompatActivity {
    private Button caCancelar, caAceptar;
    public TextView idArticulo, nombre;
    public EditText TextInputEditText;
    public articuloAuxModel art = new articuloAuxModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contar_ar);

        caCancelar = findViewById(R.id.caCancelar);
        caAceptar = findViewById(R.id.caAceptar);
        idArticulo = findViewById(R.id.tvIdArticuloContarAr);
        nombre = findViewById(R.id.tvNombreArticuloContarAr);
        TextInputEditText = findViewById(R.id.TextInputEditText);

        art.nombreArticuloAux = getIntent().getExtras().getString("nombreArticulo");
        art.numeroSerie = getIntent().getExtras().getString("numeroSerie");
        art.pasillo = getIntent().getExtras().getInt("idPasillo");

        idArticulo.setText(art.numeroSerie);
        nombre.setText(art.nombreArticuloAux);


        caCancelar.setOnClickListener(view -> startActivity(new Intent(ContarAR.this,LevNuevoArticulo.class)));
        caAceptar.setOnClickListener(view -> {
            if(!TextInputEditText.getText().toString().equals("")){
                art.cantidad = Integer.parseInt(TextInputEditText.getText().toString());
                agregarArticuloAux(art);
            }else{
                Toast.makeText(getApplicationContext(), "Llene los campos", Toast.LENGTH_LONG).show();
            }
            //Agregar articulo aux
            //startActivity(new Intent(ContarAR.this, LevantarArticulos.class));
        });
    }

    public void agregarArticuloAux(articuloAuxModel aux){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit service = retrofit.create(serviceRetrofit.class);

        Call<msgModelEliminarArticulo> call = service.agregarArticulosAux(aux);

        call.enqueue(new Callback<msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo> call, Response<msgModelEliminarArticulo> response) {
                if (response.isSuccessful()){
                    if (!response.body().message.equals("")){
                        if (response.body().message.equals("Registro guardado correctamente")) {
                            Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ContarAR.this,AgregarTarea.class));
                        }else {
                            Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "no valido", Toast.LENGTH_LONG).show();
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

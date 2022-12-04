package com.example.practicaley;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import Interfaces.serviceRetrofit;
import Model.articuloModel;
import Model.loadModel;
import Model.msgModelEliminarArticulo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarArticuloLoad extends AppCompatActivity {
    private EditText editCodigoAg;
    private Button escaneadorAg, continuadorAg;
    public int idArt;
    public articuloModel articulo = new articuloModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_articulo_load);

        idArt = getIntent().getExtras().getInt("idArticulo");
        articulo.numeroSerie = getIntent().getExtras().getString("numeroSerie");
        articulo.cantidad = getIntent().getExtras().getInt("cantidad");
        articulo.nombreArticulo = getIntent().getExtras().getString("nombreArticulo");

        editCodigoAg = findViewById(R.id.editCodigoAg);
        escaneadorAg = findViewById(R.id.escaneadorAg);
        continuadorAg = findViewById(R.id.continuarAg);

        escaneadorAg.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher8.launch(options);
        });

        continuadorAg.setOnClickListener(view -> {
                    //Consultar el objeto

                    if(!editCodigoAg.getText().toString().equals("")){
                        consulta(editCodigoAg.getText().toString());
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "Favor de llenar el campo" ,Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void consulta(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);

        Call<loadModel> call = loadServicio.consultaLoad(Long.parseLong(id));

        call.enqueue(new Callback<loadModel>() {
            @Override
            public void onResponse(Call<loadModel> call, Response<loadModel> response) {
                if (response.isSuccessful()){
                    if (response.body().idLoad != 0){   // body() respuesta del request que viene del servicio
                        articulo.load = response.body().idLoad;
                        modificar(idArt, articulo);
                        //Toast.makeText(getApplicationContext(), "Load valido", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Load no valido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<loadModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getApplicationContext(),response.body().message, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AgregarArticuloLoad.this,AgregarTarea.class));

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


    private final ActivityResultLauncher<ScanOptions> barcodeLauncher8 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(AgregarArticuloLoad.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgregarArticuloLoad.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoAg.setText(result.getContents());
                }
            });
}
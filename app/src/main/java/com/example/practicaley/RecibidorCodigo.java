package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import Interfaces.serviceRetrofit;
import Model.loadModel;
import Model.msgModelEliminarArticulo;
import Model.tareaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecibidorCodigo extends AppCompatActivity {
    private EditText editCodigoUno, editDesc;
    private Button escaneadorUno, continuadorUno;
    public tareaModel tarea = new tareaModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibidor_codigo);

        editDesc = findViewById(R.id.editDesc);
        editCodigoUno = findViewById(R.id.editCodigoUno);
        escaneadorUno = findViewById(R.id.escaneadorUno);
        continuadorUno = findViewById(R.id.continuarUno);

        escaneadorUno.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher.launch(options);
        });

        continuadorUno.setOnClickListener(view -> {
            if(!editDesc.getText().toString().equals("")){
                consulta(editCodigoUno.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(), "La descripcion de la tarea es necesaria", Toast.LENGTH_LONG).show();
            }
            //startActivity(new Intent(RecibidorCodigo.this,NuevoLoad.class));
        });


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
                        tarea.load = response.body().idLoad;
                        tarea.descripcion = editDesc.getText().toString();

                        registrarTarea(tarea);
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

    private void registrarTarea(tareaModel tarea){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit tareaServicio = retrofit.create(serviceRetrofit.class);

        Call<msgModelEliminarArticulo> call = tareaServicio.agregarTarea(tarea);

        call.enqueue(new Callback<msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo> call, Response<msgModelEliminarArticulo> response) {
                if (response.isSuccessful()){
                    if (!response.body().message.equals("")){
                        if(response.body().message.equals("Se creo la tarea correctamente"))
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RecibidorCodigo.this,AgregarTarea.class));

                    }else{
                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
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

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(RecibidorCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RecibidorCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoUno.setText(result.getContents());
                }
            });


}
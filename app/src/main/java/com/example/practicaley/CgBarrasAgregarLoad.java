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

import java.util.List;

import Interfaces.serviceRetrofit;
import Model.loadModel;
import Model.msgModelEliminarArticulo;
import Model.pasilloModel;
import Model.reservaModel;
import Model.tareaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CgBarrasAgregarLoad extends AppCompatActivity {
    private EditText editCodigoArCon2;
    private Button escaneadorArCon2, continuadorArCon2;
    public loadModel load = new loadModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cg_barras_agregar_load);


        editCodigoArCon2 = findViewById(R.id.editCodigoBarras);
        escaneadorArCon2 = findViewById(R.id.escaneadorArCon2);
        continuadorArCon2 = findViewById(R.id.continuarArCon2);

        escaneadorArCon2.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher6.launch(options);
        });

        continuadorArCon2.setOnClickListener(view -> {
            if(!editCodigoArCon2.getText().toString().equals("")){
                load.codigoBarras = editCodigoArCon2.getText().toString();
                consultaReservaPorPasillo(getIntent().getExtras().getInt("idPasillo"));

            }else {
                Toast.makeText(getApplicationContext(), "Favor de llenar el campo", Toast.LENGTH_LONG).show();
            }});
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher6 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(CgBarrasAgregarLoad.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CgBarrasAgregarLoad.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoArCon2.setText(result.getContents());

                }
            });

    private void consultaReservaPorPasillo(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit reservaServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call<List<reservaModel>> call = reservaServicio.consultaReservaPasillo(id);

        call.enqueue(new Callback<List<reservaModel>>() {
            @Override
            public void onResponse(Call<List<reservaModel>> call, Response<List<reservaModel>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() != 0){

                        List<reservaModel> lst = response.body();
                        load.reserva = null;
                        load.usuario = 2;
                        agregarLoad(load);
                    }else{
                        Toast.makeText(getApplicationContext(), "Reserva no valida", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<reservaModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void agregarLoad(loadModel load){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit tareaServicio = retrofit.create(serviceRetrofit.class);

        Call<msgModelEliminarArticulo> call = tareaServicio.agregarLoad(load);

        call.enqueue(new Callback<msgModelEliminarArticulo>() {
            @Override
            public void onResponse(Call<msgModelEliminarArticulo> call, Response<msgModelEliminarArticulo> response) {
                if (response.isSuccessful()){
                    if (!response.body().message.equals("")){
                        if (response.body().message.equals("Load guardado correctamente")) {
                            Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CgBarrasAgregarLoad.this,AgregarTarea.class));

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
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarCodigo extends AppCompatActivity {
    private EditText editCodigoAg;
    private Button escaneadorAg, continuadorAg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_codigo);

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

        //continuadorAg.setOnClickListener(view -> startActivity(new Intent(AgregarCodigo.this,AgregarAR.class)));

        continuadorAg.setOnClickListener(view -> {
            if (!editCodigoAg.getText().toString().equals("")){
                consulta(editCodigoAg.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(), "Favor de llenar el campo", Toast.LENGTH_LONG).show();
            }
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
                        if (response.body().reserva == null){
                            Toast.makeText(getApplicationContext(), "No hay una reserva en este Load", Toast.LENGTH_LONG).show();
                        }else{
                        Intent i = new Intent(AgregarCodigo.this, AgregarAR.class);
                        i.putExtra("idLoad", response.body().idLoad);
                        i.putExtra("idReserva", response.body().reserva);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Load valido", Toast.LENGTH_LONG).show();
                        }
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

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher8 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(AgregarCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgregarCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoAg.setText(result.getContents());
                }
            });
}
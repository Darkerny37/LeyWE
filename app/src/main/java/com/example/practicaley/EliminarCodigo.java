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
import Model.reservaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EliminarCodigo extends AppCompatActivity {
    private EditText editCodigoEr;
    private Button escaneadorEr, continuadorEr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_codigo);

        editCodigoEr = findViewById(R.id.editCodigoEr);
        escaneadorEr = findViewById(R.id.escaneadorEr);
        continuadorEr = findViewById(R.id.continuarEr);

        escaneadorEr.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher9.launch(options);
        });

        //continuadorEr.setOnClickListener(view -> startActivity(new Intent(EliminarCodigo.this,EliminarER.class)));

        continuadorEr.setOnClickListener(view -> {
            if (!editCodigoEr.getText().toString().equals("")){
                consulta(editCodigoEr.getText().toString());
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

        serviceRetrofit reservaServicio = retrofit.create(serviceRetrofit.class);

        Call<reservaModel> call = reservaServicio.consultaReserva(Long.parseLong(id));

        call.enqueue(new Callback<reservaModel>() {
            @Override
            public void onResponse(Call<reservaModel> call, Response<reservaModel> response) {
                if (response.isSuccessful()){
                    if (response.body().idReserva != 0){   // body() respuesta del request que viene del servicio
                        Intent i = new Intent(EliminarCodigo.this, EliminarER.class);
                        i.putExtra("idReserva", response.body().idReserva);
                        //i.putExtra("idLoad", response.body().idLoad);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Reserva valida", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Reserva no valida", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<reservaModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher9 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(EliminarCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EliminarCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoEr.setText(result.getContents());
                }
            });
}
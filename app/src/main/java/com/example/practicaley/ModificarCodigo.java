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
import Model.reservaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarCodigo extends AppCompatActivity {
    private EditText editCodigoMd;
    private Button escaneadorMd, continuadorMd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_codigo);

        editCodigoMd = findViewById(R.id.editCodigoMd);
        escaneadorMd = findViewById(R.id.escaneadorMd);
        continuadorMd = findViewById(R.id.continuarMd);

        escaneadorMd.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher8.launch(options);
        });

        //continuadorMd.setOnClickListener(view -> startActivity(new Intent(ModificarCodigo.this,ModificarMR.class)));
        continuadorMd.setOnClickListener(view -> {
            if (!editCodigoMd.getText().toString().equals("")){
                consulta(editCodigoMd.getText().toString());
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
                        Intent i = new Intent(ModificarCodigo.this, ModificarMR.class);
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

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher8 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ModificarCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ModificarCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoMd.setText(result.getContents());
                }
            });
}
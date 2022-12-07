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
import Model.pasilloModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecibidorCPasillo extends AppCompatActivity {
    private EditText editCodigoPas1;
    private Button escaneadorPas1, continuarPas1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibidor_pasilloc);

        editCodigoPas1 = findViewById(R.id.editCodigoPas1);
        escaneadorPas1 = findViewById(R.id.escaneadorPas1);
        continuarPas1 = findViewById(R.id.continuarPas1);

        escaneadorPas1.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher3.launch(options);
        });

        //continuarPas1.setOnClickListener(view -> startActivity(new Intent(RecibidorCPasillo.this,LevantarArticulos.class)));
        continuarPas1.setOnClickListener(view -> {
            if (!editCodigoPas1.getText().toString().equals("")){
                consulta(editCodigoPas1.getText().toString());
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

        serviceRetrofit pasilloServicio = retrofit.create(serviceRetrofit.class);

        Call<pasilloModel> call = pasilloServicio.consultaPasilloID(Long.parseLong(id));

        call.enqueue(new Callback<pasilloModel>() {
            @Override
            public void onResponse(Call<pasilloModel> call, Response<pasilloModel> response) {
                if (response.isSuccessful()){
                    if (response.body().idPasillo != 0){
                        Intent i = new Intent(RecibidorCPasillo.this, LevantarArticulos.class);
                        i.putExtra("idPasillo", response.body().idPasillo);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Pasillo valido", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(), "Pasillo no valido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Problemaa" + response.code(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<pasilloModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher3 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(RecibidorCPasillo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RecibidorCPasillo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoPas1.setText(result.getContents());
                }
            });
}
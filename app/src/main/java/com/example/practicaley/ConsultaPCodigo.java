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

public class ConsultaPCodigo extends AppCompatActivity {
    private EditText editCodigoArCon2;
    private Button escaneadorArCon2, continuadorArCon2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_p_cod);

        editCodigoArCon2 = findViewById(R.id.editCodigoArCon2);
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
                consulta(editCodigoArCon2.getText().toString());
            }else {
                Toast.makeText(getApplicationContext(), "Favor de llenar el campo", Toast.LENGTH_LONG).show();
            }});
    }

    private void consulta(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()) // para que la respuesta se convierta en modelo directamente
                .build();

        serviceRetrofit pasilloServicio = retrofit.create(serviceRetrofit.class);//  serviceRetrofit es una implementacion del servicio para los endpoints(rutas)

        Call<pasilloModel> call = pasilloServicio.consultaPasilloID(Long.parseLong(id));

        call.enqueue(new Callback<pasilloModel>() {
            @Override
            public void onResponse(Call<pasilloModel> call, Response<pasilloModel> response) {
                if (response.isSuccessful()){
                    if (response.body().idPasillo != 0){
                        Intent i = new Intent(ConsultaPCodigo.this, PasilloConsulta.class);
                        i.putExtra("idPasillo", response.body().idPasillo);
                        i.putExtra("nombrePasillo", response.body().nombrePasillo);
                        startActivity(i);
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


    private final ActivityResultLauncher<ScanOptions> barcodeLauncher6 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ConsultaPCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ConsultaPCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoArCon2.setText(result.getContents());

                }
            });
}

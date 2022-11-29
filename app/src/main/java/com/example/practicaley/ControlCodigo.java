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

public class ControlCodigo extends AppCompatActivity {
    private EditText editCodigoCont;
    private Button escaneadorCont, continuarCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_codigo);

        editCodigoCont = findViewById(R.id.editCodigoCont);
        escaneadorCont = findViewById(R.id.escaneadorCont);
        continuarCont = findViewById(R.id.continuarCont);

        escaneadorCont.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher7.launch(options);
        });

        continuarCont.setOnClickListener(view -> {
            if(!editCodigoCont.getText().toString().equals("")){
                consulta(editCodigoCont.getText().toString());
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
                        Intent i = new Intent(ControlCodigo.this, MenuControlI.class);
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

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher7 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ControlCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ControlCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoCont.setText(result.getContents());
                }
            });
}
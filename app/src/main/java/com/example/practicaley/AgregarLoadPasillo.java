package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.practicaley.databinding.ActivityAgregarLoadPasilloBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import Interfaces.serviceRetrofit;
import Model.loadModel;
import Model.pasilloModel;
import Model.tareaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarLoadPasillo extends AppCompatActivity {
    private EditText editCodigoArCon2;
    private Button escaneadorArCon2, continuadorArCon2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_load_pasillo);

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
                consultarLoad(editCodigoArCon2.getText().toString());
            }else {
                Toast.makeText(getApplicationContext(), "Favor de llenar el campo", Toast.LENGTH_LONG).show();
            }});
    }

    private void consultarLoad(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit loadServicio = retrofit.create(serviceRetrofit.class);

        Call<pasilloModel> call = loadServicio.consultaPasilloID(Long.parseLong(id));

        call.enqueue(new Callback<pasilloModel>() {
            @Override
            public void onResponse(Call<pasilloModel> call, Response<pasilloModel> response) {
                if (response.isSuccessful()){
                    if (response.body().idPasillo != 0){   // body() respuesta del request que viene del servicio
                        Toast.makeText(getApplicationContext(), "Pasillo valido", Toast.LENGTH_LONG).show();
                        //Activity para poner el codigo de barras
                        Intent i = new Intent(AgregarLoadPasillo.this, CgBarrasAgregarLoad.class);
                        i.putExtra("idPasillo", response.body().idPasillo);
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
                    Toast.makeText(AgregarLoadPasillo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgregarLoadPasillo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoArCon2.setText(result.getContents());

                }
            });


}
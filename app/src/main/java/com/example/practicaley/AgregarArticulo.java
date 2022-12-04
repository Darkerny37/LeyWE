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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarArticulo extends AppCompatActivity {
    private EditText editCodigoArCon1;
    private Button escaneadorArCon1, continuadorArCon1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_articulo);

        editCodigoArCon1 = findViewById(R.id.editCodigoArCon1);
        escaneadorArCon1 = findViewById(R.id.escaneadorArCon1);
        continuadorArCon1 = findViewById(R.id.continuarArCon1);

        escaneadorArCon1.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher5.launch(options);
        });

        continuadorArCon1.setOnClickListener(view -> {
                    //Consultar el objeto

                    if(!editCodigoArCon1.getText().toString().equals("")){
                        consulta(editCodigoArCon1.getText().toString());
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

        serviceRetrofit consultaCdgService = retrofit.create(serviceRetrofit.class);

        Call<articuloModel> call = consultaCdgService.consultaPorNumSerie(Long.parseLong(id));

        call.enqueue(new Callback<articuloModel>() {
                         @Override
                         public void onResponse(Call<articuloModel> call, Response<articuloModel> response) {
                             if(response.isSuccessful()){
                                 if(response.body().idArticulo != 0){
                                     Intent i = new Intent(AgregarArticulo.this, AgregarArticuloLoad.class);
                                     i.putExtra("idArticulo", response.body().idArticulo);
                                     i.putExtra("cantidad", response.body().cantidad);
                                     i.putExtra("nombreArticulo", response.body().nombreArticulo);
                                     i.putExtra("numeroSerie", response.body().numeroSerie);
                                     startActivity(i);
                                 }else {
                                     Toast.makeText(getApplicationContext(), "Articulo no valido", Toast.LENGTH_LONG).show();

                                 }

                             }else{
                                 Toast.makeText(getApplicationContext(), "Problemaaa" + response.code(),Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<articuloModel> call, Throwable t) {
                             Toast.makeText(getApplicationContext(), "Problema" + t.toString(),Toast.LENGTH_LONG).show();
                         }
                     }
        );
    }


    private final ActivityResultLauncher<ScanOptions> barcodeLauncher5 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(AgregarArticulo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgregarArticulo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoArCon1.setText(result.getContents());
                }
            });
}
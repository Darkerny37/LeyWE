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
import Model.articuloModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MontacargaSCodigo extends AppCompatActivity {
    private EditText editCodigoDos;
    private Button escaneadorDos, continuadorDos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montacarga_scodigo);

        editCodigoDos = findViewById(R.id.editCodigoUno);
        escaneadorDos = findViewById(R.id.escaneadorDos);
        continuadorDos = findViewById(R.id.continuarDos);

        escaneadorDos.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher2.launch(options);
        });

        continuadorDos.setOnClickListener(view -> {
            //Consultar el objeto

            if(!editCodigoDos.getText().toString().equals("")){
                consulta(editCodigoDos.getText().toString());

            }else
            {
                Toast.makeText(getApplicationContext(), "Favor de llenar el campo" ,Toast.LENGTH_LONG).show();
            }
        });


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

                                     Intent i = new Intent(MontacargaSCodigo.this, SurtirDom.class);
                                     i.putExtra("idArticulo", response.body().idArticulo);
                                     i.putExtra("cantidad", response.body().cantidad);
                                     i.putExtra("nombreArticulo", response.body().nombreArticulo);
                                     i.putExtra("numeroSerie", response.body().numeroSerie);
                                     i.putExtra("load", response.body().load);
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


    private final ActivityResultLauncher<ScanOptions> barcodeLauncher2 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(MontacargaSCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MontacargaSCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoDos.setText(result.getContents());
                }
            });
}
package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

//import Config.Config;
import Interfaces.serviceRetrofit;
import Model.msgModel;
import Model.userModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText txtID, txtPass;
    private Button button;
    //private serviceRetrofit servicio = Config.getRetrofit().create(serviceRetrofit.class);
    userModel user = new userModel();
    msgModel msg = new msgModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnLogin);
        txtID = findViewById(R.id.txtID);
        txtPass = findViewById(R.id.txtPass);



        button.setOnClickListener(view -> {
            user.usuario = txtID.getText().toString();
            user.passUsuario = txtPass.getText().toString();
            userLogin();



        });
    }

    public void userLogin(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leybodega.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceRetrofit userService = retrofit.create(serviceRetrofit.class);

        Call<msgModel> call = userService.login(user);

        call.enqueue(new Callback<msgModel>() {
             @Override
             public void onResponse(Call<msgModel> call, Response<msgModel> response) {
                 if(response.isSuccessful()){
                     if(response.body().rol != 0){
                         if(response.body().rol == 1){
                             startActivity(new Intent(MainActivity.this,MenuAdmin.class));
                         }else if(response.body().rol == 2){
                             startActivity(new Intent(MainActivity.this,MenuRecibidor.class));
                         }else if(response.body().rol== 3){
                             startActivity(new Intent(MainActivity.this,MenuMontacarga.class));
                         }else{
                             startActivity(new Intent(MainActivity.this,MainActivity.class));
                         }
                     }
                     Toast.makeText(getApplicationContext(), response.body().message ,Toast.LENGTH_LONG).show();
                 }else{
                     Toast.makeText(getApplicationContext(), "Respuesta mal",Toast.LENGTH_LONG).show();

                 }
             }

             @Override
             public void onFailure(Call<msgModel> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), "Problema",Toast.LENGTH_LONG).show();
             }
            }
        );
    }

}


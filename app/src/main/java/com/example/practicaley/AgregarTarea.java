package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class AgregarTarea extends AppCompatActivity{
    private final String URL = "";
    private Button botonAgregarTareas, botonMandarTareas;
    private ListView ListaTarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_tarea);
        botonAgregarTareas = findViewById(R.id.botonAgregarTareas);
        ListaTarea = findViewById(R.id.ListaTarea);
        botonMandarTareas = findViewById(R.id.botonMandarTareas);

        botonMandarTareas.setOnClickListener(view -> startActivity(new Intent(AgregarTarea.this,MenuRecibidor.class)));

    }
    public void AgregarTarea(View V){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    String TipoUsuario = ""/* Sacar el tipo de usuario de la base de datos */;
                    switch (TipoUsuario) {

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(null, "No se pudo asignar la tarea", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Toast.makeText(null, "No se pudo asignar la tarea", Toast.LENGTH_SHORT).show();
                return super.getParams();
            }
        };
    }
    public void MandarTareas(View V){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    String TipoUsuario = ""/* Sacar el tipo de usuario de la base de datos */;
                    switch (TipoUsuario) {

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(null, "No se pudo asignar la tarea", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Toast.makeText(null, "No se pudo asignar la tarea", Toast.LENGTH_SHORT).show();
                return super.getParams();
            }
        };
    }

}

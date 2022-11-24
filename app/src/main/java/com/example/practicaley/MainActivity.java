package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText etID, etContraseña;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btnLogin);
        etID = findViewById(R.id.etID);
        etContraseña = findViewById(R.id.etContraseña);
        button.setOnClickListener(view -> {
            ValidarUsuario(new Intent(view.getContext(), MenuRecibidor.class));
        });
    }
    private void ValidarUsuario(Intent I){
        Usuario U = new Usuario(etID.getText()+"", etContraseña.getText()+"", 0);
        I.putExtra("Usuario", U.getUsuario());
        startActivity(new Intent(MainActivity.this, MenuRecibidor.class));
        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    String TipoUsuario = ""/* Sacar el tipo de usuario de la base de datos */;
                    /*switch (TipoUsuario) {
                        case "Recibidor":
                            startActivity(new Intent(MainActivity.this, MenuRecibidor.class));
                            break;
                        case "Montacarga":
                            startActivity(new Intent(MainActivity.this, MenuMontacarga.class));
                            break;
                        case "Admin":
                            startActivity(new Intent(MainActivity.this,MenuAdmin.class));
                            break;
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(null, "No es valido ese identificador", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError{
                Toast.makeText(null, "No es valido ese identificador", Toast.LENGTH_SHORT).show();
                return super.getParams();
            }
        };*/
    }
}
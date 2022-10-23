package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText etID;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btnLogin);
        etID = findViewById(R.id.etID);

        button.setOnClickListener(view -> {
            if(etID.getText().toString().equals("1"))
                startActivity(new Intent(MainActivity.this,MenuRecibidor.class));
           else if(etID.getText().toString().equals("2"))
                startActivity(new Intent(MainActivity.this,MenuMontacarga.class));
           else if(etID.getText().toString().equals("3"))
                startActivity(new Intent(MainActivity.this,MenuAdmin.class));
           else
                Toast.makeText(this, "No es valido ese identificador", Toast.LENGTH_SHORT).show();
        });
    }

}
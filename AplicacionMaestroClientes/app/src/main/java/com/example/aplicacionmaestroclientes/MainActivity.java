package com.example.aplicacionmaestroclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnEnviar, btnMarcar, btnContactos, btnWeb, btnCalculadora;
    private EditText etRut, etRazon, etEmail;
    private TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referencias();
        eventos();
    }

    public void eventos(){
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rut, razon, correo;
                rut = etRut.getText().toString();
                razon = etRazon.getText().toString();
                correo = etEmail.getText().toString();

                Cliente cli = new Cliente(rut, razon, correo);

                Intent i = new Intent(MainActivity.this, MostrarActivity.class);
                i.putExtra("cliente", cli);

                startActivity(i);

                Toast.makeText(MainActivity.this, getResources().getString(R.string.enviando), Toast.LENGTH_LONG).show();


            }
        });

        btnMarcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+56966072222"));
                startActivity(i);
            }
        });

        btnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(i);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.emol.com"));
                startActivity(i);
            }
        });

        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(new ComponentName(
                        "com.android.calculator2",
                        "com.android.calculator2.Calculator"));

                startActivity(intent);
            }
        });


    }

    public void referencias(){
        btnEnviar = findViewById(R.id.btnEnviar);
        btnMarcar = findViewById(R.id.btnMarcar);
        btnContactos = findViewById(R.id.btnContactos);
        btnCalculadora = findViewById(R.id.btnCalculadora);
        btnWeb = findViewById(R.id.btnWeb);
        tvTitulo = findViewById(R.id.tvTitulo);
        etRut = findViewById(R.id.etRut);
        etRazon = findViewById(R.id.etRazon);
        etEmail = findViewById(R.id.etEmail);
    }
}
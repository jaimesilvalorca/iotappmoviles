package com.example.appmovil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MostrarActivity extends AppCompatActivity {

    private Button btnSuscriptionFree, btnConfirm, btnCancel;
    private TextView tvRut,tvName,tvAge,tvSuscription;

    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        referencias();
        eventos();
        buscarDatos();
    }

    private void buscarDatos(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            User user;

            user = (User) bundle.getSerializable("user");
            tvRut.setText("Rut:" + user.getRut());
            Log.d("TAG_", "Rut -> " + user.getRut());

            tvName.setText("Nombre:" + user.getName()+" "+user.getLastName());
            Log.d("TAG_", "Nombre -> " + user.getName() + user.getLastName());

            tvAge.setText("Edad:" + user.getAge());
            Log.d("TAG_", "Edad -> " + user.getAge());

            tvSuscription.setText("Valido hasta:" + calcularFechaCaducidad());
            Log.d("TAG_", "Valido hasta -> " + calcularFechaCaducidad());
        }

    }

    private String calcularFechaCaducidad() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, 1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }


    private void eventos() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        btnSuscriptionFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFechaGratuita();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Usuario Ingresado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MostrarActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setFechaGratuita() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 3);
        String fecha = sdf.format(calendar.getTime());
        tvSuscription.setText("Válido hasta: " + fecha);

        Toast.makeText(this, "La fecha ha cambiado", Toast.LENGTH_SHORT).show();
    }

    private void referencias() {
        btnCancel = findViewById(R.id.button5);
        btnSuscriptionFree = findViewById(R.id.button3);
        btnConfirm = findViewById(R.id.button4);
        tvRut = findViewById(R.id.textView2);
        tvName = findViewById(R.id.textView3);
        tvAge = findViewById(R.id.textView4);
        tvSuscription = findViewById(R.id.textView5);
    }


}

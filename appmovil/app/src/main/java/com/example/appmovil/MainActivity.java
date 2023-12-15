package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnSendForm, btnCancel;
    private EditText etRut, etName, etLastname, etBirthdate;

    private TextView terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        references();
        events();
    }

    public void events() {

        btnSendForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rut, name, lastname, birthday;
                rut = etRut.getText().toString();
                name = etName.getText().toString();
                lastname = etLastname.getText().toString();
                birthday = etBirthdate.getText().toString();

                if (rut.isEmpty() || name.isEmpty() || lastname.isEmpty() || birthday.isEmpty()) {
                    if (rut.isEmpty()) {
                        etRut.setError("Rut es obligatorio");
                    }
                    if (name.isEmpty()) {
                        etName.setError("Nombre es obligatorio");
                    }
                    if (lastname.isEmpty()) {
                        etLastname.setError("Apellido es obligatorio");
                    }
                    if (birthday.isEmpty()) {
                        etBirthdate.setError("Fecha de nacimiento es obligatorio");
                    }

                } else {
                    rut = formatRut(rut);
                    if (isAgeValid(birthday)) {
                        User user = new User(rut, name, lastname, birthday);
                        user.setAge(calculateAge(birthday));

                        Intent i = new Intent(MainActivity.this, MostrarActivity.class);
                        i.putExtra("user", user);
                        startActivity(i);
                    } else {
                        etBirthdate.setError("La persona debe ser mayor de 18 años y menor a 150 años.");
                    }
                }
            }
        });

        etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRut.getText().clear();
                etName.getText().clear();
                etLastname.getText().clear();
                etBirthdate.getText().clear();
                finish();
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.inacap.cl"));
                startActivity(i);
            }
        });


    }

    private String formatRut(String rut) {
        rut = rut.replaceAll("[^0-9kK]", "").toUpperCase();

        if (rut.length() > 1) {
            String cleanRut = rut.substring(0, rut.length() - 1);
            StringBuilder formattedRut = new StringBuilder();
            int count = 0;

            for (int i = cleanRut.length() - 1; i >= 0; i--) {
                formattedRut.insert(0, cleanRut.charAt(i));
                count++;

                if (count == 3 && i != 0) {
                    formattedRut.insert(0, '.');
                    count = 0;
                }
            }

            formattedRut.append('-').append(rut.charAt(rut.length() - 1));

            return formattedRut.toString();
        }
        return rut;
    }

    private boolean isAgeValid(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar birthDate = Calendar.getInstance();
        try {
            birthDate.setTime(sdf.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return (age >= 18 && age <= 150);
    }

    private int calculateAge(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar birthDate = Calendar.getInstance();
        try {
            birthDate.setTime(sdf.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    etBirthdate.setText(date);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    public void references() {
        btnSendForm = findViewById(R.id.button1);
        btnCancel = findViewById(R.id.button2);
        etRut = findViewById(R.id.editText1);
        etName = findViewById(R.id.editText2);
        etLastname = findViewById(R.id.editText3);
        etBirthdate = findViewById(R.id.editText4);
        terms = findViewById(R.id.textView);
    }
}

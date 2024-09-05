package com.example.actividadone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone;
    private Button buttonSubmit, buttonEmail;
    private static final String TAG = "MainActivity";
    private TextView textView;
    private EditText editText;
    private Button buttonChangeText;
    private Button buttonResetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonEmail = findViewById(R.id.buttonEmail);

        // Log para indicar que se ha creado la actividad
        Log.d(TAG, "Estoy en el onCreate creando la actividad");


        // Acción del botón Enviar
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capturar los datos ingresados
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();

                // Crear intent para enviar los datos a DisplayActivity
                Intent intent = new Intent(MainActivity.this, Activity_display.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipient = editTextEmail.getText().toString(); // Usar el campo de email como destinatario
                String subject = "Formulario de Contacto"; // Asunto del correo
                String body = "Nombre: " + editTextName.getText().toString() +
                        "\nEmail: " + editTextEmail.getText().toString() +
                        "\nTeléfono: " + editTextPhone.getText().toString(); // Cuerpo del correo

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviar correo..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    // Manejar el caso en que no hay aplicaciones de correo disponibles
                    System.err.println("No hay aplicaciones de correo instaladas.");
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Estoy en el onStar ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Estoy en el onResume");
    }


}

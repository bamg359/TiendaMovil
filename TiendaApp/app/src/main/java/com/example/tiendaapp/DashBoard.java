package com.example.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DashBoard extends AppCompatActivity {

    Button btnSalir;

    ImageButton btnIrMisDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        btnSalir = findViewById(R.id.btnSalir);
        btnIrMisDatos = findViewById(R.id.btn_ver_datos_usuario);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        btnIrMisDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irVerDatosUsuario();
            }
        });

    }

    public void cerrarSesion(){

        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);

    }

    public void irVerDatosUsuario(){

        Intent intent = new Intent(this, MisDatos.class);
        startActivity(intent);

    }

}
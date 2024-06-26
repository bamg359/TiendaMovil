package com.example.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {


    Button irRegistro;
    Button irInicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        irRegistro = findViewById(R.id.btn_registro);

        irInicioSesion = findViewById(R.id.btn_inicio_sesion);

        irRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irRegistro();
            }
        });


        irInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irInicioSesion();
            }
        });



    }

    public void irRegistro(){

        Intent intent = new Intent(Inicio.this, Registro.class);

        startActivity(intent);
    }

    public void irInicioSesion(){

        Intent intent = new Intent(Inicio.this, InicioSesion.class);

        startActivity(intent);
    }

}
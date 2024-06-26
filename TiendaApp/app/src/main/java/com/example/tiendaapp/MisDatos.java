package com.example.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MisDatos extends AppCompatActivity {


    TextView verIdUsuario;

    TextView verNombreUsuario;

    TextView verApellidoUsuario;

    TextView verCorreoUsuario;

    EditText inputIdUsuario;

    Button btnVerDatosUsuario;

    Button btnVolverDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);

        verIdUsuario = findViewById(R.id.txt_ver_id_usuario);

        verNombreUsuario = findViewById(R.id.txt_ver_nombre_usuario);

        verApellidoUsuario = findViewById(R.id.txt_ver_apellido_usaurio);

        verCorreoUsuario = findViewById(R.id.txt_ver_correo_usuario);

        inputIdUsuario = findViewById(R.id.input_consulta_id);

        btnVerDatosUsuario = findViewById(R.id.btn_ver_datos);

        btnVolverDashboard = findViewById(R.id.btn_volver_dashboard);



    }
}
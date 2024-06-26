package com.example.tiendaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    EditText inputNombreUsuario;
    EditText inputApellidoUsuario;
    EditText inputIdUsuario;
    EditText inputCorreo;
    EditText inputContrasena;

    Button btnRegistrar;
    Button btnVolverInicio;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference reference = database.getReference().child("Usuarios");

    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inputNombreUsuario = findViewById(R.id.input_nombre_usuario);
        inputApellidoUsuario = findViewById(R.id.input_apellido_usuario);
        inputIdUsuario = findViewById(R.id.input_id_usuario);
        inputCorreo = findViewById(R.id.input_email);
        inputContrasena = findViewById(R.id.input_password);
        btnRegistrar = findViewById(R.id.btn_registrar);
        btnVolverInicio = findViewById(R.id.btn_volver_inicio);

        btnVolverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverInicio();
            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    public void volverInicio(){

        Intent intent = new Intent(this, Inicio.class);

        startActivity(intent);
    }

    public void registrarUsuario(){

        String usuarioId = inputIdUsuario.getText().toString();

        DatabaseReference nuevoUsuario = reference.child(usuarioId);

        String nombreUsuario = inputNombreUsuario.getText().toString();

        nuevoUsuario.child("Nombre").setValue(nombreUsuario);

        String apellidoUsuario = inputApellidoUsuario.getText().toString();

        nuevoUsuario.child("Apellido").setValue(apellidoUsuario);

        String idUsuario = inputIdUsuario.getText().toString();

        nuevoUsuario.child("id").setValue(idUsuario);

        String correo = inputCorreo.getText().toString();

        nuevoUsuario.child("Correo").setValue(correo);

        String password = inputContrasena.getText().toString();

        nuevoUsuario.child("Password").setValue(password);

        firebaseAuthRegistro(correo,password);

        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();

    }

    public void firebaseAuthRegistro(String correo , String password){

        auth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Usuario creado con exito", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Registro.this, "No se pudo crear el usuario", Toast.LENGTH_LONG).show();
                        }
                    }
                });







    }

}
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

public class InicioSesion extends AppCompatActivity {


    EditText inputCorreoUsuario;

    EditText inputPasswordUsuario;

    Button btnIniciarSesion;

    Button btnVolverInicioDesdeInicioSesion;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        inputCorreoUsuario = findViewById(R.id.input_mail_usuario);

        inputPasswordUsuario = findViewById(R.id.input_password_usuario);

        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);

        btnVolverInicioDesdeInicioSesion = findViewById(R.id.btn_volver_inicio_2);




        btnVolverInicioDesdeInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverInicioDesdeInicioSesion();
            }
        });


        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo = inputCorreoUsuario.getText().toString();

                String password = inputPasswordUsuario.getText().toString();
                iniciarSesion(correo,password);
            }
        });





    }


    public void irDashboard(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);

    }

    public void iniciarSesion(String correo, String password){

        auth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            irDashboard();
                            Toast.makeText(InicioSesion.this, "Inicio de Sesion Correcto" , Toast.LENGTH_LONG).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(InicioSesion.this, "Valide sus credenciales" , Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void volverInicioDesdeInicioSesion(){

        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);

    }

}
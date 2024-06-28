package com.example.tiendaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MisDatos extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference reference = database.getReference().child("Usuarios");


    TextView verIdUsuario;

    TextView verNombreUsuario;

    TextView verApellidoUsuario;

    TextView verCorreoUsuario;

    EditText inputIdUsuario;

    EditText inputEditarNombreUsuario;

    EditText inputEditarApellidoUsuario;

    EditText inputEditarCorreoUsuario;

    Button btnVerDatosUsuario;

    Button btnVolverDashboard;

    Button btnActualizarDatosUsuario;

    Button btnEliminarDatosUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);

        verIdUsuario = findViewById(R.id.txt_ver_id_usuario);

        verNombreUsuario = findViewById(R.id.txt_ver_nombre_usuario);

        verApellidoUsuario = findViewById(R.id.txt_ver_apellido_usaurio);

        verCorreoUsuario = findViewById(R.id.txt_ver_correo_usuario);

        inputIdUsuario = findViewById(R.id.input_consulta_id);

        inputEditarNombreUsuario = findViewById(R.id.input_editar_nombre);

        inputEditarApellidoUsuario = findViewById(R.id.input_editar_apellido);

        inputEditarCorreoUsuario = findViewById(R.id.input_editar_correo);

        btnVerDatosUsuario = findViewById(R.id.btn_ver_datos);

        btnVolverDashboard = findViewById(R.id.btn_volver_dashboard);

        btnActualizarDatosUsuario = findViewById(R.id.btn_actualizar_usuario);

        btnEliminarDatosUsuario = findViewById(R.id.btn_eliminar_usuario);


        btnVerDatosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDatosUsuario();

            }
        });


        btnActualizarDatosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatosUsuario();
            }
        });

        btnEliminarDatosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUsuario();
            }
        });



        btnVolverDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverDashboard();
            }
        });



    }


    public void recuperarDatosUsuario(){

        String usuarioId = inputIdUsuario.getText().toString();

        DatabaseReference usuario = reference.child(usuarioId);

        usuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.exists()){

                    String id = snapshot.child("id").getValue(String.class);
                    verIdUsuario.setText("Id: " + id);
                    String nombre = snapshot.child("Nombre").getValue(String.class);
                    verNombreUsuario.setText("Nombre: " + nombre);
                    String apellido = snapshot.child("Apellido").getValue(String.class);
                    verApellidoUsuario.setText("Apellido: " + apellido);
                    String correo = snapshot.child("Correo").getValue(String.class);
                    verCorreoUsuario.setText("Correo: " + correo);

                }else{

                    Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    public void volverDashboard(){

        Intent intent = new Intent(this, DashBoard.class);

        startActivity(intent);
    }


    public void actualizarDatosUsuario(){

        String usuarioId = inputIdUsuario.getText().toString();

        Map<String, Object> nuevosDatos = new HashMap<>();

        nuevosDatos.put("id",usuarioId);
        nuevosDatos.put("Nombre", inputEditarNombreUsuario.getText().toString());
        nuevosDatos.put("Apellido", inputEditarApellidoUsuario.getText().toString());
        nuevosDatos.put("Correo", inputEditarCorreoUsuario.getText().toString());


        DatabaseReference usuario = reference.child(usuarioId);

        usuario.updateChildren(nuevosDatos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Usuario actualizado correctamente" , Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void eliminarUsuario(){

        String usuarioId = inputIdUsuario.getText().toString();

        DatabaseReference usuario = reference.child(usuarioId);

        usuario.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Usuario Eliminado" , Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al eliminar usuario" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
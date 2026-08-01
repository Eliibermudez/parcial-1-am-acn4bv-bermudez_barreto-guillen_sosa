package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText inputNombre;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputConfirmarPassword;
    private Button btnRegistrarme;
    private TextView txtVolverLogin;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        inputNombre = findViewById(R.id.inputNombreRegistro);
        inputEmail = findViewById(R.id.inputEmailRegistro);
        inputPassword = findViewById(R.id.inputPasswordRegistro);
        inputConfirmarPassword = findViewById(R.id.inputConfirmarPasswordRegistro);
        btnRegistrarme = findViewById(R.id.btnRegistrarme);
        txtVolverLogin = findViewById(R.id.txtVolverLogin);

        btnRegistrarme.setOnClickListener(v -> validarRegistro());

        txtVolverLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void validarRegistro() {
        String nombre = inputNombre.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirmarPassword = inputConfirmarPassword.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(this, "Ingresá un email válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmarPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        registrarUsuario(nombre, email, password);
    }

    private void registrarUsuario(String nombre, String email, String password) {
        btnRegistrarme.setEnabled(false);
        btnRegistrarme.setText("Registrando...");

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && firebaseAuth.getCurrentUser() != null) {
                        String uid = firebaseAuth.getCurrentUser().getUid();
                        guardarUsuarioEnFirestore(uid, nombre, email);
                    } else {
                        btnRegistrarme.setEnabled(true);
                        btnRegistrarme.setText("Registrarme");
                        Toast.makeText(this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void guardarUsuarioEnFirestore(String uid, String nombre, String email) {
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nombre", nombre);
        usuario.put("email", email);
        usuario.put("telefono", "Sin cargar");
        usuario.put("carrera", "Analista de Sistemas");
        usuario.put("comision", "ACN1BV");
        usuario.put("turno", "Noche");
        usuario.put("imagenUrl", "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg");

        db.collection("usuarios")
                .document(uid)
                .set(usuario)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegistroActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    btnRegistrarme.setEnabled(true);
                    btnRegistrarme.setText("Registrarme");
                    Toast.makeText(this, "Error al guardar el perfil", Toast.LENGTH_SHORT).show();
                });
    }
}

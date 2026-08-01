package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private Button btnIngresar;
    private TextView txtIrRegistro;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        firebaseAuth = FirebaseAuth.getInstance();

        btnIngresar.setOnClickListener(v -> validarLogin());
        txtIrRegistro = findViewById(R.id.txtIrRegistro);

        txtIrRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioActual = firebaseAuth.getCurrentUser();

        if (usuarioActual != null) {
            irAHome();
        }
    }

    private void validarLogin() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.login_error_campos), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(this, getString(R.string.login_error_email), Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, getString(R.string.login_error_password_firebase), Toast.LENGTH_SHORT).show();
            return;
        }

        iniciarSesionFirebase(email, password);
    }

    private void iniciarSesionFirebase(String email, String password) {
        btnIngresar.setEnabled(false);
        btnIngresar.setText(getString(R.string.login_cargando));

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    btnIngresar.setEnabled(true);
                    btnIngresar.setText(getString(R.string.ingresar));

                    if (task.isSuccessful()) {
                        Toast.makeText(this, getString(R.string.login_exitoso), Toast.LENGTH_SHORT).show();
                        irAHome();
                    } else {
                        Toast.makeText(this, getString(R.string.login_error_firebase), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void irAHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
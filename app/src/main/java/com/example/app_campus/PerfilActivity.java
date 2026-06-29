package com.example.app_campus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    TextView txtNombre, txtEmail, txtCarrera, txtComision, txtTurno, txtTelefono;
    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Referencias
        txtNombre = findViewById(R.id.txtNombre);
        txtEmail = findViewById(R.id.txtEmail);
        txtCarrera = findViewById(R.id.txtCarrera);
        txtComision = findViewById(R.id.txtComision);
        txtTurno = findViewById(R.id.txtTurno);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnEditar = findViewById(R.id.btnEditar);

        // Datos de prueba
        cargarDatos();

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_perfil);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_materias) {
                startActivity(new Intent(this, MateriasActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_grupos) {
                startActivity(new Intent(this, GruposActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_calendario) {
                startActivity(new Intent(this, CalendarioActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_chat) {
                Toast.makeText(this, "Chat en desarrollo", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (id == R.id.nav_perfil) {
                return true;
            }

            return false;
        });
    }

    private void cargarDatos() {
        txtNombre.setText("Juana Pérez");
        txtEmail.setText("juan.perez@davinci.edu.ar");

        txtCarrera.setText("Analista de Sistemas");
        txtComision.setText("ACN4BV");
        txtTurno.setText("Noche");
        txtTelefono.setText("+11 5555-5555");
    }
}

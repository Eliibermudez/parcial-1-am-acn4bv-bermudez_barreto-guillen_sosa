package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity {

    TextView txtNombre, txtEmail, txtCarrera, txtComision, txtTurno, txtTelefono;
    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        initViews();
        initListeners();

        cargarDatos();
        setupBottomNav();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatos();
    }

    //init views
    private void initViews() {
        txtNombre = findViewById(R.id.txtNombre);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCarrera = findViewById(R.id.txtCarrera);
        txtComision = findViewById(R.id.txtComision);
        txtTurno = findViewById(R.id.txtTurno);
        btnEditar = findViewById(R.id.btnEditar);
    }

    //listeners
    private void initListeners() {

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditarPerfilActivity.class);
            startActivity(intent);
        });
    }

    private void cargarDatos() {

        txtNombre.setText(PerfilRepository.nombre);
        txtEmail.setText(PerfilRepository.email);
        txtTelefono.setText(PerfilRepository.telefono);

        txtCarrera.setText(PerfilRepository.carrera);
        txtComision.setText(PerfilRepository.comision);
        txtTurno.setText(PerfilRepository.turno);
    }

    //nav
    private void setupBottomNav() {

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

            return id == R.id.nav_perfil;
        });
    }
}
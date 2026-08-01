package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

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
        imgPerfil = findViewById(R.id.imgPerfil);
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

    ImageView imgPerfil;
    private void cargarDatos() {

        txtNombre.setText(PerfilRepository.nombre);
        txtEmail.setText(PerfilRepository.email);
        txtTelefono.setText(PerfilRepository.telefono);

        txtCarrera.setText(PerfilRepository.carrera);
        txtComision.setText(PerfilRepository.comision);
        txtTurno.setText(PerfilRepository.turno);

        Glide.with(this)
                .load("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg")
                .placeholder(R.drawable.bg_chip_gray)
                .error(R.drawable.bg_chip_red)
                .circleCrop()
                .into(imgPerfil);
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
                startActivity(new Intent(this, NovedadesActivity.class));
                finish();
                return true;
            }
            if (id == R.id.nav_contacto) {
                startActivity(new Intent(this, ContactoActivity.class));
                finish();
                return true;
            }

            return id == R.id.nav_perfil;
        });
    }
}
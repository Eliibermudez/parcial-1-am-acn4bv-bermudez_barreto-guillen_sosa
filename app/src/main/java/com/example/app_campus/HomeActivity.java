package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout btnAccesoMaterias;
    private LinearLayout btnAccesoGrupos;
    private LinearLayout btnAccesoCalendario;
    private LinearLayout btnAccesoNovedades;

    private LinearLayout btnAccesoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAccesoMaterias = findViewById(R.id.btnAccesoMaterias);
        btnAccesoGrupos = findViewById(R.id.btnAccesoGrupos);
        btnAccesoCalendario = findViewById(R.id.btnAccesoCalendario);
        btnAccesoNovedades = findViewById(R.id.btnAccesoNovedades);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_inicio);

        btnAccesoMaterias.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MateriasActivity.class);
            startActivity(intent);
        });

        btnAccesoGrupos.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, GruposActivity.class);
            startActivity(intent);
        });

        btnAccesoCalendario.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CalendarioActivity.class);
            startActivity(intent);
        });

        btnAccesoNovedades.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NovedadesActivity.class);
            startActivity(intent);
        });

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_inicio) {
                return true;
            }

            if (itemId == R.id.nav_materias) {
                Intent intent = new Intent(HomeActivity.this, MateriasActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_grupos) {
                Intent intent = new Intent(HomeActivity.this, GruposActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_calendario) {
                Intent intent = new Intent(HomeActivity.this, CalendarioActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_chat) {
                Intent intent = new Intent(HomeActivity.this, NovedadesActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_perfil) {
                Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });
    }
}
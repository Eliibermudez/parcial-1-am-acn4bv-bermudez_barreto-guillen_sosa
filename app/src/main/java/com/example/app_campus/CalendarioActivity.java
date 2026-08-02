package com.example.app_campus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarioActivity extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        // botón volver
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // contenedor
        container = findViewById(R.id.containerParciales);

        // datos
        if (ParcialesRepository.listaParciales.isEmpty()) {

            ParcialesRepository.listaParciales.add(
                    new Parcial("Aplicaciones Móviles", "06/05/2026 - 19hs", "Aula virtual", "#7E57C2")
            );

            ParcialesRepository.listaParciales.add(
                    new Parcial("Base de Datos III", "10/05/2026 - 21hs", "Laboratorio", "#FFA726")
            );
        }

        // navegación
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_calendario);

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

            if (id == R.id.nav_calendario) {
                return true;
            }

            if (id == R.id.nav_chat) {
                startActivity(new Intent(this, NovedadesActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_grupos) {
                startActivity(new Intent(this, GruposActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, PerfilActivity.class));
                finish();
                return true;
            }


            return false;
        });
    }

    // render dinámico
    @Override
    protected void onResume() {
        super.onResume();

        container.removeAllViews();

        for (Parcial p : ParcialesRepository.listaParciales) {
            crearParcialDinamico(p);
        }
    }

    // card dinámica
    private void crearParcialDinamico(Parcial p) {

        View card = LayoutInflater.from(this)
                .inflate(R.layout.item_parcial, container, false);

        TextView txtMateria = card.findViewById(R.id.txtMateria);
        TextView txtFecha = card.findViewById(R.id.txtFecha);
        TextView txtDetalle = card.findViewById(R.id.txtDetalle);
        View dot = card.findViewById(R.id.dot);

        txtMateria.setText(p.materia);
        txtFecha.setText(p.fecha);
        txtDetalle.setText(p.detalle);

        dot.setBackgroundColor(Color.parseColor(p.color));

        // eliminar con long press
        card.setOnLongClickListener(v -> {
            ParcialesRepository.listaParciales.remove(p);
            container.removeView(card);
            return true;
        });

        container.addView(card);
    }
}
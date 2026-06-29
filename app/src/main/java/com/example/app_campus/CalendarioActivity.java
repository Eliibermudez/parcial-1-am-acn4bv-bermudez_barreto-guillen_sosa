package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

        container = findViewById(R.id.containerParciales);

        agregarParcial("Aplicaciones Móviles", "06/05/2026 - 19hs", "#7E57C2");
        agregarParcial("Base de Datos III", "06/05/2026 - 19hs", "#FFA726");
        agregarParcial("Plataformas de Desarrollo", "06/05/2026 - 19hs", "#66BB6A");
        agregarParcial("Comunicaciones y redes", "06/05/2026 - 19hs", "#42A5F5");

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setSelectedItemId(R.id.nav_calendario);

        //Navegación menú
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            }

            if (item.getItemId() == R.id.nav_materias) {
                startActivity(new Intent(this, MateriasActivity.class));
                return true;
            }

            if (item.getItemId() == R.id.nav_calendario) {
                return true;
            }

            return false;
        });
    }

    private void agregarParcial(String materia, String fecha, String color) {

        View card = LayoutInflater.from(this)
                .inflate(R.layout.item_parcial, container, false);

        TextView txtMateria = card.findViewById(R.id.txtMateria);
        TextView txtFecha = card.findViewById(R.id.txtFecha);
        View dot = card.findViewById(R.id.dot);

        txtMateria.setText(materia);
        txtFecha.setText(fecha);

        dot.setBackgroundColor(android.graphics.Color.parseColor(color));

        container.addView(card);
    }
}
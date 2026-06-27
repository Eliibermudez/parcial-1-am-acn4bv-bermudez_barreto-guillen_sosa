package com.example.app_campus;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MateriasActivity extends AppCompatActivity {

    private LinearLayout listaMaterias;

    String[] materias = {
            "Matemática",
            "Aplicaciones Móviles",
            "Java II",
            "Arquitectura de Computadoras",
            "Sistemas Operativos"
    };

    int index = 2;
    int MAX_INICIAL = 2;
    int MAX_TOTAL = materias.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        listaMaterias = findViewById(R.id.layoutListaMaterias);
        ImageView btnAgregar = findViewById(R.id.btnAdd);


        btnAgregar.setOnClickListener(v -> agregarMateria());
    }
    private void agregarMateria() {

        if (index >= materias.length) {
            Toast.makeText(this, "Ya agregaste todas las materias", Toast.LENGTH_SHORT).show();
            return;
        }

        agregarCardMateria(materias[index]);

        Toast.makeText(this, "Materia agregada", Toast.LENGTH_SHORT).show();

        index++;
    }

    private void agregarCardMateria(String nombreMateria) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(32, 32, 32, 32);
        card.setBackgroundResource(R.drawable.bg_card);

        LinearLayout.LayoutParams paramsCard = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        paramsCard.setMargins(0, 0, 0, 24);
        card.setLayoutParams(paramsCard);

        TextView titulo = new TextView(this);
        titulo.setText(nombreMateria);
        titulo.setTextSize(16);
        titulo.setTextColor(0xFF222222);
        titulo.setTypeface(null, Typeface.BOLD);

        TextView detalle = new TextView(this);
        detalle.setText("Pendiente de horario");
        detalle.setTextSize(13);
        detalle.setTextColor(0xFF555555);

        LinearLayout.LayoutParams paramsDetalle = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        paramsDetalle.setMargins(0, 8, 0, 0);
        detalle.setLayoutParams(paramsDetalle);

        card.addView(titulo);
        card.addView(detalle);

        listaMaterias.addView(card);

        card.setOnClickListener(v ->
                Toast.makeText(this, nombreMateria, Toast.LENGTH_SHORT).show()
        );
    }
}
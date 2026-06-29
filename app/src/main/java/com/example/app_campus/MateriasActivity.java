package com.example.app_campus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MateriasActivity extends AppCompatActivity {

    private LinearLayout contenedorMaterias;
    private ImageView btnAgregar;

    private ActivityResultLauncher<Intent> agregarMateriaLauncher;

    private static final int MAX_MATERIAS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        contenedorMaterias = findViewById(R.id.contenedorMaterias);
        btnAgregar = findViewById(R.id.btnAdd);

        if (MateriasRepository.listaMaterias.isEmpty()) {

            Materia materiaInicial = new Materia(
                    "4to Cuatrimestre",
                    "Comisión 1",
                    "Programación I",
                    "08:00 - 10:00",
                    "Juan Pérez"
            );

            MateriasRepository.listaMaterias.add(materiaInicial);
        }

        // agregar materia
        agregarMateriaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        if (contenedorMaterias.getChildCount() >= MAX_MATERIAS) {
                            Toast.makeText(this, "Máximo 5 materias permitidas", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Intent data = result.getData();

                        crearMateriaDinamica(
                                data.getStringExtra("cuatrimestre"),
                                data.getStringExtra("comision"),
                                data.getStringExtra("materia"),
                                data.getStringExtra("horario"),
                                data.getStringExtra("docente")
                        );
                    }
                }
        );

        btnAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarMateriaActivity.class);
            agregarMateriaLauncher.launch(intent);
        });

        configurarBottomNav();
    }

    // card dinamica
    private void crearMateriaDinamica(String cuatri, String comision,
                                      String materia, String horario, String docente) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(20), dp(16), dp(20), dp(16));
        card.setBackgroundResource(R.drawable.bg_card);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, dp(16));
        card.setLayoutParams(params);

        LinearLayout filaTop = new LinearLayout(this);
        filaTop.setOrientation(LinearLayout.HORIZONTAL);

        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.ic_book);
        icon.setLayoutParams(new LinearLayout.LayoutParams(dp(40), dp(40)));

        LinearLayout texto = new LinearLayout(this);
        texto.setOrientation(LinearLayout.VERTICAL);
        texto.setPadding(dp(12), 0, 0, 0);

        TextView txtMateria = new TextView(this);
        txtMateria.setText(materia);
        txtMateria.setTypeface(null, Typeface.BOLD);
        txtMateria.setTextSize(16f);

        TextView txtHorario = new TextView(this);
        txtHorario.setText(horario);

        texto.addView(txtMateria);
        texto.addView(txtHorario);

        filaTop.addView(icon);
        filaTop.addView(texto);

        TextView detalle = new TextView(this);
        detalle.setText(cuatri + " | " + comision + "\nDocente: " + docente);
        detalle.setPadding(0, dp(8), 0, 0);

        // eliminar
        TextView btnEliminar = new TextView(this);
        btnEliminar.setText("Eliminar");
        btnEliminar.setPadding(dp(12), dp(6), dp(12), dp(6));
        btnEliminar.setBackgroundResource(R.drawable.bg_chip_gray);
        btnEliminar.setTextColor(0xFFAA0000);
        btnEliminar.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams lpBtn = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lpBtn.topMargin = dp(10);
        btnEliminar.setLayoutParams(lpBtn);

        btnEliminar.setOnClickListener(v -> {
            MateriasRepository.listaMaterias.removeIf(m ->
                    m.nombre.equals(materia) &&
                            m.horario.equals(horario)
            );

            contenedorMaterias.removeView(card);
        });

        card.addView(filaTop);
        card.addView(detalle);
        card.addView(btnEliminar);

        contenedorMaterias.addView(card);
    }

    @Override
    protected void onResume() {
        super.onResume();

        contenedorMaterias.removeAllViews();

        for (Materia m : MateriasRepository.listaMaterias) {
            crearMateriaDinamica(
                    m.cuatrimestre,
                    m.comision,
                    m.nombre,
                    m.horario,
                    m.docente
            );
        }
    }

    // navegacion
    private void configurarBottomNav() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_materias);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_grupos) {
                startActivity(new Intent(this, GruposActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_materias) {
                return true;
            }

            if (id == R.id.nav_calendario) {
                startActivity(new Intent(this, CalendarioActivity.class));
                finish();
            }

            if (id == R.id.nav_chat) {
                Intent intent = new Intent(MateriasActivity.this, NovedadesActivity.class);
                startActivity(intent);
                return true;
            }

            if (id == R.id.nav_perfil) {
                Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show();
                return false;
            }

            return false;
        });
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }
}
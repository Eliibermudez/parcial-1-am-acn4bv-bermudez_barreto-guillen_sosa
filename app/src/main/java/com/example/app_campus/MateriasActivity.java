package com.example.app_campus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

        ImageView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish();
        });

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
        filaTop.setGravity(android.view.Gravity.CENTER_VERTICAL);

        FrameLayout fondoIcono = new FrameLayout(this);
        fondoIcono.setBackgroundResource(R.drawable.bg_icon_blue);

        LinearLayout.LayoutParams lpFondoIcono = new LinearLayout.LayoutParams(
                dp(44),
                dp(44)
        );
        lpFondoIcono.setMargins(0, 0, dp(12), 0);
        fondoIcono.setLayoutParams(lpFondoIcono);

        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.ic_book);
        icon.setColorFilter(getColor(R.color.colorPrimary));

        FrameLayout.LayoutParams lpIcono = new FrameLayout.LayoutParams(
                dp(26),
                dp(26),
                android.view.Gravity.CENTER
        );
        icon.setLayoutParams(lpIcono);

        fondoIcono.addView(icon);

        LinearLayout texto = new LinearLayout(this);
        texto.setOrientation(LinearLayout.VERTICAL);

        TextView txtMateria = new TextView(this);
        txtMateria.setText(materia);
        txtMateria.setTypeface(null, Typeface.BOLD);
        txtMateria.setTextSize(16f);
        txtMateria.setTextColor(getColor(R.color.textPrimary));

        TextView txtHorario = new TextView(this);
        txtHorario.setText(horario);
        txtHorario.setTextSize(13f);
        txtHorario.setTextColor(getColor(R.color.textSecondary));

        texto.addView(txtMateria);
        texto.addView(txtHorario);

        filaTop.addView(fondoIcono);
        filaTop.addView(texto);

        TextView detalle = new TextView(this);
        detalle.setText(cuatri + " | " + comision + "\nDocente: " + docente);
        detalle.setTextSize(13f);
        detalle.setTextColor(getColor(R.color.textSecondary));

        LinearLayout.LayoutParams lpDetalle = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lpDetalle.setMargins(dp(56), dp(8), 0, 0);
        detalle.setLayoutParams(lpDetalle);

        // eliminar
        TextView btnEliminar = new TextView(this);
        btnEliminar.setText("Eliminar");
        btnEliminar.setTextSize(12);
        btnEliminar.setGravity(android.view.Gravity.CENTER);
        btnEliminar.setPadding(dp(14), dp(6), dp(14), dp(6));
        btnEliminar.setBackgroundResource(R.drawable.bg_chip_delete);
        btnEliminar.setTextColor(getColor(R.color.colorDanger));
        btnEliminar.setTypeface(null, Typeface.BOLD);
        btnEliminar.setClickable(true);
        btnEliminar.setFocusable(true);

        LinearLayout.LayoutParams lpBtn = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                dp(32)
        );
        btnEliminar.setLayoutParams(lpBtn);

        LinearLayout filaEliminar = new LinearLayout(this);
        filaEliminar.setOrientation(LinearLayout.HORIZONTAL);
        filaEliminar.setGravity(android.view.Gravity.END);

        LinearLayout.LayoutParams lpFilaEliminar = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lpFilaEliminar.topMargin = dp(10);
        filaEliminar.setLayoutParams(lpFilaEliminar);

        filaEliminar.addView(btnEliminar);

        btnEliminar.setOnClickListener(v -> {
            MateriasRepository.listaMaterias.removeIf(m ->
                    m.nombre.equals(materia) &&
                            m.horario.equals(horario)
            );

            contenedorMaterias.removeView(card);
        });

        card.addView(filaTop);
        card.addView(detalle);
        card.addView(filaEliminar);

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
                return true;
            }

            if (id == R.id.nav_chat) {
                Intent intent = new Intent(MateriasActivity.this, NovedadesActivity.class);
                startActivity(intent);
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

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }
}
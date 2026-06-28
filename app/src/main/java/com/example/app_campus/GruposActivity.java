package com.example.app_campus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class GruposActivity extends AppCompatActivity {

    private LinearLayout contenedorListaGrupos;
    private ActivityResultLauncher<Intent> crearGrupoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        Button btnCrearGrupo = findViewById(R.id.btnCrearGrupo);
        contenedorListaGrupos = findViewById(R.id.contenedorListaGrupos);

        crearGrupoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();

                        String titulo = data.getStringExtra(CrearGrupoActivity.EXTRA_TITULO_GRUPO);
                        String detalle = data.getStringExtra(CrearGrupoActivity.EXTRA_DETALLE_GRUPO);
                        String descripcion = data.getStringExtra(CrearGrupoActivity.EXTRA_DESCRIPCION_GRUPO);

                        crearGrupoDinamico(titulo, detalle, descripcion);
                    }
                }
        );

        btnCrearGrupo.setOnClickListener(v -> {
            Intent intent = new Intent(GruposActivity.this, CrearGrupoActivity.class);
            crearGrupoLauncher.launch(intent);
        });
    }

    private void crearGrupoDinamico(String titulo, String detalle, String descripcion) {
        LinearLayout cardGrupo = new LinearLayout(this);
        cardGrupo.setOrientation(LinearLayout.VERTICAL);
        cardGrupo.setPadding(24, 20, 24, 20);
        cardGrupo.setBackgroundResource(R.drawable.bg_card);

        LinearLayout.LayoutParams parametrosCard = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        parametrosCard.setMargins(0, 0, 0, 20);
        cardGrupo.setLayoutParams(parametrosCard);

        TextView tituloGrupo = new TextView(this);
        tituloGrupo.setText(titulo);
        tituloGrupo.setTextSize(16);
        tituloGrupo.setTextColor(0xFF222222);
        tituloGrupo.setTypeface(null, Typeface.BOLD);

        TextView detalleGrupo = new TextView(this);
        detalleGrupo.setText(detalle);
        detalleGrupo.setTextSize(12);
        detalleGrupo.setTextColor(0xFF666666);

        TextView descripcionGrupo = new TextView(this);
        descripcionGrupo.setText(descripcion);
        descripcionGrupo.setTextSize(12);
        descripcionGrupo.setTextColor(0xFF555555);
        descripcionGrupo.setPadding(0, 8, 0, 0);

        TextView estadoGrupo = new TextView(this);
        estadoGrupo.setText(getString(R.string.grupo_estado_abierto));
        estadoGrupo.setTextSize(12);
        estadoGrupo.setTextColor(0xFF2E7D32);
        estadoGrupo.setTypeface(null, Typeface.BOLD);
        estadoGrupo.setPadding(0, 12, 0, 0);

        cardGrupo.addView(tituloGrupo);
        cardGrupo.addView(detalleGrupo);
        cardGrupo.addView(descripcionGrupo);
        cardGrupo.addView(estadoGrupo);

        contenedorListaGrupos.addView(cardGrupo);
    }
}
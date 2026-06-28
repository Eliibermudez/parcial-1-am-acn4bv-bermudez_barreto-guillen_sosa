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

        TextView estadoGrupo1 = findViewById(R.id.estadoGrupo1);
        TextView btnUnirseGrupo1 = findViewById(R.id.btnUnirseGrupo1);
        TextView detalleGrupo1 = findViewById(R.id.detalleGrupo1);

        configurarAccionGrupo(estadoGrupo1, btnUnirseGrupo1, detalleGrupo1, 2);

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

    private void configurarAccionGrupo(TextView estadoGrupo, TextView btnUnirseGrupo, TextView detalleGrupo, int integrantesIniciales) {
        final int[] integrantesFaltantes = {integrantesIniciales};

        btnUnirseGrupo.setOnClickListener(v -> {
            if (btnUnirseGrupo.getText().toString().equals(getString(R.string.unirme_grupo))) {
                integrantesFaltantes[0]--;

                btnUnirseGrupo.setText(getString(R.string.salir_grupo));
                estadoGrupo.setText(getString(R.string.grupo_estado_integrante));
                estadoGrupo.setTextColor(0xFF5B45D9);
                detalleGrupo.setText(obtenerTextoIntegrantes(integrantesFaltantes[0]));
            } else {
                integrantesFaltantes[0]++;

                btnUnirseGrupo.setText(getString(R.string.unirme_grupo));
                estadoGrupo.setText(getString(R.string.grupo_estado_abierto));
                estadoGrupo.setTextColor(0xFF2E7D32);
                detalleGrupo.setText(obtenerTextoIntegrantes(integrantesFaltantes[0]));
            }
        });
    }

    private String obtenerTextoIntegrantes(int cantidad) {
        if (cantidad == 1) {
            return "Falta 1 integrante";
        }

        return "Faltan " + cantidad + " integrantes";
    }

    private int obtenerCantidadDesdeDetalle(String detalle) {
        String soloNumeros = detalle.replaceAll("[^0-9]", "");

        if (soloNumeros.isEmpty()) {
            return 1;
        }

        return Integer.parseInt(soloNumeros);
    }
    private void crearGrupoDinamico(String titulo, String detalle, String descripcion) {
        LinearLayout cardGrupo = new LinearLayout(this);
        cardGrupo.setOrientation(LinearLayout.VERTICAL);
        cardGrupo.setPadding(dp(24), dp(20), dp(24), dp(20));
        cardGrupo.setBackgroundResource(R.drawable.bg_card);

        LinearLayout.LayoutParams parametrosCard = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        parametrosCard.setMargins(0, 0, 0, dp(20));
        cardGrupo.setLayoutParams(parametrosCard);

        TextView tituloGrupo = new TextView(this);
        tituloGrupo.setText(titulo);
        tituloGrupo.setTextSize(16);
        tituloGrupo.setTextColor(0xFF222222);
        tituloGrupo.setTypeface(null, Typeface.BOLD);

        TextView detalleGrupo = new TextView(this);
        detalleGrupo.setText(detalle);
        detalleGrupo.setTextSize(13);
        detalleGrupo.setTextColor(0xFF555555);

        TextView descripcionGrupo = new TextView(this);
        descripcionGrupo.setText(descripcion);
        descripcionGrupo.setTextSize(13);
        descripcionGrupo.setTextColor(0xFF555555);
        descripcionGrupo.setPadding(0, 4, 0, 0);

        TextView estadoGrupo = new TextView(this);
        estadoGrupo.setText(getString(R.string.grupo_estado_abierto));
        estadoGrupo.setTextSize(12);
        estadoGrupo.setTextColor(0xFF2E7D32);
        estadoGrupo.setTypeface(null, Typeface.BOLD);
        estadoGrupo.setGravity(android.view.Gravity.CENTER);
        estadoGrupo.setPadding(dp(18), dp(6), dp(18), dp(6));
        estadoGrupo.setBackgroundResource(R.drawable.bg_chip_green);

        TextView btnUnirseGrupo = new TextView(this);
        btnUnirseGrupo.setText(getString(R.string.unirme_grupo));
        btnUnirseGrupo.setTextSize(12);
        btnUnirseGrupo.setTextColor(0xFF666666);
        btnUnirseGrupo.setGravity(android.view.Gravity.CENTER);
        btnUnirseGrupo.setPadding(dp(18), dp(6), dp(18), dp(6));
        btnUnirseGrupo.setBackgroundResource(R.drawable.bg_chip_gray);
        btnUnirseGrupo.setClickable(true);
        btnUnirseGrupo.setFocusable(true);

        LinearLayout.LayoutParams parametrosBoton = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        parametrosBoton.setMargins(dp(12), 0, 0, 0);
        btnUnirseGrupo.setLayoutParams(parametrosBoton);

        configurarAccionGrupo(
                estadoGrupo,
                btnUnirseGrupo,
                detalleGrupo,
                obtenerCantidadDesdeDetalle(detalle)
        );

        LinearLayout filaAcciones = new LinearLayout(this);
        filaAcciones.setOrientation(LinearLayout.HORIZONTAL);
        filaAcciones.setGravity(android.view.Gravity.CENTER_VERTICAL);
        filaAcciones.setPadding(0, dp(12), 0, 0);

        filaAcciones.addView(estadoGrupo);
        filaAcciones.addView(btnUnirseGrupo);

        cardGrupo.addView(tituloGrupo);
        cardGrupo.addView(detalleGrupo);
        cardGrupo.addView(descripcionGrupo);
        cardGrupo.addView(filaAcciones);

        contenedorListaGrupos.addView(cardGrupo);
    }

    private int dp(int valor) {
        return (int) (valor * getResources().getDisplayMetrics().density);
    }
}

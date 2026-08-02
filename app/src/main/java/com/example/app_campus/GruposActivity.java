package com.example.app_campus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class GruposActivity extends AppCompatActivity {

    private LinearLayout contenedorListaGrupos;
    private ActivityResultLauncher<Intent> crearGrupoLauncher;
    private boolean mostrandoMisGrupos = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        ImageView btnCrearGrupo = findViewById(R.id.btnCrearGrupo);
        ImageView btnBackGrupos = findViewById(R.id.btnBackGrupos);
        contenedorListaGrupos = findViewById(R.id.contenedorListaGrupos);

        TextView tabBuscarGrupos = findViewById(R.id.tabBuscarGrupos);
        TextView tabMisGrupos = findViewById(R.id.tabMisGrupos);

        LinearLayout cardGrupoBaseDatos = findViewById(R.id.cardGrupoBaseDatos);
        LinearLayout cardGrupoProgramacion = findViewById(R.id.cardGrupoProgramacion);

        TextView estadoGrupo1 = findViewById(R.id.estadoGrupo1);
        TextView btnUnirseGrupo1 = findViewById(R.id.btnUnirseGrupo1);
        TextView detalleGrupo1 = findViewById(R.id.detalleGrupo1);

        cardGrupoBaseDatos.setTag(false);
        cardGrupoProgramacion.setTag(false);

        configurarAccionGrupo(estadoGrupo1, btnUnirseGrupo1, detalleGrupo1, 2, cardGrupoBaseDatos);

        btnBackGrupos.setOnClickListener(v -> {
            Intent intent = new Intent(GruposActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        tabBuscarGrupos.setOnClickListener(v -> {
            mostrandoMisGrupos = false;
            mostrarTodosLosGrupos();
            actualizarTextoBotonesSegunTab();
            actualizarTabs(tabBuscarGrupos, tabMisGrupos);
        });

        tabMisGrupos.setOnClickListener(v -> {
            mostrandoMisGrupos = true;
            mostrarSoloMisGrupos();
            actualizarTextoBotonesSegunTab();
            actualizarTabs(tabMisGrupos, tabBuscarGrupos);
        });

        actualizarTabs(tabBuscarGrupos, tabMisGrupos);

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

        configurarBottomNavigation();
    }

    private void configurarBottomNavigation() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_grupos);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_inicio) {
                startActivity(new Intent(GruposActivity.this, HomeActivity.class));
                return true;
            }

            if (itemId == R.id.nav_materias) {
                startActivity(new Intent(GruposActivity.this, MateriasActivity.class));
                return true;
            }

            if (itemId == R.id.nav_grupos) {
                return true;
            }

            if (itemId == R.id.nav_calendario) {
                startActivity(new Intent(GruposActivity.this, CalendarioActivity.class));
                finish();
                return true;
            }

            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(GruposActivity.this, NovedadesActivity.class));
                return true;
            }

            if (itemId == R.id.nav_perfil) {
                startActivity(new Intent(GruposActivity.this, PerfilActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }

    private void configurarAccionGrupo(TextView estadoGrupo, TextView btnUnirseGrupo, TextView detalleGrupo, int integrantesIniciales, LinearLayout cardGrupo) {
        final int[] integrantesFaltantes = {integrantesIniciales};

        btnUnirseGrupo.setOnClickListener(v -> {
            String textoBoton = btnUnirseGrupo.getText().toString();

            if (textoBoton.equals(getString(R.string.unirme_grupo))) {
                integrantesFaltantes[0]--;

                btnUnirseGrupo.setText(getString(R.string.ya_estas_grupo));
                estadoGrupo.setText(getString(R.string.grupo_estado_integrante));
                estadoGrupo.setTextColor(getColor(R.color.colorAccentTurquoiseDark));
                detalleGrupo.setText(obtenerTextoIntegrantes(integrantesFaltantes[0]));

                cardGrupo.setTag(true);

                if (mostrandoMisGrupos) {
                    btnUnirseGrupo.setText(getString(R.string.salir_grupo));
                }

            } else if (textoBoton.equals(getString(R.string.salir_grupo))) {
                integrantesFaltantes[0]++;

                btnUnirseGrupo.setText(getString(R.string.unirme_grupo));
                estadoGrupo.setText(getString(R.string.grupo_estado_abierto));
                estadoGrupo.setTextColor(getColor(R.color.colorSuccess));
                detalleGrupo.setText(obtenerTextoIntegrantes(integrantesFaltantes[0]));

                cardGrupo.setTag(false);

                if (mostrandoMisGrupos) {
                    cardGrupo.setVisibility(View.GONE);
                }
            }
        });
    }

    private void mostrarTodosLosGrupos() {
        for (int i = 0; i < contenedorListaGrupos.getChildCount(); i++) {
            View card = contenedorListaGrupos.getChildAt(i);
            card.setVisibility(View.VISIBLE);
        }
    }

    private void mostrarSoloMisGrupos() {
        for (int i = 0; i < contenedorListaGrupos.getChildCount(); i++) {
            View card = contenedorListaGrupos.getChildAt(i);
            Object tag = card.getTag();

            if (tag instanceof Boolean && (Boolean) tag) {
                card.setVisibility(View.VISIBLE);
            } else {
                card.setVisibility(View.GONE);
            }
        }
    }

    private void actualizarTextoBotonesSegunTab() {
        for (int i = 0; i < contenedorListaGrupos.getChildCount(); i++) {
            View card = contenedorListaGrupos.getChildAt(i);
            Object tag = card.getTag();

            TextView botonAccion = obtenerBotonAccion(card);

            if (botonAccion != null && tag instanceof Boolean && (Boolean) tag) {
                if (mostrandoMisGrupos) {
                    botonAccion.setText(getString(R.string.salir_grupo));
                } else {
                    botonAccion.setText(getString(R.string.ya_estas_grupo));
                }
            }
        }
    }

    private TextView obtenerBotonAccion(View card) {
        if (!(card instanceof LinearLayout)) {
            return null;
        }

        LinearLayout cardLayout = (LinearLayout) card;

        if (cardLayout.getChildCount() == 0) {
            return null;
        }

        View ultimaVista = cardLayout.getChildAt(cardLayout.getChildCount() - 1);

        if (!(ultimaVista instanceof LinearLayout)) {
            return null;
        }

        LinearLayout filaAcciones = (LinearLayout) ultimaVista;

        if (filaAcciones.getChildCount() < 2) {
            return null;
        }

        View boton = filaAcciones.getChildAt(1);

        if (boton instanceof TextView) {
            return (TextView) boton;
        }

        return null;
    }

    private void actualizarTabs(TextView tabActivo, TextView tabInactivo) {
        tabActivo.setBackgroundResource(R.drawable.bg_tab_selected);
        tabActivo.setTextColor(getColor(R.color.colorPrimary));
        tabActivo.setTypeface(null, Typeface.BOLD);

        tabInactivo.setBackgroundResource(R.drawable.bg_tab_unselected);
        tabInactivo.setTextColor(getColor(R.color.textSecondary));
        tabInactivo.setTypeface(null, Typeface.NORMAL);
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
        cardGrupo.setTag(false);

        LinearLayout.LayoutParams parametrosCard = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        parametrosCard.setMargins(0, 0, 0, dp(20));
        cardGrupo.setLayoutParams(parametrosCard);

        TextView tituloGrupo = new TextView(this);
        tituloGrupo.setText(titulo);
        tituloGrupo.setTextSize(16);
        tituloGrupo.setTextColor(getColor(R.color.textPrimary));
        tituloGrupo.setTypeface(null, Typeface.BOLD);

        TextView detalleGrupo = new TextView(this);
        detalleGrupo.setText(detalle);
        detalleGrupo.setTextSize(13);
        detalleGrupo.setTextColor(getColor(R.color.textSecondary));

        TextView descripcionGrupo = new TextView(this);
        descripcionGrupo.setText(descripcion);
        descripcionGrupo.setTextSize(13);
        descripcionGrupo.setTextColor(getColor(R.color.textSecondary));
        descripcionGrupo.setPadding(0, dp(4), 0, 0);

        TextView estadoGrupo = new TextView(this);
        estadoGrupo.setText(getString(R.string.grupo_estado_abierto));
        estadoGrupo.setTextSize(12);
        estadoGrupo.setTextColor(getColor(R.color.colorSuccess));
        estadoGrupo.setTypeface(null, Typeface.BOLD);
        estadoGrupo.setGravity(android.view.Gravity.CENTER);
        estadoGrupo.setPadding(dp(18), dp(6), dp(18), dp(6));
        estadoGrupo.setBackgroundResource(R.drawable.bg_chip_green);

        TextView btnUnirseGrupo = new TextView(this);
        btnUnirseGrupo.setText(getString(R.string.unirme_grupo));
        btnUnirseGrupo.setTextSize(12);
        btnUnirseGrupo.setTextColor(getColor(R.color.textSecondary));
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
                obtenerCantidadDesdeDetalle(detalle),
                cardGrupo
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

        if (mostrandoMisGrupos) {
            cardGrupo.setVisibility(View.GONE);
        }
    }

    private int dp(int valor) {
        return (int) (valor * getResources().getDisplayMetrics().density);
    }
}
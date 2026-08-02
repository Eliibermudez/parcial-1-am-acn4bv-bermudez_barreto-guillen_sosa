package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NovedadesActivity extends AppCompatActivity {

    private boolean novedad1Leida = false;
    private boolean novedad2Leida = false;
    private boolean novedad3Leida = false;

    private TextView txtResumenNovedades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novedades);


        ImageView btnBackNovedades = findViewById(R.id.btnBackNovedades);

        TextView btnLeidaNovedad1 = findViewById(R.id.btnLeidaNovedad1);
        TextView btnLeidaNovedad2 = findViewById(R.id.btnLeidaNovedad2);
        TextView btnLeidaNovedad3 = findViewById(R.id.btnLeidaNovedad3);

        txtResumenNovedades = findViewById(R.id.txtResumenNovedades);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        ImageView imgBannerNovedades = findViewById(R.id.imgBannerNovedades);

        Glide.with(this)
                .load("https://images.pexels.com/photos/5965923/pexels-photo-5965923.jpeg?_gl=1*1iyo4gu*_ga*MTI2ODc2MDYwMi4xNzgyNzAyNDUw*_ga_8JE65Q40S6*czE3ODI3MDI0NTAkbzEkZzEkdDE3ODI3MDQwNDckajQ2JGwwJGgw")
                .placeholder(R.drawable.bg_chip_gray)
                .error(R.drawable.bg_chip_red)
                .centerCrop()
                .into(imgBannerNovedades);

        btnBackNovedades.setOnClickListener(v -> {
            Intent intent = new Intent(NovedadesActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        configurarBotonLeida(btnLeidaNovedad1, 1);
        configurarBotonLeida(btnLeidaNovedad2, 2);
        configurarBotonLeida(btnLeidaNovedad3, 3);

        actualizarResumenNovedades();
        configurarNavbar(bottomNavigation);
    }

    private void configurarBotonLeida(TextView boton, int numeroNovedad) {
        boton.setOnClickListener(v -> {
            boolean estaLeida = obtenerEstadoLeida(numeroNovedad);

            if (!estaLeida) {
                boton.setText(getString(R.string.novedad_leida));
                boton.setTextColor(0xFF2E7D32);
                boton.setBackgroundResource(R.drawable.bg_chip_green);
                guardarEstadoLeida(numeroNovedad, true);
            } else {
                boton.setText(getString(R.string.marcar_como_leida));
                boton.setTextColor(0xFF666666);
                boton.setBackgroundResource(R.drawable.bg_chip_gray);
                guardarEstadoLeida(numeroNovedad, false);
            }

            actualizarResumenNovedades();
        });
    }

    private boolean obtenerEstadoLeida(int numeroNovedad) {
        if (numeroNovedad == 1) {
            return novedad1Leida;
        }

        if (numeroNovedad == 2) {
            return novedad2Leida;
        }

        return novedad3Leida;
    }

    private void guardarEstadoLeida(int numeroNovedad, boolean leida) {
        if (numeroNovedad == 1) {
            novedad1Leida = leida;
        } else if (numeroNovedad == 2) {
            novedad2Leida = leida;
        } else {
            novedad3Leida = leida;
        }
    }

    private void actualizarResumenNovedades() {
        int pendientes = 0;

        if (!novedad1Leida) {
            pendientes++;
        }

        if (!novedad2Leida) {
            pendientes++;
        }

        if (!novedad3Leida) {
            pendientes++;
        }

        if (pendientes == 0) {
            txtResumenNovedades.setText("Todas las novedades fueron leídas");
        } else if (pendientes == 1) {
            txtResumenNovedades.setText("Tenés 1 novedad pendiente");
        } else {
            txtResumenNovedades.setText("Tenés " + pendientes + " novedades pendientes");
        }
    }

    private void configurarNavbar(BottomNavigationView bottomNavigation) {
        bottomNavigation.setSelectedItemId(R.id.nav_chat);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_inicio) {
                Intent intent = new Intent(NovedadesActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_materias) {
                Intent intent = new Intent(NovedadesActivity.this, MateriasActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_grupos) {
                Intent intent = new Intent(NovedadesActivity.this, GruposActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_calendario) {
                startActivity(new Intent(NovedadesActivity.this, CalendarioActivity.class));
                finish();
                return true;
            }

            if (itemId == R.id.nav_chat) {
                return true;
            }

            if (itemId == R.id.nav_perfil) {
                startActivity(new Intent(NovedadesActivity.this, PerfilActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }
}
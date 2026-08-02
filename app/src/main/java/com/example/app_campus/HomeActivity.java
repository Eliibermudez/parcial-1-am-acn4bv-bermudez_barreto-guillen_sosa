package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.View;
import android.widget.PopupMenu;
import android.view.Gravity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout btnAccesoMaterias;
    private LinearLayout btnAccesoGrupos;
    private LinearLayout btnAccesoCalendario;
    private LinearLayout btnAccesoNovedades;

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        inicializarVistas();
        configurarAccesosRapidos();
        configurarBottomNavigation();
    }

    private void inicializarVistas() {
        btnAccesoMaterias = findViewById(R.id.btnAccesoMaterias);
        btnAccesoGrupos = findViewById(R.id.btnAccesoGrupos);
        btnAccesoCalendario = findViewById(R.id.btnAccesoCalendario);
        btnAccesoNovedades = findViewById(R.id.btnAccesoNovedades);

        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void configurarAccesosRapidos() {
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
    }

    private void configurarBottomNavigation() {
        bottomNavigation.setSelectedItemId(R.id.nav_inicio);

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

            if (itemId == R.id.nav_novedades) {
                Intent intent = new Intent(HomeActivity.this, NovedadesActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_perfil) {
                Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
                startActivity(intent);
                return true;
            }

            if (itemId == R.id.nav_mas) {

                PopupMenu popup = new PopupMenu(
                        HomeActivity.this,
                        bottomNavigation,
                        Gravity.END
                );

                popup.getMenuInflater()
                        .inflate(R.menu.menu_mas, popup.getMenu());


                popup.setOnMenuItemClickListener(subItem -> {

                    int id = subItem.getItemId();


                    if (id == R.id.nav_novedades) {

                        Intent intent = new Intent(
                                HomeActivity.this,
                                NovedadesActivity.class
                        );

                        startActivity(intent);
                        return true;
                    }


                    if (id == R.id.nav_calendario) {

                        Intent intent = new Intent(
                                HomeActivity.this,
                                CalendarioActivity.class
                        );

                        startActivity(intent);
                        return true;
                    }


                    if (id == R.id.nav_contacto) {

                        Intent intent = new Intent(
                                HomeActivity.this,
                                ContactoActivity.class
                        );

                        startActivity(intent);
                        return true;
                    }


                    return false;
                });


                popup.show();

                return true;
            }

            return false;
        });
    }
}
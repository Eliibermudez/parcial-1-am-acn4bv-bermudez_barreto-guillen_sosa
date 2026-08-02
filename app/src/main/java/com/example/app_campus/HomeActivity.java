package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout btnAccesoMaterias;
    private LinearLayout btnAccesoGrupos;
    private LinearLayout btnAccesoCalendario;
    private LinearLayout btnAccesoNovedades;

    private TextView txtBienvenida;

    private BottomNavigationView bottomNavigation;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        inicializarVistas();
        cargarNombreUsuario();
        configurarAccesosRapidos();
        configurarBottomNavigation();
    }

    private void inicializarVistas() {
        txtBienvenida = findViewById(R.id.txtBienvenida);

        btnAccesoMaterias = findViewById(R.id.btnAccesoMaterias);
        btnAccesoGrupos = findViewById(R.id.btnAccesoGrupos);
        btnAccesoCalendario = findViewById(R.id.btnAccesoCalendario);
        btnAccesoNovedades = findViewById(R.id.btnAccesoNovedades);

        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void cargarNombreUsuario() {
        FirebaseUser usuarioActual = firebaseAuth.getCurrentUser();

        if (usuarioActual == null) {
            txtBienvenida.setText("Hola");
            return;
        }

        String uid = usuarioActual.getUid();

        db.collection("usuarios")
                .document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String nombre = obtenerTexto(documentSnapshot.get("nombre"));

                        if (!nombre.isEmpty()) {
                            txtBienvenida.setText("Hola, " + nombre);
                        } else {
                            txtBienvenida.setText("Hola");
                        }
                    } else {
                        txtBienvenida.setText("Hola");
                    }
                })
                .addOnFailureListener(e -> {
                    txtBienvenida.setText("Hola");
                });
    }

    private String obtenerTexto(Object valor) {
        if (valor == null) {
            return "";
        }

        return String.valueOf(valor);
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
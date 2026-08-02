package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class NovedadesActivity extends AppCompatActivity {

    private boolean novedad1Leida = false;
    private boolean novedad2Leida = false;
    private boolean novedad3Leida = false;

    private TextView txtResumenNovedades;

    private TextView categoriaNovedad1;
    private TextView categoriaNovedad2;
    private TextView categoriaNovedad3;

    private TextView tituloNovedad1;
    private TextView tituloNovedad2;
    private TextView tituloNovedad3;

    private TextView descripcionNovedad1;
    private TextView descripcionNovedad2;
    private TextView descripcionNovedad3;

    private TextView btnLeidaNovedad1;
    private TextView btnLeidaNovedad2;
    private TextView btnLeidaNovedad3;

    private ImageView imgNovedad1;
    private ImageView imgNovedad2;
    private ImageView imgNovedad3;

    private FirebaseFirestore db;

    private ImageView btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novedades);

        db = FirebaseFirestore.getInstance();

        inicializarVistas();
        configurarEventos();
        cargarNovedadesDesdeFirestore();
        actualizarResumenNovedades();
        configurarBottomNav();

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(v -> AuthUtils.cerrarSesion(this));
    }

    private void inicializarVistas() {
        txtResumenNovedades = findViewById(R.id.txtResumenNovedades);

        categoriaNovedad1 = findViewById(R.id.categoriaNovedad1);
        categoriaNovedad2 = findViewById(R.id.categoriaNovedad2);
        categoriaNovedad3 = findViewById(R.id.categoriaNovedad3);

        tituloNovedad1 = findViewById(R.id.tituloNovedad1);
        tituloNovedad2 = findViewById(R.id.tituloNovedad2);
        tituloNovedad3 = findViewById(R.id.tituloNovedad3);

        descripcionNovedad1 = findViewById(R.id.descripcionNovedad1);
        descripcionNovedad2 = findViewById(R.id.descripcionNovedad2);
        descripcionNovedad3 = findViewById(R.id.descripcionNovedad3);

        btnLeidaNovedad1 = findViewById(R.id.btnLeidaNovedad1);
        btnLeidaNovedad2 = findViewById(R.id.btnLeidaNovedad2);
        btnLeidaNovedad3 = findViewById(R.id.btnLeidaNovedad3);

        imgNovedad1 = findViewById(R.id.imgNovedad1);
        imgNovedad2 = findViewById(R.id.imgNovedad2);
        imgNovedad3 = findViewById(R.id.imgNovedad3);
    }

    private void configurarEventos() {
        ImageView btnBackNovedades = findViewById(R.id.btnBackNovedades);

        btnBackNovedades.setOnClickListener(v -> {
            Intent intent = new Intent(NovedadesActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        configurarBotonLeida(btnLeidaNovedad1, 1);
        configurarBotonLeida(btnLeidaNovedad2, 2);
        configurarBotonLeida(btnLeidaNovedad3, 3);
    }

    private void cargarNovedadesDesdeFirestore() {
        db.collection("novedades")
                .limit(3)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    if (queryDocumentSnapshots.isEmpty()) {
                        cargarNovedadesPorDefecto();
                        return;
                    }

                    int posicion = 1;

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String categoria = obtenerTexto(documentSnapshot.get("categoria"));
                        String titulo = obtenerTexto(documentSnapshot.get("titulo"));
                        String descripcion = obtenerTexto(documentSnapshot.get("descripcion"));
                        String imagenUrl = obtenerTexto(documentSnapshot.get("imagenUrl"));

                        cargarNovedadEnCard(posicion, categoria, titulo, descripcion, imagenUrl);

                        posicion++;

                        if (posicion > 3) {
                            break;
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(
                            NovedadesActivity.this,
                            "No se pudieron cargar las novedades desde Firestore",
                            Toast.LENGTH_SHORT
                    ).show();

                    cargarNovedadesPorDefecto();
                });
    }

    private void cargarNovedadEnCard(
            int posicion,
            String categoria,
            String titulo,
            String descripcion,
            String imagenUrl
    ) {
        if (posicion == 1) {
            categoriaNovedad1.setText(categoria);
            tituloNovedad1.setText(titulo);
            descripcionNovedad1.setText(descripcion);
            cargarImagenNovedad(imgNovedad1, imagenUrl);
        } else if (posicion == 2) {
            categoriaNovedad2.setText(categoria);
            tituloNovedad2.setText(titulo);
            descripcionNovedad2.setText(descripcion);
            cargarImagenNovedad(imgNovedad2, imagenUrl);
        } else if (posicion == 3) {
            categoriaNovedad3.setText(categoria);
            tituloNovedad3.setText(titulo);
            descripcionNovedad3.setText(descripcion);
            cargarImagenNovedad(imgNovedad3, imagenUrl);
        }
    }

    private void cargarImagenNovedad(ImageView imageView, String imagenUrl) {
        if (imagenUrl == null || imagenUrl.isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        }

        imageView.setVisibility(View.VISIBLE);

        Glide.with(this)
                .load(imagenUrl)
                .placeholder(R.drawable.bg_chip_gray)
                .error(R.drawable.bg_chip_red)
                .centerCrop()
                .into(imageView);
    }

    private void cargarNovedadesPorDefecto() {
        cargarNovedadEnCard(
                1,
                "Académica",
                "Inscripción a materias abierta",
                "Ya podés revisar las materias disponibles para el próximo período académico.",
                "https://images.pexels.com/photos/267885/pexels-photo-267885.jpeg"
        );

        cargarNovedadEnCard(
                2,
                "Grupos",
                "Nuevo grupo disponible",
                "Se creó un grupo para preparar el parcial de Aplicaciones Móviles.",
                "https://images.pexels.com/photos/159711/books-bookstore-book-reading-159711.jpeg"
        );

        cargarNovedadEnCard(
                3,
                "Institucional",
                "Recordatorio de entrega",
                "La entrega del Parcial 2 debe realizarse mediante el Campus con el enlace al repositorio.",
                "https://images.pexels.com/photos/3184291/pexels-photo-3184291.jpeg"
        );
    }

    private String obtenerTexto(Object valor) {
        if (valor == null) {
            return "";
        }

        return String.valueOf(valor);
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

    private void configurarBottomNav() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setSelectedItemId(R.id.nav_mas);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_materias) {
                startActivity(new Intent(this, MateriasActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_grupos) {
                startActivity(new Intent(this, GruposActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, PerfilActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_mas) {
                PopupMenu popup = new PopupMenu(
                        NovedadesActivity.this,
                        bottomNavigation,
                        Gravity.END
                );

                popup.getMenuInflater()
                        .inflate(
                                R.menu.menu_mas,
                                popup.getMenu()
                        );

                popup.setOnMenuItemClickListener(subItem -> {

                    if (subItem.getItemId() == R.id.nav_novedades) {
                        return true;
                    }

                    if (subItem.getItemId() == R.id.nav_calendario) {
                        startActivity(
                                new Intent(
                                        this,
                                        CalendarioActivity.class
                                )
                        );
                        return true;
                    }

                    if (subItem.getItemId() == R.id.nav_contacto) {
                        startActivity(
                                new Intent(
                                        this,
                                        ContactoActivity.class
                                )
                        );
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
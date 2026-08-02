package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NovedadesActivity extends AppCompatActivity {

    private boolean novedad1Leida = false;
    private boolean novedad2Leida = false;
    private boolean novedad3Leida = false;

    private TextView txtResumenNovedades;

    private TextView categoriaNovedad1, categoriaNovedad2, categoriaNovedad3;
    private TextView tituloNovedad1, tituloNovedad2, tituloNovedad3;
    private TextView descripcionNovedad1, descripcionNovedad2, descripcionNovedad3;

    private ImageView imgBannerNovedades;

    private ImageView imgNovedad1, imgNovedad2, imgNovedad3;

    private FirebaseFirestore db;

    private final String imagenBannerDefault = "https://images.pexels.com/photos/5965923/pexels-photo-5965923.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novedades);

        db = FirebaseFirestore.getInstance();

        ImageView btnBackNovedades = findViewById(R.id.btnBackNovedades);

        TextView btnLeidaNovedad1 = findViewById(R.id.btnLeidaNovedad1);
        TextView btnLeidaNovedad2 = findViewById(R.id.btnLeidaNovedad2);
        TextView btnLeidaNovedad3 = findViewById(R.id.btnLeidaNovedad3);

        txtResumenNovedades = findViewById(R.id.txtResumenNovedades);

        imgBannerNovedades = findViewById(R.id.imgBannerNovedades);
        imgNovedad1 = findViewById(R.id.imgNovedad1);
        imgNovedad2 = findViewById(R.id.imgNovedad2);
        imgNovedad3 = findViewById(R.id.imgNovedad3);

        categoriaNovedad1 = findViewById(R.id.categoriaNovedad1);
        categoriaNovedad2 = findViewById(R.id.categoriaNovedad2);
        categoriaNovedad3 = findViewById(R.id.categoriaNovedad3);

        tituloNovedad1 = findViewById(R.id.tituloNovedad1);
        tituloNovedad2 = findViewById(R.id.tituloNovedad2);
        tituloNovedad3 = findViewById(R.id.tituloNovedad3);

        descripcionNovedad1 = findViewById(R.id.descripcionNovedad1);
        descripcionNovedad2 = findViewById(R.id.descripcionNovedad2);
        descripcionNovedad3 = findViewById(R.id.descripcionNovedad3);

        cargarImagenBanner(imagenBannerDefault);

        btnBackNovedades.setOnClickListener(v -> {
            Intent intent = new Intent(NovedadesActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        configurarBotonLeida(btnLeidaNovedad1, 1);
        configurarBotonLeida(btnLeidaNovedad2, 2);
        configurarBotonLeida(btnLeidaNovedad3, 3);

        actualizarResumenNovedades();

        cargarNovedadesDesdeFirestore();

        configurarBottomNav();
    }

    private void cargarImagenBanner(String imagenUrl) {
        Glide.with(this)
                .load(imagenUrl)
                .placeholder(R.drawable.bg_chip_gray)
                .error(R.drawable.bg_chip_red)
                .centerCrop()
                .into(imgBannerNovedades);
    }

    private void cargarImagenNovedad(ImageView imageView, String imagenUrl) {
        Glide.with(this)
                .load(imagenUrl)
                .placeholder(R.drawable.bg_chip_gray)
                .error(R.drawable.bg_chip_red)
                .centerCrop()
                .into(imageView);
    }

    private void cargarNovedadesDesdeFirestore() {
        db.collection("novedades")
                .limit(3)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documentos = queryDocumentSnapshots.getDocuments();

                    if (documentos.isEmpty()) {
                        Toast.makeText(this, "No hay novedades cargadas", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (int i = 0; i < documentos.size(); i++) {
                        DocumentSnapshot documento = documentos.get(i);

                        String titulo = obtenerTexto(documento, "titulo");
                        String descripcion = obtenerTexto(documento, "descripcion");
                        String categoria = obtenerTexto(documento, "categoria");
                        String imagenUrl = obtenerTexto(documento, "imagenUrl");

                        cargarNovedadEnCard(i + 1, categoria, titulo, descripcion, imagenUrl);

                        if (i == 0 && !imagenUrl.isEmpty()) {
                            cargarImagenBanner(imagenUrl);
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar novedades", Toast.LENGTH_SHORT).show();
                });
    }

    private void cargarNovedadEnCard(int numero, String categoria, String titulo, String descripcion, String imagenUrl) {
        if (numero == 1) {
            categoriaNovedad1.setText(categoria);
            tituloNovedad1.setText(titulo);
            descripcionNovedad1.setText(descripcion);
            cargarImagenNovedad(imgNovedad1, imagenUrl);
        }

        if (numero == 2) {
            categoriaNovedad2.setText(categoria);
            tituloNovedad2.setText(titulo);
            descripcionNovedad2.setText(descripcion);
            cargarImagenNovedad(imgNovedad2, imagenUrl);
        }

        if (numero == 3) {
            categoriaNovedad3.setText(categoria);
            tituloNovedad3.setText(titulo);
            descripcionNovedad3.setText(descripcion);
            cargarImagenNovedad(imgNovedad3, imagenUrl);
        }
    }

    private String obtenerTexto(DocumentSnapshot documentSnapshot, String campo) {
        Object valor = documentSnapshot.get(campo);

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

        bottomNavigation.setSelectedItemId(R.id.nav_novedades);

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

            if (id == R.id.nav_novedades) {
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
                    if (subItem.getItemId() == R.id.nav_calendario) {
                        startActivity(new Intent(this, CalendarioActivity.class));
                        return true;
                    }

                    if (subItem.getItemId() == R.id.nav_contacto) {
                        startActivity(new Intent(this, ContactoActivity.class));
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
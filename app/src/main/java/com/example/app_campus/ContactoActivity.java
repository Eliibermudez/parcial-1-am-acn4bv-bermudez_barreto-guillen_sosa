package com.example.app_campus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private TextView tvLugar, tvDireccion;
    private Button btnIr;

    private LatLng ubicacion = new LatLng(-34.6037, -58.3816);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        tvLugar = findViewById(R.id.tvLugar);
        tvDireccion = findViewById(R.id.tvDireccion);
        btnIr = findViewById(R.id.btnIr);

        configurarBottomNav();


        btnIr.setOnClickListener(v -> {
            String uri = "google.navigation:q=" + ubicacion.latitude + "," + ubicacion.longitude;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });

        // MAPA
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void configurarBottomNav() {

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setSelectedItemId(R.id.nav_mas);


        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();


            if (id == R.id.nav_inicio) {

                startActivity(
                        new Intent(this, HomeActivity.class)
                );

                finish();

                return true;
            }


            if (id == R.id.nav_materias) {

                startActivity(
                        new Intent(this, MateriasActivity.class)
                );

                finish();

                return true;
            }


            if (id == R.id.nav_grupos) {

                startActivity(
                        new Intent(this, GruposActivity.class)
                );

                finish();

                return true;
            }


            if (id == R.id.nav_perfil) {

                startActivity(
                        new Intent(this, PerfilActivity.class)
                );

                finish();

                return true;
            }


            if (id == R.id.nav_mas) {

                PopupMenu popup = new PopupMenu(
                        ContactoActivity.this,
                        bottomNavigation,
                        Gravity.END
                );

                popup.getMenuInflater().inflate(R.menu.menu_mas, popup.getMenu());

                popup.setOnMenuItemClickListener(subItem -> {

                    if (subItem.getItemId() == R.id.nav_contacto) {
                        return true;
                    }

                    if (subItem.getItemId() == R.id.nav_calendario) {
                        startActivity(new Intent(this, CalendarioActivity.class));
                        return true;
                    }

                    if (subItem.getItemId() == R.id.nav_novedades) {
                        startActivity(new Intent(this, NovedadesActivity.class));
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions()
                .position(ubicacion)
                .title("Campus Estudiantil"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));

        mMap.setOnMarkerClickListener(marker -> {
            tvLugar.setText(marker.getTitle());
            tvDireccion.setText("Ubicación seleccionada");
            return false;
        });
    }

}
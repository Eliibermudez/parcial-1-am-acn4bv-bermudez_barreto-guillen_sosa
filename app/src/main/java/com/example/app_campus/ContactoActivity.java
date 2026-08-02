package com.example.app_campus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

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
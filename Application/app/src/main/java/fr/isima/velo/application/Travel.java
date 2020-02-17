package fr.isima.velo.application;

import android.graphics.Color;
import android.icu.text.DateFormat;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.velo.lib.Journey;
import fr.velo.lib.utils.Point4D;

public class Travel extends AppCompatActivity implements OnMapReadyCallback {

    private Journey journey;
    private TextView title;
    private TextView date;
    private TextView duration;
    private GoogleMap mMap;
    private PolylineOptions polylineOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_travel);
        mapFragment.getMapAsync(this);

        journey = (Journey)getIntent().getSerializableExtra(Journey.class.getName());

        title = findViewById(R.id.travel_title);
        date = findViewById(R.id.travel_date);
        duration = findViewById(R.id.travel_duration);

        title.setText(journey.getName());
        date.setText(DateFormat.getInstance().format(journey.getDateTime()));
        duration.setText("durée");

        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(3);

        journey.forEach(point4D -> {
            polylineOptions.add(new LatLng(point4D.getX(), point4D.getY()));
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CameraPosition cam = mMap.getCameraPosition();
        Point4D firstPoint = journey.iterator().next();
        LatLng firstPos = new LatLng(firstPoint.getX(), firstPoint.getY());
        mMap.addMarker(new MarkerOptions().position(firstPos));
        Log.d("MAP READY", "Pos: " + firstPos.latitude + ", " + firstPos.longitude);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(firstPos, 18, cam.tilt, cam.bearing)));
        mMap.addPolyline(polylineOptions);
    }
}

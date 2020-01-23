package fr.isima.velo.application.ui.newTravel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import fr.isima.velo.application.R;

public class MapNewTravel extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private PolylineOptions polyline;
    private LocationManager locationManager;
    private boolean onTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        onTravel = false;

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 10);
            return;
        }
        else {
            configureLocationManager();
        }
    }

    @SuppressLint("MissingPermission")
    public void startTravel() {
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (loc == null) {
            loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
        LatLng nPos = new LatLng(loc.getLatitude(), loc.getLongitude());
;
        polyline = new PolylineOptions();
        polyline.color(Color.RED);
        polyline.width(3);

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(nPos));

        polyline.add(nPos);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nPos));
        mMap.moveCamera(CameraUpdateFactory.zoomBy(15));
    }

    @SuppressLint("MissingPermission")
    private void configureLocationManager() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, this);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 5, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configureLocationManager();
                }
                return;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (onTravel) {
            LatLng nPos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(nPos));
            polyline.add(nPos);
            mMap.animateCamera(CameraUpdateFactory.newLatLng(nPos));
            mMap.addPolyline(polyline);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("LISTENER", "status changed");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("LISTENER", "Provider Enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("LISTENER", "Provider Disabled");
        /*
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        */
    }
}

package fr.isima.velo.application.ui.newTravel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class MapNewTravel extends Fragment implements OnMapReadyCallback, LocationListener {

    private PolylineOptions polyline;
    private LocationManager locationManager;
    private boolean travelOn = false;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_new_travel, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 10);
        }
        else {
            configureLocationManager();
        }

        return root;
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

        Log.d("NEW TRAVEL", "lat: " + loc.getLatitude() + ", lon: " + loc.getLongitude());

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(nPos));

        polyline.add(nPos);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nPos));
        mMap.moveCamera(CameraUpdateFactory.zoomBy(15));
        travelOn = true;
    }

    public void endTravel() {
        travelOn = false;
    }

    public boolean isTravelOn() {
        return travelOn;
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
    public void onLocationChanged(Location location) {
        if (travelOn) {
            LatLng nPos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(nPos));
            polyline.add(nPos);
            mMap.animateCamera(CameraUpdateFactory.newLatLng(nPos));
            mMap.addPolyline(polyline);
        }
        Log.d("MAP LOCATION CHANGED", "Lat: " + location.getLatitude() + " , Lon: " + location.getLongitude());
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}

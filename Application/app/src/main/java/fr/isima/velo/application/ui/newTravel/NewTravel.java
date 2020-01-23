package fr.isima.velo.application.ui.newTravel;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import fr.isima.velo.application.R;

import static java.lang.System.*;

public class NewTravel extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button newTravelButton;
    private TextView debugTextView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private List<LatLng> list;
    private PolylineOptions polyline;
    private boolean travelOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_travel);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        travelOn = false;

        list = new ArrayList<>(200);

        newTravelButton = findViewById(R.id.button_new_travel);
        debugTextView = findViewById(R.id.debugCoords);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                debugTextView.append("\n latitude: " + location.getLatitude() + "longitude: " + location.getLongitude());
                if (true/*travelOn*/) {
                    LatLng nPos = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(nPos).title("Marker"));
                    polyline.add(nPos);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(nPos));
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
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 10);
            return;
        }
        else {
            configureLocationManager();
            configureButton();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configureLocationManager();
                    configureButton();
                }
                return;
        }
    }

    @SuppressLint("MissingPermission")
    private void configureLocationManager() {
        /*
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);
        if(provider != null) {
            locationManager.requestLocationUpdates(provider, 10, 1, locationListener);
            debugTextView.append(provider);
        } else {
            locationManager.requestLocationUpdates("gps", 10, 1, locationListener);
        }
        */
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 10, locationListener);
    }

    private void configureButton() {

        newTravelButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (loc == null) {
                    Log.d("eznf", "pas gps");
                    loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                }


                if (!travelOn) {
                    Log.d("BUTTON", "travelOn");
                    debugTextView.append("\n\nlatitude: " + loc.getLatitude() + "\nlongitude: " + loc.getLongitude());
                    mMap.clear();
                    polyline = new PolylineOptions();
                    polyline.color(Color.RED);
                    polyline.width(3);

                    LatLng nPos = new LatLng(loc.getLatitude(), loc.getLongitude());

                    mMap.addMarker(new MarkerOptions().position(nPos).title("Marker"));
                    polyline.add(nPos);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(nPos));

                    travelOn = true;
                    newTravelButton.setText("Arrêter le trajet");
                } else {
                    Log.d("BUTTON", "travelOff");
                    newTravelButton.setText("Nouveau trajet");
                    travelOn = false;
                }
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

    }
}

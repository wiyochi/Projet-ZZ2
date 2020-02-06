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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import fr.isima.velo.application.R;
import fr.velo.lib.Journey;
import fr.velo.lib.JourneyHistory;
import fr.velo.lib.utils.Point4D;

public class MapNewTravel extends Fragment implements OnMapReadyCallback, LocationListener {

    private PolylineOptions polyline;
    private LocationManager locationManager;
    private boolean travelOn = false;
    private GoogleMap mMap;
    private Location lastValidLocation;
    private long lastTime;
    private Journey journey;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
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
            loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (loc == null) {
                loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            } else {
                Log.d("NEW_TRAVEL", "Pas de provider");
            }
        }
        journey = new Journey();
;
        polyline = new PolylineOptions();
        polyline.color(Color.RED);
        polyline.width(3);

        Log.d("NEW TRAVEL", "lat: " + loc.getLatitude() + ", lon: " + loc.getLongitude());

        lastValidLocation = loc;
        lastTime = System.currentTimeMillis();
        newPoint(loc);

        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        travelOn = true;
    }

    public void endTravel(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.fragment_enter_name_travel, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        final Button popupButton = popupView.findViewById(R.id.pop_up_validate);
        final TextView popupText = popupView.findViewById(R.id.pop_up_input);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });

        popupButton.setOnClickListener(v -> {
            journey.setName(popupText.getText().toString());
            JourneyHistory.getInstance().insert(journey);
            Log.d("END TRAVEL", "travel: " + journey.toString());

            lastTime = 0;
            lastValidLocation = null;
            travelOn = false;
            mMap.clear();
            popupWindow.dismiss();
        });
    }

    public boolean isTravelOn() {
        return travelOn;
    }

    public void newPoint(Location location) {
        LatLng nPos = new LatLng(location.getLatitude(), location.getLongitude());

        journey.insert(new Point4D(location.getLatitude(), location.getLongitude(), location.getAltitude(), lastTime));

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(nPos));
        polyline.add(nPos);
        CameraPosition cam = mMap.getCameraPosition();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(nPos, cam.zoom, cam.tilt, cam.bearing)));
        mMap.addPolyline(polyline);
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
            long t = System.currentTimeMillis();
            long dt = t - lastTime;

            Log.d("NEW_POINT", "Speed:" + location.getSpeed() + ", dt: " + dt);
            if (location.distanceTo(lastValidLocation) < location.getSpeed() * dt / 1000) {
                lastTime = t;
                lastValidLocation = location;
                newPoint(location);
            }
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

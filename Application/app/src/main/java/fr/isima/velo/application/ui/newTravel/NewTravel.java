package fr.isima.velo.application.ui.newTravel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.isima.velo.application.R;

import static java.lang.System.*;

public class NewTravel extends Fragment {

    private MapNewTravel map;

    private Button newTravelButton;
    private boolean travelOn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_new_travel, container, false);
        //setContentView(R.layout.fragment_new_travel);

        travelOn = false;

        newTravelButton = root.findViewById(R.id.button_new_travel);

        map = new MapNewTravel();
        configureButton();

        return root;
    }

    private void configureButton() {

        newTravelButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                if (!travelOn) {
                    Log.d("BUTTON", "travelOn");

                    map.startTravel();

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
}

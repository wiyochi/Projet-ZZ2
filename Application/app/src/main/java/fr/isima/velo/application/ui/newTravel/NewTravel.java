package fr.isima.velo.application.ui.newTravel;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.isima.velo.application.R;

public class NewTravel extends Fragment {

    private MapNewTravel map;

    private Button newTravelButton;
    private View view;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_new_travel, container, false);
        view = root;

        newTravelButton = root.findViewById(R.id.button_new_travel);

        map = new MapNewTravel();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container, map, "one");
        fragmentTransaction.commit();

        configureButton();

        return root;
    }

    private void configureButton() {

        newTravelButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                if (!map.isTravelOn()) {
                    Log.d("BUTTON", "Start Travel");

                    map.startTravel();

                    newTravelButton.setText("Arrêter le trajet");
                } else {
                    Log.d("BUTTON", "End Travel");

                    map.endTravel(view);

                    newTravelButton.setText("Nouveau trajet");
                }
            }
        });
    }
}

package fr.isima.velo.application.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fr.isima.velo.application.R;
import fr.velo.lib.Journey;

public class CompactTravelFragment extends Fragment {

    private Journey journey;
    private TextView name;
    private TextView date;
    private TextView time;

    public CompactTravelFragment(Journey j) {
        journey = j;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_compact_travel, container, false);

        name = root.findViewById(R.id.compact_travel_title);
        date = root.findViewById(R.id.compact_travel_date);
        time = root.findViewById(R.id.compact_travel_duration);

        name.setText(journey.getName());
        date.setText("" + journey.getDateTime());
        time.setText("none");

        return root;
    }
}

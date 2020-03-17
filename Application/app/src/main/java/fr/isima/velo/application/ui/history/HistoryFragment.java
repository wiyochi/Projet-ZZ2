package fr.isima.velo.application.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Iterator;

import fr.isima.velo.application.R;
import fr.isima.velo.application.ui.CompactTravelFragment;
import fr.isima.velo.application.ui.newTravel.MapNewTravel;
import fr.velo.lib.Journey;
import fr.velo.lib.JourneyHistory;

public class HistoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        JourneyHistory history = JourneyHistory.getInstance();
        for (int i = history.size()-1; i >= 0 && i >= history.size()-11; i--) {
            CompactTravelFragment c = new CompactTravelFragment(history.get(i));
            fragmentTransaction.add(R.id.container_history, c, "one");
        }

        fragmentTransaction.commit();

        return root;
    }
}

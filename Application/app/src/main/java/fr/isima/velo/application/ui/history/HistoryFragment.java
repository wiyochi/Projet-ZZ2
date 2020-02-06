package fr.isima.velo.application.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import fr.isima.velo.application.R;
import fr.isima.velo.application.ui.CompactTravelFragment;
import fr.isima.velo.application.ui.newTravel.MapNewTravel;

public class HistoryFragment extends Fragment {

    private CompactTravelFragment frag1;
    private CompactTravelFragment frag2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_history, container, false);


        frag1 = new CompactTravelFragment();
        frag2 = new CompactTravelFragment();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container_history, frag1, "one");
        fragmentTransaction.add(R.id.container_history, frag2, "one");
        fragmentTransaction.commit();


        return root;
    }
}

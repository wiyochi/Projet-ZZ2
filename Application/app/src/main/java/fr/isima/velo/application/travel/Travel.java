package fr.isima.velo.application.travel;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Travel {

    private int id;
    private List<LatLng> points;

    public Travel(List<LatLng> path) {
        points = new ArrayList<>(path);
    }

}

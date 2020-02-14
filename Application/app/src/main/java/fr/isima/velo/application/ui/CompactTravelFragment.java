package fr.isima.velo.application.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.concurrent.TimeUnit;

import fr.isima.velo.application.R;
import fr.velo.lib.Journey;

public class CompactTravelFragment extends Fragment {

    private Journey journey;
    private TextView name;
    private TextView date;
    private TextView time;
    private ImageView image;

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
        long d = journey.getDateTime();
        date.setText("" + TimeUnit.MILLISECONDS.toDays(d) + "::" + TimeUnit.MILLISECONDS.toHours(d) + "::" + TimeUnit.MILLISECONDS.toMinutes(d));
        time.setText("none");

        image = root.findViewById(R.id.image_card);
        getImage(root);

        return root;
    }

    private void getImage(View v) {
        String path = v.getContext().getFilesDir() + "/maps/" + journey.getName() + ".bmp";
        File imageFile = new File(path);

        Log.d("SCREEN IN", path);
        image.setImageBitmap(BitmapFactory.decodeFile(imageFile.getPath()));
    }
}

package fr.isima.velo.application.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.util.Iterator;

import fr.isima.velo.application.R;
import fr.isima.velo.application.Travel;
import fr.velo.lib.Journey;
import fr.velo.lib.utils.Point4D;

public class CompactTravelFragment extends Fragment {

    private Journey journey;
    private TextView name;
    private TextView date;
    private TextView time;
    private ImageView image;
    private MaterialCardView card;

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
        card = root.findViewById(R.id.card);

        name.setText(journey.getName());
        long d = journey.getDateTime();
        date.setText(DateFormat.getInstance().format(d));

        double dt = 0;
        double t = 0;
        Iterator<Point4D> it = journey.iterator();
        if (it.hasNext()) {
            t = it.next().getT();
        }
        while (it.hasNext()) {
            double timePoint = it.next().getT();
            dt += timePoint - t;
            t = timePoint;
        }
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumIntegerDigits(2);
        f.setMinimumIntegerDigits(2);
        int sec = (int)dt / 1000;
        int min = sec / 60;
        int heu = min / 60;
        time.setText("Durée: " + f.format(heu) + ":" + f.format(min % 60) + ":" + f.format(sec % 60));

        image = root.findViewById(R.id.image_card);
        getImage(root);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Travel.class);
                intent.putExtra(Journey.class.getName(), journey);
                startActivity(intent);
            }
        });

        return root;
    }

    private void getImage(View v) {
        String path = v.getContext().getFilesDir() + "/maps/" + journey.getName() + ".bmp";
        File imageFile = new File(path);

        Log.d("SCREEN IN", path);
        image.setImageBitmap(BitmapFactory.decodeFile(imageFile.getPath()));
    }
}

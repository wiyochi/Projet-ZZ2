package fr.isima.velo.application.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import fr.isima.velo.application.R;
import fr.velo.lib.Journey;
import fr.velo.lib.JourneyHistory;
import fr.velo.lib.utils.Point4D;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final Button save = root.findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Journey journey = new Journey();
                journey.insert(new Point4D(new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble()));
                journey.setName("n'importe " + new Random().nextInt());
                Log.d("SAVE", journey.toString());
                try {
                    File folder = new File(root.getContext().getFilesDir(), "saves");
                    if (!folder.exists())
                        folder.mkdir();
                    journey.saveToStream(new FileOutputStream(new File(folder,journey.getName() + ".txt")));
                } catch (IOException e) {
                    Log.e("un truc", "erreur création", e);
                }
            }
        });

        final Button load = root.findViewById(R.id.buttonLoad);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = new File(root.getContext().getFilesDir(), "saves");
                for (File file : folder.listFiles()) {
                    try {
                        Journey journey = new Journey(new FileInputStream(file));
                        Log.d("LOAD:", journey.toString());
                    } catch (IOException e) {
                        Log.e("un truc", "erreur load", e);
                    }
                }
            }
        });

        return root;
    }
}
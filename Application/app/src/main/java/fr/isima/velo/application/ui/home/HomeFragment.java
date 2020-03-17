package fr.isima.velo.application.ui.home;

import android.app.AlertDialog;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executor;

import fr.isima.velo.application.R;

public class HomeFragment extends Fragment {

    private Socket socket;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final Button b = (Button) root.findViewById(R.id.button_click);
        final TextView text = (TextView) root.findViewById(R.id.text_connection_status);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("INFO", "Click");
                new Thread() {
                    @Override
                    public void run() {
                        Log.d("INFO", "run");
                        try{
                            socket = new Socket("172.16.40.88", 45703);
                            Log.d("INFO", "J'ai fini");
                        } catch (IOException e){
                            Log.e("Connection", "Création du socket échouée", e);
                        } finally {
                            textView.setText("Connection : " + socket.isConnected());
                        }
                    }
                }.start();
            }
        });


        return root;
    }
}
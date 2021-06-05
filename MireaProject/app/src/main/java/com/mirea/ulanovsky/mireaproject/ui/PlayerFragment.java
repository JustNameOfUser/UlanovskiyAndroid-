package com.mirea.ulanovsky.mireaproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mirea.ulanovsky.mireaproject.R;
import com.mirea.ulanovsky.mireaproject.services.PlayerService;

public class PlayerFragment extends Fragment {

    private boolean isPlaying = false;
    private Button playBtn, stopBtn;

    public PlayerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_player, container, false);

        playBtn = root.findViewById(R.id.btnPlay);
        stopBtn = root.findViewById(R.id.btnStop);
        stopBtn.setEnabled(false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playBtn.setOnClickListener(v -> {
            stopBtn.setEnabled(true);
            if (isPlaying) {
                playBtn.setText(R.string.button_play);
            } else {
                playBtn.setText(R.string.button_pause);
            }
            isPlaying = !isPlaying;

            getActivity().startService(new Intent(getActivity(), PlayerService.class));
        });

        stopBtn.setOnClickListener(v -> {
            stopBtn.setEnabled(false);
            playBtn.setText(R.string.button_play);
            isPlaying = false;

            getActivity().stopService(new Intent(getActivity(), PlayerService.class));
        });
    }
}
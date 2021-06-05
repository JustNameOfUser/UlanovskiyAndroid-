package com.mirea.ulanovsky.mireaproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Button;

import com.mirea.ulanovsky.mireaproject.MainActivity;
import com.mirea.ulanovsky.mireaproject.R;
import com.mirea.ulanovsky.mireaproject.services.VoiceRecorderService;

import org.jetbrains.annotations.NotNull;

public class VoiceRecorderFragment extends Fragment {
    private static FragmentActivity activityInstance;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PERMISSION = 100;
    private boolean isWork;
    private final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };

    private boolean isPlaying = false;
    private Button startRecordButton;
    private Button stopRecordButton;
    private Button pauseRecordButton;

    public VoiceRecorderFragment() {}

    public static VoiceRecorderFragment newInstance(String param1, String param2) {
        VoiceRecorderFragment fragment = new VoiceRecorderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        activityInstance = requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_voice_recorder, container, false);

        startRecordButton = root.findViewById(R.id.btnRec);
        stopRecordButton = root.findViewById(R.id.btnStop);
        pauseRecordButton = root.findViewById(R.id.btnPause);
        pauseRecordButton.setEnabled(false);

        isWork = hasPermissions(getActivity(), PERMISSIONS);
        if (!isWork) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, REQUEST_CODE_PERMISSION);
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startRecordButton.setOnClickListener(v -> {
            requireActivity().startService(new Intent(getActivity(), VoiceRecorderService.class));
            isPlaying = true;

            startRecordButton.setEnabled(false);
            pauseRecordButton.setEnabled(true);
            pauseRecordButton.setText(R.string.button_pause);
            stopRecordButton.setEnabled(true);
            stopRecordButton.requestFocus();
        });

        pauseRecordButton.setOnClickListener(v -> {
            Intent pauseIntent = new Intent(getActivity(), VoiceRecorderService.class);
            pauseIntent.putExtra("KEY", isPlaying);
            requireActivity().startService(pauseIntent);

            if (isPlaying) {
                pauseRecordButton.setText(R.string.button_play);
                isPlaying = false;
            } else {
                pauseRecordButton.setText(R.string.button_pause);
                isPlaying = true;
            }
        });

        stopRecordButton.setOnClickListener(v -> {
            requireActivity().stopService(new Intent(getActivity(), VoiceRecorderService.class));
            isPlaying = false;

            stopRecordButton.setEnabled(false);
            pauseRecordButton.setEnabled(false);
            pauseRecordButton.setText(R.string.button_play);
            startRecordButton.setEnabled(true);
            startRecordButton.requestFocus();
        });
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    public static FragmentActivity getActivityInstance() {
        return activityInstance;
    }
}
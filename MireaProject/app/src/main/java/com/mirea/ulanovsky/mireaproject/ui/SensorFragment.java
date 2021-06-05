package com.mirea.ulanovsky.mireaproject.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirea.ulanovsky.mireaproject.R;

public class SensorFragment extends Fragment implements SensorEventListener {
    private TextView azimuthTextView, pitchTextView, rollTextView;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SensorFragment() {}

    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sensor, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        azimuthTextView = root.findViewById(R.id.textViewAzimuth);
        pitchTextView = root.findViewById(R.id.textViewPitch);
        rollTextView = root.findViewById(R.id.textViewRoll);

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            String azimuthValue = String.valueOf(event.values[0]);
            String pitchValue = String.valueOf(event.values[1]);
            String rollValue = String.valueOf(event.values[2]);

            azimuthTextView.setText(azimuthValue);
            pitchTextView.setText(pitchValue);
            rollTextView.setText(rollValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
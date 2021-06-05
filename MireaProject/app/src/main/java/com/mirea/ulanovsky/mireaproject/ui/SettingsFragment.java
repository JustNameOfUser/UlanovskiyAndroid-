package com.mirea.ulanovsky.mireaproject.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mirea.ulanovsky.mireaproject.MainActivity;
import com.mirea.ulanovsky.mireaproject.R;

import org.jetbrains.annotations.NotNull;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String FULL_NAME_TEXT = "full_name_text",
                                UNIVERSITY_TEXT = "university_text";

    private String mParam1;
    private String mParam2;

    private SharedPreferences preferences;
    private EditText fullNameET, universityET;
    private Button buttonBack, buttonLoad, buttonSave;

    public SettingsFragment() {}

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        fullNameET = root.findViewById(R.id.edit_text_full_name);
        universityET = root.findViewById(R.id.edit_text_university);
        buttonBack = root.findViewById(R.id.btnBack);
        buttonLoad = root.findViewById(R.id.btnLoad);
        buttonSave = root.findViewById(R.id.btnSave);

        preferences = requireActivity().getPreferences(MODE_PRIVATE);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonBack.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().remove(MainActivity.settingsFragment).commit());

        buttonLoad.setOnClickListener(v -> {
            fullNameET.setText(preferences.getString(FULL_NAME_TEXT, ""));
            universityET.setText(preferences.getString(UNIVERSITY_TEXT, ""));
        });

        buttonSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(FULL_NAME_TEXT, fullNameET.getText().toString());
            editor.putString(UNIVERSITY_TEXT, universityET.getText().toString());
            editor.apply();

            Toast.makeText(getActivity(), "Settings saved", Toast.LENGTH_SHORT).show();
        });
    }
}
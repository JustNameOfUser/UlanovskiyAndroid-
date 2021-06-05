package com.mirea.ulanovsky.mireaproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mirea.ulanovsky.mireaproject.MainActivity;
import com.mirea.ulanovsky.mireaproject.R;

public class NewStoryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button saveButton;
    Button backButton;

    public NewStoryFragment() {}

    public static NewStoryFragment newInstance(String param1, String param2) {
        NewStoryFragment fragment = new NewStoryFragment();
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
        View root = inflater.inflate(R.layout.fragment_new_story, container, false);
        EditText text = root.findViewById(R.id.story_text);

        saveButton = root.findViewById(R.id.button_save);
        saveButton.setOnClickListener(v -> {
            String string = text.getText().toString();
            StoriesFragment.stories.add(string);
        });

        backButton = root.findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().remove(MainActivity.newStoryFragment).commit());

        return root;
    }
}
package com.mirea.ulanovsky.mireaproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mirea.ulanovsky.mireaproject.R;
import com.mirea.ulanovsky.mireaproject.api.CalculatorAPI;

import java.util.List;

public class CalculatorFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    EditText expressionET;
    Button calculateBtn;

    public CalculatorFragment() {}

    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
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
        View inflated = inflater.inflate(R.layout.fragment_calculator, container, false);

        expressionET = inflated.findViewById(R.id.edit_text_expression);
        calculateBtn = inflated.findViewById(R.id.calculate_button);
        calculateBtn.setOnClickListener(view -> {
            List<String> expression = CalculatorAPI.getParsedStr(expressionET.getText().toString());
            String errorText = getResources().getString(R.string.error_text);

            if (expression.size() != 0 && !expression.contains(errorText)) {
                expressionET.setText(String.valueOf(CalculatorAPI.calculate(expression)));
            } else {
                expressionET.setText(errorText);
            }
        });

        return inflated;
    }
}
package com.mirea.ulanovsky.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent2 = getIntent();
        String data = intent2.getStringExtra("m");

        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setText(data);


    }
}
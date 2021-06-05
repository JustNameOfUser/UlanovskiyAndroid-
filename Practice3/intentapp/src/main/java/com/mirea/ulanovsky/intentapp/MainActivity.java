package com.mirea.ulanovsky.intentapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onIntentButtonClicked(View view) {
        long dateInMillis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateString = simpleDateFormat.format(new Date(dateInMillis));

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("m", dateString);

        startActivity(intent);

    }
}
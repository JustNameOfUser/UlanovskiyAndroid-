package com.mirea.ulanovsky.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.textView);

        final Runnable runnable1 = new Runnable() {
            public void run() {
                tvInfo.setText("runnable1");
            }
        };

        final Runnable runnable2 = new Runnable() {
            public void run() {
                tvInfo.setText("runnable2");
            }
        };

        final Runnable runnable3 = new Runnable() {
            public void run() {
                tvInfo.setText("runnable3");
            }
        };

        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runnable1);
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runnable3, 2000);
                    tvInfo.post(runnable2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
package com.mirea.ulanovsky.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static int thread_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoTextView = findViewById(R.id.textView);

        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Текущий поток: " + mainThread.getName());
        mainThread.setName("MireaThread");
        infoTextView.append("\n Имя нового потока: " + mainThread.getName());
    }

    public void onButtonClicked(View view) {
        new Thread(new Runnable() {
            public void run() {
                int numberThread = thread_count++;
                Log.i("ThreadProject", "Запущен поток №" + numberThread);

                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                            System.out.println(Arrays.toString(e.getStackTrace()));
                        }
                    }
                }

                Log.i("ThreadProject", "Выполнен поток №" + numberThread);
            }
        }).start();
    }
}
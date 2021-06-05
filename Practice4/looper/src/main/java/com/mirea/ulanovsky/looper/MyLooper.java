package com.mirea.ulanovsky.looper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int sentInt = msg.getData().getInt("key");

                long delaySeconds = 20;
                for (int i = 0; i < delaySeconds; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sentInt++;
                }

                Log.d("MyLooper", "Имя: " + "Улановский Даниил\t" + "Возраст: " + sentInt);
            }
        };
        Looper.loop();
    }
}

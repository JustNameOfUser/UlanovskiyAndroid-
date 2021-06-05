package com.mirea.ulanovsky.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyLooper myLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLooper = new MyLooper();
        myLooper.start();
    }

    public void onButtonClicked(View view) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt("key", 0);
        msg.setData(bundle);

        if (myLooper != null) {
            myLooper.handler.sendMessage(msg);
        }
    }
}
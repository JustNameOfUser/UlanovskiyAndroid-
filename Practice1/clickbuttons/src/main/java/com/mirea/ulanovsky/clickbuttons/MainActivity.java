package com.mirea.ulanovsky.clickbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tvOut);
        TextView okBtn = findViewById(R.id.btnOk);
        TextView cancelBtn = findViewById(R.id.btnCancel);

        View.OnClickListener oclOkBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Нажата кнопка OK");
            }
        };

        okBtn.setOnClickListener(oclOkBtn);

        View.OnClickListener oclCancelBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Нажата кнопка Cancel");
            }
        };

        cancelBtn.setOnClickListener(oclCancelBtn);
    }

    public void onMoreButtonClick(View view)
    {
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show();
    }

    public void onGoButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.mirea.ulanovsky.multiactivity", "com.mirea.ulanovsky.multiactivity.SecondActivity");
        intent.putExtra("key", "MIREA - УЛАНОВСКИЙ ДАНИИЛ ИГОРЕВИЧ");
        startActivity(intent);
    }
}
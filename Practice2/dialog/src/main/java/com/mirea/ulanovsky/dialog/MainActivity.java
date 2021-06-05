package com.mirea.ulanovsky.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView timeView, dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeView = (TextView) findViewById(R.id.timeView);
        dateView = (TextView) findViewById(R.id.dateView);

    }

    // Tutorial dialog
    public void onClickShowTutorialDialog(View view) {
        TutorialDialogFragment dialogFragment = new TutorialDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onClickShowTimeDialog(View view) {
        MyTimeDialogFragment dialogFragment = new MyTimeDialogFragment(timeView);
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onClickShowDateDialog(View view) {
        MyDateDialogFragment dialogFragment = new MyDateDialogFragment(dateView);
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onClickShowProgressDialog(View view) {
        MyProgressDialogFragment dialogFragment = new MyProgressDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }
}
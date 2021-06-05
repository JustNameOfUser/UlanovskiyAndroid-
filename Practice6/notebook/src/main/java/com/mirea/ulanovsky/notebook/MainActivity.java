package com.mirea.ulanovsky.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String LAST_PATH = "last_path";
    private static final String SUBDIR_NAME = "Notebook notes/";

    SharedPreferences preferences;
    private EditText titleText, editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getPreferences(Context.MODE_PRIVATE);
        titleText = findViewById(R.id.title_text);
        editText = findViewById(R.id.edit_text);

        onFileLoad();
    }

    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(titleText.getText().toString());
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(LOG_TAG, text);
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        return null;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public File getDocStorageDir(String dirName, String fileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), dirName);

        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LAST_PATH, file.getPath() + fileName);
        editor.apply();

        return file;
    }

    private void onFileLoad() {
        if (isExternalStorageReadable()) {
            editText.post(new Runnable() {
                public void run() {
                    titleText.setText(preferences.getString(LAST_PATH, ""));
                    editText.setText(getTextFromFile());
                }
            });
        } else {
            Log.e(LOG_TAG, "External storage is not readable!");
        }
    }

    public void onSaveClick(View view) {
        if (isExternalStorageWritable()) {
            String title = titleText.getText().toString();
            String content = editText.getText().toString();

            File newTextFile = getDocStorageDir(SUBDIR_NAME, title);
            try {
                newTextFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput(title, Context.MODE_WORLD_READABLE);
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, "External storage is not writable!");
        }
    }
}
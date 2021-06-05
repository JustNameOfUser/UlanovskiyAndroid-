package com.mirea.ulanovsky.loadermanger;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String> {
    private String firstName;
    public static final String KEY_WORD = "word";

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            firstName = args.getString(KEY_WORD);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        StringBuilder rearranged = new StringBuilder();

        for (int i = 0; i < firstName.length(); i++) {
            rearranged.append(firstName.charAt(i));

            firstName = new StringBuilder(firstName).reverse().toString();
        }

        return rearranged.toString();
    }
}

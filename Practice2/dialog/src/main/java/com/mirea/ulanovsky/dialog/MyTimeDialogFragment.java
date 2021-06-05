package com.mirea.ulanovsky.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment {
    private final Calendar calendar = Calendar.getInstance();
    private final TextView timeTextView;

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            timeTextView.setText(DateUtils.formatDateTime(getActivity(),
                    calendar.getTimeInMillis(),
                    DateUtils.FORMAT_SHOW_TIME));
        }
    };

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        return new TimePickerDialog(getActivity(), onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true);
    }

    public MyTimeDialogFragment(TextView textView)
    {
        timeTextView = textView;
    }
}

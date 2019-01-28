package com.androidag.sqlitelogin.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.androidag.sqlitelogin.R;

import java.util.Calendar;

public class DateFormate extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    int y, m, d;
    Calendar calendar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);
        calendar = Calendar.getInstance();

        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog date = new DatePickerDialog(getActivity(), this, y, m, d);
        date.getDatePicker().setMaxDate(System.currentTimeMillis());
        return date;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        EditText txtDate = (EditText) getActivity().getWindow().getDecorView()
                .getRootView().findViewById(R.id.et_birth_date);

        year = datePicker.getYear();
        month = datePicker.getMonth();
        day = datePicker.getDayOfMonth();
        txtDate.setText(day +"/"+(month+1) + "/"+ year);
        /*txtDate.setText(new StringBuilder().append("").append(day)
                .append("/").append(month + 1).append("/").append(year));*/

    }
}


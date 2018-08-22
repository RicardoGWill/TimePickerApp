package com.ricardogwill.timepickerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTimeButtonOnClick();
    }

    private TimePicker timePicker;
    private Button showTimeButton;

    public void showTimeButtonOnClick() {
        timePicker = findViewById(R.id.simple_TimePicker);
        showTimeButton = findViewById(R.id.show_time_button);
        // This is optional, as the 12-hour clock VIEW is the default...but getCurrentHour() is 24-hour.
        timePicker.setIs24HourView(false);

        showTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The reason for the lunacy between here and the Toast is to convert 24 hours
                // into 12, and to make sure all minutes are 2 digits long, as well as to add
                // AM if it is before 12 noon, and PM if it is 12 noon or later.
                int twelveHourTime = timePicker.getCurrentHour();
                // Turns 24-hour time into 12-hour time (but 12:00am is 0:0am).
                if (timePicker.getCurrentHour() > 12) {
                    twelveHourTime -= 12;
                }
                // If the hour (out of 24) is less than 12, "amPm" is AM, otherwise PM.
                String amPm;
                if (timePicker.getCurrentHour() < 12) {
                    amPm = " AM";
                } else {
                    amPm = " PM";
                }
                // If the converted 12-hour time is NOT "0", keep it as is. Change the hour "0" to "12".
                String hour;
                if (twelveHourTime != 0) {
                    hour = Integer.toString(twelveHourTime);
                } else {
                    hour = Integer.toString(12);
                }
                // Minutes (ints) may be single-digit (if less than 10), so make sure all minutes
                // are two-digits long, and convert them from "ints" to "Strings".
                String minute = String.format("%02d", timePicker.getCurrentMinute());
                // Make the Toast come together via concatenation of Strings with objects.
                Toast.makeText(MainActivity.this, "The chosen time is:  " + hour
                        + ":" + minute + amPm, Toast.LENGTH_LONG).show();
            }
        });
    }

}

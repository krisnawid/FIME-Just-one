package com.sdkdroid.fime_just_one;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CompanyProfileActivity extends AppCompatActivity {

    private SimpleDateFormat dateFormatter;
    TextView textViewHello;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        textViewHello = findViewById(R.id.textViewHello);

    }

    public void handleHellow(View view) {
    }


}

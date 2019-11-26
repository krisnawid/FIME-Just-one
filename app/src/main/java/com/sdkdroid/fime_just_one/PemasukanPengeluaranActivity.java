package com.sdkdroid.fime_just_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PemasukanPengeluaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan_pengeluaran);
    }

    public void handleTambahData(View view) {
        Intent intent = new Intent(this, InputDataActivity.class);
        startActivity(intent);
    }
}

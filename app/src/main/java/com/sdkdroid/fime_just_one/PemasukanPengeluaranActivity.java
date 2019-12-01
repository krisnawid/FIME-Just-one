package com.sdkdroid.fime_just_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sdkdroid.fime_just_one.data.FIMEAppDatabase;
import com.sdkdroid.fime_just_one.data.Laporan.LaporanDao;

public class PemasukanPengeluaranActivity extends AppCompatActivity {

    private TextView textViewPemasukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int totalPemasukan;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan_pengeluaran);

    }

    public void handleTambahData(View view) {
        Intent intent = new Intent(this, InputDataActivity.class);
        startActivity(intent);
    }
}

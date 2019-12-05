package com.sdkdroid.fime_just_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBiodata(View view) {
        Intent intent = new Intent(this, PerusahaanActivity.class);
        startActivity(intent);
    }

    public void handlePemasukan(View view) {
        Intent intent = new Intent(this, PemasukanPengeluaranActivity.class);
        startActivity(intent);
    }

    public void handleCetak(View view) {
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        startActivity(intent);
    }

    public void handleLaporan(View view) {
        Intent intent = new Intent(this, LaporanActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}

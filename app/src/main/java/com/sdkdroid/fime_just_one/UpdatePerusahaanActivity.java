package com.sdkdroid.fime_just_one;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePerusahaanActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.sdkdroid.fime_just_one.EXTRA_ID";
    public static final String EXTRA_NAMA =
            "com.sdkdroid.fime_just_one.EXTRA_NAMA";
    public static final String EXTRA_PEMILIK =
            "com.sdkdroid.fime_just_one.EXTRA_PEMILIK";
    public static final String EXTRA_ALAMAT =
            "com.sdkdroid.fime_just_one.EXTRA_ALAMAT";

    private EditText editTextNama;
    private EditText editTextPemilik;
    private EditText editTextAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_perusahaan);

        editTextNama = findViewById(R.id.edit_text_nama_perusahaan);
        editTextPemilik = findViewById(R.id.edit_text_pemilik_perusahaan);
        editTextAlamat = findViewById(R.id.edit_text_alamat_perusahaan);

//        numberPickerPriority.setMinValue(1);
//        numberPickerPriority.setMaxValue(10);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Perusahaan");
            editTextNama.setText(intent.getStringExtra(EXTRA_NAMA));
            editTextPemilik.setText(intent.getStringExtra(EXTRA_PEMILIK));
            editTextAlamat.setText(intent.getStringExtra(EXTRA_ALAMAT));
        }else{
            setTitle("Add Perusahaan");
        }
    }

    private void savePerusahaan(){
        String nama = editTextNama.getText().toString();
        String pemilik = editTextPemilik.getText().toString();
//        int priority = numberPickerPriority.getValue();
        String alamat = editTextAlamat.getText().toString();

        if(nama.trim().isEmpty() || pemilik.trim().isEmpty() || alamat.trim().isEmpty()){
            Toast.makeText(this, "Tolong masukkan nama, alamat, dan pemilik", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAMA, nama);
        data.putExtra(EXTRA_PEMILIK, pemilik);
        data.putExtra(EXTRA_ALAMAT, alamat);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.update_perusahaan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_perusahaan:
                savePerusahaan();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
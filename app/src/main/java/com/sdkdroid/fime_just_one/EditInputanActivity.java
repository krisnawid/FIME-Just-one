package com.sdkdroid.fime_just_one;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditInputanActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.sdkdroid.fime_just_one.EXTRA_ID";
    public static final String EXTRA_KETERANGAN =
            "com.sdkdroid.fime_just_one.EXTRA_KETERANGAN";
    public static final String EXTRA_TANGGAL =
            "com.sdkdroid.fime_just_one.EXTRA_TANGGAL";
    public static final String EXTRA_PEMASUKAN =
            "com.sdkdroid.fime_just_one.EXTRA_PEMASUKAN";
    public static final String EXTRA_PENGELUARAN =
            "com.sdkdroid.fime_just_one.EXTRA_PENGELUARAN";
    public static final String EXTRA_JUDUL =
            "com.sdkdroid.fime_just_one.EXTRA_JUDUL";

    private EditText editTextJudul;
    private EditText editTextKeterangan;
    private EditText editTextTanggal;
    private EditText editTextPemasukan;
    private EditText editTextPengeluaran;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inputan);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        editTextJudul = findViewById(R.id.edit_text_judul);
        editTextKeterangan = findViewById(R.id.edit_text_keterangan);
        editTextTanggal = findViewById(R.id.edit_text_tanggal);
        editTextPemasukan = findViewById(R.id.edit_text_pemasukan);
        editTextPengeluaran = findViewById(R.id.edit_text_pengeluaran);

//        numberPickerPriority.setMinValue(1);
//        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Laporan");
            editTextJudul.setText(intent.getStringExtra(EXTRA_JUDUL));
            editTextKeterangan.setText(intent.getStringExtra(EXTRA_KETERANGAN));
            editTextTanggal.setText(intent.getStringExtra(EXTRA_TANGGAL));
            editTextPemasukan.setText(String.valueOf(intent.getDoubleExtra(EXTRA_PEMASUKAN,1)));
            editTextPengeluaran.setText(String.valueOf(intent.getDoubleExtra(EXTRA_PENGELUARAN,1)));
        }else{
            setTitle("Add Laporan");
        }

    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                editTextTanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void saveLaporan(){
        String judul = editTextJudul.getText().toString();
        String keterangan = editTextKeterangan.getText().toString();
        String tanggal = editTextTanggal.getText().toString();
        double pemasukan = Double.parseDouble(editTextPemasukan.getText().toString());
        double pengeluaran = Double.parseDouble(editTextPengeluaran.getText().toString());

        if( judul.trim().isEmpty() || keterangan.trim().isEmpty() || tanggal.trim().isEmpty()){
            Toast.makeText(this, "Tolong Input Data Dengan VALID", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_JUDUL, judul);
        data.putExtra(EXTRA_KETERANGAN, keterangan);
        data.putExtra(EXTRA_TANGGAL, tanggal);
        data.putExtra(EXTRA_PEMASUKAN, pemasukan);
        data.putExtra(EXTRA_PENGELUARAN, pengeluaran);

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
        menuInflater.inflate(R.menu.update_laporan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_laporan:
                saveLaporan();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void handleCalender(View view) {
        showDateDialog();
    }
}

package com.sdkdroid.fime_just_one;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.sdkdroid.fime_just_one.R;
import com.sdkdroid.fime_just_one.data.Laporan.Laporan;
import com.sdkdroid.fime_just_one.data.Laporan.LaporanViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LaporanActivity extends AppCompatActivity {

    private TextView textViewWaktu;
    private TextView textViewRpTotalPemasukan;
    private TextView textViewRpTotalPengeluaran;
    private TextView textViewRpTotalSelisih;
    private TextView textViewTanggalAwal;
    private TextView textViewTanggalAkhir;


    private LaporanViewModel laporanViewModel;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter,bulan,tahun,tanggal;
    private long tanggalawal;
    private long tanggalakhir;
    private Date tanggalAwalDate, tanggalAkhirDate;
    private int tanggalAwalLoop, tanggalAkhirLoop, bulanAwal, bulanAkhir, tahunAwal, tahunAkhir;
//    private int tanggalAwalLoopDate, tanggalAkhirLoopDate, bulanAwalDate, bulanAkhirDate, tahunAwalDate, tahunAkhirDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

        textViewTanggalAwal = (TextView) findViewById(R.id.text_tanggal_awal_laporan);
        textViewTanggalAkhir = (TextView) findViewById(R.id.text_tanggal_akhir_laporan);
        textViewRpTotalPemasukan = findViewById(R.id.rp_total_pemasukan_laporan);
        textViewRpTotalPengeluaran = findViewById(R.id.rp_total_pengeluaran_laporan);
        textViewRpTotalSelisih = findViewById(R.id.rp_total_selisih_laporan);
        textViewWaktu = findViewById(R.id.text_waktu_laporan);

        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        bulan = new SimpleDateFormat("MM");
        tahun = new SimpleDateFormat("yyyy");
        tanggal = new SimpleDateFormat("dd");
        String formattedDate = df.format(currentTime);
        String formatbulan = bulan.format(currentTime);
        String formattahun = tahun.format(currentTime);

        textViewWaktu.setText(formattedDate);

    }

    public void handleTanggalAkhir(View view) {
        showDateDialogTanggalAkhir();
    }

    public void handleTanggalAwal(View view) {
        showDateDialogTanggalAwal();
    }

    private void showDateDialogTanggalAwal(){

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
                textViewTanggalAwal.setText(dateFormatter.format(newDate.getTime()));
                String convertTanggal1 = String.valueOf(bulan.format(newDate.getTime()));
                tanggalAwalDate = newDate.getTime();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void showDateDialogTanggalAkhir(){

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
                textViewTanggalAkhir.setText(dateFormatter.format(newDate.getTime()));
                tanggalAkhirDate = newDate.getTime();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    public void handleRefresh(View view) {
//        Toast.makeText(this, tanggalAwalDate.toString(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, tanggalAkhirDate.toString(), Toast.LENGTH_SHORT).show();
        laporanViewModel = ViewModelProviders.of(this).get(LaporanViewModel.class);
        laporanViewModel.getAllLaporan().observe(this, new Observer<List<Laporan>>() {
            @Override
            public void onChanged(@Nullable List<Laporan> laporans) {
                double totalPemasukan = 0;
                double totalPengeluaran = 0;
                
                if (laporans.isEmpty()) {
                    Toast.makeText(LaporanActivity.this, "kosong", Toast.LENGTH_SHORT).show();
                }

                for (Laporan l: laporans){
                    if (l.getTanggal().after(tanggalAwalDate) && l.getTanggal().before(tanggalAkhirDate)) {
                        totalPemasukan += l.getPemasukan();
                        totalPengeluaran += l.getPengeluaran();
                    }else{
                        Toast.makeText(LaporanActivity.this, "kosong", Toast.LENGTH_SHORT).show();
                    }
                }
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                textViewRpTotalPemasukan.setText(kursIndonesia.format(totalPemasukan));
                textViewRpTotalPengeluaran.setText(kursIndonesia.format(totalPengeluaran));
                textViewRpTotalSelisih.setText(kursIndonesia.format(totalPemasukan-totalPengeluaran));
            }
        });
    }
}

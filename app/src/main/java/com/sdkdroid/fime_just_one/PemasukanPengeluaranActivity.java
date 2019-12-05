package com.sdkdroid.fime_just_one;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sdkdroid.fime_just_one.data.FIMEAppDatabase;
import com.sdkdroid.fime_just_one.data.Laporan.Laporan;
import com.sdkdroid.fime_just_one.data.Laporan.LaporanDao;
import com.sdkdroid.fime_just_one.data.Laporan.LaporanViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PemasukanPengeluaranActivity extends AppCompatActivity {

    private TextView textViewWaktu;
    private TextView textViewRpTotalPemasukan;
    private TextView textViewRpTotalPengeluaran;
    private TextView textViewRpTotalSelisih;

    private LaporanViewModel laporanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        double totalPemasukan, totalPengeluaran;
//        final double[] totalPemasukan = {0};
//        final double[] totalPengeluaran = {0};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan_pengeluaran);

        textViewRpTotalPemasukan = findViewById(R.id.rp_total_pemasukan);
        textViewRpTotalPengeluaran = findViewById(R.id.rp_total_pengeluaran);
        textViewRpTotalSelisih = findViewById(R.id.rp_total_selisih);
        textViewWaktu = findViewById(R.id.text_waktu);

        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("MMM-yyyy");
        SimpleDateFormat bulan = new SimpleDateFormat("MM");
        SimpleDateFormat tahun = new SimpleDateFormat("yyyy");
        String formattedDate = df.format(currentTime);
        String formatbulan = bulan.format(currentTime);
        String formattahun = tahun.format(currentTime);

        textViewWaktu.setText(formattedDate);

        laporanViewModel = ViewModelProviders.of(this).get(LaporanViewModel.class);
        laporanViewModel.getAllLaporan().observe(this, new Observer<List<Laporan>>() {
            @Override
            public void onChanged(@Nullable List<Laporan> laporans) {
                double totalPemasukan = 0;
                double totalPengeluaran = 0;

                for (Laporan l: laporans){
                    totalPemasukan += l.getPemasukan();
                    totalPengeluaran += l.getPengeluaran();
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

    public void handleTambahData(View view) {
        Intent intent = new Intent(this, InputDataActivity.class);
        startActivity(intent);
    }
}

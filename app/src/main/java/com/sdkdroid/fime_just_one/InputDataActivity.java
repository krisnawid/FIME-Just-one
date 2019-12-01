package com.sdkdroid.fime_just_one;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdkdroid.fime_just_one.data.Laporan.Laporan;
import com.sdkdroid.fime_just_one.data.Laporan.LaporanAdapter;
import com.sdkdroid.fime_just_one.data.Laporan.LaporanViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InputDataActivity extends AppCompatActivity {

    public static final int ADD_LAPORAN_REQUEST = 1;
    public static final int EDIT_LAPORAN_REQUEST = 2;

    private LaporanViewModel laporanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        FloatingActionButton buttonAddLaporan = findViewById(R.id.button_add_input_data);
        buttonAddLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputDataActivity.this, EditInputanActivity.class);
                startActivityForResult(intent, ADD_LAPORAN_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final LaporanAdapter adapter = new LaporanAdapter();
        recyclerView.setAdapter(adapter);

        laporanViewModel = ViewModelProviders.of(this).get(LaporanViewModel.class);
        laporanViewModel.getAllLaporan().observe(this, new Observer<List<Laporan>>() {
            @Override
            public void onChanged(@Nullable List<Laporan> laporans) {
                adapter.setLaporans(laporans);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                laporanViewModel.delete(adapter.getLaporanAt(viewHolder.getAdapterPosition()));
                Toast.makeText(InputDataActivity.this, "Data terhapus", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new LaporanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Laporan laporan) {
                Intent intent = new Intent(InputDataActivity.this, EditInputanActivity.class);
                intent.putExtra(EditInputanActivity.EXTRA_ID, laporan.getId_laporan());
                intent.putExtra(EditInputanActivity.EXTRA_JUDUL, laporan.getJudul());
                intent.putExtra(EditInputanActivity.EXTRA_KETERANGAN, laporan.getKeterangan());
                intent.putExtra(EditInputanActivity.EXTRA_TANGGAL, laporan.getTanggal());
                intent.putExtra(EditInputanActivity.EXTRA_PENGELUARAN, laporan.getPengeluaran());
                intent.putExtra(EditInputanActivity.EXTRA_PEMASUKAN, laporan.getPemasukan());
                startActivityForResult(intent, EDIT_LAPORAN_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_LAPORAN_REQUEST && resultCode == RESULT_OK){
            String judul = data.getStringExtra(EditInputanActivity.EXTRA_JUDUL);
            String keterangan = data.getStringExtra(EditInputanActivity.EXTRA_KETERANGAN);
            String tanggalString = data.getStringExtra(EditInputanActivity.EXTRA_TANGGAL);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date tanggal = null;
            try {
                tanggal = format.parse(tanggalString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int pemasukan = data.getIntExtra(EditInputanActivity.EXTRA_PEMASUKAN ,1);
            int pengeluaran = data.getIntExtra(EditInputanActivity.EXTRA_PENGELUARAN, 1);

            Laporan laporan = new Laporan(judul, pemasukan, pengeluaran, tanggal, keterangan);
            laporanViewModel.insert(laporan);

            Toast.makeText(this, "Laporan tersimpan", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_LAPORAN_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(EditInputanActivity.EXTRA_ID, -1);

            if(id == -1){
                Toast.makeText(this, "Laporan tidak dapat diupdate", Toast.LENGTH_SHORT).show();
                return;
            }

            String judul = data.getStringExtra(EditInputanActivity.EXTRA_JUDUL);
            String keterangan = data.getStringExtra(EditInputanActivity.EXTRA_KETERANGAN);
            String tanggalString = data.getStringExtra(EditInputanActivity.EXTRA_TANGGAL);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date tanggal = null;
            try {
                tanggal = format.parse(tanggalString);
            } catch (ParseException e) {
                e.printStackTrace();
            };
            int pemasukan = data.getIntExtra(EditInputanActivity.EXTRA_PEMASUKAN, 1);
            int pengeluaran = data.getIntExtra(EditInputanActivity.EXTRA_PENGELUARAN, 1);

            Laporan laporan = new Laporan(judul, pemasukan, pengeluaran, tanggal, keterangan);
            laporan.setId_laporan(id);
            laporanViewModel.update(laporan);
            Toast.makeText(this, "Laporan telah diupdate", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Laporan tidak tersimpan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_laporan:
                laporanViewModel.deleteAllLaporans();
                Toast.makeText(this, "Semua Laporan terhapus", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

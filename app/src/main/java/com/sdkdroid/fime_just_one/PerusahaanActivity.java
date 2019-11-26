package com.sdkdroid.fime_just_one;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
import com.sdkdroid.fime_just_one.data.Perusahaan.Perusahaan;
import com.sdkdroid.fime_just_one.data.Perusahaan.PerusahaanAdapter;
import com.sdkdroid.fime_just_one.data.Perusahaan.PerusahaanViewModel;

import java.util.List;

public class PerusahaanActivity extends AppCompatActivity {

    public static final int ADD_PERUSAHAAN_REQUEST = 1;
    public static final int EDIT_PERUSAHAAN_REQUEST = 2;

    private PerusahaanViewModel perusahaanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perusahaan);

//        FloatingActionButton buttonAddPerusahaan = findViewById(R.id.button_add_perusahaan);
//        buttonAddPerusahaan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PerusahaanActivity.this, UpdatePerusahaanActivity.class);
//                startActivityForResult(intent, ADD_PERUSAHAAN_REQUEST);
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PerusahaanAdapter adapter = new PerusahaanAdapter();
        recyclerView.setAdapter(adapter);

        perusahaanViewModel = ViewModelProviders.of(this).get(PerusahaanViewModel.class);
        perusahaanViewModel.selectOne().observe(this, new Observer<List<Perusahaan>>() {
            @Override
            public void onChanged(@Nullable List<Perusahaan> perusahaans) {
                adapter.setPerusahaans(perusahaans);
            }
        });

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                perusahaanViewModel.delete(adapter.getPerusahaanAt(viewHolder.getAdapterPosition()));
//                Toast.makeText(PerusahaanActivity.this, "Perusahaan terhapus", Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PerusahaanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Perusahaan perusahaan) {
                Intent intent = new Intent(PerusahaanActivity.this, UpdatePerusahaanActivity.class);
                intent.putExtra(UpdatePerusahaanActivity.EXTRA_ID, perusahaan.getId_perusahaan());
                intent.putExtra(UpdatePerusahaanActivity.EXTRA_NAMA, perusahaan.getNama_perusahaan());
                intent.putExtra(UpdatePerusahaanActivity.EXTRA_PEMILIK, perusahaan.getPemilik_perusahaan());
                intent.putExtra(UpdatePerusahaanActivity.EXTRA_ALAMAT, perusahaan.getAlamat_perusahaan());
                startActivityForResult(intent, EDIT_PERUSAHAAN_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PERUSAHAAN_REQUEST && resultCode == RESULT_OK){
            String nama = data.getStringExtra(UpdatePerusahaanActivity.EXTRA_NAMA);
            String pemilik = data.getStringExtra(UpdatePerusahaanActivity.EXTRA_PEMILIK);
            String alamat = data.getStringExtra(UpdatePerusahaanActivity.EXTRA_ALAMAT);

            Perusahaan perusahaan = new Perusahaan(nama, pemilik, alamat);
            perusahaanViewModel.insert(perusahaan);

            Toast.makeText(this, "Perusahaan tersimpan", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_PERUSAHAAN_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(UpdatePerusahaanActivity.EXTRA_ID, -1);

            if(id == -1){
                Toast.makeText(this, "Perusahaan tidak dapat diupdate", Toast.LENGTH_SHORT).show();
                return;
            }

            String nama = data.getStringExtra(UpdatePerusahaanActivity.EXTRA_NAMA);
            String pemilik = data.getStringExtra(UpdatePerusahaanActivity.EXTRA_PEMILIK);
            String alamat = data.getStringExtra(UpdatePerusahaanActivity.EXTRA_ALAMAT);

            Perusahaan perusahaan = new Perusahaan(nama,pemilik, alamat);
            perusahaan.setId_perusahaan(id);
            perusahaanViewModel.update(perusahaan);
            Toast.makeText(this, "Perusahaan telah diupdate", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Perusahaan tidak tersimpan", Toast.LENGTH_SHORT).show();
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
            case R.id.delete_all_perusahaan:
                perusahaanViewModel.deleteAllPerusahaans();
                Toast.makeText(this, "Semua perusahaan terhapus", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

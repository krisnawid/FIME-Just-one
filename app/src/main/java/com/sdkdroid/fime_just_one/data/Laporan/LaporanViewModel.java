package com.sdkdroid.fime_just_one.data.Laporan;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class LaporanViewModel extends AndroidViewModel {
    private LaporanRepository repository;
    //    private Perusahaan perusahaan;
    private LiveData<List<Laporan>> allLaporan;
    private LiveData<List<Laporan>> allLaporanByTanggal;
    private long tanggalAwal1, tangglAkhir1;

    public LaporanViewModel(@NonNull Application application ){
        super(application);
        repository = new LaporanRepository(application);
        allLaporan = repository.getAllLaporan();
        allLaporanByTanggal = repository.getAllLaporanByTanggal(tanggalAwal1, tangglAkhir1);
    }

    public void insert(Laporan laporan){
        repository.insert(laporan);
    }

    public void update(Laporan laporan){
        repository.update(laporan);
    }

    public void delete(Laporan laporan){
        repository.delete(laporan);
    }

//    public void getPemasukanLaporan(){
//        repository.getPemasukanLaporan();
//    }
//
//    public void getPengeluaranLaporan(){
//        repository.getPengeluaranLaporan();
//    }

    public void deleteAllLaporans(){
        repository.deleteAllLaporans();
    }


    public LiveData<List<Laporan>> getAllLaporan(){
        return allLaporan;
    }

    public LiveData<List<Laporan>> getAllLaporanByTanggal(long tanggalAwal, long tangglAkhir){
        return allLaporanByTanggal;
    }

}

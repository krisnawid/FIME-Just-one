package com.sdkdroid.fime_just_one.data.Perusahaan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class PerusahaanViewModel extends AndroidViewModel {
    private PerusahaanRepository repository;
    //    private Perusahaan perusahaan;
    private LiveData<List<Perusahaan>> allPerusahaan;

    public PerusahaanViewModel(@NonNull Application application ){
        super(application);
        repository = new PerusahaanRepository(application);
        allPerusahaan = repository.selectOne();
    }

    public void insert(Perusahaan perusahaan){
        repository.insert(perusahaan);
    }

    public void update(Perusahaan perusahaan){
        repository.update(perusahaan);
    }

    public void delete(Perusahaan perusahaan){
        repository.delete(perusahaan);
    }

    public void deleteAllPerusahaans(){
        repository.deleteAllPerusahaans();
    }

    public LiveData<List<Perusahaan>> selectOne(){
        return allPerusahaan;
    }

}

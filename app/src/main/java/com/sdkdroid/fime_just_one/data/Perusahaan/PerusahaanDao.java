package com.sdkdroid.fime_just_one.data.Perusahaan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PerusahaanDao {

    @Insert
    void insert(Perusahaan perusahaan);

    @Update
    void update(Perusahaan perusahaan);

    @Delete
    void delete(Perusahaan perusahaan);

    @Query("DELETE FROM perusahaan_table")
    void deleteAllPerusahaans();

    @Query("SELECT * FROM perusahaan_table LIMIT 1")
    LiveData<List<Perusahaan>> selectOne();

    @Query("SELECT * FROM perusahaan_table")
    LiveData<List<Perusahaan>> getAllPerusahaan();

}


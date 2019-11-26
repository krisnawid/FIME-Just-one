package com.sdkdroid.fime_just_one.data.Laporan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LaporanDao {
    @Insert
    void insert(Laporan laporan);

    @Update
    void update(Laporan laporan);

    @Delete
    void delete(Laporan laporan);

    @Query("DELETE FROM laporan_table")
    void deleteAllLaporans();

    @Query("SELECT * FROM laporan_table LIMIT 1")
    LiveData<List<Laporan>> selectOneLaporan();

    @Query("SELECT * FROM laporan_table")
    LiveData<List<Laporan>> getAllLaporan();
}

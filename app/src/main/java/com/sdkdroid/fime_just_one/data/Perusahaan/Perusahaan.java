package com.sdkdroid.fime_just_one.data.Perusahaan;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "perusahaan_table")
public class Perusahaan {

    @PrimaryKey(autoGenerate = true)
    public int id_perusahaan;

    String nama_perusahaan;

    String pemilik_perusahaan;

    String alamat_perusahaan;

    @Ignore
    public Perusahaan(String nama_perusahaan, String pemilik_perusahaan, String alamat_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
        this.pemilik_perusahaan = pemilik_perusahaan;
        this.alamat_perusahaan = alamat_perusahaan;
    }

    public Perusahaan(){}

    public int getId_perusahaan() {
        return id_perusahaan;
    }

    public void setId_perusahaan(int id_perusahaan) {
        this.id_perusahaan = id_perusahaan;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getPemilik_perusahaan() {
        return pemilik_perusahaan;
    }

    public void setPemilik_perusahaan(String pemilik_perusahaan) {
        this.pemilik_perusahaan = pemilik_perusahaan;
    }

    public String getAlamat_perusahaan() {
        return alamat_perusahaan;
    }

    public void setAlamat_perusahaan(String alamat_perusahaan) {
        this.alamat_perusahaan = alamat_perusahaan;
    }
}
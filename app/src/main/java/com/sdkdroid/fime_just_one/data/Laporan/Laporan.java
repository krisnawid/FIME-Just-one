package com.sdkdroid.fime_just_one.data.Laporan;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "laporan_table")
public class Laporan {

    @PrimaryKey(autoGenerate = true)
    public int id_laporan;

    int pemasukan;

    int pengeluaran;

    String tanggal;

    String keterangan;

    public Laporan(int pemasukan, int pengeluaran, String tanggal, String keterangan) {
        this.pemasukan = pemasukan;
        this.pengeluaran = pengeluaran;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }

    public int getId_laporan() {
        return id_laporan;
    }

    public void setId_laporan(int id_laporan) {
        this.id_laporan = id_laporan;
    }

    public int getPemasukan() {
        return pemasukan;
    }

    public void setPemasukan(int pemasukan) {
        this.pemasukan = pemasukan;
    }

    public int getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(int pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
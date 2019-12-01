package com.sdkdroid.fime_just_one.data.Laporan;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.sdkdroid.fime_just_one.data.DataConverter;

import java.util.Date;

@Entity(tableName = "laporan_table")
@TypeConverters(DataConverter.class)
public class Laporan {

    @PrimaryKey(autoGenerate = true)
    public int id_laporan;

    int pemasukan;

    int pengeluaran;

    Date tanggal;

    String keterangan;

    String judul;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Laporan(String judul, int pemasukan, int pengeluaran, Date tanggal, String keterangan) {
        this.judul = judul;
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

    public Date getTanggal() {

        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
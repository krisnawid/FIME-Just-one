package com.sdkdroid.fime_just_one.data.Laporan;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sdkdroid.fime_just_one.data.FIMEAppDatabase;

import java.util.List;

public class LaporanRepository {

    private LaporanDao laporanDao;
    private Laporan laporan;
    LiveData<List<Laporan>> allLaporan;

    public LaporanRepository(Application application){
        FIMEAppDatabase database = FIMEAppDatabase.getInstance(application);
        laporanDao = database.laporanDao();
        allLaporan = laporanDao.getAllLaporan();
    }

    public void insert(Laporan laporan){
        new InsertLaporanAsyncTask(laporanDao).execute(laporan);
    }

    public void update(Laporan laporan){
        new UpdateLaporanAsyncTask(laporanDao).execute(laporan);
    }

    public void delete(Laporan laporan){
        new DeleteLaporanAsyncTask(laporanDao).execute(laporan);
    }

    public void deleteAllLaporans(){
        new DeleteAllLaporanAsyncTask(laporanDao).execute();
    }

    public LiveData<List<Laporan>> getAllLaporan(){
        return allLaporan;
    }

    private static class InsertLaporanAsyncTask extends AsyncTask<Laporan, Void, Void> {
        private LaporanDao laporanDao;

        private InsertLaporanAsyncTask(LaporanDao laporanDao){
            this.laporanDao = laporanDao;
        }

        @Override
        protected Void doInBackground(Laporan... laporans){
            laporanDao.insert(laporans[0]);
            return null;
        }
    }

    private static class UpdateLaporanAsyncTask extends AsyncTask<Laporan, Void, Void>{
        private LaporanDao laporanDao;

        private UpdateLaporanAsyncTask(LaporanDao laporanDao){
            this.laporanDao = laporanDao;
        }

        @Override
        protected Void doInBackground(Laporan... laporans){
            laporanDao.update(laporans[0]);
            return null;
        }
    }

    private static class DeleteLaporanAsyncTask extends AsyncTask<Laporan, Void, Void>{
        private LaporanDao laporanDao;

        private DeleteLaporanAsyncTask(LaporanDao laporanDao){
            this.laporanDao = laporanDao;
        }

        @Override
        protected Void doInBackground(Laporan... laporans){
            laporanDao.delete(laporans[0]);
            return null;
        }
    }

    private static class DeleteAllLaporanAsyncTask extends AsyncTask<Void, Void, Void>{
        private LaporanDao laporanDao;

        private DeleteAllLaporanAsyncTask(LaporanDao laporanDao){
            this.laporanDao = laporanDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            laporanDao.deleteAllLaporans();
            return null;
        }
    }


}
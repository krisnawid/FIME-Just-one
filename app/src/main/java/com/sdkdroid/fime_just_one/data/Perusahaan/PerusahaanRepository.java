package com.sdkdroid.fime_just_one.data.Perusahaan;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sdkdroid.fime_just_one.data.FIMEAppDatabase;

import java.util.List;

public class PerusahaanRepository {
    private PerusahaanDao perusahaanDao;
    private Perusahaan perusahaan;
    LiveData<List<Perusahaan>> allPerusahaan;

    public PerusahaanRepository(Application application){
        FIMEAppDatabase database = FIMEAppDatabase.getInstance(application);
        perusahaanDao = database.perusahaanDao();
        allPerusahaan = perusahaanDao.selectOne();
    }

    public void insert(Perusahaan perusahaan){
        new InsertPerusahaanAsyncTask(perusahaanDao).execute(perusahaan);
    }

    public void update(Perusahaan perusahaan){
        new UpdatePerusahaanAsyncTask(perusahaanDao).execute(perusahaan);
    }

    public void delete(Perusahaan perusahaan){
        new DeletePerusahaanAsyncTask(perusahaanDao).execute(perusahaan);
    }

    public void deleteAllPerusahaans(){
        new DeleteAllPerusahaanAsyncTask(perusahaanDao).execute();
    }

    public LiveData<List<Perusahaan>> selectOne(){
        return allPerusahaan;
    }

    private static class InsertPerusahaanAsyncTask extends AsyncTask<Perusahaan, Void, Void> {
        private PerusahaanDao perusahaanDao;

        private InsertPerusahaanAsyncTask(PerusahaanDao perusahaanDao){
            this.perusahaanDao = perusahaanDao;
        }

        @Override
        protected Void doInBackground(Perusahaan... perusahaans){
            perusahaanDao.insert(perusahaans[0]);
            return null;
        }
    }

    private static class UpdatePerusahaanAsyncTask extends AsyncTask<Perusahaan, Void, Void>{
        private PerusahaanDao perusahaanDao;

        private UpdatePerusahaanAsyncTask(PerusahaanDao perusahaanDao){
            this.perusahaanDao = perusahaanDao;
        }

        @Override
        protected Void doInBackground(Perusahaan... perusahaans){
            perusahaanDao.update(perusahaans[0]);
            return null;
        }
    }

    private static class DeletePerusahaanAsyncTask extends AsyncTask<Perusahaan, Void, Void>{
        private PerusahaanDao perusahaanDao;

        private DeletePerusahaanAsyncTask(PerusahaanDao perusahaanDao){
            this.perusahaanDao = perusahaanDao;
        }

        @Override
        protected Void doInBackground(Perusahaan... perusahaans){
            perusahaanDao.delete(perusahaans[0]);
            return null;
        }
    }

    private static class DeleteAllPerusahaanAsyncTask extends AsyncTask<Void, Void, Void>{
        private PerusahaanDao perusahaanDao;

        private DeleteAllPerusahaanAsyncTask(PerusahaanDao perusahaanDao){
            this.perusahaanDao = perusahaanDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            perusahaanDao.deleteAllPerusahaans();
            return null;
        }
    }

}
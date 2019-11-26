package com.sdkdroid.fime_just_one.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sdkdroid.fime_just_one.data.Laporan.Laporan;
import com.sdkdroid.fime_just_one.data.Laporan.LaporanDao;
import com.sdkdroid.fime_just_one.data.Perusahaan.Perusahaan;
import com.sdkdroid.fime_just_one.data.Perusahaan.PerusahaanDao;


@Database(entities = {Perusahaan.class, Laporan.class}, version = 1, exportSchema = false)
public abstract class FIMEAppDatabase extends RoomDatabase {
    private static FIMEAppDatabase instance;

    public abstract PerusahaanDao perusahaanDao();
    public abstract LaporanDao laporanDao();

    public static synchronized FIMEAppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FIMEAppDatabase.class, "db_fime")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PerusahaanDao perusahaanDao;
        private LaporanDao laporanDao;

        private PopulateDbAsyncTask(FIMEAppDatabase db){
            perusahaanDao = db.perusahaanDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            perusahaanDao.insert(new Perusahaan("Default","Default","Default"));
            return null;
        }
    }

}

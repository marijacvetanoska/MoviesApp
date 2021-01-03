package com.example.lab03.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.lab03.database.dao.SearchListDao;
import com.example.lab03.model.Movie;
import com.example.lab03.model.MoviesList;

@Database(entities = {MoviesList.class, Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SearchListDao searchListDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "searchList.db").build();
    }
}

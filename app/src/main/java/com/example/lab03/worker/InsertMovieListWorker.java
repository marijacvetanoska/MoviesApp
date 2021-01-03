package com.example.lab03.worker;

import android.os.AsyncTask;

import com.example.lab03.database.AppDatabase;
import com.example.lab03.model.MoviesList;

public class InsertMovieListWorker extends AsyncTask<MoviesList, Void, Void> {
    private AppDatabase database;

    public InsertMovieListWorker(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected Void doInBackground(MoviesList... moviesLists) {
        database.searchListDao().insertMovieListWithMovies(moviesLists[0], moviesLists[0].getSearch());
        return null;
    }
}

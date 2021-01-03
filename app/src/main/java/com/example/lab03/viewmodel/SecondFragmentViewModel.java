package com.example.lab03.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.lab03.database.AppDatabase;
import com.example.lab03.database.relationship.SearchListWithMovies;
import com.example.lab03.model.Movie;

public class SecondFragmentViewModel extends AndroidViewModel {
    private AppDatabase database;
    private MutableLiveData<Movie> movieMutableLiveData;

    public SecondFragmentViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        movieMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Movie> getMovieMutableLiveData() {
        return movieMutableLiveData;
    }

    public void loadDataFromDatabase(String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Movie movie = database.searchListDao().getMovie(id);
                movieMutableLiveData.postValue(movie);
            }
        }).start();
    }
}

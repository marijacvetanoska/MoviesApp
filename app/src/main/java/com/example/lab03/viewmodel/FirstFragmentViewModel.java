package com.example.lab03.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.lab03.api.OmdbApi;
import com.example.lab03.api.OmdbApiClient;
import com.example.lab03.database.AppDatabase;
import com.example.lab03.model.MoviesList;
import com.example.lab03.worker.InsertMovieListWorker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragmentViewModel extends AndroidViewModel {

    private OmdbApi omdbApi;
    private Application app;
    private AppDatabase database;

    private MutableLiveData<MoviesList> moviesListMutableLiveData;
    private InsertMovieListWorker insertMovieListWorker;

    public FirstFragmentViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
        omdbApi = OmdbApiClient.getOmdbApiInstance();
        moviesListMutableLiveData = new MutableLiveData<>();
        database = AppDatabase.getInstance(application);
        insertMovieListWorker = new InsertMovieListWorker(database);
    }

    public void searchMovies(String title) {
        omdbApi.getAllMovies(title, "short", "json", "17596310").enqueue(new Callback<MoviesList>() {

            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                Log.d("Retrofit", "Success");
//                displayData(response.body());
                if(response.body() != null) {
                    MoviesList moviesList = response.body();
                    saveInLocalDatabase(moviesList);
                    moviesListMutableLiveData.setValue(moviesList);
                }
                else {
                    Toast.makeText(app, "Movie with this has not been found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Toast.makeText(app, "Movie with this title was not found", Toast.LENGTH_SHORT);
            }
        });
    }

    private void saveInLocalDatabase(MoviesList moviesList) {
        if(moviesList.getSearch() != null) {
            insertMovieListWorker.execute(moviesList);
        }
    }

    public MutableLiveData<MoviesList> getMoviesListMutableLiveData() {
        return moviesListMutableLiveData;
    }
}

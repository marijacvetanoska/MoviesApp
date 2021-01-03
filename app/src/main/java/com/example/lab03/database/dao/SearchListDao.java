package com.example.lab03.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.lab03.database.relationship.SearchListWithMovies;
import com.example.lab03.model.Movie;
import com.example.lab03.model.MoviesList;

import java.util.List;

@Dao
public abstract class SearchListDao {

    @Transaction
    @Query("SELECT * FROM Movie WHERE imdbID = :id")
    public abstract Movie getMovie(String id);

    @Transaction
    @Query("SELECT * FROM MoviesList WHERE id = :id")
    public abstract SearchListWithMovies getListWithMovies(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertMovieList(MoviesList moviesList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertMovies(List<Movie> movies);

    public void insertMovieListWithMovies(MoviesList list, List<Movie> movies) {
        try {
            insertMovieList(list);
            for(Movie movie : movies)
                movie.setSearchId(list.getId());
            insertMovies(movies);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

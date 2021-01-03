package com.example.lab03.database.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.lab03.model.Movie;
import com.example.lab03.model.MoviesList;

import java.util.List;

public class SearchListWithMovies {
    @Embedded
    public MoviesList moviesList;
    @Relation(
            parentColumn = "id",
            entityColumn = "searchId"
    )
    public List<Movie> movies;
}

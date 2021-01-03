package com.example.lab03.api;

import com.example.lab03.model.Movie;
import com.example.lab03.model.MoviesList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OmdbApi {

//    String API_KEY = "17596310";

    //http://www.omdbapi.com/?i=tt3896198&apikey=17596310
    @GET(".")
    Call<MoviesList> getAllMovies(@Query("s") String Title,
                                  @Query("plot") String Plot,
                                  @Query("r") String Format,
                                  @Query("apikey") String Key);

}

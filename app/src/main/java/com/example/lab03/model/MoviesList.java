package com.example.lab03.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class MoviesList {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @Ignore
    @SerializedName("Search")
    private List<Movie> Search;

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("Response")
    private String Response;

    public MoviesList() {
    }

    public MoviesList(List<Movie> search, String totalResults, String response) {
        Search = search;
        this.totalResults = totalResults;
        Response = response;
    }

    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

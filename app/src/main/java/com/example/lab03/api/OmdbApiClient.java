package com.example.lab03.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OmdbApiClient {

    private static OmdbApi omdbClient;

    public static OmdbApi getOmdbApiInstance() {
        if(omdbClient == null) {
            omdbClient = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/?")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OmdbApi.class);
        }
        return omdbClient;
    }

}

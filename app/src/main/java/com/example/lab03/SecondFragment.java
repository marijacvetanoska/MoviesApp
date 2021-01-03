package com.example.lab03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.lab03.model.Movie;
import com.example.lab03.model.MoviesList;
import com.example.lab03.viewmodel.FirstFragmentViewModel;
import com.example.lab03.viewmodel.SecondFragmentViewModel;

public class SecondFragment extends Fragment {

    private SecondFragmentViewModel secondFragmentViewModel;
    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvDirector;
    private TextView tvActors;
    private TextView tvGenre;
    private ImageView tvPoster;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitle = view.findViewById(R.id.tvMovieTitle);
        tvActors = view.findViewById(R.id.tvMovieActors);
        tvDirector = view.findViewById(R.id.tvMovieDirector);
        tvYear = view.findViewById(R.id.tvMovieYear);
        tvGenre = view.findViewById(R.id.tvMovieGenre);
        tvPoster = view.findViewById(R.id.ivMoviePoster);


        secondFragmentViewModel = ViewModelProviders.of(this).get(SecondFragmentViewModel.class);
        secondFragmentViewModel.getMovieMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Movie>() {

            @Override
            public void onChanged(Movie movie) {
                displayData(movie);
            }
        });

        String id = getArguments().getString("movieId");
        secondFragmentViewModel.loadDataFromDatabase(id);
    }

    private void displayData(Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvActors.setText(movie.getActors());
        tvDirector.setText(movie.getDirector());
        tvYear.setText(movie.getYear());
        tvGenre.setText(movie.getGenre());
        Glide.with(this).load(movie.getPoster()).into(tvPoster);
    }
}
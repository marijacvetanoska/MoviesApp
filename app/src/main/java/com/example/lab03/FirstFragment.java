package com.example.lab03;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab03.adapter.MovieAdapter;
import com.example.lab03.api.OmdbApi;
import com.example.lab03.api.OmdbApiClient;
import com.example.lab03.model.Movie;
import com.example.lab03.model.MoviesList;
import com.example.lab03.viewmodel.FirstFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {

    private EditText etTitle;
    private RecyclerView moviesList;
    private MovieAdapter movieAdapter;
    private FirstFragmentViewModel firstFragmentViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstFragmentViewModel = ViewModelProviders.of(this).get(FirstFragmentViewModel.class);
        firstFragmentViewModel.getMoviesListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<MoviesList>() {
            @Override
            public void onChanged(MoviesList moviesList) {
                displayData(moviesList);
            }
        });

        etTitle = (EditText) view.findViewById(R.id.etTitle);
        moviesList = (RecyclerView) view.findViewById(R.id.moviesList);
        movieAdapter = new MovieAdapter(new ArrayList<Movie>());

        movieAdapter.setMovieClickListener(new MovieAdapter.MovieClickListener() {
            @Override
            public void onMovieClicked(String id) {
                Bundle bundle = new Bundle();
                bundle.putString("movieId", id);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });

        moviesList.setAdapter(movieAdapter);

        etTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    String title = etTitle.getText().toString();
                    if(title != null && !title.isEmpty()) {
                        firstFragmentViewModel.searchMovies(title);
                    }
                    else {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT);
                    }
                    return true;
                }
                return false;
            }
        });

    }


    private void displayData(MoviesList movies) {

        movieAdapter.updateData(movies.getSearch());
    }

}
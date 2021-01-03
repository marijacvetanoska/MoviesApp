package com.example.lab03.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab03.R;
import com.example.lab03.model.Movie;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private MovieClickListener movieClickListener;


    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(movies.get(position), movieClickListener);
    }

    @Override
    public int getItemCount() {
        if(movies == null)
            return 0;
        return movies.size();
    }

    public void updateData(List<Movie> movies){
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvYear;
        private ImageView movieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
        }

        public void bindData(Movie movie, final MovieClickListener listener) {
            tvTitle.setText(movie.getTitle());
            tvYear.setText(movie.getYear());
            Glide.with(itemView).load(movie.getPoster()).into(movieImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMovieClicked(movie.getImdbID());
                }
            });
        }
    }

    public interface MovieClickListener{
        void onMovieClicked(String id);
    }

    public void setMovieClickListener(MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }
}

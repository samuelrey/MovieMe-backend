package com.movie.me.android.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.me.android.R;
import com.movie.me.android.domain.Movie;

import java.io.InputStream;
import java.util.List;

/**
 * Created by hargueta on 10/27/16.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movieResultList;
    private LayoutInflater layoutInflater;

    public MovieListAdapter(Context context, List<Movie> movieResultList) {
        this.context = context;
        this.movieResultList = movieResultList;
        this.layoutInflater = LayoutInflater.from(this.context);
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieView = layoutInflater.inflate(R.layout.single_movie_result, parent, false);
        MovieViewHolder movieHolder = new MovieViewHolder(movieView);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currMovie = movieResultList.get(position);
        holder.setMoviePoster(currMovie.getPoster());
        holder.movieTitle.setText(currMovie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.moviePoster = (ImageView) itemView.findViewById(R.id.poster_image);
            this.movieTitle = (TextView) itemView.findViewById(R.id.title);
        }

        public void setMoviePoster(String posterURL) {
            new DownloadPosterTask(moviePoster).execute(posterURL);
        }
    }

    private class DownloadPosterTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadPosterTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap poster = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                poster = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return poster;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
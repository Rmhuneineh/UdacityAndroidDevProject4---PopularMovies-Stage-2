package com.example.android.popmovies;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by rmhuneineh on 13/03/2018.
 */

public class MovieCursorAdapter extends CursorRecyclerAdapter<MovieCursorAdapter.ViewHolder> {

    private static final String base_path = "https://image.tmdb.org/t/p/w342";

    private static final String data_key = "data";
    private static final String DATA = "DATA";

    private MainActivity activity = new MainActivity();

    public MovieCursorAdapter(MainActivity context, Cursor c) {
        super(context, c);
        this.activity = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView posterImage;

        public ViewHolder(View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.poster_image);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        int posterColumnIndex = cursor.getColumnIndex(FavoritesContract.FavoritesEntry.
                COLUMN_MOVIE_POSTER);

        String posterPath = cursor.getString(posterColumnIndex);

        Picasso.with(activity)
                .load(base_path + posterPath)
                .placeholder(R.drawable.fight_club_poster)
                .into(viewHolder.posterImage);

        viewHolder.posterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra(data_key, DATA);
                activity.startActivity(intent);
            }
        });
    }

}

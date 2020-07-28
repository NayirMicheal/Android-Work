package com.example.nayir.moviesapp.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.fragments.detailFragment;
import com.example.nayir.moviesapp.models.movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nayir on 4/26/2017.
 */

public class filmsAdapter extends RecyclerView.Adapter<filmsAdapter.ViewHolder> {

    private ArrayList<movie> movies = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context con;

    public filmsAdapter(Context context, ArrayList<movie> films) {
        this.mInflater = LayoutInflater.from(context);
        this.movies = films;
        con = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_view, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailFragment secondFragment = new detailFragment();
                Bundle args = new Bundle();
                args.putInt("position", viewHolder.getPosition());
                secondFragment.setArguments(args);
                if (con.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ((FragmentActivity) con).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.your_placeholder2, secondFragment) // replace flContainer
                            .commit();
                } else {
                    ((FragmentActivity) con).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.your_placeholder, secondFragment) // replace flContainer
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        movie mov = movies.get(position);
        String name = mov.getName();
        holder.nameTv.setText(name);
        String relasedDate = mov.getReleasedDate();
        holder.relasedDateTv.setText(relasedDate);
        String img_url = mov.getPosterPath();
        Picasso.with(con).load("http://image.tmdb.org/t/p/w185//" + img_url).resize(80, 80).into(holder.filmImageIv);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public void update(ArrayList<movie> list) {
        this.movies = list;
        this.notifyDataSetChanged();
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public TextView relasedDateTv;
        public ImageView filmImageIv;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name);
            relasedDateTv = (TextView) itemView.findViewById(R.id.relDate);
            filmImageIv = (ImageView) itemView.findViewById(R.id.imgview);
        }


    }
}

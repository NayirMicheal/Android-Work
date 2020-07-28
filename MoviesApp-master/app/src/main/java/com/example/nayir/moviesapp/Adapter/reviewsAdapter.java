package com.example.nayir.moviesapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.models.review;

import java.util.ArrayList;

/**
 * Created by nayir on 5/2/2017.
 */

public class reviewsAdapter extends RecyclerView.Adapter<reviewsAdapter.ViewHolder> {
    private ArrayList<review> reviews = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context con;

    public reviewsAdapter(Context context, ArrayList<review> reveiws) {
        con = context;
        this.mInflater = LayoutInflater.from(con);
        this.reviews = reveiws;

    }

    @Override
    public reviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.reviewitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(reviewsAdapter.ViewHolder holder, int position) {
        review rev = reviews.get(position);
        String aut = rev.getAUTHORMODEL().trim();
        holder.Author.setText(aut);
        String Overview = rev.getCONTENTMODEL().trim();
        holder.Description.setText(Overview);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void update(ArrayList<review> list) {
        this.reviews = list;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Author;
        public TextView Description;

        public ViewHolder(View itemView) {
            super(itemView);
            Author = (TextView) itemView.findViewById(R.id.aut);
            Description = (TextView) itemView.findViewById(R.id.des);
        }
    }
}

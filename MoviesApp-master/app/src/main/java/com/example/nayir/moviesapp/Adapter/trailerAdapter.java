package com.example.nayir.moviesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.models.trailers;

import java.util.ArrayList;


/**
 * Created by nayir on 5/3/2017.
 */

public class trailerAdapter extends RecyclerView.Adapter<trailerAdapter.ViewHolder> {
    private ArrayList<trailers> trailers = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context con;

    public trailerAdapter(Context context, ArrayList<trailers> trailers) {
        this.mInflater = LayoutInflater.from(context);
        this.trailers = trailers;
        con = context;
    }

    @Override
    public trailerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.traileritem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final trailerAdapter.ViewHolder holder, int position) {
        trailers trailer = this.trailers.get(position);
        final String name = trailer.getName();
        holder.trailerName.setText(name);
        final String key = trailer.getKey();
        holder.filmImageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=" + key;
                Intent n = new Intent(Intent.ACTION_VIEW);
                n.setData(Uri.parse(url));
                n.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                con.startActivity(n);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.trailers.size();
    }

    public void update(ArrayList<trailers> trails) {
        this.trailers = trails;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView filmImageIv;
        public TextView trailerName;

        public ViewHolder(View itemView) {
            super(itemView);
            trailerName = (TextView) itemView.findViewById(R.id.trailername);
            filmImageIv = (ImageView) itemView.findViewById(R.id.traileryoutube);
        }
    }
}

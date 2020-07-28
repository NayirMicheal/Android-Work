package com.example.nayir.moviesapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayir.moviesapp.Adapter.reviewsAdapter;
import com.example.nayir.moviesapp.Adapter.simpleDividerItemDecoration;
import com.example.nayir.moviesapp.Adapter.trailerAdapter;
import com.example.nayir.moviesapp.MoviesMVP.MoviesPresenter;
import com.example.nayir.moviesapp.MoviesMVP.MoviesView;
import com.example.nayir.moviesapp.MoviesMVP.PresenterImpl;
import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.TrailerandReviewsMvp.TRPresenterImpl;
import com.example.nayir.moviesapp.TrailerandReviewsMvp.TrailerReviewPresenter;
import com.example.nayir.moviesapp.TrailerandReviewsMvp.TrailerReviewView;
import com.example.nayir.moviesapp.models.movie;
import com.example.nayir.moviesapp.models.review;
import com.example.nayir.moviesapp.models.trailers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.nayir.moviesapp.R.id.reviews;

/**
 * Created by nayir on 4/26/2017.
 */

public class detailFragment extends Fragment implements TrailerReviewView, MoviesView.movieview {

    int FILMID;
    int DataBaseRealPosition;
    RecyclerView reviewsrecyclerview;
    reviewsAdapter rAdapter;
    trailerAdapter tAdapter;
    RecyclerView tRecyclerview;
    ArrayList<trailers> trailerForAdapter;
    TextView tvTitle;
    TextView tvDate;
    TextView tvOverView;
    ScrollView scroll;
    ImageView posterImage;
    MoviesPresenter presenterMovies;
    int position = 0;
    Button loadreview;
    ArrayList<review> reviewsForAdapter;
    TrailerReviewPresenter presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            if (getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_details, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tvTitle = (TextView) view.findViewById(R.id.name_detail);
        tvDate = (TextView) view.findViewById(R.id.relDate_detail);
        tvOverView = (TextView) view.findViewById(R.id.overview_detail);
        posterImage = (ImageView) view.findViewById(R.id.imgview_det);
        reviewsrecyclerview = (RecyclerView) view.findViewById(R.id.reviewrecycler);
        tRecyclerview = (RecyclerView) view.findViewById(R.id.trailerrecyclerview);
        loadreview = (Button) view.findViewById(reviews);
        scroll = (ScrollView) view.findViewById(R.id.scroller);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        tRecyclerview.setLayoutManager(layoutManager);

        reviewsrecyclerview.addItemDecoration(new simpleDividerItemDecoration(getActivity().getApplicationContext()));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        reviewsrecyclerview.setLayoutManager(mLayoutManager);

        reviewsForAdapter = new ArrayList<>();
        trailerForAdapter = new ArrayList<>();
        tAdapter = new trailerAdapter(getActivity().getApplicationContext(), trailerForAdapter);
        rAdapter = new reviewsAdapter(getActivity().getApplicationContext(), reviewsForAdapter);
        reviewsrecyclerview.setAdapter(rAdapter);
        tRecyclerview.setAdapter(tAdapter);
        DataBaseRealPosition = position + 1;
        presenter = new TRPresenterImpl(this);
        presenterMovies = new PresenterImpl(this);
        presenterMovies.getFilm(DataBaseRealPosition);
        loadreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewsrecyclerview.setVisibility(View.VISIBLE);
                presenter.getReviews(FILMID);
                scroll.fullScroll(View.FOCUS_DOWN);
            }
        });
    }


    @Override
    public void updateTrailers(ArrayList<trailers> trailerses) {
        tAdapter.update(trailerses);
    }

    @Override
    public void updateReviews(ArrayList<review> reviews) {
        rAdapter.update(reviews);
        scroll.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    public void sendToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void cantRetrivefilm() {
        tvTitle.setVisibility(View.GONE);
        tvDate.setVisibility(View.GONE);
        tvOverView.setVisibility(View.GONE);
        posterImage.setVisibility(View.GONE);

    }

    @Override
    public void updateDetailView(movie film) {
        FILMID = film.getId();
        tvTitle.setVisibility(View.VISIBLE);
        tvDate.setVisibility(View.VISIBLE);
        tvOverView.setVisibility(View.VISIBLE);
        posterImage.setVisibility(View.VISIBLE);
        tvTitle.setText(film.getName());
        tvDate.setText(film.getReleasedDate());
        tvOverView.setText(film.getOverview());
        Picasso.with(getActivity().getApplicationContext()).load("http://image.tmdb.org/t/p/w185//" + film.getPosterPath()).fit().centerInside().into(posterImage);
        presenter.getTrailers(FILMID);
    }
}


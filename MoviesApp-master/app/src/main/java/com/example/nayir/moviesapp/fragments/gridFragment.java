package com.example.nayir.moviesapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.nayir.moviesapp.Adapter.filmsAdapter;
import com.example.nayir.moviesapp.Adapter.simpleDividerItemDecoration;
import com.example.nayir.moviesapp.MoviesMVP.MoviesPresenter;
import com.example.nayir.moviesapp.MoviesMVP.MoviesView;
import com.example.nayir.moviesapp.MoviesMVP.PresenterImpl;
import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.models.movie;

import java.util.ArrayList;

import static com.example.nayir.moviesapp.MainActivity.bar;


/**
 * Created by nayir on 4/26/2017.
 */

public class gridFragment extends Fragment implements MoviesView {
    filmsAdapter Adapter;
    FragmentActivity listener;
    RecyclerView recyclerView;
    View emptyView;
    ScrollView scroll;
    private ArrayList<movie> movies;

    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gridview, parent, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movies = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        scroll = (ScrollView) getActivity().findViewById(R.id.scrollView);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recView);
        recyclerView.addItemDecoration(new simpleDividerItemDecoration(getActivity().getApplicationContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
        emptyView = getActivity().findViewById(R.id.empty_view);
        Adapter = new filmsAdapter(getActivity(), movies);
        recyclerView.setAdapter(Adapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        /////////////////////
        MoviesPresenter presenter = new PresenterImpl(this);
        presenter.getMovies();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void UpdateRecyclerView(ArrayList<movie> Movies) {
        scroll.setVisibility(View.VISIBLE);
        Adapter.update(Movies);
        bar.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void cantRetrieveFromOnline() {
        bar.setVisibility(View.GONE);
        scroll.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


}

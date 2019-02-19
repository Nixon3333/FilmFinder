package com.finder.filmfinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Objects.requireNonNull(getActivity()).setTitle("Fragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

// Create your sections with the list of data from your HashMap
        for (Map.Entry<Long, List<Film>> entry : MainActivity.filmMap.entrySet()) {
            MySection section = new MySection(entry.getKey(), entry.getValue());
            // add your section to the adapter
            sectionAdapter.addSection(section);

        }

// Set up your RecyclerView with the SectionedRecyclerViewAdapter
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(sectionAdapter);

        return view;
    }
}

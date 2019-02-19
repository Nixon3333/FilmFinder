package com.finder.filmfinder.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finder.filmfinder.AdapterAndVH.MySection;
import com.finder.filmfinder.MainActivity;
import com.finder.filmfinder.Pojo.Film;
import com.finder.filmfinder.R;

import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

        //Создаём заголовки по ключам Map
        for (Map.Entry<Long, List<Film>> entry : MainActivity.filmMap.entrySet()) {
            MySection section = new MySection(entry.getKey(), entry.getValue());
            // add your section to the adapter
            sectionAdapter.addSection(section);

        }

        //Устанавливаем адаптер для RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(sectionAdapter);

        return view;
    }
}

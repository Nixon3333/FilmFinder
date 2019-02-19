package com.finder.filmfinder.AdapterAndVH;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.finder.filmfinder.Pojo.Film;
import com.finder.filmfinder.R;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class MySection extends StatelessSection {

    private Long title;
    private List<Film> list;

    public MySection(Long title, List<Film> list) {
        // call constructor with layout resources for this Section header and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_item)
                .headerResourceId(R.layout.section_header)
                .build());

        this.title = title;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;

        //Биндим ItemView
        itemHolder.tvLocName.setText(list.get(position).getLocalizedName());
        itemHolder.tvName.setText(list.get(position).getName());
        itemHolder.tvRating.setText(String.valueOf(list.get(position).getRating()));

        if (itemHolder.tvRating.getText().equals("0.0"))
            itemHolder.tvRating.setText("N/A");

        itemHolder.url = list.get(position).getImageUrl();
        itemHolder.desc = list.get(position).getDescription();
        itemHolder.year = list.get(position).getYear();
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;

        //Биндим заголовок
        headerHolder.tvYear.setText(title.toString());
    }
}

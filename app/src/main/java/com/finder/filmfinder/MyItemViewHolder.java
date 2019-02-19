package com.finder.filmfinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyItemViewHolder extends RecyclerView.ViewHolder {

    TextView tvLocName;
    TextView tvName;
    TextView tvRating;

    public MyItemViewHolder(@NonNull View itemView) {

        super(itemView);
        tvLocName = itemView.findViewById(R.id.tvLocName);
        tvName = itemView.findViewById(R.id.tvName);
        tvRating = itemView.findViewById(R.id.tvRating);
    }
}

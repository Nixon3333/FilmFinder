package com.finder.filmfinder.AdapterAndVH;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.finder.filmfinder.R;

public class MyHeaderViewHolder extends RecyclerView.ViewHolder {

    TextView tvYear;

    public MyHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvYear = itemView.findViewById(R.id.tvYear);
    }
}

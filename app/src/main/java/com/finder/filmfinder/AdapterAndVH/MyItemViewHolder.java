package com.finder.filmfinder.AdapterAndVH;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.finder.filmfinder.Fragments.DetailFragment;
import com.finder.filmfinder.MainActivity;
import com.finder.filmfinder.R;

public class MyItemViewHolder extends RecyclerView.ViewHolder {

    TextView tvLocName;
    TextView tvName;
    TextView tvRating;
    String url;
    String desc;
    long year;

    MyItemViewHolder(@NonNull final View itemView) {

        super(itemView);
        tvLocName = itemView.findViewById(R.id.tvLocName);
        tvName = itemView.findViewById(R.id.tvName);
        tvRating = itemView.findViewById(R.id.tvRating);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", String.valueOf(tvName.getText()));
                bundle.putString("locName", String.valueOf(tvLocName.getText()));
                bundle.putString("urlImage", url);
                bundle.putString("rating", String.valueOf(tvRating.getText()));
                bundle.putString("year", String.valueOf(year));
                bundle.putString("desc", desc);


                fragment.setArguments(bundle);


                FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}

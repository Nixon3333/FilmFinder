package com.finder.filmfinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);

        TextView tvDetName = view.findViewById(R.id.tvDetName);
        TextView tvDetYear = view.findViewById(R.id.tvDetYear);
        TextView tvDetDesc = view.findViewById(R.id.tvDetDesc);
        TextView tvDetColorRating = view.findViewById(R.id.tvDetColorRating);

        Bundle bundle = this.getArguments();

        tvDetName.setText(bundle.getString("name"));
        tvDetColorRating.setText(bundle.getString("rating"));
        tvDetYear.setText("Год: " + bundle.getString("year"));
        tvDetDesc.setText(bundle.getString("desc"));

        Picasso.with(getContext())
                .load(bundle.getString("urlImage"))
                .placeholder(R.drawable.no_image)
                .into(imageView);

        return view;
    }


}

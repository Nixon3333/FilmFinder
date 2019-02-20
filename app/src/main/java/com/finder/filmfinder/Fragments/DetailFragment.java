package com.finder.filmfinder.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finder.filmfinder.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class DetailFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Включаем кнопку "назад"
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        TextView toolbar_title = Objects.requireNonNull(container).getRootView().findViewById(R.id.toolbar_title);

        //Центрируем заголовок с учётом кнопки
        Toolbar.LayoutParams llp = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        llp.setMargins(0, 0, 150, 0); // llp.setMargins(left, top, right, bottom);
        toolbar_title.setLayoutParams(llp);

        ImageView imageView = view.findViewById(R.id.imageView);

        TextView tvDetName = view.findViewById(R.id.tvDetName);
        TextView tvDetYear = view.findViewById(R.id.tvDetYear);
        TextView tvDetDesc = view.findViewById(R.id.tvDetDesc);
        TextView tvDetColorRating = view.findViewById(R.id.tvDetColorRating);

        tvDetDesc.setMovementMethod(new ScrollingMovementMethod());

        Bundle bundle = this.getArguments();

        assert bundle != null;
        tvDetName.setText(bundle.getString("name"));
        tvDetColorRating.setText(bundle.getString("rating"));

        if (tvDetColorRating.getText().equals("0.0"))
            tvDetColorRating.setText("N/A");

        tvDetYear.setText("Год: " + bundle.getString("year"));
        tvDetDesc.setText(bundle.getString("desc"));

        if (tvDetDesc.getText().equals(""))
            tvDetDesc.setText(R.string.no_desc);

        Picasso.with(getContext())
                .load(bundle.getString("urlImage"))
                .placeholder(R.drawable.no_image)
                .into(imageView);

        toolbar_title.setText(bundle.getString("locName"));

        return view;
    }


}

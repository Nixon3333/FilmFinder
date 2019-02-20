package com.finder.filmfinder;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.finder.filmfinder.API.FilmsAPI;
import com.finder.filmfinder.Fragments.MainFragment;
import com.finder.filmfinder.Pojo.Film;
import com.finder.filmfinder.Pojo.Films;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private boolean backPressedOnce = false;

    public static Map<Long, List<Film>> filmMap = new TreeMap<>();
    public static FragmentManager fragmentManager;
    public static Fragment DetFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText(R.string.title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (DetFragment == null)
            makeRequest();
    }

    //Делаем запрос
    private void makeRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FilmsAPI filmsAPI = retrofit.create(FilmsAPI.class);

        Call<Films> listFilms = filmsAPI.getFilms();

        listFilms.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(@NonNull Call<Films> call, @NonNull Response<Films> response) {
                if (response.isSuccessful()) {
                    Log.d("response", String.valueOf(response.body().getFilms()));
                    List<Film> responseList = response.body().getFilms();
                    Collections.sort(responseList);
                    filmMap = groupDataIntoTreeMap(responseList);
                    for (Map.Entry<Long, List<Film>> map : filmMap.entrySet()) {
                        Log.d("mapData", map.getKey().toString() + " : " + map.getValue());
                    }

                    switchFragment(new MainFragment());
                }
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Log.d("responseError", t.getMessage());
                Toast.makeText(getApplicationContext(), R.string.server_not_response, Toast.LENGTH_LONG).show();
            }
        });
    }

    //Меняем фрагмент
    public void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    //Группируем полученный ответ по годам
    private TreeMap<Long, List<Film>> groupDataIntoTreeMap(List<Film> listOfFilms) {

        TreeMap<Long, List<Film>> groupedTreeMap = new TreeMap<>();

        for (Film film : listOfFilms) {

            Long treeMapKey = film.getYear();

            if (groupedTreeMap.containsKey(treeMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedTreeMap.get(treeMapKey).add(film);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<Film> list = new ArrayList<>();
                list.add(film);
                groupedTreeMap.put(treeMapKey, list);
            }
        }
        return groupedTreeMap;
    }

    @Override
    public void onBackPressed() {

        //Выключаем кнопку "назад" в toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText(R.string.title);

        //Центрируем заголовок без кнопки
        Toolbar.LayoutParams llp = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        llp.setMargins(0, 0, 0, 0);
        toolbar_title.setLayoutParams(llp);
        toolbar_title.setGravity(Gravity.CENTER);

        //Двойное нажатие для выхода из приложения
        if (backPressedOnce) {
            super.onBackPressed();
            return;
        }

        if (DetFragment != null && DetFragment.isVisible()) {
            super.onBackPressed();
        } else {
            this.backPressedOnce = true;
            Toast.makeText(this, R.string.back_pressed_message, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    backPressedOnce = false;
                }
            }, 2000);
        }
    }


}

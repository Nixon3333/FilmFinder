package com.finder.filmfinder.API;

import com.finder.filmfinder.Pojo.Films;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsAPI {
    @GET("sequeniatesttask/films.json")
    Call<Films> getFilms();
}

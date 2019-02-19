package com.finder.filmfinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsAPI {
    @GET("sequeniatesttask/films.json")
    Call<Films> getFilms();
}

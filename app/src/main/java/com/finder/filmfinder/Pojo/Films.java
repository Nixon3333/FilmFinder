
package com.finder.filmfinder.Pojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Films {

    @SerializedName("films")

    private List<Film> films = null;

    public List<Film> getFilms() {
        return films;
    }

}

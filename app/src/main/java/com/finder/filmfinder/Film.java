
package com.finder.filmfinder;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Film implements Comparable<Film>{

    @SerializedName("id")
    private long id;

    @SerializedName("localized_name")
    private String localizedName;

    @SerializedName("name")
    private String name;

    @SerializedName("year")
    private int year;

    @SerializedName("rating")
    private float rating;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("description")
    private String description;

    public long getId() {
        return id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public String getName() {
        return name;
    }

    public long getYear() {
        return year;
    }

    public float getRating() {
        return rating;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "localizedName='" + localizedName + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int compareTo(@NonNull Film film) {
        return Float.compare(film.rating, this.rating);
    }
}

package com.fcai.ecinema;

import android.os.health.HealthStats;

public class Movie_Data {
    String name,url,cast,cost,seats,description,poster,rating,categories;

    public Movie_Data(String name, String url, String cast, String cost, String seats, String description, String poster, String rating, String categories) {
        this.name = name;
        this.url = url;
        this.cast = cast;
        this.cost = cost;
        this.seats = seats;
        this.description = description;
        this.poster = poster;
        this.rating = rating;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}

package com.fcai.ecinema;

public class movie_post {
    String title;
    String desc;
    String genre;
    String img;

    public movie_post(String title, String desc, String genre, String img) {
        this.title = title;
        this.desc = desc;
        this.genre = genre;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

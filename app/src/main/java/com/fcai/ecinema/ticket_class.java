package com.fcai.ecinema;
public class ticket_class {
    String movie_name;
    String movie_date;
    String seat;
    String random_order;
    String cinema_name;

    public ticket_class(String movie_name, String movie_date, String seat) {
        this.movie_name = movie_name;
        this.movie_date = movie_date;
        this.seat = seat;
        this.cinema_name="HANZO";
    }


    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }

    public String getRandom_order() {
        return random_order;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
    public void setRandom_order(String random_order) {
        this.random_order = random_order;
    }

    public String getSeat() {
        return seat;
    }
    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }
}

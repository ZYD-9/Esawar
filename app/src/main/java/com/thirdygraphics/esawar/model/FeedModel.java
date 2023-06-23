package com.thirdygraphics.esawar.model;

public class FeedModel {

    String date;
    String time;
    String name;
    String places;
    String description;

    int profile;
    int pic1;
    int pic2;
    int pic3;
    int pic4;
    Boolean isLike;

    public FeedModel(String date, String time, String name, String places, String description, int profile, int pic1, int pic2, int pic3, int pic4, Boolean isLike) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.places = places;
        this.description = description;
        this.profile = profile;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.pic4 = pic4;
        this.isLike = isLike;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getPlaces() {
        return places;
    }

    public int getProfile() {
        return profile;
    }

    public int getPic1() {
        return pic1;
    }

    public int getPic2() {
        return pic2;
    }

    public int getPic3() {
        return pic3;
    }

    public int getPic4() {
        return pic4;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}

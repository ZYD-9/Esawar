package com.thirdygraphics.esawar.model;

public class RecommendedModel {

    String id;
    int pic;
    String name;
    String place;

    public RecommendedModel(int pic, String name) {
        this.pic = pic;
        this.name = name;

    }

    public RecommendedModel(String id, int pic, String name, String place) {
        this.id = id;
        this.pic = pic;
        this.name = name;
        this.place = place;
    }


    public String getId() {
        return id;
    }

    public int getPic() {
        return pic;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}

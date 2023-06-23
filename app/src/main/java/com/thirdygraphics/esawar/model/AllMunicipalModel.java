package com.thirdygraphics.esawar.model;

public class AllMunicipalModel {

    String name;
    int logo;

    public AllMunicipalModel(int logo, String name) {
        this.logo = logo;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getLogo() {
        return logo;
    }
}

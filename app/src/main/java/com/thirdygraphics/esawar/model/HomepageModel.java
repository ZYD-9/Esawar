package com.thirdygraphics.esawar.model;

public class HomepageModel {
    int image;
    String categoryName;

    public HomepageModel(int image, String categoryName) {
        this.image = image;
        this.categoryName = categoryName;
    }

    public int getImage() {
        return image;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

package com.localfridge.localfridge;

/**
 * Created by solo on 1/15/18.
 */

public class Food {
    private int id, image;
    private String foodTitle, foodDesc, foodLoc, foodAuthor, foodEmail;

    public Food(int id, int image, String foodTitle, String foodDesc, String foodLoc, String foodAuthor, String foodEmail) {
        this.id = id;
        this.image = image;
        this.foodTitle = foodTitle;
        this.foodDesc = foodDesc;
        this.foodLoc = foodLoc;
        this.foodAuthor = foodAuthor;
        this.foodEmail = foodEmail;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public String getFoodLoc() {
        return foodLoc;
    }

    public String getFoodAuthor() {
        return foodAuthor;
    }

    public String getFoodEmail() {
        return foodEmail;
    }
}

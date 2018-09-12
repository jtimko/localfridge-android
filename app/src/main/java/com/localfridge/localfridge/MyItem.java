package com.localfridge.localfridge;

/**
 * Created by solo on 1/21/18.
 */

public class MyItem {
    private int m_id;// image;
    private String foodTitle, foodDesc;

    public MyItem(int m_id, String foodTitle, String foodDesc) {
        this.m_id = m_id;
        this.foodTitle = foodTitle;
        this.foodDesc = foodDesc;

    }

    public int getId() {
        return m_id;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    /*public String getFoodLoc() {
        return foodLoc;
    }

    public String getFoodAuthor() {
        return foodAuthor;
    }

    public String getFoodEmail() {
        return foodEmail;
    }*/
}



package com.example.cook_buddy;

import java.util.ArrayList;

public class RecipeItem {
    private String mName;
    private ArrayList<String> mItems;
    private Integer mCalories;

    public RecipeItem(String name, Integer calories) {
        mName = name;
        mCalories = calories;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<String> getItems() {
        return mItems;
    }

    public Integer getCalories() {
        return mCalories;
    }
}

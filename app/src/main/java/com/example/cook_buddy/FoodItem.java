package com.example.cook_buddy;

public class FoodItem {
    private String mName;
    private Integer mQuantity;
    private Integer mCalories;

    public FoodItem(String name, Integer quantity, Integer calories) {
        mName = name;
        mQuantity = quantity;
        mCalories = calories;
    }

    public String getName() {
        return mName;
    }

    public Integer getQuantity() {
        return mQuantity;
    }

    public Integer getCalories() {
        return mCalories;
    }
}
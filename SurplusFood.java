package com.example.rukyfood;

public class SurplusFood {
    public String id;
    public String foodType;
    public String quantity;
    public String location;

    public SurplusFood() {
        // Default constructor required for calls to DataSnapshot.getValue(SurplusFood.class)
    }

    public SurplusFood(String id, String foodType, String quantity, String location) {
        this.id = id;
        this.foodType = foodType;
        this.quantity = quantity;
        this.location = location;
    }
}

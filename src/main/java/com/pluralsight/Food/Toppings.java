package com.pluralsight.Food;

import java.util.List;

public class Toppings {
    private String name;
    private String category;
    private double price;
    private List<String> validFor; //If its valid for Burger, Pizza, Milkshake Nacho

    public Toppings(String name, String category, double price, List<String> validFor) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.validFor = validFor;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isValidFor(String itemType) {
        return validFor.contains(itemType);
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

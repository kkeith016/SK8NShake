package com.pluralsight.Toppings;

import java.util.List;

public class Topping {
    private String name;
    private String category;
    private String tier;
    private double basePrice;
    private List<String> validFor; //If its valid for Burger, Pizza, Milkshake Nacho

    public Topping(String name, String category, String tier, double basePrice, List<String> validFor) {
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.validFor = validFor;
        this.tier = tier;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getTier() { return tier; }
    public double getBasePrice() { return basePrice; }

    public boolean isValidFor(String itemType) {
        return validFor.contains(itemType);
    }

    @Override
    public String toString() {
        return name + " (" + tier + ")";
    }
}

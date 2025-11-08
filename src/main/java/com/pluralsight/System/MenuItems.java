package com.pluralsight.System;

import com.pluralsight.Toppings.Topping;

import java.util.List;
import java.util.ArrayList;

public abstract class MenuItems {
    public String name;
    protected String size;
    protected double basePrice;
    protected List<Topping> toppings;
    protected String notes;

    //----Constructor----

    public MenuItems(String name, String size, double price, String notes) {
        this.name = name;
        this.size = size;
        this.basePrice = price;
        this.toppings = new ArrayList<>();
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public String getNotes() {
        return notes;
    }

    public double calculatePrice(){
        double total = basePrice;
        int basicCount = 0;

        for (Topping topping : toppings) {
            if (topping.getTier().equalsIgnoreCase("Basic")) {
                basicCount++;
                if (basicCount > 3) {
                    total += 0.50; // charge for extra basics after 3
                }
            } else {
                total += topping.getBasePrice(); // premium, deluxe, vegan
            }
        }
        return total;
    }
    public String displayName() {
        return name; // safely exposes the item name for UI
    }

}



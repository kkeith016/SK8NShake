package com.pluralsight;

import com.pluralsight.Food.Topping;

import java.util.List;
import java.util.ArrayList;

public abstract class MenuItems {
    protected String name;
    protected String size;
    protected double basePrice;
    protected List<Topping> toppings;
    protected String notes;

    //----Constructor----

    public MenuItems(String name, String size, double price, List<Topping> toppings, String notes) {
        this.name = name;
        this.size = size;
        this.basePrice = price;
        this.toppings = new ArrayList<>();
        this.notes = notes;
    }

    /*
    Core Methods
   - CalculatePrice - Done
   - addTopping
   - removeTopping
     */

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
    // --- Add a topping ---
    public void addTopping(Topping topping) {
        if (topping != null) {
            toppings.add(topping);
            System.out.println("Added topping: " + topping.getName());
        } else {
            System.out.println("Invalid topping.");
        }
    }

    // --- Remove a topping by name ---
    public void removeTopping(String toppingName) {
        boolean removed = toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
        if (removed) {
            System.out.println("Removed topping: " + toppingName);
        } else {
            System.out.println("Topping not found: " + toppingName);
        }
    }

}

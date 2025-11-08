package com.pluralsight.Food;

import com.pluralsight.System.MenuItems;
import com.pluralsight.Options.Bread;
import com.pluralsight.System.Customizable;
import com.pluralsight.Toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class Burger extends MenuItems implements Customizable {

    private Bread bread;
    private final List<Topping> toppings; // Each burger can have its own topping list

    public Burger(String name, String size, double basePrice, Bread bread, String notes) {
        super(name, size, basePrice, notes);
        this.bread = bread;
        this.toppings = new ArrayList<>();
    }

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    @Override
    public double calculatePrice() {
        double total = basePrice + bread.getExtraCost();

        long basicCount = toppings.stream()
                .filter(t -> t.getTier().equalsIgnoreCase("Basic"))
                .count();

        // First 3 basics free, rest $0.50 each
        double extraBasicCost = Math.max(0, basicCount - 3) * 0.50;

        double premiumCost = toppings.stream()
                .filter(t -> !t.getTier().equalsIgnoreCase("Basic"))
                .mapToDouble(Topping::getBasePrice)
                .sum();

        return total + extraBasicCost + premiumCost;
    }

    // ---- Customizable interface methods ----

    @Override
    public void addTopping(Topping topping) {
        toppings.add(topping);
        System.out.println(topping.getName() + " added to your burger!");
    }

    @Override
    public void removeTopping(String toppingName) {
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
        System.out.println(toppingName + " removed from your burger.");
    }

    @Override
    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getSize() {
        return size;
    }
    public String toString() {
        return String.format(
                "Burger: %s (%s)\nBread: %s\nToppings: %s\nPrice: $%.2f\nNotes: %s",
                name, size, bread.getDisplayName(),
                toppings.isEmpty() ? "None" : toppings.stream().map(Topping::getName).toList(),
                calculatePrice(), notes
        );
    }
}
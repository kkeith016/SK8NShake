package com.pluralsight.System;

import com.pluralsight.Options.Size;
import com.pluralsight.Options.Topping;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuItems {
    protected String name;
    protected Size size;
    protected double basePrice;
    protected List<Topping> toppings;
    protected String notes;

    public MenuItems(String name, Size size, double basePrice, String notes) {
        this.name = name;
        this.size = size;
        this.basePrice = basePrice;
        this.notes = notes;
        this.toppings = new ArrayList<>();
    }

    public String getName() { return name; }
    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }
    public double getBasePrice() { return basePrice; }
    public List<Topping> getToppings() { return toppings; }
    public String getNotes() { return notes; }

    public double calculatePrice() {
        double total = basePrice;
        if (size != null) total += size.getPriceModifier();

        int basicCount = 0;
        for (Topping t : toppings) {
            if (t.getTier().equalsIgnoreCase("Basic")) {
                basicCount++;
                if (basicCount > 3) total += 0.50;
            } else total += t.getBasePrice();
        }
        return total;
    }

    public String displayName() { return name; }
}

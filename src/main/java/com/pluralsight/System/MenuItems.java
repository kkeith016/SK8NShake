package com.pluralsight.System;

import com.pluralsight.Options.Size;
import com.pluralsight.Options.Topping;
import com.pluralsight.Options.Protein;

import java.util.List;

public abstract class MenuItems {
    protected String name;
    protected Size size;
    protected double basePrice;
    protected String notes;

    public MenuItems(String name, Size size, double basePrice, String notes) {
        this.name = name;
        this.size = size;
        this.basePrice = basePrice;
        this.notes = notes;
    }

    public String getName() { return name; }
    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }
    public double getBasePrice() { return basePrice; }

    public abstract List<Topping> getToppings();
    public abstract List<Protein> getProteins();

    public double calculatePrice() {
        double total = basePrice;


        if (size != null) {
            total += size.getPriceModifier();
        }

        for (Topping t : getToppings()) {
            total += t.getBasePrice();
        }

        for (Protein p : getProteins()) {
            total += p.getBasePrice();
        }

        return total;
    }
}
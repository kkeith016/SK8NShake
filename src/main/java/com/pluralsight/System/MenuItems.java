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
    protected int extraBasicCount;      // How many basic toppings cost extra
    protected double extraBasicCharge;  // Total charge for extras
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

    public int getExtraBasicCount() { return extraBasicCount; }
    public double getExtraBasicCharge() { return extraBasicCharge; }

    public double calculatePrice() {
        double total = basePrice;
        extraBasicCount = 0;     // Reset each time the price is calculated
        extraBasicCharge = 0.0;

        if (size != null) total += size.getPriceModifier();

        int basicCount = 0;
        for (Topping t : toppings) {
            if (t.getTier().equalsIgnoreCase("Basic")) {
                basicCount++;
                // ✅ First 3 basic toppings are free
                if (basicCount > 3) {
                    total += 0.50;
                    extraBasicCount++;
                    extraBasicCharge += 0.50;
                }
            } else {
                // Premium toppings add their base price
                total += t.getBasePrice();
            }
        }

        return total;
    }

    public String displayName() {
        return name;
    }
}

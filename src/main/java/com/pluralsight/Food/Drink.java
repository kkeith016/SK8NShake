package com.pluralsight.Food;

import com.pluralsight.Options.Size;
import com.pluralsight.System.MenuItems;

public class Drink extends MenuItems {

    private String flavor;

    public Drink(String name, Size size, double basePrice, String flavor) {
        super(name, size, basePrice, "");

        if (flavor != null && !flavor.isBlank()) {
            this.flavor = flavor;
        } else {
            this.flavor = "Original";
        }
    }

    public String getFlavor() { return flavor; }
    public void setFlavor(String flavor) { this.flavor = flavor; }

    @Override
    public double calculatePrice() {
        return super.calculatePrice(); // size modifier included
    }

    public String preview() {
        String sizeName = "None";
        if (getSize() != null) {
            sizeName = getSize().getDisplayName();
        }

        return String.format(
                "------------------- DRINK PREVIEW -------------------\n" +
                        "Drink: %s (%s)\n" +
                        "Flavor: %s\n" +
                        "Price: $%.2f\n" +
                        "-----------------------------------------------------\n",
                getName(),
                sizeName,
                flavor,
                calculatePrice()
        );
    }

    @Override
    public String toString() {
        String sizeName = "None";
        if (getSize() != null) {
            sizeName = getSize().getDisplayName();
        }

        return String.format("%s (%s) - %s - $%.2f",
                getName(), sizeName, flavor, calculatePrice());
    }
}

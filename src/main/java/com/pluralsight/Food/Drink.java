package com.pluralsight.Food;

import com.pluralsight.Options.Size;
import com.pluralsight.System.MenuItems;

public class Drink extends MenuItems {

    private String flavor;

    public Drink(String name, Size size, double basePrice, String flavor) {
        super(name, size, basePrice, "");
        this.flavor = flavor != null && !flavor.isBlank() ? flavor : "Original";
    }

    public String getFlavor() { return flavor; }
    public void setFlavor(String flavor) { this.flavor = flavor; }

    @Override
    public double calculatePrice() {
        return super.calculatePrice(); // size modifier included
    }

    public String preview() {
        return String.format(
                "------------------- DRINK PREVIEW -------------------\n" +
                        "Drink: %s (%s)\n" +
                        "Flavor: %s\n" +
                        "Price: $%.2f\n" +
                        "-----------------------------------------------------\n",
                getName(),
                getSize().getDisplayName(),
                flavor,
                calculatePrice()
        );
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s - $%.2f",
                getName(), getSize().getDisplayName(), flavor, calculatePrice());
    }
}

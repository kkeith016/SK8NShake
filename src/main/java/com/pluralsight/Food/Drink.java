package com.pluralsight.Food;

import com.pluralsight.Options.Size;
import com.pluralsight.Options.Topping;
import com.pluralsight.Options.Protein;
import com.pluralsight.System.MenuItems;

import java.util.List;

public class Drink extends MenuItems {

    private final String flavor;

    public Drink(String name, Size size, double basePrice, String flavor) {
        super(name, size, basePrice, "");
        this.flavor = (flavor == null || flavor.isBlank()) ? "Original" : flavor;
    }

    public String getFlavor() {
        return flavor;
    }
    public void setFlavor(String flavor) {
    }

    @Override
    public List<Topping> getToppings() {
        return List.of();
    }

    @Override
    public List<Protein> getProteins() {
        return List.of();
    }
}

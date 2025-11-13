package com.pluralsight.Food;

import com.pluralsight.System.MenuItems;
import com.pluralsight.Options.Protein;
import com.pluralsight.Options.Topping;

import java.util.List;

public class Sides extends MenuItems {

    private final String tier;

    public Sides(String name, double basePrice, String tier) {
        super(name, null, basePrice, "Side");
        this.tier = tier;
    }

    public String getTier() {
        return tier;
    }

    @Override
    public double calculatePrice() {
        return basePrice;
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


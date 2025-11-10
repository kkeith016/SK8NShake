package com.pluralsight.Options;

import com.pluralsight.System.MenuItems;

public class Sides extends MenuItems {
    private final String tier;

    public Sides(String name, double basePrice, String tier) {
        super(name, null, basePrice, "Side");
        this.tier = tier;
    }

    public String getTier() {
        return tier;
    }
}

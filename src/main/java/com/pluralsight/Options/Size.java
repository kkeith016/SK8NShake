package com.pluralsight.Options;

public enum Size {
    SMALL("Small", 0.0),
    MEDIUM("Medium", 2.0),
    LARGE("Large", 4.0);

    private final String displayName;
    private final double priceModifier;

    // Constructor
    Size(String displayName, double priceModifier) {
        this.displayName = displayName;
        this.priceModifier = priceModifier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPriceModifier() {
        return priceModifier;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

package com.pluralsight.Options;

public enum Chips {
    CORNCHIPS("Classic Corn Chips", 0.0, "Basic"),
    LIMETORTILLA("Lime Tortilla", 2.0, "Deluxe"),
    DORITOS("Doritos", 1.0, "Premium");

    private final String displayName;
    private final double extraCost;
    private final String tier;

    // Constructor MUST match enum name
    Chips(String displayName, double extraCost, String tier) {
        this.displayName = displayName;
        this.extraCost = extraCost;
        this.tier = tier;
    }

    public String getDisplayName() { return displayName; }
    public double getExtraCost() { return extraCost; }
    public String getTier() { return tier; }
}

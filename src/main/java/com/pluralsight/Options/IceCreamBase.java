package com.pluralsight.Options;

public enum IceCreamBase {
    VANILLA("Vanilla", 0.0, "Basic"),
    CHOCOLATE("Chocolate", 0.0, "Basic"),
    SWIRL("Swirl", 0.0, "Basic");


    private final String displayName;
    private final double extraCost;
    private final String tier;

    // Constructor MUST match enum name
    IceCreamBase(String displayName, double extraCost, String tier) {
        this.displayName = displayName;
        this.extraCost = extraCost;
        this.tier = tier;
    }

    public String getDisplayName() { return displayName; }
    public double getExtraCost() { return extraCost; }
    public String getTier() { return tier; }
}

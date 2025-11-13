package com.pluralsight.Options;

public enum Bread {
    PITA("Pita Bread", 1.0, "Premium"),
    PRETZEL("Pretzel Roll", 2.0, "Deluxe"),
    BRIOCHE("Brioche Bun", 0.0, "Basic");

    private final String displayName;
    private final double extraCost;
    private final String tier;

    // Constructor MUST match enum name
    Bread(String displayName, double extraCost, String tier) {
        this.displayName = displayName;
        this.extraCost = extraCost;
        this.tier = tier;
    }

    public String getDisplayName() { return displayName; }
    public double getExtraCost() { return extraCost; }
}

package com.pluralsight.Options;

public enum Crusts {
    HANDTOSSED("Hand Tossed", 0.0, "Basic"),
    THIN("Thin", 1.0, "Premium"),
    STUFFED("Stuffed", 1.0, "Premium");

    private final String displayName;
    private final double extraCost;
    private final String tier;

    // Constructor MUST match enum name
    Crusts(String displayName, double extraCost, String tier) {
        this.displayName = displayName;
        this.extraCost = extraCost;
        this.tier = tier;
    }

    public String getDisplayName() { return displayName; }
    public double getExtraCost() { return extraCost; }
    public String getTier() { return tier; }
}
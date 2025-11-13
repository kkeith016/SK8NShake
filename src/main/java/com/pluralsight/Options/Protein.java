package com.pluralsight.Options;
    import java.util.List;

    public class Protein {
        private final String name;
        private final double basePrice;
        private final List<String> validFor; // Which menu items this protein is valid for

        public Protein(String name, double basePrice, List<String> validFor) {
            this.name = name;
            this.basePrice = basePrice;
            this.validFor = validFor;
        }

        public String getName() { return name; }
        public double getBasePrice() { return basePrice; }

        public boolean isValidFor(String itemType) {
            return validFor.contains(itemType);
        }
    }

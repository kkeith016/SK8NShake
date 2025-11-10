package com.pluralsight.Options;

import java.util.List;

public class ProteinLibrary {

    public static List<Protein> getAllProteins() {
        return List.of(
                new Protein("Beef Patty", 0.0, "Basic", List.of("Sandwich")),
                new Protein("Chicken Breast", 1.0, "Premium", List.of("Sandwich", "Nacho")),
                new Protein("Impossible Meat", 2.0, "Vegan", List.of("Sandwich")),
                new Protein("Brisket", 2.0, "Deluxe", List.of("Sandwich", "Nacho")),
                new Protein("Pulled Pork", 2.5, "Deluxe", List.of("Sandwich", "Nacho")),
                new Protein("Buffalo Chicken", 2.0, "Deluxe", List.of("Nacho")),
                new Protein("Grilled Steak", 3.0, "Deluxe", List.of("Nacho"))
        );
    }
}
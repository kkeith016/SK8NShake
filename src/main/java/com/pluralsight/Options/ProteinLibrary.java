package com.pluralsight.Options;

import java.util.List;

public class ProteinLibrary {

    public static List<Protein> getAllProteins() {
        return List.of(
                new Protein("Beef Patty", 0.0, "Basic", List.of("Burger")),
                new Protein("Chicken Breast", 1.0, "Premium", List.of("Burger", "Nacho")),
                new Protein("Impossible Meat", 2.0, "Vegan", List.of("Burger")),
                new Protein("Brisket", 2.0, "Deluxe", List.of("Burger", "Pizza")),
                new Protein("Extra Meat", 2.0, "Deluxe", List.of("Burger", "Pizza"))
        );
    }
}
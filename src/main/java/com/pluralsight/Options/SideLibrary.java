package com.pluralsight.Options;

import java.util.List;

public class SideLibrary {

    public static List<Sides> getAllSides() {
        return List.of(
                // BASIC SIDES
                new Sides("Fries", 2.50, "Basic"),
                new Sides("Onion Rings", 2.75, "Basic"),
                new Sides("Bag of Chips", 1.75, "Basic"),

                // PREMIUM SIDES
                new Sides("Mozzarella Sticks", 4.00, "Premium"),
                new Sides("Loaded Nacho Bites", 4.25, "Premium"),
                new Sides("Breadsticks with Marinara", 4.50, "Premium"),

                // DELUXE SIDES
                new Sides("Garlic Parmesan Fries", 5.50, "Deluxe"),
                new Sides("Mini Pretzel Bites w/ Cheese Dip", 5.75, "Deluxe")
        );
    }
}
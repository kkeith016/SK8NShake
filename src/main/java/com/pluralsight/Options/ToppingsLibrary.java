package com.pluralsight.Options;

import java.util.List;

public class ToppingsLibrary {

    public static List<Topping> getAllToppings() {
        return List.of(
                // === Basic ===
                new Topping("American Cheese", "Cheese", "Basic", 0.0, List.of("Sandwich", "Pizza")),
                new Topping("Cheddar", "Cheese", "Basic", 0.0, List.of("Sandwich", "Pizza")),
                new Topping("Fiesta Blend", "Cheese", "Basic", 0.0, List.of("Nacho")),
                new Topping("Lettuce", "Veggie", "Basic", 0.0, List.of("Sandwich", "Pizza")),
                new Topping("Tomato", "Veggie", "Basic", 0.0, List.of("Sandwich", "Pizza")),
                new Topping("Onion", "Veggie", "Basic", 0.0, List.of("Sandwich", "Pizza")),
                new Topping("Ketchup", "Sauce", "Basic", 0.0, List.of("Sandwich")),
                new Topping("Mustard", "Sauce", "Basic", 0.0, List.of("Sandwich")),
                new Topping("Alfredo", "Sauce", "Basic", 0.0, List.of("Pizza")),
                new Topping("Marinera", "Sauce", "Basic", 0.0, List.of("Pizza","Sandwich")),
                new Topping("Mayo", "Sauce", "Basic", 0.0, List.of("Sandwich")),
                new Topping("Miracle Whip", "Sauce", "Basic", 0.0, List.of("Sandwich")),
                new Topping("Pickles", "Veggie", "Basic", 0.0, List.of("Sandwich")),
                new Topping("Jalapeños", "Veggie", "Basic", 0.0, List.of("Sandwich", "Nacho", "Pizza")),
                new Topping("Olives", "Veggie", "Basic", 0.0, List.of("Pizza")),
                new Topping("BBQ Sauce", "Sauce", "Basic", 0.50, List.of("Sandwich", "Pizza")),
                new Topping("Ranch", "Sauce", "Basic", 0.50, List.of("Sandwich", "Pizza")),
                new Topping("Hot Sauce", "Sauce", "Basic", 0.0, List.of("Nacho")),
                new Topping("Whipped Cream", "Garnish", "Basic", 0.10, List.of("Milkshake")),
                new Topping("Cherry on Top", "Garnish", "Basic", 0, List.of("Milkshake")),
                new Topping("Sprinkles", "Garnish", "Basic", 0, List.of("Milkshake")),
                new Topping("Chocolate Syrup", "Sweet Sauce", "Basic", 0.10, List.of("Milkshake")),
                new Topping("Lettuce", "Veggie", "Basic", 0.0, List.of("Nacho")),
                new Topping("Tomatoes", "Veggie", "Basic", 0.0, List.of("Nacho")),
                new Topping("Bell Peppers", "Veggie", "Basic", 0.0, List.of("Nacho")),
                new Topping("Corn", "Veggie", "Basic", 0.0, List.of("Nacho")),
                new Topping("Red Onion", "Veggie", "Basic", 0.0, List.of("Nacho")),
                new Topping("Black Beans", "Veggie", "Premium", 0.0, List.of("Nacho")),

                // === Premium ===
                new Topping("Swiss", "Cheese", "Premium", 1.00, List.of("Sandwich", "Pizza")),
                new Topping("Pepper Jack", "Cheese", "Premium", 1.00, List.of("Sandwich", "Pizza")),
                new Topping("Mozzarella", "Cheese", "Basic", 0.0, List.of("Pizza", "Sandwich")),
                new Topping("Bacon", "Meat", "Premium", 1.00, List.of("Sandwich", "Pizza")),
                new Topping("Pepperoni", "Meat", "Premium", 1.00, List.of("Pizza")),
                new Topping("Chicken", "Meat", "Premium", 1.00, List.of("Pizza", "Nacho")),
                new Topping("Mushrooms", "Veggie", "Premium", 1.00, List.of("Pizza", "Sandwich")),
                new Topping("Sour Cream", "Sauce", "Premium", 1.00, List.of("Nacho")),
                new Topping("Guacamole", "Sauce", "Premium", 1.00, List.of("Nacho")),
                new Topping("Chocolate Chips", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Cookie Crumbles", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Brownie Bits", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Caramel Drizzle", "Sweet Sauce", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Peanut Butter Swirl", "Sweet Sauce", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Protein Boost", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Liquid Queso", "Cheese", "Premium", 1.00, List.of("Nacho")),

                // === Deluxe ===
                new Topping("Brisket", "Meat", "Deluxe", 2.00, List.of("Sandwich", "Pizza")),
                new Topping("Fudge", "Sweet Sauce", "Deluxe", 2.00, List.of("Milkshake")),
                new Topping("Combo Mix-Ins", "Mix-In", "Deluxe", 2.50, List.of("Milkshake")),
                new Topping("Extra Cheese", "Add-On", "Deluxe", 2.00, List.of("Sandwich", "Pizza","Nachos")),
                new Topping("Pulled Pork", "Meat", "Deluxe", 2.50, List.of("Sandwich", "Pizza","Nacho")),
                new Topping("Caramelized Onions", "Veggie", "Deluxe", 2.00, List.of("Sandwich", "Pizza")),
                new Topping("Extra Meat", "Add-On", "Deluxe", 2.00, List.of("Sandwich", "Pizza","Nacho")),
                new Topping("Nutella Drizzle", "Sweet Sauce", "Deluxe", 2.50, List.of("Milkshake")),
                new Topping("Daiya Cheese", "Cheese", "Deluxe", 1.00, List.of("Pizza", "Sandwich","Nachos")),
                new Topping("Impossible Meat Crumble", "Meat", "Deluxe", 2.00, List.of("Pizza","Nachos")),
                new Topping("Vegan Combo (Plant Based Cheese&Meat)", "Add On", "Vegan", 2.00, List.of("Sandwich", "Pizza","Nacho"))
        );
    }
}



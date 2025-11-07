package com.pluralsight.Food;
import java.util.Arrays;
import java.util.List;

public class ToppingsLibrary {

    public static List<Topping> getAllToppings() {
        return List.of(
                // === Basic (0 → 0.50 after 3) ===
                new Topping("American Cheese", "Cheese", "Basic", 0, List.of("Burger", "Pizza")),
                new Topping("Cheddar", "Cheese", "Basic", 0, List.of("Burger")),
                new Topping("Lettuce", "Veggie", "Basic", 0, List.of("Burger")),
                new Topping("Tomato", "Veggie", "Basic", 0, List.of("Burger")),
                new Topping("Onion", "Veggie", "Basic", 0, List.of("Burger")),
                new Topping("Pickles", "Veggie", "Basic", 0, List.of("Burger")),
                new Topping("Jalapeños", "Veggie", "Basic", 0, List.of("Burger", "Nacho")),
                new Topping("Olives", "Veggie", "Basic", 0, List.of("Pizza")),
                new Topping("BBQ Sauce", "Sauce", "Basic", 0, List.of("Burger", "Pizza")),
                new Topping("Ranch", "Sauce", "Basic", 0, List.of("Burger", "Pizza")),
                new Topping("Hot Sauce", "Sauce", "Basic", 0, List.of("Nacho")),
                new Topping("Whipped Cream", "Garnish", "Basic", 0, List.of("Milkshake")),
                new Topping("Cherry on Top", "Garnish", "Basic", 0, List.of("Milkshake")),
                new Topping("Sprinkles", "Garnish", "Basic", 0, List.of("Milkshake")),
                new Topping("Chocolate Syrup", "Sweet Sauce", "Basic", 0, List.of("Milkshake")),

                // === Premium (+$1) ===
                new Topping("Swiss", "Cheese", "Premium", 1.00, List.of("Burger")),
                new Topping("Pepper Jack", "Cheese", "Premium", 1.00, List.of("Burger")),
                new Topping("Mozzarella", "Cheese", "Premium", 1.00, List.of("Pizza")),
                new Topping("Bacon", "Meat", "Premium", 1.00, List.of("Burger")),
                new Topping("Pepperoni", "Meat", "Premium", 1.00, List.of("Pizza")),
                new Topping("Chicken", "Meat", "Premium", 1.00, List.of("Pizza", "Nacho")),
                new Topping("Mushrooms", "Veggie", "Premium", 1.00, List.of("Pizza", "Burger")),
                new Topping("Sour Cream", "Sauce", "Premium", 1.00, List.of("Nacho")),
                new Topping("Guacamole", "Sauce", "Premium", 1.00, List.of("Nacho")),
                new Topping("Chocolate Chips", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Cookie Crumbles", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Brownie Bits", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Caramel Drizzle", "Sweet Sauce", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Peanut Butter Swirl", "Sweet Sauce", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Fruit Add-In", "Mix-In", "Premium", 1.00, List.of("Milkshake")),
                new Topping("Protein Boost", "Mix-In", "Premium", 1.00, List.of("Milkshake")),

                // === Deluxe (+$2) ===
                new Topping("Brisket", "Meat", "Deluxe", 2.00, List.of("Burger", "Pizza")),
                new Topping("Fudge", "Sweet Sauce", "Deluxe", 2.00, List.of("Milkshake")),
                new Topping("Combo Mix-Ins", "Mix-In", "Deluxe", 2.50, List.of("Milkshake")),
                new Topping("Extra Meat", "Add-On", "Deluxe", 2.00, List.of("Burger", "Pizza")),
                new Topping("Extra Cheese", "Add-On", "Deluxe", 2.00, List.of("Burger", "Pizza")),

                // === Vegan Options ===
                new Topping("Daiya Cheese", "Vegan", "Vegan", 1.00, List.of("Pizza", "Burger")),
                new Topping("Impossible Meat", "Vegan", "Vegan", 2.00, List.of("Burger")),
                new Topping("Vegan Combo", "Vegan", "Vegan", 2.00, List.of("Burger", "Pizza"))
        );
    }
    // Optional helper — get toppings by category
    public static List<Topping> getToppingsByCategory(String category) {
        return getAllToppings().stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // Optional helper — get toppings valid for a specific menu item (e.g. “Burger”)
    public static List<Topping> getValidToppingsForItem(String itemType) {
        return getAllToppings().stream()
                .filter(t -> t.isValidFor(itemType))
                .toList();
    }
}



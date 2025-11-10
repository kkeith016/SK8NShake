package com.pluralsight.Options;

import com.pluralsight.Food.Drink;
import java.util.List;

public class DrinkLibrary{

    public static List<Drink> getAllDrinks() {
        return List.of(
                new Drink("Soda", Size.SMALL, 2.00, ""),
                new Drink("Soda", Size.MEDIUM, 2.50, ""),
                new Drink("Soda", Size.LARGE, 3.00, ""),

                new Drink("Lemonade", Size.SMALL, 2.50, ""),
                new Drink("Lemonade", Size.MEDIUM, 3.00, ""),
                new Drink("Lemonade", Size.LARGE, 3.50, ""),

                new Drink("Iced Tea", Size.SMALL, 2.25, ""),
                new Drink("Iced Tea", Size.MEDIUM, 2.75, ""),
                new Drink("Iced Tea", Size.LARGE, 3.25, ""),

                new Drink("Water", Size.SMALL, 0, ""),
                new Drink("Water", Size.MEDIUM, 0, ""),
                new Drink("Water", Size.LARGE, 0, "")
        );
    }

    public static List<String> getFlavorsForDrink(String drinkName) {
        return switch (drinkName) {
            case "Soda" -> List.of("Cola", "Orange", "Ginger Ale", "Root Beer");
            case "Lemonade" -> List.of("Classic", "Strawberry", "Peach");
            case "Iced Tea" -> List.of("Black", "Green", "Peach");
            case "Water" -> List.of("Add Lemon");
            default -> List.of("Original");
        };
    }
}

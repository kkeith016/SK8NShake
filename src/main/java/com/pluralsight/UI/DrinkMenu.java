package com.pluralsight.UI;

import com.pluralsight.Food.Drink;
import com.pluralsight.System.Cart;
import com.pluralsight.System.MenuHelper;
import com.pluralsight.Options.DrinkLibrary;

import java.util.List;
import java.util.Scanner;

public class DrinkMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== DRINK MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Show All Drinks
                2) Choose Drink & Add to Cart
                3) Return to Main Menu
                """ + UIColors.RESET);

            int choice = MenuHelper.getIntInput(scanner);

            switch (choice) {
                case 1 -> showAllDrinks();
                case 2 -> {
                    Drink selectedDrink = chooseDrinkAndSize();
                    if (selectedDrink != null) {
                        cart.addItem(selectedDrink);
                        System.out.println(UIColors.NEON_BLUE + selectedDrink.getName() +
                                " added to cart! Size: " + selectedDrink.getSize().getDisplayName() +
                                ", Flavor: " + selectedDrink.getFlavor() + UIColors.RESET);
                    }
                }
                case 3 -> running = false;
                default -> System.out.println(UIColors.NEON_PINK + "Invalid choice. Try again." + UIColors.RESET);
            }
        }
    }

    private static void showAllDrinks() {
        List<Drink> drinks = DrinkLibrary.getAllDrinks();

        System.out.println(UIColors.NEON_YELLOW + "\n🍹 AVAILABLE DRINKS 🍹" + UIColors.RESET);
        drinks.forEach(d -> System.out.printf("   %-20s (%s) $%.2f%n",
                d.getName(),
                d.getSize().getDisplayName(),
                d.getBasePrice()));
        System.out.println();
    }

    private static Drink chooseDrinkAndSize() {
        List<Drink> drinks = DrinkLibrary.getAllDrinks();

        System.out.println(UIColors.NEON_YELLOW + "\nSelect a Drink:" + UIColors.RESET);
        for (int i = 0; i < drinks.size(); i++) {
            Drink d = drinks.get(i);
            System.out.printf("%d) %-20s (%s) $%.2f%n", i + 1, d.getName(), d.getSize().getDisplayName(), d.getBasePrice());
        }

        int choice = MenuHelper.getIntInput(scanner);
        if (choice > 0 && choice <= drinks.size()) {
            Drink template = drinks.get(choice - 1);

            Drink selected = new Drink(template.getName(), template.getSize(), template.getBasePrice(), "Original");


            MenuHelper.chooseSize(selected, scanner);

            // Let user choose flavor if available
            List<String> flavors = DrinkLibrary.getFlavorsForDrink(selected.getName());
            if (!flavors.isEmpty()) {
                System.out.println("Select Flavor:");
                for (int i = 0; i < flavors.size(); i++) {
                    System.out.println((i + 1) + ") " + flavors.get(i));
                }
                int flavorChoice = MenuHelper.getIntInput(scanner);
                if (flavorChoice > 0 && flavorChoice <= flavors.size()) {
                    selected.setFlavor(flavors.get(flavorChoice - 1));
                }
            }

            // Preview
            System.out.println(UIColors.NEON_PINK + "------------------- DRINK PREVIEW -------------------" + UIColors.RESET);
            System.out.printf("%s (%s)\nFlavor: %s\nPrice: $%.2f%n",
                    selected.getName(),
                    selected.getSize().getDisplayName(),
                    selected.getFlavor(),
                    selected.calculatePrice());
            System.out.println(UIColors.NEON_PINK + "-----------------------------------------------------" + UIColors.RESET);

            return selected;
        } else {
            System.out.println(UIColors.NEON_PINK + "Invalid choice." + UIColors.RESET);
            return null;
        }
    }
}

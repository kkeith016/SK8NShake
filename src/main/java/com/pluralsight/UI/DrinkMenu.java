package com.pluralsight.UI;

import com.pluralsight.Food.Drink;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.DrinkLibrary;
import com.pluralsight.Options.Size;

import java.util.List;
import java.util.Scanner;

public class DrinkMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        List<String> drinkTypes = List.of("Soda", "Lemonade", "Iced Tea", "Water");
        System.out.println(UIColors.NEON_PINK + "===================== DRINK MENU =====================" + UIColors.RESET);

        for (int i = 0; i < drinkTypes.size(); i++) {
            System.out.println((i + 1) + ") " + drinkTypes.get(i));
        }
        System.out.println((drinkTypes.size() + 1) + ") Return to Main Menu");

        System.out.print(UIColors.NEON_GREEN + "Select a drink: " + UIColors.RESET);
        int drinkChoice = getIntInput();

        if (drinkChoice < 1 || drinkChoice > drinkTypes.size()) {
            System.out.println("Returning to Main Menu...");
            return;
        }

        String selectedDrink = drinkTypes.get(drinkChoice - 1);

        System.out.println("Select Size:");
        for (Size s : Size.values()) {
            System.out.println(s.ordinal() + 1 + ") " + s.getDisplayName() + " (+$" + s.getPriceModifier() + ")");
        }
        int sizeChoice = getIntInput();
        if (sizeChoice < 1 || sizeChoice > Size.values().length) {
            System.out.println("Invalid size. Returning to Main Menu...");
            return;
        }
        Size selectedSize = Size.values()[sizeChoice - 1];

        // Step 3: Select Flavor
        List<String> flavors = DrinkLibrary.getFlavorsForDrink(selectedDrink);
        System.out.println("Select Flavor:");
        for (int i = 0; i < flavors.size(); i++) {
            System.out.println((i + 1) + ") " + flavors.get(i));
        }
        int flavorChoice = getIntInput();
        if (flavorChoice < 1 || flavorChoice > flavors.size()) {
            System.out.println("Invalid flavor. Returning to Main Menu...");
            return;
        }
        String selectedFlavor = flavors.get(flavorChoice - 1);


        Drink drink = new Drink(selectedDrink, selectedSize, getBasePrice(selectedDrink, selectedSize), selectedFlavor);


        System.out.println(UIColors.NEON_PINK + "------------------- DRINK PREVIEW -------------------" + UIColors.RESET);
        System.out.printf("%s (%s)\nFlavor: %s\nPrice: $%.2f%n",
                drink.getName(), drink.getSize().getDisplayName(), drink.getFlavor(), drink.calculatePrice());
        System.out.println(UIColors.NEON_PINK + "-----------------------------------------------------" + UIColors.RESET);


        System.out.print("Add this drink to cart? (Y/N): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            cart.addItem(drink);
            System.out.println(UIColors.NEON_BLUE + "Drink added to cart!" + UIColors.RESET);
        } else {
            System.out.println("Drink not added.");
        }
    }

    private static double getBasePrice(String drinkName, Size size) {
        return DrinkLibrary.getAllDrinks().stream()
                .filter(d -> d.getName().equalsIgnoreCase(drinkName))
                .filter(d -> d.getSize() == size)
                .findFirst()
                .map(Drink::getBasePrice)
                .orElse(0.0);
    }

    private static int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("> ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[91mInvalid number. Try again.\u001B[0m");
            }
        }
    }
}
package com.pluralsight.UI;

import com.pluralsight.Food.Pizza;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.*;
import com.pluralsight.System.PromptDrink;

import java.util.List;
import java.util.Scanner;

public class PizzaMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        Pizza pizza = new Pizza("Custom Pizza", Size.SMALL, 7.00, Crusts.HANDTOSSED, "");

        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== PIZZA MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Choose Size
                2) Choose Crust
                3) Add Toppings
                4) Preview Pizza
                5) Add to Cart
                6) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = getIntInput();

            switch (choice) {
                case 1 -> chooseSize(pizza);
                case 2 -> chooseCrust(pizza);
                case 3 -> addToppings(pizza);
                case 4 -> previewPizza(pizza);
                case 5 -> {
                    cart.addItem(pizza);
                    System.out.println(UIColors.NEON_BLUE + "Pizza added to cart!" + UIColors.RESET);

                    // ✅ Ask user if they want a drink (same as SandwichMenu)
                    PromptDrink.askForDrink(cart);

                    running = false;
                }
                case 6 -> running = false;
                default -> System.out.println("\u001B[91mInvalid choice. Try again.\u001B[0m");
            }
        }
    }

    private static void chooseSize(Pizza pizza) {
        System.out.println("Select Size:");
        for (Size s : Size.values()) {
            System.out.println(s.ordinal() + 1 + ") " + s.getDisplayName());
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Size.values().length) {
            pizza.setSize(Size.values()[choice - 1]);
            System.out.println("Size set to " + pizza.getSize().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void chooseCrust(Pizza pizza) {
        System.out.println("Select Crust:");
        for (Crusts c : Crusts.values()) {
            System.out.println(c.ordinal() + 1 + ") " + c.getDisplayName() + " ($" + c.getExtraCost() + ")");
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Crusts.values().length) {
            pizza.setCrust(Crusts.values()[choice - 1]);
            System.out.println("Crust set to " + pizza.getCrust().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void addToppings(Pizza pizza) {
        List<Topping> validToppings = ToppingsLibrary.getAllToppings().stream()
                .filter(t -> t.isValidFor("Pizza"))
                .toList();

        List<String> categories = List.of("Cheese", "Veggie", "Meat", "Sauce", "Add-On", "Vegan");

        for (String category : categories) {
            List<Topping> catToppings = validToppings.stream()
                    .filter(t -> t.getCategory().equalsIgnoreCase(category))
                    .toList();
            if (catToppings.isEmpty()) continue;

            System.out.println(UIColors.NEON_YELLOW + "--- " + category + " ---" + UIColors.RESET);
            for (int i = 0; i < catToppings.size(); i++) {
                Topping t = catToppings.get(i);
                System.out.println((i + 1) + ") " + t.getName() + " ($" + t.getBasePrice() + ")");
            }

            System.out.println("Enter numbers separated by commas to add " + category + " (or press Enter to skip):");
            String input = scanner.nextLine();
            if (!input.isBlank()) {
                String[] parts = input.split(",");
                for (String part : parts) {
                    try {
                        int index = Integer.parseInt(part.trim()) - 1;
                        if (index >= 0 && index < catToppings.size()) {
                            pizza.addTopping(catToppings.get(index));
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        System.out.println(UIColors.NEON_BLUE + "Finished adding toppings!" + UIColors.RESET);
    }

    private static void previewPizza(Pizza pizza) {
        System.out.println(UIColors.NEON_PINK + "------------------- PIZZA PREVIEW -------------------" + UIColors.RESET);

        double mainPrice = pizza.getBasePrice() + pizza.getCrust().getExtraCost();
        System.out.printf("1x %s (%s) ............ $%.2f%n", pizza.getName(), pizza.getSize().getDisplayName(), mainPrice);
        System.out.println("Crust: " + pizza.getCrust().getDisplayName());

        for (Topping t : pizza.getToppings()) {
            System.out.printf("   + %-25s $%.2f%n", t.getName(), t.getBasePrice());
        }

        System.out.printf("Total Price: $%.2f%n", pizza.calculatePrice());
        System.out.println(UIColors.NEON_PINK + "-----------------------------------------------------" + UIColors.RESET);
    }

    private static int getIntInput() {
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
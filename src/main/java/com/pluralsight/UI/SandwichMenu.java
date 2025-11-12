package com.pluralsight.UI;

import com.pluralsight.Food.Sandwich;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.*;
import com.pluralsight.System.PromptDrink;

import java.util.List;
import java.util.Scanner;

public class SandwichMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        // Create a new sandwich with default values
        Sandwich sandwich = new Sandwich("Custom Sandwich", Size.SMALL, 5.00, Bread.PITA, "");

        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== SANDWICH MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Choose Size
                2) Choose Bread
                3) Choose Protein
                4) Add Toppings
                5) Preview Sandwich
                6) Add to Cart
                7) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = getIntInput();

            switch (choice) {
                case 1 -> chooseSize(sandwich);
                case 2 -> chooseBread(sandwich);
                case 3 -> chooseProtein(sandwich);
                case 4 -> addToppings(sandwich);
                case 5 -> previewSandwich(sandwich);
                case 6 -> {
                    cart.addItem(sandwich);
                    System.out.println(UIColors.NEON_BLUE + "Sandwich added to cart!" + UIColors.RESET);
                    // Ask user if they want a drink
                    PromptDrink.askForDrink(cart);
                    running = false;
                }
                case 7 -> running = false;
                default -> System.out.println("\u001B[91mInvalid choice. Try again.\u001B[0m");
            }
        }
    }

    private static void chooseSize(Sandwich sandwich) {
        System.out.println("Select Size:");
        for (Size s : Size.values()) {
            System.out.println(s.ordinal() + 1 + ") " + s.getDisplayName());
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Size.values().length) {
            sandwich.setSize(Size.values()[choice - 1]);
            System.out.println("Size set to " + sandwich.getSize().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void chooseBread(Sandwich sandwich) {
        System.out.println("Select Bread:");
        for (Bread b : Bread.values()) {
            System.out.println(b.ordinal() + 1 + ") " + b.getDisplayName() + " ($" + b.getExtraCost() + ")");
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Bread.values().length) {
            sandwich.setBread(Bread.values()[choice - 1]);
            System.out.println("Bread set to " + sandwich.getBread().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void chooseProtein(Sandwich sandwich) {
        System.out.println("Select Protein:");
        List<Protein> proteins = ProteinLibrary.getAllProteins().stream()
                .filter(p -> p.isValidFor("Sandwich"))
                .toList();

        for (int i = 0; i < proteins.size(); i++) {
            Protein p = proteins.get(i);
            System.out.println((i + 1) + ") " + p.getName() + " ($" + p.getBasePrice() + ")");
        }

        int choice = getIntInput();
        if (choice > 0 && choice <= proteins.size()) {
            Protein selected = proteins.get(choice - 1);
            sandwich.addTopping(new Topping(
                    selected.getName(),
                    "Meat",
                    selected.getTier(),
                    selected.getBasePrice(),
                    List.of("Sandwich")
            ));
            System.out.println("Protein set to " + selected.getName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void addToppings(Sandwich sandwich) {
        List<Topping> validToppings = ToppingsLibrary.getAllToppings().stream()
                .filter(t -> t.isValidFor("Sandwich"))
                .toList();

        List<String> categories = List.of("Cheese", "Veggie", "Sauce", "Add-On", "Vegan", "Mix-In");

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
                            sandwich.addTopping(catToppings.get(index));
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        System.out.println(UIColors.NEON_BLUE + "Finished adding toppings!" + UIColors.RESET);
    }

    private static void previewSandwich(Sandwich sandwich) {
        System.out.println(UIColors.NEON_PINK + "------------------- SANDWICH PREVIEW -------------------" + UIColors.RESET);

        double mainPrice = sandwich.getBasePrice() + sandwich.getBread().getExtraCost();
        System.out.printf("1x %s (%s) ............ $%.2f%n", sandwich.getName(), sandwich.getSize().getDisplayName(), mainPrice);
        System.out.println("Bread: " + sandwich.getBread().getDisplayName());

        for (Topping t : sandwich.getToppings()) {
            System.out.printf("   + %-25s $%.2f%n", t.getName(), t.getBasePrice());
        }

        System.out.printf("Total Price: $%.2f%n", sandwich.calculatePrice());
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

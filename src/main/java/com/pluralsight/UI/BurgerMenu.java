package com.pluralsight.UI;

import com.pluralsight.Food.Burger;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.*;

import java.util.List;
import java.util.Scanner;

public class BurgerMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        // Create a new burger with default values
        Burger burger = new Burger("Custom Burger", Size.SMALL, 5.00, Bread.PITA, "");

        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== BURGER MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Choose Size
                2) Choose Bread
                3) Choose Protein
                4) Add Toppings
                5) Preview Burger
                6) Add to Cart
                7) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = getIntInput();

            switch (choice) {
                case 1 -> chooseSize(burger);
                case 2 -> chooseBread(burger);
                case 3 -> chooseProtein(burger);
                case 4 -> addToppings(burger);
                case 5 -> previewBurger(burger);
                case 6 -> {
                    cart.addItem(burger);
                    System.out.println(UIColors.NEON_BLUE + "Burger added to cart!" + UIColors.RESET);
                    running = false;
                }
                case 7 -> running = false;
                default -> System.out.println("\u001B[91mInvalid choice. Try again.\u001B[0m");
            }
        }
    }

    private static void chooseSize(Burger burger) {
        System.out.println("Select Size:");
        for (Size s : Size.values()) {
            System.out.println(s.ordinal() + 1 + ") " + s.name());
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Size.values().length) {
            burger.setSize(String.valueOf(Size.values()[choice - 1]));
            System.out.println("Size set to " + burger.getSize());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void chooseBread(Burger burger) {
        System.out.println("Select Bread:");
        for (Bread b : Bread.values()) {
            System.out.println(b.ordinal() + 1 + ") " + b.getDisplayName() + " ($" + b.getExtraCost() + ")");
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Bread.values().length) {
            burger.setBread(Bread.values()[choice - 1]);
            System.out.println("Bread set to " + burger.getBread().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void chooseProtein(Burger burger) {
        System.out.println("Select Protein:");
        List<Protein> proteins = ProteinLibrary.getAllProteins().stream()
                .filter(p -> p.isValidFor("Burger"))
                .toList();
        for (int i = 0; i < proteins.size(); i++) {
            Protein p = proteins.get(i);
            System.out.println((i + 1) + ") " + p.getName() + " ($" + p.getBasePrice() + ")");
        }

        int choice = getIntInput();
        if (choice > 0 && choice <= proteins.size()) {
            Protein selected = proteins.get(choice - 1);
            burger.addTopping(new Topping(selected.getName(), "Meat", selected.getTier(), selected.getBasePrice(), List.of("Burger")));
            System.out.println("Protein set to " + selected.getName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void addToppings(Burger burger) {

        List<Topping> validToppings = ToppingsLibrary.getAllToppings().stream()
                .filter(t -> t.isValidFor("Burger"))
                .toList();

        // Group toppings by category
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
                            burger.addTopping(catToppings.get(index));
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        System.out.println(UIColors.NEON_BLUE + "Finished adding toppings!" + UIColors.RESET);
    }

    private static void previewBurger(Burger burger) {
        System.out.println(UIColors.NEON_PINK + "------------------- SANDWICH PREVIEW -------------------" + UIColors.RESET);

        double mainPrice = burger.getBasePrice() + burger.getBread().getExtraCost();
        System.out.printf("1x %s (%s) ............ $%.2f%n", burger.getName(), burger.getSize(), mainPrice);
        System.out.println("Bread: " + burger.getBread().getDisplayName());

        // Print toppings with individual prices
        for (Topping t : burger.getToppings()) {
            System.out.printf("   + %-25s $%.2f%n", t.getName(), t.getBasePrice());
        }

        System.out.printf("Total Price: $%.2f%n", burger.calculatePrice());
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

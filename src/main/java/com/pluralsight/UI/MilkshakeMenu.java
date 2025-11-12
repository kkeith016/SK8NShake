package com.pluralsight.UI;

import com.pluralsight.Food.Milkshake;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.*;

import java.util.List;
import java.util.Scanner;

public class MilkshakeMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        Milkshake milkshake = new Milkshake("Custom Milkshake", Size.SMALL, 4.00);

        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== MILKSHAKE MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Choose Size
                2) Choose Ice Cream Base
                3) Add Toppings
                4) Preview Milkshake
                5) Add to Cart
                6) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = getIntInput();

            switch (choice) {
                case 1 -> chooseSize(milkshake);
                case 2 -> chooseBase(milkshake);
                case 3 -> addToppings(milkshake);
                case 4 -> previewMilkshake(milkshake);
                case 5 -> {
                    cart.addItem(milkshake);
                    System.out.println(UIColors.NEON_BLUE + "Milkshake added to cart!" + UIColors.RESET);
                    running = false;
                }
                case 6 -> running = false;
                default -> System.out.println("\u001B[91mInvalid choice. Try again.\u001B[0m");
            }
        }
    }

    private static void chooseSize(Milkshake milkshake) {
        System.out.println("Select Size:");
        for (Size s : Size.values()) {
            System.out.println(s.ordinal() + 1 + ") " + s.getDisplayName());
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Size.values().length) {
            milkshake.setSize(Size.values()[choice - 1]);
            System.out.println("Size set to " + milkshake.getSize().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void chooseBase(Milkshake milkshake) {
        System.out.println("Select Ice Cream Base:");
        IceCreamBase[] bases = IceCreamBase.values();
        for (int i = 0; i < bases.length; i++) {
            IceCreamBase b = bases[i];
            System.out.printf("%d) %s (%s) $%.2f%n", i + 1, b.getDisplayName(), b.getTier(), b.getExtraCost());
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= bases.length) {
            milkshake.setFlavor(bases[choice - 1].getDisplayName());
            System.out.println("Base flavor set to " + milkshake.getFlavor());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void addToppings(Milkshake milkshake) {
        List<Topping> validToppings = ToppingsLibrary.getAllToppings().stream()
                .filter(t -> t.isValidFor("Milkshake"))
                .toList();

        List<String> categories = List.of("Garnish", "Mix-In", "Sweet Sauce", "Premium", "Deluxe");

        for (String category : categories) {
            List<Topping> catToppings = validToppings.stream()
                    .filter(t -> t.getCategory().equalsIgnoreCase(category) || t.getTier().equalsIgnoreCase(category))
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
                            milkshake.addTopping(catToppings.get(index));
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        System.out.println(UIColors.NEON_BLUE + "Finished adding toppings!" + UIColors.RESET);
    }

    private static void previewMilkshake(Milkshake milkshake) {
        System.out.println(UIColors.NEON_PINK + "------------------- MILKSHAKE PREVIEW -------------------" + UIColors.RESET);

        System.out.printf("1x %s (%s) ............ $%.2f%n",
                milkshake.getName(),
                milkshake.getSize() != null ? milkshake.getSize().getDisplayName() : "None",
                milkshake.calculatePrice());

        System.out.println("Base Flavor: " + milkshake.getFlavor());

        for (Topping t : milkshake.getToppings()) {
            System.out.printf("   + %-25s $%.2f%n", t.getName(), t.getBasePrice());
        }

        System.out.printf("Total Price: $%.2f%n", milkshake.calculatePrice());
        System.out.println(UIColors.NEON_PINK + "--------------------------------------------------------" + UIColors.RESET);
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


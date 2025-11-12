package com.pluralsight.UI;

import com.pluralsight.Food.Nachos;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.*;
import com.pluralsight.System.PromptDrink;

import java.util.List;
import java.util.Scanner;

public class NachoMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        Nachos nachos = new Nachos("Custom Nachos", Size.SMALL, 6.00, Chips.CORNCHIPS, "");

        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== NACHO MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Choose Size
                2) Choose Chips
                3) Add Protein
                4) Preview Nachos
                5) Add to Cart
                6) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = getIntInput();

            switch (choice) {
                case 1 -> chooseSize(nachos);
                case 2 -> chooseChips(nachos);
                case 3 -> addProtein(nachos);
                case 4 -> previewNachos(nachos);
                case 5 -> {
                    cart.addItem(nachos);
                    System.out.println(UIColors.NEON_BLUE + "Nachos added to cart!" + UIColors.RESET);

                    // ✅ Ask if user wants a drink (matches Sandwich and Pizza)
                    PromptDrink.askForDrink(cart);

                    running = false;
                }
                case 6 -> running = false;
                default -> System.out.println("\u001B[91mInvalid choice. Try again.\u001B[0m");
            }
        }
    }

    private static void chooseSize(Nachos nachos) {
        System.out.println("Select Size:");
        for (Size s : Size.values()) {
            System.out.println(s.ordinal() + 1 + ") " + s.getDisplayName());
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Size.values().length) {
            nachos.setSize(Size.values()[choice - 1]);
            System.out.println("Size set to " + nachos.getSize().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void chooseChips(Nachos nachos) {
        System.out.println("Select Chips:");
        for (Chips c : Chips.values()) {
            System.out.println(c.ordinal() + 1 + ") " + c.getDisplayName() + " ($" + c.getExtraCost() + ")");
        }
        int choice = getIntInput();
        if (choice > 0 && choice <= Chips.values().length) {
            nachos.setChips(Chips.values()[choice - 1]);
            System.out.println("Chips set to " + nachos.getChips().getDisplayName());
        } else {
            System.out.println("\u001B[91mInvalid choice.\u001B[0m");
        }
    }

    private static void addProtein(Nachos nachos) {
        System.out.println("Select Protein:");
        List<Protein> proteins = ProteinLibrary.getAllProteins().stream()
                .filter(p -> p.isValidFor("Nacho"))
                .toList();

        for (int i = 0; i < proteins.size(); i++) {
            Protein p = proteins.get(i);
            System.out.println((i + 1) + ") " + p.getName() + " ($" + p.getBasePrice() + ")");
        }

        System.out.println("Enter numbers separated by commas to add proteins (or press Enter to skip):");
        String input = scanner.nextLine();
        if (!input.isBlank()) {
            String[] parts = input.split(",");
            for (String part : parts) {
                try {
                    int index = Integer.parseInt(part.trim()) - 1;
                    if (index >= 0 && index < proteins.size()) {
                        nachos.addProtein(proteins.get(index));
                    }
                } catch (NumberFormatException ignored) {}
            }
        }

        System.out.println(UIColors.NEON_BLUE + "Finished adding proteins!" + UIColors.RESET);
    }

    private static void previewNachos(Nachos nachos) {
        System.out.println(UIColors.NEON_PINK + "------------------- NACHO PREVIEW -------------------" + UIColors.RESET);

        double mainPrice = nachos.getBasePrice() + nachos.getChips().getExtraCost();
        System.out.printf("1x %s (%s) ............ $%.2f%n", nachos.getName(), nachos.getSize().getDisplayName(), mainPrice);
        System.out.println("Chips: " + nachos.getChips().getDisplayName());

        for (Protein p : nachos.getProteins()) {
            System.out.printf("   + %-25s $%.2f%n", p.getName(), p.getBasePrice());
        }

        System.out.printf("Total Price: $%.2f%n", nachos.calculatePrice());
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
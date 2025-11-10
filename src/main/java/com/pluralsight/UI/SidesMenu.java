package com.pluralsight.UI;

import com.pluralsight.Options.Sides;
import com.pluralsight.Options.SideLibrary;
import com.pluralsight.System.Cart;
import com.pluralsight.System.MenuItems;

import java.util.List;
import java.util.Scanner;

public class SidesMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== SIDE MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) View All Sides
                2) Add Side to Cart
                3) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = getIntInput();

            switch (choice) {
                case 1 -> showAllSides();
                case 2 -> addSideToCart(cart);
                case 3 -> running = false;
                default -> System.out.println(UIColors.NEON_PINK + "Invalid choice. Try again." + UIColors.RESET);
            }
        }
    }

    private static void showAllSides() {
        List<Sides> sides = SideLibrary.getAllSides();

        System.out.println(UIColors.NEON_YELLOW + "\n🍟 BASIC SIDES 🍟" + UIColors.RESET);
        sides.stream()
                .filter(s -> s.getTier().equalsIgnoreCase("Basic"))
                .forEach(s -> System.out.printf("   %-35s $%.2f%n", s.getName(), s.getBasePrice()));

        System.out.println(UIColors.NEON_BLUE + "\n🧀 PREMIUM SIDES 🧀" + UIColors.RESET);
        sides.stream()
                .filter(s -> s.getTier().equalsIgnoreCase("Premium"))
                .forEach(s -> System.out.printf("   %-35s $%.2f%n", s.getName(), s.getBasePrice()));

        System.out.println(UIColors.NEON_PINK + "\n🔥 DELUXE SIDES 🔥" + UIColors.RESET);
        sides.stream()
                .filter(s -> s.getTier().equalsIgnoreCase("Deluxe"))
                .forEach(s -> System.out.printf("   %-35s $%.2f%n", s.getName(), s.getBasePrice()));

        System.out.println();
    }

    private static void addSideToCart(Cart cart) {
        List<Sides> sides = SideLibrary.getAllSides();

        System.out.println(UIColors.NEON_YELLOW + "\nChoose a Side to Add:" + UIColors.RESET);
        for (int i = 0; i < sides.size(); i++) {
            Sides s = sides.get(i);
            System.out.printf("%d) %-30s $%.2f (%s)%n", i + 1, s.getName(), s.getBasePrice(), s.getTier());
        }

        int choice = getIntInput();
        if (choice > 0 && choice <= sides.size()) {
            Sides selected = sides.get(choice - 1);
            cart.addItem(selected);
            System.out.println(UIColors.NEON_BLUE + selected.getName() + " added to cart!" + UIColors.RESET);

            // 🥤 Offer a drink after side added
            PromptDrink.askForDrink(cart);
        } else {
            System.out.println(UIColors.NEON_PINK + "Invalid choice. Try again." + UIColors.RESET);
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                System.out.print(UIColors.NEON_GREEN + "> " + UIColors.RESET);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(UIColors.NEON_PINK + "Invalid number. Try again." + UIColors.RESET);
            }
        }
    }
}

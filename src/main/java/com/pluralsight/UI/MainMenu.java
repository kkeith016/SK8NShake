package com.pluralsight.UI;

import com.pluralsight.System.Cart;

import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMainMenu(Cart cart) {
        int choice;

        do {
            System.out.println(UIColors.NEON_PINK + "===========================================================" + UIColors.RESET);
            System.out.println(UIColors.NEON_PINK + "                   WELCOME TO SK8 N’ SHAKE" + UIColors.RESET);
            System.out.println(UIColors.NEON_PINK + "===========================================================" + UIColors.RESET);

            System.out.println(UIColors.NEON_BLUE + "             Slide into flavor with our signature picks!\n" + UIColors.RESET);

            System.out.println(UIColors.NEON_YELLOW + """
                1) Sandwich
                2) Nacho
                3) Pizza
                4) Drinks
                5) Milkshake
                6) Sides
                7) Neon Hits
                8) Check Out
                9) Exit
                """ + UIColors.RESET);

            System.out.println(UIColors.NEON_BLUE + "-----------------------------------------------------------" + UIColors.RESET);
            System.out.print(UIColors.NEON_GREEN + "Please enter your choice (1–9): " + UIColors.RESET);

            choice = getIntInput();

            switch (choice) {
                case 1 -> SandwichMenu.display(cart);
                case 2 -> NachoMenu.display(cart);
                case 3 -> PizzaMenu.display(cart);
                case 4 -> DrinkMenu.display(cart);
                case 5 -> MilkshakeMenu.display(cart);
                case 6 -> SidesMenu.display(cart);
                case 7 -> NeonHitsMenu.display();
                case 8 -> CheckoutMenu.display(cart);
                case 9 -> System.out.println(UIColors.NEON_PINK + "Thank you for visiting SK8 N’ SHAKE!" + UIColors.RESET);
                default -> System.out.println(UIColors.NEON_RED + "Invalid choice. Please enter 1–9." + UIColors.RESET);

            }

        } while (choice != 9);
    }

    private static int getIntInput() {
        while (true) {
            try {
                System.out.print("> ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(UIColors.NEON_RED + "Please enter a valid number." + UIColors.RESET);
            }
        }
    }
}

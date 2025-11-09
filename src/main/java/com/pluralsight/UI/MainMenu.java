package com.pluralsight.UI;

import com.pluralsight.System.Cart;

import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMainMenu() {
        Cart cart = new Cart(); // shared cart instance
        int choice;

        do {
            System.out.println(UIColors.NEON_PINK + "===========================================================" + UIColors.RESET);
            System.out.println(UIColors.NEON_PINK + "                     WELCOME TO SK8 N’ SHAKE" + UIColors.RESET);
            System.out.println(UIColors.NEON_PINK + "===========================================================" + UIColors.RESET);

            System.out.println(UIColors.NEON_BLUE + "             Slide into flavor with our signature picks!\n" + UIColors.RESET);

            System.out.println(UIColors.NEON_YELLOW + """
                1) Sandwich Menu
                2) Nacho Menu
                3) Pizza Menu
                4) Milkshake Menu
                5) Sides
                6) Neon Hits (Specials)
                7) Check Out
                8) Exit
                """ + UIColors.RESET);

            System.out.println(UIColors.NEON_BLUE + "-----------------------------------------------------------" + UIColors.RESET);
            System.out.print(UIColors.NEON_GREEN + "Please enter your choice (1–8): " + UIColors.RESET);

            choice = getIntInput();

            switch (choice) {
                case 1 -> BurgerMenu.display(cart);
                case 2 -> NachoMenu.display();
                case 3 -> PizzaMenu.display();
                case 4 -> MilkshakeMenu.display();
                case 5 -> SidesMenu.display();
                case 6 -> NeonHitsMenu.display();
                case 7 -> CheckoutMenu.display(cart);
                case 8 -> System.out.println(UIColors.NEON_PINK + "Thank you for visiting SK8 N’ SHAKE!" + UIColors.RESET);
                default -> System.out.println("\u001B[91mInvalid choice. Please enter 1–8.\u001B[0m"); // red text for errors
            }

        } while (choice != 8);
    }

    private static int getIntInput() {
        while (true) {
            try {
                System.out.print("> ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[91mPlease enter a valid number.\u001B[0m"); // red text
            }
        }
    }
}

package com.pluralsight.UI;

import com.pluralsight.System.Cart;

import java.util.Scanner;


public class PromptDrink {

        private static final Scanner scanner = new Scanner(System.in);

        public static void askForDrink(Cart cart) {
            System.out.print("Would you like to add a drink to your order? (Y/N): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                DrinkMenu.display(cart);
            } else {
                System.out.println("No drink added.");
            }
        }
    }

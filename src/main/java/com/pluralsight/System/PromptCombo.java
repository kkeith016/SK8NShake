package com.pluralsight.System;

import com.pluralsight.UI.DrinkMenu;
import com.pluralsight.UI.SidesMenu;

import java.util.Scanner;

public class PromptCombo {

    private static final Scanner scanner = new Scanner(System.in);

    public static void askForCombo(Cart cart) {
        System.out.print("Would you like to add a drink to your order? (Y/N): ");
        String drinkInput = scanner.nextLine().trim().toLowerCase();
        if (drinkInput.equals("y") || drinkInput.equals("yes")) {
            DrinkMenu.display(cart);
        } else {
            System.out.println("No drink added.");
        }

        System.out.print("Would you like to add a side to your order? (Y/N): ");
        String sideInput = scanner.nextLine().trim().toLowerCase();
        if (sideInput.equals("y") || sideInput.equals("yes")) {
            SidesMenu.display(cart);
        } else {
            System.out.println("No side added.");
        }
    }
}

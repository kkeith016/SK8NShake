package com.pluralsight.UI;

import com.pluralsight.Food.Milkshake;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.*;
import com.pluralsight.System.MenuHelper;

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
                4) Remove Toppings
                5) Preview Milkshake
                6) Add to Cart
                7) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = MenuHelper.getIntInput(scanner);

            switch (choice) {
                case 1 -> MenuHelper.chooseSize(milkshake, scanner);
                case 2 -> chooseBase(milkshake);
                case 3 -> MenuHelper.addToppings(milkshake, "Milkshake", scanner); // <- FIXED
                case 4 -> MenuHelper.removeToppings(milkshake, scanner);
                case 5 -> MenuHelper.previewItem(milkshake,"Milkshake");
                case 6 -> {
                    cart.addItem(milkshake);
                    System.out.println(UIColors.NEON_BLUE + "Milkshake added to cart!" + UIColors.RESET);
                    running = false;
                }
                case 7 -> running = false;
                default -> System.out.println(UIColors.NEON_RED + "Invalid choice. Try again." + UIColors.RESET);
            }
        }
    }

    private static void chooseBase(Milkshake milkshake) {
        System.out.println("Select Ice Cream Base:");
        IceCreamBase[] bases = IceCreamBase.values();
        for (int i = 0; i < bases.length; i++) {
            IceCreamBase b = bases[i];
            System.out.printf("%d) %s (%s) $%.2f%n", i + 1, b.getDisplayName(), b.getTier(), b.getExtraCost());
        }
        int choice = MenuHelper.getIntInput(scanner);
        if (choice > 0 && choice <= bases.length) {
            milkshake.setFlavor(bases[choice - 1].getDisplayName());
            System.out.println("Base flavor set to " + milkshake.getFlavor());
        } else {
            System.out.println(UIColors.NEON_RED + "Invalid choice." + UIColors.RESET);
        }
    }
}

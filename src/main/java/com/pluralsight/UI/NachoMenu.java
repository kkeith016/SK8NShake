package com.pluralsight.UI;

import com.pluralsight.Food.Nachos;
import com.pluralsight.Options.*;
import com.pluralsight.System.Cart;
import com.pluralsight.System.MenuHelper;
import com.pluralsight.System.PromptCombo;

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
                    3) Add Toppings
                    4) Remove Topping
                    5) Preview Nachos
                    6) Add to Cart
                    7) Return to Main Menu
                    """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = MenuHelper.getIntInput(scanner);

            switch (choice) {
                case 1 -> MenuHelper.chooseSize(nachos, scanner);
                case 2 -> chooseChips(nachos);
                case 3 -> MenuHelper.addToppings(nachos, "Nacho", scanner);
                case 4 -> MenuHelper.removeToppings(nachos, scanner);
                case 5 -> MenuHelper.previewItem(nachos,"Nachos");
                case 6 -> {
                    cart.addItem(nachos);
                    System.out.println(UIColors.NEON_BLUE + "Nachos added to cart!" + UIColors.RESET);
                    PromptCombo.askForCombo(cart);
                    running = false;
                }
                case 7 -> running = false;
                default -> System.out.println(UIColors.NEON_RED + "Invalid choice. Try again." + UIColors.RESET);
            }
        }
    }

    private static void chooseChips(Nachos nachos) {
        System.out.println("Select Chips:");
        for (Chips c : Chips.values()) {
            System.out.println((c.ordinal() + 1) + ") " + c.getDisplayName() + " ($" + c.getExtraCost() + ")");
        }
        int choice = MenuHelper.getIntInput(scanner);
        if (choice > 0 && choice <= Chips.values().length) {
            nachos.setChips(Chips.values()[choice - 1]);
            System.out.println(UIColors.NEON_GREEN + "Chips set to " + nachos.getChips().getDisplayName() + UIColors.RESET);
        } else {
            System.out.println(UIColors.NEON_RED + "Invalid choice." + UIColors.RESET);
        }
    }
}

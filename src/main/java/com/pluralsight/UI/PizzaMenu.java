package com.pluralsight.UI;

import com.pluralsight.Food.Pizza;
import com.pluralsight.Options.*;
import com.pluralsight.System.Cart;
import com.pluralsight.System.MenuHelper;
import com.pluralsight.System.PromptCombo;

import java.util.Scanner;

public class PizzaMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        Pizza pizza = new Pizza("Custom Pizza", Size.SMALL, 7.00, Crusts.HANDTOSSED, "");

        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== PIZZA MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Choose Size
                2) Choose Crust
                3) Add Toppings
                4) Remove Topping
                5) Preview Pizza
                6) Add to Cart
                7) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = MenuHelper.getIntInput(scanner);

            switch (choice) {
                case 1 -> MenuHelper.chooseSize(pizza, scanner);
                case 2 -> chooseCrust(pizza);
                case 3 -> MenuHelper.addToppings(pizza, "Pizza", scanner);
                case 4 -> MenuHelper.removeToppings(pizza, scanner);
                case 5 -> MenuHelper.previewItem(pizza,"Pizza");
                case 6 -> {
                    cart.addItem(pizza);
                    System.out.println(UIColors.NEON_BLUE + "Pizza added to cart!" + UIColors.RESET);
                    PromptCombo.askForCombo(cart);
                    running = false;
                }
                case 7 -> running = false;
                default -> System.out.println(UIColors.NEON_RED + "Invalid choice. Try again." + UIColors.RESET);
            }
        }
    }

    private static void chooseCrust(Pizza pizza) {
        System.out.println("Select Crust:");
        for (Crusts c : Crusts.values()) {
            System.out.println((c.ordinal() + 1) + ") " + c.getDisplayName() + " ($" + c.getExtraCost() + ")");
        }
        int choice = MenuHelper.getIntInput(scanner);
        if (choice > 0 && choice <= Crusts.values().length) {
            pizza.setCrust(Crusts.values()[choice - 1]);
            System.out.println(UIColors.NEON_GREEN + "Crust set to " + pizza.getCrust().getDisplayName() + UIColors.RESET);
        } else {
            System.out.println(UIColors.NEON_RED + "Invalid choice." + UIColors.RESET);
        }
    }
}
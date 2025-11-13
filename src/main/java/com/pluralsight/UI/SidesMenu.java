package com.pluralsight.UI;

import com.pluralsight.Food.Sides;
import com.pluralsight.Options.SideLibrary;
import com.pluralsight.System.Cart;
import com.pluralsight.System.MenuHelper;
import com.pluralsight.System.PromptCombo;

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
                2) Choose a Side & Add to Cart
                3) Return to Main Menu
                """ + UIColors.RESET);

            int choice = MenuHelper.getIntInput(scanner);

            switch (choice) {
                case 1 -> showAllSides();
                case 2 -> {
                    Sides selectedSide = chooseSideAndSize();
                    if (selectedSide != null) {
                        cart.addItem(selectedSide);
                        System.out.println(UIColors.NEON_BLUE + selectedSide.getName() +
                                " added to cart! Size: " + selectedSide.getSize() + UIColors.RESET);
                        PromptCombo.askForCombo(cart);
                    }
                }
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

    private static Sides chooseSideAndSize() {
        List<Sides> sides = SideLibrary.getAllSides();

        System.out.println(UIColors.NEON_YELLOW + "\nSelect a Side:" + UIColors.RESET);
        for (int i = 0; i < sides.size(); i++) {
            Sides s = sides.get(i);
            System.out.printf("%d) %-30s $%.2f (%s)%n", i + 1, s.getName(), s.getBasePrice(), s.getTier());
        }

        int choice = MenuHelper.getIntInput(scanner);
        if (choice > 0 && choice <= sides.size()) {
            Sides selected = sides.get(choice - 1);
            // Let MenuHelper handle size selection
            MenuHelper.chooseSize(selected, scanner);
            return selected;
        } else {
            System.out.println(UIColors.NEON_PINK + "Invalid choice." + UIColors.RESET);
            return null;
        }
    }
}
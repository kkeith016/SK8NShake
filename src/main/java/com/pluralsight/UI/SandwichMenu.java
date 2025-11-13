package com.pluralsight.UI;

import com.pluralsight.Food.Sandwich;
import com.pluralsight.System.Cart;
import com.pluralsight.Options.*;
import com.pluralsight.System.PromptCombo;
import com.pluralsight.System.MenuHelper;

import java.util.List;
import java.util.Scanner;

public class SandwichMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        Sandwich sandwich = new Sandwich("Custom Sandwich", Size.SMALL, 5.00, Bread.PITA, "");

        boolean running = true;
        while (running) {
            System.out.println(UIColors.NEON_PINK + "===================== SANDWICH MENU =====================" + UIColors.RESET);
            System.out.println(UIColors.NEON_YELLOW + """
                1) Choose Size
                2) Choose Bread
                3) Choose Protein
                4) Add Toppings
                5) Preview Sandwich
                6) Add to Cart
                7) Return to Main Menu
                """ + UIColors.RESET);

            System.out.print(UIColors.NEON_GREEN + "Enter your choice: " + UIColors.RESET);
            int choice = MenuHelper.getIntInput(scanner);

            switch (choice) {
                case 1 -> MenuHelper.chooseSize(sandwich, scanner);
                case 2 -> chooseBread(sandwich);
                case 3 -> chooseProtein(sandwich);
                case 4 -> MenuHelper.addToppings(sandwich, "Sandwich", scanner);
                case 5 -> MenuHelper.previewItem(sandwich, "Sandwich");
                case 6 -> {
                    cart.addItem(sandwich);
                    System.out.println(UIColors.NEON_BLUE + "Sandwich added to cart!" + UIColors.RESET);
                    PromptCombo.askForCombo(cart);
                    running = false;
                }
                case 7 -> running = false;
                default -> System.out.println(UIColors.NEON_RED + "Invalid choice. Try again." + UIColors.RESET);
            }
        }
    }

    private static void chooseBread(Sandwich sandwich) {
        System.out.println("Select Bread:");
        for (Bread b : Bread.values()) {
            System.out.printf("%d) %s ($%.2f)%n", b.ordinal() + 1, b.getDisplayName(), b.getExtraCost());
        }
        int choice = MenuHelper.getIntInput(scanner);
        if (choice > 0 && choice <= Bread.values().length) {
            sandwich.setBread(Bread.values()[choice - 1]);
            System.out.println(UIColors.NEON_GREEN + "Bread set to " + sandwich.getBread().getDisplayName() + UIColors.RESET);
        } else {
            System.out.println(UIColors.NEON_RED + "Invalid choice." + UIColors.RESET);
        }
    }

    private static void chooseProtein(Sandwich sandwich) {
        List<Protein> proteins = ProteinLibrary.getAllProteins().stream()
                .filter(p -> p.isValidFor("Sandwich"))
                .toList();

        System.out.println("Select Protein (comma separated for multiple, Enter to skip):");
        for (int i = 0; i < proteins.size(); i++) {
            Protein p = proteins.get(i);
            System.out.printf("%d) %s ($%.2f)%n", i + 1, p.getName(), p.getBasePrice());
        }

        String input = scanner.nextLine();
        if (!input.isBlank()) {
            for (String part : input.split(",")) {
                try {
                    int proteinIndex = Integer.parseInt(part.trim()) - 1;
                    if (proteinIndex >= 0 && proteinIndex < proteins.size()) {
                        sandwich.addProtein(proteins.get(proteinIndex));
                        System.out.println(UIColors.NEON_GREEN + proteins.get(proteinIndex).getName() + " added!" + UIColors.RESET);
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
    }
}

package com.pluralsight.System;

import com.pluralsight.Food.Milkshake;
import com.pluralsight.Food.Nachos;
import com.pluralsight.Food.Pizza;
import com.pluralsight.Food.Sandwich;
import com.pluralsight.Options.*;
import java.util.*;
import com.pluralsight.UI.UIColors;

public class MenuHelper {

    private static final Scanner scanner = new Scanner(System.in);

    // ------------------- Size -------------------
    public static void chooseSize(MenuItems item, Scanner scanner) {
        System.out.println("Select Size:");
        for (Size s : Size.values()) {
            System.out.println((s.ordinal() + 1) + ") " + s.getDisplayName());
        }

        int choice = getIntInput(scanner);
        if (choice > 0 && choice <= Size.values().length) {
            item.setSize(Size.values()[choice - 1]);
            System.out.println(UIColors.NEON_GREEN + "Size set to " + item.getSize().getDisplayName() + UIColors.RESET);
        } else {
            System.out.println(UIColors.NEON_RED + "Invalid choice." + UIColors.RESET);
        }
    }

    // ------------------- Add Toppings -------------------
    public static void addToppings(MenuItems item, String foodType, Scanner scanner) {
        if (!(item instanceof Customizable customizable)) {
            System.out.println(UIColors.NEON_RED + "This item cannot have toppings!" + UIColors.RESET);
            return;
        }


        List<Topping> validToppings = ToppingsLibrary.getAllToppings().stream()
                .filter(t -> t.isValidFor(foodType))
                .toList();

        List<String> categories = List.of("Cheese", "Veggie", "Meat", "Sauce", "Garnish", "Sweet Sauce");

        for (String category : categories) {
            List<Topping> catToppings = validToppings.stream()
                    .filter(t -> t.getCategory().equalsIgnoreCase(category))
                    .toList();
            if (catToppings.isEmpty()) continue;

            System.out.println(UIColors.NEON_YELLOW + "--- " + category + " ---" + UIColors.RESET);
            for (int i = 0; i < catToppings.size(); i++) {
                Topping t = catToppings.get(i);
                System.out.println((i + 1) + ") " + t.getName() + " ($" + t.getBasePrice() + ")");
            }

            System.out.println("Enter numbers separated by commas to add " + category + " (or press Enter to skip):");
            String input = scanner.nextLine();
            if (!input.isBlank()) {
                String[] parts = input.split(",");
                for (String part : parts) {
                    try {
                        int index = Integer.parseInt(part.trim()) - 1;
                        if (index >= 0 && index < catToppings.size()) {
                            customizable.addTopping(catToppings.get(index));
                            System.out.println(UIColors.NEON_GREEN + catToppings.get(index).getName() + " added!" + UIColors.RESET);
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        System.out.println(UIColors.NEON_BLUE + "Finished adding toppings!" + UIColors.RESET);
    }

    // ------------------- Remove Toppings -------------------
    public static void removeToppings(MenuItems item, Scanner scanner) {
        if (!(item instanceof Customizable customizable)) {
            System.out.println(UIColors.NEON_RED + "This item has no removable toppings!" + UIColors.RESET);
            return;
        }

        List<Topping> toppings = customizable.getToppings();
        if (toppings.isEmpty()) {
            System.out.println(UIColors.NEON_RED + "No toppings to remove!" + UIColors.RESET);
            return;
        }

        System.out.println("Current toppings:");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, toppings.get(i).getName());
        }

        System.out.println("Enter the number of the topping to remove (or press Enter to cancel):");
        String input = scanner.nextLine();
        if (!input.isBlank()) {
            try {
                int choice = Integer.parseInt(input.trim());
                if (choice > 0 && choice <= toppings.size()) {
                    String toppingName = toppings.get(choice - 1).getName();
                    customizable.removeTopping(toppingName);
                    System.out.println(UIColors.NEON_GREEN + toppingName + " removed!" + UIColors.RESET);
                } else {
                    System.out.println(UIColors.NEON_RED + "Invalid choice." + UIColors.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(UIColors.NEON_RED + "Invalid input." + UIColors.RESET);
            }
        }
    }

    //----------------------Preview--------------------


    public static void previewItem(Customizable item, String title) {
        String headerColor = UIColors.NEON_PINK;

        System.out.println(headerColor + "------------------- " + title.toUpperCase() + " PREVIEW -------------------" + UIColors.RESET);

        if (item instanceof MenuItems mi) {

            System.out.printf("1x %s (%s) ............ $%.2f%n",
                    mi.getName(),
                    mi.getSize() != null ? mi.getSize().getDisplayName() : "None",
                    mi.calculatePrice());


            if (mi instanceof Sandwich s && s.getBread() != null) {
                System.out.printf("Bread: %s (+$%.2f)%n", s.getBread().getDisplayName(), s.getBread().getExtraCost());
            }

            if (mi instanceof Pizza p && p.getCrust() != null) {
                System.out.printf("Crust: %s (+$%.2f)%n", p.getCrust().getDisplayName(), p.getCrust().getExtraCost());
            }

            if (mi instanceof Nachos n && n.getChips() != null) {
                System.out.printf("Chips: %s (+$%.2f)%n", n.getChips().getDisplayName(), n.getChips().getExtraCost());
            }

            if (mi instanceof Milkshake m && m.getIceCreamBase() != null) {
                System.out.printf("Ice Cream Base: %s (+$%.2f)%n", m.getIceCreamBase().getDisplayName(), m.getIceCreamBase().getExtraCost());
            }
        }

        item.showToppings();

        System.out.println(headerColor + "---------------------------------------------------" + UIColors.RESET);
    }


    // ------------------- Utility -------------------
    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                System.out.print("> ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(UIColors.NEON_RED + "Invalid number. Try again." + UIColors.RESET);
            }
        }
    }
}
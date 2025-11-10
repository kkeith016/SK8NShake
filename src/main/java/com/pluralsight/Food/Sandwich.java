package com.pluralsight.Food;

import com.pluralsight.Options.Bread;
import com.pluralsight.Options.Size;
import com.pluralsight.Options.Topping;
import com.pluralsight.System.Customizable;
import com.pluralsight.System.MenuItems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sandwich extends MenuItems implements Customizable {

    private Bread bread;
    private final List<Topping> toppings;

    public Sandwich(String name, Size size, double basePrice, Bread bread, String notes) {
        super(name, size, basePrice, notes);
        this.bread = bread;
        this.toppings = new ArrayList<>();
    }

    public Bread getBread() { return bread; }
    public void setBread(Bread bread) { this.bread = bread; }

    @Override
    public void setSize(Size size) { this.size = size; }

    @Override
    public Size getSize() { return size; }

    @Override
    public void addTopping(Topping topping) {
        toppings.add(topping);
        System.out.println(topping.getName() + " added to your sandwich!");
    }

    @Override
    public void removeTopping(String toppingName) {
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
        System.out.println(toppingName + " removed from your sandwich.");
    }

    @Override
    public List<Topping> getToppings() { return toppings; }

    @Override
    public double calculatePrice() {
        double total = basePrice;

        // Add bread extra cost if bread exists
        if (bread != null) {
            total += bread.getExtraCost();
        }

        // Count Basic toppings
        int basicCount = (int) toppings.stream()
                .filter(t -> t.getTier().equalsIgnoreCase("Basic"))
                .count();

        // Extra cost for Basic toppings over 3
        double extraBasicCost = 0;
        if (basicCount > 3) {
            extraBasicCost = (basicCount - 3) * 0.50;
        }

        // Sum price of non-Basic toppings
        double premiumCost = toppings.stream()
                .filter(t -> !t.getTier().equalsIgnoreCase("Basic"))
                .mapToDouble(Topping::getBasePrice)
                .sum();

        // Add size modifier if size exists
        double sizeModifier = 0;
        if (size != null) {
            sizeModifier = size.getPriceModifier();
        }

        return total + extraBasicCost + premiumCost + sizeModifier;
    }

    @Override
    public String toString() {
        String toppingNames = "None";
        if (!toppings.isEmpty()) {
            toppingNames = toppings.stream()
                    .map(Topping::getName)
                    .collect(Collectors.joining(", "));
        }

        String displaySize = "None";
        if (size != null) {
            displaySize = size.getDisplayName();
        }

        String displayBread = "None";
        if (bread != null) {
            displayBread = bread.getDisplayName();
        }

        return String.format("""
                Sandwich: %s (%s)
                Bread: %s
                Toppings: %s
                Price: $%.2f
                Notes: %s
                """,
                name,
                displaySize,
                displayBread,
                toppingNames,
                calculatePrice(),
                notes
        );
    }
}

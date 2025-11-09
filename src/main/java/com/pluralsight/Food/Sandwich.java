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
        double total = basePrice + (bread != null ? bread.getExtraCost() : 0);

        long basicCount = toppings.stream().filter(t -> t.getTier().equalsIgnoreCase("Basic")).count();
        double extraBasicCost = Math.max(0, basicCount - 3) * 0.50;
        double premiumCost = toppings.stream().filter(t -> !t.getTier().equalsIgnoreCase("Basic"))
                .mapToDouble(Topping::getBasePrice).sum();

        return total + extraBasicCost + premiumCost + (size != null ? size.getPriceModifier() : 0);
    }

    @Override
    public String toString() {
        String toppingNames = toppings.isEmpty()
                ? "None"
                : toppings.stream().map(Topping::getName).collect(Collectors.joining(", "));

        return String.format("""
                Sandwich: %s (%s)
                Bread: %s
                Toppings: %s
                Price: $%.2f
                Notes: %s
                """,
                name,
                size != null ? size.getDisplayName() : "None",
                bread != null ? bread.getDisplayName() : "None",
                toppingNames,
                calculatePrice(),
                notes
        );
    }
}
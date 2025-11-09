package com.pluralsight.Food;

import com.pluralsight.Options.Size;
import com.pluralsight.Options.Topping;
import com.pluralsight.System.Customizable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Milkshake extends Drink implements Customizable {

    private final List<Topping> toppings;

    public Milkshake(String flavor, Size size, double basePrice) {
        super(flavor + " Milkshake", size, basePrice, flavor);
        this.toppings = new ArrayList<>();
    }

    @Override
    public void addTopping(Topping topping) {
        toppings.add(topping);
        System.out.println(topping.getName() + " added to your milkshake!");
    }

    @Override
    public void removeTopping(String toppingName) {
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
        System.out.println(toppingName + " removed from your milkshake.");
    }

    @Override
    public List<Topping> getToppings() { return toppings; }

    @Override
    public double calculatePrice() {
        double total = super.calculatePrice();
        for (Topping t : toppings) total += t.getBasePrice();
        return total;
    }

    @Override
    public String toString() {
        String toppingList = toppings.isEmpty()
                ? "None"
                : toppings.stream().map(Topping::getName).collect(Collectors.joining(", "));

        return String.format("""
                Milkshake: %s (%s)
                Flavor: %s
                Toppings: %s
                Price: $%.2f
                """,
                getName(),
                getSize().getDisplayName(),
                super.getFlavor(),
                toppingList,
                calculatePrice()
        );
    }
}

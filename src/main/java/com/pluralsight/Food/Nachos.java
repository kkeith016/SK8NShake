package com.pluralsight.Food;

import com.pluralsight.Options.*;
import com.pluralsight.System.Customizable;
import com.pluralsight.System.MenuItems;

import java.util.ArrayList;
import java.util.List;

public class Nachos extends MenuItems implements Customizable {

    private Chips chips;
    private final List<Topping> toppings;
    private final List<Protein> proteins;

    public Nachos(String name, Size size, double basePrice, Chips chips, String notes) {
        super(name, size, basePrice, notes);
        this.chips = chips;
        this.toppings = new ArrayList<>();
        this.proteins = new ArrayList<>();
    }

    public Chips getChips() {
        return chips;
    }
    public void setChips(Chips chips) {
        this.chips = chips;
    }

    @Override
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    @Override
    public void removeTopping(String toppingName) {
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
    }

    @Override
    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public List<Protein> getProteins() {
        return proteins;
    }
    @Override
    public double calculatePrice() {
        double total = basePrice;

        if (size != null) total += size.getPriceModifier();
        if (chips != null) total += chips.getExtraCost();
        for (Topping t : getToppings()) total += t.getBasePrice();

        return total;
    }
}

package com.pluralsight.Food;

import com.pluralsight.Options.*;
import com.pluralsight.System.Customizable;
import com.pluralsight.System.MenuItems;

import java.util.ArrayList;
import java.util.List;

public class Pizza extends MenuItems implements Customizable {

    private Crusts crust;
    private final List<Topping> toppings;

    public Pizza(String name, Size size, double basePrice, Crusts crust, String notes) {
        super(name, size, basePrice, notes);
        this.crust = crust;
        this.toppings = new ArrayList<>();
    }

    public Crusts getCrust() {
        return crust;
    }
    public void setCrust(Crusts crust) {
        this.crust = crust;
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
        return List.of();
    }
    @Override
    public double calculatePrice() {
        double total = basePrice;

        if (size != null) total += size.getPriceModifier();
        if (crust != null) total += crust.getExtraCost();
        for (Topping t : getToppings()) total += t.getBasePrice();
        return total;
    }

}

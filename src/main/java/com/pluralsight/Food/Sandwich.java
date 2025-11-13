package com.pluralsight.Food;

import com.pluralsight.Options.*;
import com.pluralsight.System.Customizable;
import com.pluralsight.System.MenuItems;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends MenuItems implements Customizable {

    private Bread bread;
    private final List<Topping> toppings;
    private final List<Protein> proteins;

    public Sandwich(String name, Size size, double basePrice, Bread bread, String notes) {
        super(name, size, basePrice, notes);
        this.bread = bread;
        this.toppings = new ArrayList<>();
        this.proteins = new ArrayList<>();
    }

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
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

    public void addProtein(Protein protein) {
        proteins.add(protein);
    }

    @Override
    public List<Protein> getProteins() {
        return proteins;
    }
    @Override
    public double calculatePrice() {
        double total = basePrice;

        if (size != null) total += size.getPriceModifier();
        if (bread != null) total += bread.getExtraCost();
        for (Topping t : getToppings()) total += t.getBasePrice();
        return total;
    }

}

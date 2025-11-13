package com.pluralsight.Food;

import com.pluralsight.Options.*;
import com.pluralsight.System.Customizable;
import com.pluralsight.System.MenuItems;

import java.util.ArrayList;
import java.util.List;

public class Milkshake extends MenuItems implements Customizable {


    private String flavor;
    private final List<Topping> toppings;
    private IceCreamBase iceCreamBase;

    public Milkshake(String name, Size size, double basePrice) {
        super(name, size, basePrice, "");
        this.toppings = new ArrayList<>();
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setIceCreamBase(IceCreamBase base) {
        this.iceCreamBase = base;
    }

    public IceCreamBase getIceCreamBase() {
        return iceCreamBase;
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
        if (iceCreamBase != null) total += iceCreamBase.getExtraCost();
        for (Topping t : getToppings()) total += t.getBasePrice();

        return total;
    }

}

package com.pluralsight.Food;

import com.pluralsight.MenuItems;
import com.pluralsight.Toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class Burger extends MenuItems {
    private String bread;
    private List<Topping> proteins;
    private List<Topping> veggies;
    private List<Topping> cheeses;
    private List<Topping> sauces;

    public Burger(String name, String size, double basePrice, String bread, String notes) {
        super(name, size, basePrice, notes);
        this.bread = bread;
        this.proteins = new ArrayList<>();
        this.veggies = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.sauces = new ArrayList<>();
    }


}

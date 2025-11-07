package com.pluralsight;

import com.pluralsight.Food.Topping;

import java.util.List;
import java.util.ArrayList;

public abstract class MenuItems {
    protected String name;
    protected String size;
    protected double basePrice;
    protected List<Topping> toppings;
    protected String notes;

    //----Constructor----

    public MenuItems(String name, String size, double price, List<Topping> toppings, String notes) {
        this.name = name;
        this.size = size;
        this.basePrice = price;
        this.toppings = new ArrayList<>();
        this.notes = notes;
    }

    /*
    Core Methods
   - CalculatePrice
   - display
   - addTopping
   - removeTopping

   - work on once we get the toppings finished.
     */

    public double calculatePrice(){
        double totalPrice = basePrice;
        for(Topping topping : toppings){
            total += topping.getPrice();
        }
        return totalPrice;
    }
}

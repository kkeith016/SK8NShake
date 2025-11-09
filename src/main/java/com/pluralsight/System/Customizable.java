package com.pluralsight.System;

import com.pluralsight.Options.Topping;
import java.util.List;

public interface Customizable {
    void addTopping(Topping topping);
    void removeTopping(String toppingName);
    List<Topping> getToppings();
    void setSize(String size);
    String getSize();

    //Display toppings to the user (For UI)

    default void showToppings() {
        List<Topping> toppings = getToppings();
        if (toppings.isEmpty()) {
            System.out.println("No toppings found");
        } else {
            System.out.println("Current toppings:");
            for (Topping t : toppings) {
                System.out.println(" - " + t.getName() + " ($" + t.getBasePrice() + ")");
            }
        }
    }
}
package com.pluralsight.System;

import com.pluralsight.Options.Size;
import com.pluralsight.Options.Topping;
import java.util.List;

public interface Customizable {


    void addTopping(Topping topping);
    void removeTopping(String toppingName);
    List<Topping> getToppings();

    void setSize(Size size);
    Size getSize();


    default void showToppings() {
        List<Topping> toppings = getToppings();
        if (toppings == null || toppings.isEmpty()) {
            System.out.println("No toppings added.");
            return;
        }

        System.out.println("Current toppings:");
        toppings.forEach(t ->
                System.out.printf(" - %s ($%.2f)%n", t.getName(), t.getBasePrice())
        );
    }
}
package com.pluralsight.System;

import com.pluralsight.System.Cart;
import com.pluralsight.System.MenuItems;
import com.pluralsight.System.Customizable;

public class Receipt {

    public static String generateReceipt(Cart cart, int orderNumber) {
        StringBuilder sb = new StringBuilder();

        sb.append("------------------------------------------------------------\n");
        sb.append("                       SK8 N’ SHAKE\n");
        sb.append("                123 Skate Lane, Tampa, FL\n");
        sb.append("                     (555)-888-4567\n");
        sb.append("------------------------------------------------------------\n");

        sb.append(String.format("Order #: %-8d\n", orderNumber));
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("%-35s %10s\n", "Item", "Price"));
        sb.append("------------------------------------------------------------\n");

        double subtotal = 0.0;

        for (MenuItems item : cart.getItems()) {
            double itemPrice = item.calculatePrice();
            subtotal += itemPrice;

            sb.append(String.format("%-35s $%6.2f\n", item.getName(), itemPrice));

            if (item instanceof Customizable customizable) {
                customizable.getToppings().forEach(t ->
                        sb.append(String.format("   + %-31s $%6.2f\n",
                                t.getName(), t.getBasePrice()))
                );
            }
        }

        sb.append("------------------------------------------------------------\n");
        double tax = subtotal * 0.07;
        double total = subtotal + tax;

        sb.append(String.format("%-35s $%6.2f\n", "Subtotal", subtotal));
        sb.append(String.format("%-35s $%6.2f\n", "Tax (7%)", tax));
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("**%-33s $%6.2f**\n", "TOTAL DUE", total));
        sb.append("------------------------------------------------------------\n\n");

        sb.append("Thank you for skating with us!\n");
        sb.append("Come back for Friday Night 80’s!\n");
        sb.append("------------------------------------------------------------\n");

        return sb.toString();
    }
}

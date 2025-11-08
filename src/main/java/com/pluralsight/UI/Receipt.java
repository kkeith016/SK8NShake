package com.pluralsight.UI;

import com.pluralsight.System.Cart;
import com.pluralsight.System.MenuItems;
import com.pluralsight.System.Customizable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {

    public static String generateReceipt(Cart cart, int orderNumber, String serverName) {
        StringBuilder sb = new StringBuilder();

        // === HEADER ===
        sb.append("------------------------------------------------------------\n");
        sb.append("                       SK8 N’ SHAKE\n");
        sb.append("                123 Skate Lane, Tampa, FL\n");
        sb.append("                     (555)-888-4567\n");
        sb.append("------------------------------------------------------------\n");

        sb.append(String.format("Order #: %-8d     %s\n", orderNumber,
                new SimpleDateFormat("MM/dd/yyyy").format(new Date())));
        sb.append(String.format("Server: %s\n", serverName));
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("%-35s %10s\n", "Item", "Price"));
        sb.append("------------------------------------------------------------\n");

        // === ITEMS SECTION ===
        double subtotal = 0.0;

        for (MenuItems item : cart.getItems()) {
            double itemPrice = item.calculatePrice();
            subtotal += itemPrice;

            // main item line
            sb.append(String.format("%-35s $%6.2f\n", item.getName(), itemPrice));

            // toppings indented
            if (item instanceof Customizable customizable) {
                customizable.getToppings().forEach(t ->
                        sb.append(String.format("   + %-31s $%6.2f\n",
                                t.getName(), t.getBasePrice()))
                );
            }
        }

        sb.append("------------------------------------------------------------\n");

        // === TOTALS ===
        double tax = subtotal * 0.07;
        double total = subtotal + tax;

        sb.append(String.format("%-35s $%6.2f\n", "Subtotal", subtotal));
        sb.append(String.format("%-35s $%6.2f\n", "Tax (7%)", tax));
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("**%-33s $%6.2f**\n", "TOTAL DUE", total));
        sb.append("------------------------------------------------------------\n\n");

        // === FOOTER ===
        sb.append("Thank you for skating with us!\n");
        sb.append("Come back for Friday Night 80’s!\n");
        sb.append("------------------------------------------------------------\n");

        return sb.toString();
    }
}

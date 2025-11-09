package com.pluralsight.UI;

import com.pluralsight.System.Cart;
import com.pluralsight.System.OrderManager;
import com.pluralsight.System.Receipt;
import com.pluralsight.System.ReceiptSaver;

import java.util.Scanner;

public class CheckoutMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display(Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("\u001B[91mYour cart is empty!\u001B[0m");
            return;
        }

        boolean running = true;

        while (running) {
            System.out.println(UIColors.NEON_PINK + "=================== CHECKOUT ===================" + UIColors.RESET);
            String receiptPreview = Receipt.generateReceipt(cart, OrderManager.getNextOrderNumber());
            System.out.println(receiptPreview);

            System.out.println(UIColors.NEON_YELLOW + "[1] Clear Cart   [2] Confirm & Checkout   [3] Return to Main Menu" + UIColors.RESET);
            System.out.print(UIColors.NEON_GREEN + "Choose an option: " + UIColors.RESET);

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {
                    cart.clear();
                    System.out.println("\u001B[93mCart cleared!\u001B[0m");
                    running = false;
                }
                case "2" -> {
                    // Save receipt
                    ReceiptSaver.saveReceipt(receiptPreview);
                    cart.clear();
                    System.out.println("\u001B[92mCheckout complete! Enjoy your order!\u001B[0m");
                    running = false;
                }
                case "3" -> running = false;
                default -> System.out.println("\u001B[91mInvalid option. Please try again.\u001B[0m");
            }
        }
    }
}

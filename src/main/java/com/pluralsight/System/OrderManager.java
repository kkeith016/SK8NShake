package com.pluralsight.System;

public class OrderManager {
    private static int orderCounter = 1000;

    // Returns the next order number and increments the counter
    public static int getNextOrderNumber() {
        return orderCounter++;
    }
}

package com.pluralsight.System;

public class OrderManager {
    private static int orderCounter = 1000;

    public static int getNextOrderNumber() {
        return orderCounter++;
    }
}

package com.pluralsight.System;

public class OrderManager {
    private static int orderCounter = 1234;

    public static int getNextOrderNumber() {
        return orderCounter++;
    }
}
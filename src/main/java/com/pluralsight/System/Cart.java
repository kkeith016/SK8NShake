package com.pluralsight.System;
import com.pluralsight.System.MenuItems;

import java.util.List;
import java.util.ArrayList;

public class Cart {
    private List<MenuItems> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItems item) {
        items.add(item);
    }

    public void removeItem(MenuItems item) {
        items.remove(item);
    }

    public List<MenuItems> getItems() {
        return items;
    }

    // Calculate subtotal (before tax)
    public double getSubtotal() {
        double subtotal = 0;
        for (MenuItems item : items) {
            subtotal += item.calculatePrice();
        }
        return subtotal;
    }

    // Calculate tax
    public double getTax(double taxRate) {
        return getSubtotal() * taxRate;
    }

    // Calculate total including tax
    public double getTotal(double taxRate) {
        return getSubtotal() + getTax(taxRate);
    }

    public void clearCart() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
package com.pluralsight.System;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<MenuItems> items;

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

    public double getSubtotal() {
        double subtotal = 0;
        for (MenuItems item : items) subtotal += item.calculatePrice();
        return subtotal;
    }

    public double getTax(double taxRate) {
        return getSubtotal() * taxRate;
    }

    public double getTotal(double taxRate) {
        return getSubtotal() + getTax(taxRate);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

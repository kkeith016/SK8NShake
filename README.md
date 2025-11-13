# SK8 N’ SHAKE POS System

This is a custom **Point-of-Sale (POS) system** for a fictional roller-skate concession stand called **SK8 N’ SHAKE**.  
It allows users to order **sandwiches, pizza, nachos, milkshakes, drinks, and sides** with full customization, calculates prices, and generates receipts.

---

## Key Features

### Menus
- Sandwich, Pizza, Nachos, Milkshake, Drinks, Sides, Neon Hits
- Each menu item is customizable:
    - Choose **size**, **toppings**, **proteins**, **bread**, **crusts**, **chips**, or **ice cream base**
- Neatly formatted **neon-themed console UI** for a fun user experience

### Cart & Checkout
- Add multiple items to a **shared cart**
- View **subtotal, tax (7%), and total**
- Preview items **before adding to cart**
- **Save receipts** to files with timestamps

### Customization Interface & Toppings
- Uses a **Customizable interface** for anything that can have toppings or add-ons
- Previews show full details of each item including **size, bread/crust/chips/base, and toppings**
- All items now reflect the **correct pricing** for every topping, without free-tier exceptions

### Enums
- **Size, Bread, Crusts, Chips, IceCreamBase, Topping**
- Keeps **pricing and display consistent** across all items
- Makes it **easy to add new menu items or options**

### Receipts & Saving
- Built using **StringBuilder**
- Cleanly formatted with **itemized toppings, bases, bread, crusts, or chips**
- Shows **subtotal, tax, and total**
- Saved automatically to **timestamped files**

---

## Lessons Learned

### Enum Size Struggle
- Initially, I tried putting **Size** in every menu item separately.
- This caused repetition and complicated price calculation.
- After struggling, I realized **Size belongs in the MenuItems base class**, which:
    - Automatically gives all items a size
    - Simplifies price calculations
    - Makes previews and receipts consistent

### Customizable Interface
- Creating a **Customizable interface** helped manage toppings and add-ons for all menu items consistently
- No duplicate code needed for **Sandwiches, Pizza, Nachos, or Milkshakes**

### StringBuilder for Receipts
- Switching from simple string concatenation to **StringBuilder** improved readability and maintainability
- Allowed including **special options** like bread, crust, chips, and ice cream base
- Kept **prices aligned** and calculations clear

### Enums Everywhere
- Enums helped manage **menu options, prices, and tiers**
- Each enum includes **display name, price, and category/tier** when needed
- This made:
    - **Menus easy to generate dynamically**
    - **Price calculations straightforward**
    - **Adding new items or options quick and safe**

---

## What I Learned
- Centralizing shared properties in **MenuItems** saves time and reduces bugs
- **Interfaces + Enums = cleaner, maintainable code**
- **Formatting output** (menus, receipts) is as important as calculations
- Struggling through design issues eventually leads to **simpler, more elegant solutions**
- **Build one piece and reuse** as much as possible leads to faster turnaround for others. 

---

This project demonstrates a **working POS system** with:
- Customizable menu items
- Dynamic pricing
- Receipt generation
- Fun, interactive UI

All while making you hungry.

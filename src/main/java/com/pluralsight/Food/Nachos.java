package com.pluralsight.Food;

import com.pluralsight.Options.Chips;
import com.pluralsight.Options.Size;
import com.pluralsight.Options.Protein;
import com.pluralsight.System.Customizable;
import com.pluralsight.System.MenuItems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Nachos extends MenuItems implements Customizable {

    private Chips chips;
    private final List<Protein> proteins;

    public Nachos(String name, Size size, double basePrice, Chips chips, String notes) {
        super(name, size, basePrice, notes);
        this.chips = chips;
        this.proteins = new ArrayList<>();
    }

    public Chips getChips() { return chips; }
    public void setChips(Chips chips) { this.chips = chips; }

    @Override
    public void setSize(Size size) { this.size = size; }

    @Override
    public Size getSize() { return size; }

    // We can treat add/remove as Protein-based customization
    public void addProtein(Protein protein) {
        proteins.add(protein);
        System.out.println(protein.getName() + " added to your nachos!");
    }

    public void removeProtein(String proteinName) {
        proteins.removeIf(p -> p.getName().equalsIgnoreCase(proteinName));
        System.out.println(proteinName + " removed from your nachos.");
    }

    public List<Protein> getProteins() { return proteins; }

    @Override
    public void addTopping(com.pluralsight.Options.Topping topping) {
        // Nachos may support toppings later; currently unused
    }

    @Override
    public void removeTopping(String toppingName) {}

    @Override
    public List<com.pluralsight.Options.Topping> getToppings() { return List.of(); }

    @Override
    public double calculatePrice() {
        double total = basePrice;

        // Add chip cost
        if (chips != null) {
            total += chips.getExtraCost();
        }

        // Protein cost
        double proteinCost = proteins.stream()
                .mapToDouble(Protein::getBasePrice)
                .sum();

        // Add size modifier if size exists
        double sizeModifier = 0;
        if (size != null) {
            sizeModifier = size.getPriceModifier();
        }

        return total + proteinCost + sizeModifier;
    }

    @Override
    public String toString() {
        String proteinNames = "None";
        if (!proteins.isEmpty()) {
            proteinNames = proteins.stream()
                    .map(Protein::getName)
                    .collect(Collectors.joining(", "));
        }

        String displaySize = size != null ? size.getDisplayName() : "None";
        String displayChips = chips != null ? chips.getDisplayName() : "None";

        return String.format("""
                Nachos: %s (%s)
                Chips: %s
                Proteins: %s
                Price: $%.2f
                Notes: %s
                """,
                name,
                displaySize,
                displayChips,
                proteinNames,
                calculatePrice(),
                notes
        );
    }
}
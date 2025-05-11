package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String id;
    private Map<InventoryItem, Integer> products;
    private LocalDate date;
    private CartType type;

    public enum CartType { PHYSICAL, VIRTUAL }

    public Cart(String id, CartType type) {
        this.id = id;
        this.type = type;
        this.products = new HashMap<>();
        this.date = LocalDate.now();
    }

    public String getId() {
        return id;
    }
    public LocalDate getDate() {
        return date;
    }
    public CartType getType() {
        return type;
    }

    public Map<InventoryItem, Integer> getProducts() {
        return new HashMap<>(products);
    }

    public void addItem(InventoryItem inventoryProduct, int quantity) {
        inventoryProduct.reserveItem(quantity);
        products.merge(inventoryProduct, quantity, Integer::sum);
    }

    public void removeItem(InventoryItem inventoryProduct, int quantity) {
        int availableQuantity = products.getOrDefault(inventoryProduct, 0);
        if (availableQuantity <= quantity) {
            products.remove(inventoryProduct);
        } else {
            products.put(inventoryProduct, availableQuantity - quantity);
        }
    }

    public void printSalesSlip () {
        double total = 0;
        System.out.println("\nItems in the cart: ");
        for (Map.Entry<InventoryItem, Integer> entry : products.entrySet()) {
            double itemPrice = entry.getKey().getSalesPrice() * entry.getValue();
            total = total + itemPrice;
            System.out.println(entry.getKey().getProduct().name() + " x " + entry.getValue() + " = " + itemPrice);
        }
        System.out.println("Sales total: " + total);
    };
}

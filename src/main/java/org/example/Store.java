package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Store {
    private Map<String, InventoryItem> inventory;
    private Map<ProductCategory, List<InventoryItem>> aisleInventory;
    private List<Cart> carts;

    public Store() {
        this.inventory = new HashMap<>();
        this.aisleInventory = new HashMap<>();
        this.carts = new ArrayList<>();

        for (ProductCategory category : ProductCategory.values()) {
            aisleInventory.put(category, new ArrayList<>());
        }
    }

    public void listProductsByCategory() {
        aisleInventory.forEach((key, value) -> {
            System.out.println(key + ":");
            value.forEach(inventoryItem -> System.out.println(inventoryItem.getProduct() + ", ammount: " + inventoryItem.getAvailableQuantity()));
        });
    }

    public void manageStoreCards() {
        throw new RuntimeException("there is no description what this method should do in the challenge text");
        //there is no description what this method should do in the challenge text
    }

    public Cart createCart(Cart.CartType type) {
        String id = "CART" + System.currentTimeMillis();
        Cart cart = new Cart(id, type);
        carts.add(cart);
        return cart;
    }

    public void abandonCarts() {
        LocalDate today = LocalDate.now();
        List<Cart> abandonedCarts = carts.stream()
                .filter(cart -> !cart.getDate().equals(today))
                .collect(Collectors.toList());

        for (Cart cart : abandonedCarts) {
            for (Map.Entry<InventoryItem, Integer> entry : cart.getProducts().entrySet()) {
                InventoryItem inventoryItem = entry.getKey();
                int quantity = entry.getValue();
                inventoryItem.releaseItem(quantity);
            }
        }

        carts.removeAll(abandonedCarts);
    }

    public void checkOutCart(Cart cart) {
        for (Map.Entry<InventoryItem, Integer> entry : cart.getProducts().entrySet()) {
            InventoryItem inventoryItem = entry.getKey();
            inventoryItem.sellItem(entry.getValue());
        }
        carts.remove(cart);
    }

    public void addToIsleInventory(ProductCategory category, List<InventoryItem> products) {
        aisleInventory.put(category, products);
    }
}

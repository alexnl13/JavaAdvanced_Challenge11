package org.example;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Store store = new Store();

        Product apple = new Product("123er", "apple", "HappyFarm", ProductCategory.FRUIT);
        InventoryItem appleItem = new InventoryItem(apple, 10, 5, 2, 2.35);

        Product orange = new Product("123er", "orange", "HappyFarm", ProductCategory.FRUIT);
        InventoryItem orangeItem = new InventoryItem(orange, 10, 5, 2, 4.20);

        Product ciabata = new Product("124sdr", "ciabata", "FunnyBakery", ProductCategory.BAKERY);
        InventoryItem ciabataItem = new InventoryItem(ciabata, 25, 10, 5, 3.50);


        List<InventoryItem> fruitItems = Arrays.asList(appleItem, orangeItem);
        store.addToIsleInventory(ProductCategory.FRUIT, fruitItems);

        List<InventoryItem> bakeryItems = Arrays.asList(ciabataItem);
        store.addToIsleInventory(ProductCategory.BAKERY, bakeryItems);

        System.out.println("Available products by category");
        store.listProductsByCategory();

        Cart cart = new Cart("123", Cart.CartType.PHYSICAL);
        cart.addItem(appleItem, 2);
        cart.addItem(ciabataItem, 1);
        cart.printSalesSlip();

        store.checkOutCart(cart);

        System.out.println("\nAvailable products by category after cart sale");
        store.listProductsByCategory();
    }
}
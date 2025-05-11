package org.example;

public class InventoryItem {
    private Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReoder;
    private int qtyLow;
    private double salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyReoder, int qtyLow, Double salesPrice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReoder = qtyReoder;
        this.qtyLow = qtyLow;
        this.salesPrice = salesPrice;
        this.qtyReserved = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQtyTotal() {
        return qtyTotal;
    }

    public void setQtyTotal(int qtyTotal) {
        this.qtyTotal = qtyTotal;
    }

    public int getQtyReserved() {
        return qtyReserved;
    }

    public void setQtyReserved(int qtyReserved) {
        this.qtyReserved = qtyReserved;
    }

    public int getQtyReoder() {
        return qtyReoder;
    }

    public void setQtyReoder(int qtyReoder) {
        this.qtyReoder = qtyReoder;
    }

    public int getQtyLow() {
        return qtyLow;
    }

    public void setQtyLow(int qtyLow) {
        this.qtyLow = qtyLow;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getAvailableQuantity() {
        return qtyTotal - qtyReserved;
    }

    public void reserveItem(int quantity) {
        if (quantity > getAvailableQuantity()) {
            throw new IllegalArgumentException("Not enough available quantity");
        }
        qtyReserved += quantity;
    }

    public void releaseItem(int quantity) {
        if (quantity > qtyReserved) {
            throw new IllegalArgumentException("Unable to release more than reserved");
        }
        qtyReserved -= quantity;
    }

    public void sellItem(int quantity) {
        if (quantity > qtyReserved) {
            throw new IllegalArgumentException("Unavailable quantity");
        }
        qtyReserved -= quantity;
        qtyTotal -= quantity;

        if (qtyTotal <= qtyLow) {
            placeInventoryOrder();
        }
    }

    public void placeInventoryOrder() {
        qtyTotal += qtyReoder;
    }

}

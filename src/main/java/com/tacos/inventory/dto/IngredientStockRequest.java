package com.tacos.inventory.dto;

public class IngredientStockRequest {

    private String id;

    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IngredientStockRequest{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

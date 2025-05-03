package com.tacos.inventory.model;

import javax.persistence.*;

@Entity
public class IngredientStock {

    @Id
    private String ingredientId;

    private int quantity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Ingredient ingredient;

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

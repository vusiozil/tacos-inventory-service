package com.tacos.inventory.dto;


import javax.validation.constraints.NotNull;
import java.util.Map;

public class DecreaseStockRequest {

    @NotNull(message = "Ingredient quantities must not be empty")
    private Map<String, Integer> ingredientQuantities;

    public Map<String, Integer> getIngredientQuantities() {
        return ingredientQuantities;
    }

    public void setIngredientQuantities(Map<String, Integer> ingredientQuantities) {
        this.ingredientQuantities = ingredientQuantities;
    }
}

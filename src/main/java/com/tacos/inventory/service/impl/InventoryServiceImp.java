package com.tacos.inventory.service.impl;

import com.tacos.inventory.model.IngredientStock;
import com.tacos.inventory.repository.IngredientStockRepository;
import com.tacos.inventory.service.InventoryService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;


@ApplicationScoped
public class InventoryServiceImp implements InventoryService {


    @Inject
    private IngredientStockRepository stockRepository;


    @Override
    public void decreaseStock(Map<String, Integer> ingredientQuantities) {

        for (Map.Entry<String, Integer> entry : ingredientQuantities.entrySet()) {

            String ingredientId = entry.getKey();

            int quantity = entry.getValue();

            IngredientStock stock = stockRepository
                    .getById(ingredientId)
                    .orElseThrow(() -> new RuntimeException("Ingredient stock not found of ID" + ingredientId));

            if (stock.getQuantity() < quantity) {
                throw new RuntimeException("Not enough stock for: " + ingredientId);
            }
            stock.setQuantity(stock.getQuantity() - quantity);

            stockRepository.saveOrUpdate(stock);
        }

    }
}

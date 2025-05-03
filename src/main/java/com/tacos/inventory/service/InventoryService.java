package com.tacos.inventory.service;

import java.util.Map;

public interface InventoryService {

    void decreaseStock(Map<String, Integer> ingredientQuantities);

}

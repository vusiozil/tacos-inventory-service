package com.tacos.inventory.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Ingredient implements Serializable {

    @NotNull
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IngredientType type;

    private double price;

    public Ingredient() {
    }

    public Ingredient(String id, String name, IngredientType type, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    public double getPrice() {
        return BigDecimal
                .valueOf(price)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}

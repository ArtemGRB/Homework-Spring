package org.skypro.skyshop.model.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;


    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + price;
    }
}

package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> basket = new HashMap<>();


    public void addProductToBasket(UUID id) {
        if (basket.get(id) == null) {
            basket.put(id, 1);
        } else {
            int temp = basket.get(id);
            basket.put(id, temp + 1);
        }
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }

}

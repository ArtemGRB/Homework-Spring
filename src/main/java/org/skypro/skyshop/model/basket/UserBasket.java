package org.skypro.skyshop.model.basket;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBasket {

    private List<BasketItem> basketItemsList;
    private int total;


    public UserBasket(List<BasketItem> basketItemsList) {
        this.basketItemsList = basketItemsList;
        this.total = basketItemsList.stream()
                .mapToInt(v-> v.getProduct().getPrice()* v.getQuantity()).sum();
    }

    public List<BasketItem> getBasketItemsList() {
        return basketItemsList;
    }

    public int getTotal() {
        return total;
    }
}

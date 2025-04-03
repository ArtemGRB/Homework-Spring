package org.skypro.skyshop.model.basket;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return total == that.total && Objects.equals(basketItemsList, that.basketItemsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketItemsList, total);
    }

    @Override
    public String toString() {
        return "UserBasket{" +
                "basketItemsList=" + basketItemsList +
                ", total=" + total +
                '}';
    }
}

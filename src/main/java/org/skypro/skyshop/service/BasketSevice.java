package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketSevice {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    private BasketSevice(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }


    public void addProductToBasket(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isPresent()) {
            productBasket.addProductToBasket(id);
        } else {
            throw new NoSuchProductException("Такого продукта нет");
        }
    }


    public UserBasket getUserBasket() {

        Map<Product, Integer> temp = productBasket.getBasket().entrySet().stream()
                .filter(entry -> storageService.getProductById(entry.getKey()).isPresent())
                .collect(Collectors.toMap(
                        entry -> storageService.getProductById(entry.getKey())
                                .orElseThrow(() -> new RuntimeException("Product not found")),
                        entity -> entity.getValue()));


        List<BasketItem> temp2 = temp.entrySet().stream()
                .map(k -> new BasketItem(k.getKey(), k.getValue()))
                .collect(Collectors.toList());

        return new UserBasket(temp2);
    }
}

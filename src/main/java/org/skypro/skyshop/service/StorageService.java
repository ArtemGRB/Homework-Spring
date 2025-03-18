package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService() {
        this.storageProduct = fillingTheMapWithProducts();
        this.storageArticle = fillingTheMapWithArticle();
    }

    public Map<UUID, Product> getStorageProduct() {
        return storageProduct;
    }

    public Map<UUID, Article> getStorageArticle() {
        return storageArticle;
    }

    private Map<UUID, Product> fillingTheMapWithProducts() {
        Map<UUID, Product> productsMap = new HashMap<>();
        Product product1 = new SimpleProduct("egg", 100, UUID.randomUUID());
        Product product2 = new SimpleProduct("milk", 80, UUID.randomUUID());
        Product product3 = new DiscountedProduct("cookie", 150, 10, UUID.randomUUID());
        Product product4 = new DiscountedProduct("cookie", 150, 10, UUID.randomUUID());
        Product product5 = new SimpleProduct("egg", 100, UUID.randomUUID());
        Product product6 = new FixPriceProduct("vegetable cutter", UUID.randomUUID());

        productsMap.put(product1.getID(), product1);
        productsMap.put(product2.getID(), product2);
        productsMap.put(product3.getID(), product3);
        productsMap.put(product4.getID(), product4);
        productsMap.put(product5.getID(), product5);
        productsMap.put(product6.getID(), product6);

        return productsMap;
    }

    private Map<UUID, Article> fillingTheMapWithArticle() {
        Map<UUID, Article> articlesMap = new HashMap<>();

        Article article1 = new Article("Ночник",
                "Ночник для новорожденных с генератором белого шума", UUID.randomUUID());
        Article article2 = new Article("Видеоняня",
                "Видеоняня с монитором, беспроводная 1280 * 720 HD", UUID.randomUUID());

        articlesMap.put(article1.getID(), article1);
        articlesMap.put(article2.getID(), article2);

        return articlesMap;
    }

    public Map<UUID, Searchable> getSumProductAndArticleMap() {
        Map<UUID, Searchable> sum = Stream.concat(
                        fillingTheMapWithArticle().entrySet().stream(),
                        fillingTheMapWithProducts().entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        return sum;
    }

}

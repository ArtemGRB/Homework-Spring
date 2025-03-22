package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketSevice;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketSevice basketSevice;

    public ShopController(StorageService storageService, SearchService searchService, BasketSevice basketSevice) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketSevice = basketSevice;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {

        return storageService.getStorageProduct().values().stream().toList();

    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {

        return storageService.getStorageArticle().values().stream().toList();

    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {

        Map<UUID, SearchResult> result = searchService.search(pattern);
        Collection<SearchResult> results = result.values().stream().toList();

        return results;
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id){
        basketSevice.addProductToBasket(id);
        return "*Продукт успешно добавлен*”";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket(){
        return basketSevice.getUserBasket();
    }
}

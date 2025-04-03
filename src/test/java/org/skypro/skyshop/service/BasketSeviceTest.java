package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BasketSeviceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketSevice basketSevice;

    @Test
    void addProductToBasket_whenNotUUID_thenThrowException() {
        UUID testUUID = UUID.randomUUID();
        Mockito.when(storageService.getProductById(testUUID)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketSevice.addProductToBasket(testUUID));
    }


    @Test
    void addProductToBasket_whenGetProductByIdReturnProduct_thenWillToAddProductToBasket() {
        UUID testUUID = UUID.randomUUID();
        Optional<Product> product = Optional.of(new SimpleProduct("egg", 100, testUUID));

        Mockito.when(storageService.getProductById(testUUID)).thenReturn(product);

        basketSevice.addProductToBasket(testUUID);

        Mockito.verify(productBasket).addProductToBasket(testUUID);
    }

    @Test
    void getUserBasket_whenBasketIsEmpty_thenUserBasketIsEmpty() {
        assertEquals(new UserBasket(new ArrayList<BasketItem>()), basketSevice.getUserBasket());
    }

    @Test
    void getUserBasket_whenBasketHasProducts() {
        Map<UUID, Integer> testMap = new HashMap<>();
        UUID testUUID = UUID.randomUUID();
        testMap.put(testUUID,1);
        Optional<Product> testProduct = Optional.of(new SimpleProduct("egg", 100, testUUID));

        List<BasketItem> testList = new ArrayList<>();
        testList.add(new BasketItem(new SimpleProduct("egg", 100, testUUID),1));

        Mockito.when(productBasket.getBasket()).thenReturn(testMap);
        Mockito.when(storageService.getProductById(testUUID)).thenReturn(testProduct);


        Assertions.assertEquals(new UserBasket(testList), basketSevice.getUserBasket());
    }

}
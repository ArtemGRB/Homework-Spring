package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private int basePrice;
    private int discount;


    public DiscountedProduct(String name, int basePrice, int discount, UUID id) {
        super(name, id);
        if (basePrice > 0) {
            this.basePrice = basePrice;
        } else {
            throw new IllegalArgumentException();
        }
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public int getPrice() {
        return basePrice - basePrice * discount / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " ( скидка: " + discount + "% )";
    }


    public static final class Article {

        private String title;
        private String article;

        @Override
        public String toString() {
            return title + '\'' + article;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }
    }
}

package com.example.sotest1;

import java.util.List;

public class TestModel {
    public static class ProductList {
        private List<Product> products;
        private int total;
        private int skip;
        private int limit;

        public List<Product> getProducts() {
            return products;
        }
    }
    public static class Product {
        private int id;
        private String title;
        private String description;
        private double price;
        private double discountPercentage;
        private double rating;
        private int stock;
        private String brand;
        private String category;
        private String thumbnail;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}
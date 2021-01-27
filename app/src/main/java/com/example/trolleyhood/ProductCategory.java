package com.example.trolleyhood;

public enum ProductCategory {
    meat,
    sweet,
    bread,
    fish,
    drinks,
    cosmetics,
    dry,
    fruits,
    dairy,
    vegetables,
    _default;

    static public ProductCategory parseCategory(String string) {
        switch(string) {
            case "meat":
                return meat;
            case "sweet":
                return sweet;
            case "bread":
                return bread;
            case "fish":
                return fish;
            case "drinks":
                return drinks;
            case "cosmetics":
                return cosmetics;
            case "dry":
                return dry;
            case "fruits":
                return fruits;
            case "dairy":
                return dairy;
            case "vegetables":
                return vegetables;
            default:
                return _default;
        }
    }
}



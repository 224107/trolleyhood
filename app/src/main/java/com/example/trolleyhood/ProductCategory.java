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
    vegetables;

    public String toString(){
        switch(this){
            case fruits :
                return "fruits";
        }
        return null;
    }

    }

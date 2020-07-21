package net.class101.server1.Product;

import lombok.Getter;

@Getter
public enum ProductEnum {
    categoryClass("categoryClass","클래스"),
    categoryKit("categoryKit","키트");

    private String category;
    private String name;

    ProductEnum(String category, String name) {
        this.category = category;
        this.name = name;
    }

}

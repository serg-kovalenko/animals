package com.kovalenko.task.entity;

public enum Categories {

    ANIMALS("animals"),
    NUMBERS("numbers"),
    CARS("cars");

    private String value;

    Categories(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

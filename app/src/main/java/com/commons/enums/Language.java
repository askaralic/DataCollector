package com.commons.enums;

public enum Language {
    English(1033), Arabic(14337);

    private final int value;

    Language(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

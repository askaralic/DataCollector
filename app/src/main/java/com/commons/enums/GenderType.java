package com.commons.enums;

public enum GenderType {
    UnKnown(0), Male(1), Female(2);

    private final int value;

    GenderType(int value) {
        this.value = value;
    }

    public static String getName(int value) {
        for (GenderType v : values()) {
            if (v.getValue() == value) {
                return v.name();
            }
        }
        return Male.name();
    }

    public int getValue() {
        return this.value;
    }
}

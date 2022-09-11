package com.commons.enums;

public enum MobileOSType {
    Android(1), iOS(2), CE(3);
    private final int value;

    MobileOSType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

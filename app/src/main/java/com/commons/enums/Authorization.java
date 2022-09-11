package com.commons.enums;

/**
 * Created by askar on 6/25/16.
 */

public enum Authorization {
    Auto(3),
    Open(2),
    LoginType(1);

    private final int value;

    Authorization(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }


}
package com.commons.enums;

/**
 * Created by askar ali on 12/5/2017.
 */

public enum UserVerificationMethod {

    SMS(1),
    EMAIL(2),
    Both(3);
    private final int value;

    UserVerificationMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

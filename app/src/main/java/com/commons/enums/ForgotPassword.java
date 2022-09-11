package com.commons.enums;

public enum ForgotPassword {
    WithUserNameAndEmail(0), WithUserName(1), WithEmail(2);

    private final int value;

    ForgotPassword(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

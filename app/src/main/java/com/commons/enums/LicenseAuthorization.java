package com.commons.enums;

/**
 * Created by Wajid on 03/13/2018.
 */

public enum LicenseAuthorization {
    Authorized(1), NotAuthorized(2);
    private final int value;

    LicenseAuthorization(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

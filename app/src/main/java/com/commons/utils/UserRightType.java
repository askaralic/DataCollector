package com.commons.utils;

/**
 * Created by Wajid on 16-11-2016.
 */

public enum UserRightType {
    HasView(1), HasAdd(2), HasEdit(3), HasDelete(4);

    private final int value;

    UserRightType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

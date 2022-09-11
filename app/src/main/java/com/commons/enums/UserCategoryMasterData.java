package com.commons.enums;

/**
 * Created by : Vrinda
 * Created on : 7/25/2020
 * Email : vrinda@dt.ae
 * Project : FTS-Services-Android
 */
public enum UserCategoryMasterData {

    Employee(6);

    private final int value;

    UserCategoryMasterData(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

package com.commons.enums;

/**
 * Created by askar on 6/25/16.
 */

public enum OperationTypes {

    FieldSupervisor(59),
    StoreManager(60),
    FieldTechnician(61);

    private final int value;

    OperationTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
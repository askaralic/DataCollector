package com.commons.enums;

/**
 * Created by askarali on 11/13/2017.
 */

public enum DTPProductType {
    SchoolBus(7), IMS(6), LogMan(5), BusMan(4), TaskMan(3), FleetMan(2), CabMan(1), General(0);
    private final int value;

    DTPProductType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

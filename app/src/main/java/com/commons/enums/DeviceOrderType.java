package com.commons.enums;

/**
 * Created by Wajid on 19-04-2016.
 */
public enum DeviceOrderType {

    Order(1), NRMFromApplication(2), NRMFromDevice(3), Taximeter(4),
    TaximeterFromApplication(8), FTMS(11), Recovery(14), LTMS(17), SelfOrder(24);

    private final int value;

    DeviceOrderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

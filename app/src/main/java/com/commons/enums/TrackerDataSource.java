package com.commons.enums;

/**
 * Created by Wajid on 14-02-2018.
 */

public enum TrackerDataSource {
    Bluetooth(1), Mqtt(2), Wifi(3),
    Server(4), LocalGPS(5);
    private final int value;

    TrackerDataSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

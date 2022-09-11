package com.commons.enums;

/**
 * Created by Wajid on 25-03-2018.
 */

//TaskManSL DeviceCommand for ZPH
public enum TaskManSLDeviceCommand {

    Booking(1), NRM(2),
    RevokeBooking(6), ForceLogout(11),
    MessageToVehicle(3), FlashMessage(3),
    CloseMisc(10), LocationToDevice(7);


    private final int value;

    TaskManSLDeviceCommand(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

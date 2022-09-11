package com.commons.enums;

public enum NotificationType {

    General(155),
    ProcessRequestApproval(180),
    VehicleRequestApproval(181),
    VehicleRequestApproved(182),
    VehicleRequestAssigned(183),
    VehicleRequestCancelled(184);

    private final int value;

    NotificationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

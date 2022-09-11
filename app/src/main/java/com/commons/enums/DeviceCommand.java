package com.commons.enums;

public enum DeviceCommand {

    FTMSJobAutoDispatch(43), FlashMessage(46), FTMSJobComplete(47), FTMSJobRevoke(48), FTMSNRMApproval(49), RecoveryJobDispatch(56), RecoveryRevoke(66),
    FTMSForceLogoff(67), FTMSJobOrderUpdate(57), FlashMessageJob(58), FlashMessageJobRevoke(59), LTMSJobDispatch(73), LTMSJobRevoke(74), NRM(37), LTMSUpdateOrderStatus(75),
    MessageToVehicle(41), CabManOrder(36), CabManOrderAutoDispatch(38),
    CabManOrderRevoke(39), CabManOrderUpdate(45), ForceLogout(2),
    DeviceLicenseUpdate(72), LocationToDevice(68), CabManAdditionalFareUpdate(76), WMSBooking(85), WMSBookingRevoke(86);

    private final int value;

    DeviceCommand(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

package com.commons.enums;

public enum WMSBookingStatus {


    New(1),
    Dispatched(2),
    Accepted(3),
    StartJob(4),
    ReachedLocation(5),
    StartCollection(6),
    CompleteCollection(7),
    StartDumping(8),
    CompleteDumping(9),
    TimeOut(10),
    Rejected(11),
    Cancelled(13),
    Assigned(15);


    private final int value;

    WMSBookingStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

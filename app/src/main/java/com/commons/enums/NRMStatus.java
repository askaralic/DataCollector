package com.commons.enums;

public enum NRMStatus {
    //Referring to TblMstNUINRMStatus
    New(1), SentToDevice(2), ReachedDevice(3),
    Commenced(4), Completed(5), Cancelled(6), Rejected(7);
    private final int value;

    NRMStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

package com.commons.utils;

public enum RecoveryOrderStatus {

    Dispatched(3), OrderReceived(4), Revoked(8), Moving(5), Commenced(6),
    Hold(9), Assigned(1), Completed(7), ReadytoDispatch(2), Rejected(12), TimeOut(13);

    private final int value;

    RecoveryOrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

package com.commons.enums;

public enum LogOrderStatus {

    Dispatched(3), OrderReceived(4), Revoked(8),
    Hold(9), TimeOut(10), Assigned(1), Completed(7), ReadytoDispatch(2);
    private final int value;

    LogOrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

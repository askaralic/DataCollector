package com.commons.enums;

public enum NRMOrderStatus {
    BreakRequest(1), Pending(2), Commenced(3),
    Completed(4), OrderReceived(12), Dispatched(13);
    private final int value;

    NRMOrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

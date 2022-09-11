package com.commons.enums;

public enum NUILTMSOrderStatus {


    New(1),
    Scheduled(2),
    DeliveryStarted(3),
    DeliveryCompletedwithoutVerified(4),
    DeliveryCompleted(5),
    PartialDeliveredAndRescheduled(6),
    PartialDeliveredAndCancelled(7),
    Rescheduled(8),
    UnDelivered(9),
    Cancelled(10),
    Dispatched(13),
    OrderInProgress(14),
    CrossDock(27);


    private final int value;

    NUILTMSOrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

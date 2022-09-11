package com.commons.enums;

public enum WMSTicketStatus {

    New(1),
    Assigned(2),
    Open(3),
    InProgress(4),
    Escalated(5),
    ReOpen(6),
    Reassigned(7),
    Rejected(9),
    Resolved(10),
    Closed(11);

    private final int value;

    WMSTicketStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

package com.commons.enums;

public enum OrderStatus {
    Dispatched(13), OrderReceived(12), Pending(2),
    Moving(10), Joining(11), Completed(4), TimeOut(0),
    Rejected(1), InformedGuest(15), OrderAlreadyExist(16),
    Commenced(3), LoggedOut(5), Dropped(6),
    Cancelled(7), Revoked(8),
    NotRevoked(9), RevokeSent(14), Invalid(-1), Stopped(22) ;
    //TODO need to map proper value for reached location

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

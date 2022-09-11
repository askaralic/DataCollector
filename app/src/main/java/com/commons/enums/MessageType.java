package com.commons.enums;

public enum MessageType {

    FCCTokenReceived(13), PushNotificationReceived(12), UpdateView(11), UpdateData(10), InstallUpdate(8), RefreshProfileImage(7), Standard(1), NRM(2),
    CUpdateView(5);// CUpdateView temporary add on TODO:

    private final int value;

    MessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

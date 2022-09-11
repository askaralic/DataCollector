package com.commons.enums;

/**
 * Created by askar on 6/25/16.
 */

public enum UserTypes {
    SuperAdmin(1),
    Provider(2),
    CompanyAdmin(3),
    User(4),
    Authority(5),
    MonitorUser(6),
    Admin(7);

    private final int value;

    UserTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
package com.commons.enums;

/**
 * Created by askar ali on 1/15/2018.
 */

public enum PaymentSubType {
    Cash(1),
    Credit(2),
    CreditCard(3),
    Complimentary(4),
    Voucher(5),
    Membership(6),
    Cheque(7),
    CashDeposit(8),
    Online(9);
    private final int value;

    PaymentSubType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

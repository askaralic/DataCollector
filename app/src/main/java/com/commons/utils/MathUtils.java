package com.commons.utils;

public class MathUtils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    public static String digitWithTwoDecimalPoints(double amount) {
        StringBuilder amountText = new StringBuilder();

        if (amount < 10) {
            amountText.append("0").append(String.format("%.2f", amount));
            return amountText.toString();
        } else {
            return String.format("%.2f", amount);
        }

    }
}

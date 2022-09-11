package com.commons.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GConvert {
    public static int ToInt(Object objValue) {
        int Value;
        try {
            Value = Integer.parseInt((String) objValue);
        } catch (Exception ex) {
            Value = 0;
        }
        return Value;
    }

    public static long ToInt64(Object objValue) {
        long Value;
        try {
            Value = Long.parseLong((String) objValue);
        } catch (Exception ex) {
            Value = 0;
        }
        return Value;
    }

    public static double ToDouble(Object objValue) {
        double Value;
        try {
            Value = Double.parseDouble((String) objValue);
        } catch (Exception ex) {
            Value = 0;
        }
        return Value;
    }

    public static float ToFloat(Object objValue) {
        float Value;
        try {
            Value = Float.parseFloat((String) objValue);
        } catch (Exception ex) {
            Value = 0;
        }
        return Value;
    }

    public static double roundTo(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

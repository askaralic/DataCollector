package com.commons.utils;

import java.util.Vector;

public class Parsing {
    public static String[] split(final String toSplit, final char sep) {
        if (toSplit == null)
            return new String[0];

        int next = -1;
        int previous = 0;
        // FILLING STRING
        final Vector<String> list = new Vector<>();
        do {
            next = toSplit.indexOf(sep, next + 1);
            if (previous == next)
                list.add("");
            else
                list.add(toSplit.substring(previous, (next != -1) ? next
                        : toSplit.length()));
            previous = next + 1;
        } while (previous != 0);

        final String[] res = new String[list.size()];
        list.toArray(res);
        return res;
    }

    public static int getInt(final String value, final int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (final Throwable e) {
            return defaultValue;
        }
    }

    public static float getFloat(final String value, final float defaultValue) {
        try {
            return Float.parseFloat(value);
        } catch (final Throwable e) {
            return defaultValue;
        }
    }

}

package com.commons.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wajid on 29-03-2016.
 */
public class TimeUtils {
    public static final DateTimeFormatter dateTimeFmt = DateTimeFormat.forPattern("MMM-dd-yyyy hh:mm a");
    public static final DateTimeFormatter dateTimeSecFmt = DateTimeFormat.forPattern("MMM-dd-yyyy hh:mm:ss a");
    public static final DateTimeFormatter dateTimeStdFmt = DateTimeFormat.forPattern("dd-MMM-yyyy hh:mm a");
    public static final DateTimeFormatter dateFmt = DateTimeFormat.forPattern("dd-MMM-yyyy");
    public static final DateTimeFormatter timeFmt = DateTimeFormat.forPattern("hh:mm a");
    public static final String dateTime12HrPattern = "dd-MMM-yyyy hh:mm a";
    public static final String dateTime12HrSecPattern = "dd-MMM-yyyy hh:mm:ss a";
    public static final String dateTimeDayFmt = "EEE dd-MMM-yyyy hh:mm a";
    public static final DateTimeFormatter dateTime =  DateTimeFormat.forPattern("MMM dd,yyyy");

    public static long getTimeZoneOffset() {
        long offsetInMillis = DateTimeZone.forID(TimeZone.getDefault().getID())
                .getOffset(new DateTime().getMillis());
        return TimeUnit.MILLISECONDS.toSeconds(offsetInMillis);
    }

    public static String getUTCTimeString() {
        return TimeUtils.dateFmt.print(new DateTime(DateTimeZone.UTC));
    }

    public static long getEpochTimeInUTC() {
        return (new DateTime(DateTimeZone.UTC).getMillis() / 1000);
    }

    public static long getEpochTimeInGMT() {
        return new DateTime().plusSeconds((int) getTimeZoneOffset()).getMillis() / 1000;
    }

    public static String ToDateString(long epochDate) {
        try {
            return dateFmt.print(new DateTime(epochDate * 1000L));
        } catch (Exception e) {
            return "";
        }
    }
  public static String ToDateFormattedString(long epochDate) {
        try {
            return dateTime.print(new DateTime(epochDate * 1000L));
        } catch (Exception e) {
            return "";
        }
    }

    public static String ToTimeString(long epochDate) {
        try {
            return timeFmt.print(new DateTime(epochDate * 1000L));
        } catch (Exception e) {
            return "";
        }
    }

    public static String ToDateTimeString(long epochDate) {
        try {
            return dateTimeFmt.print(new DateTime(epochDate * 1000L));
        } catch (Exception e) {
            return "";
        }
    }

    public static String ToDateTimeStringFromGMT(long epochDate) {
        try {
            return dateTimeFmt.print(new DateTime(epochDate * 1000L).minusSeconds((int) getTimeZoneOffset()));
        } catch (Exception e) {
            return "";
        }
    }

    public static String ToDateTimeSecondString(long epochDate) {
        try {
            return dateTimeSecFmt.print(new DateTime(epochDate * 1000L));
        } catch (Exception e) {
            return "";
        }
    }

    public static String ToStdDateTimeString(DateTimeFormatter dateTimeSecFmt, long epochDate) {
        try {
            return dateTimeSecFmt.print(new DateTime(epochDate * 1000L));
        } catch (Exception e) {
            return "";
        }
    }

    public static DateTime ToDateTimeFromEpoch(long epochDate) {
        try {
            return new DateTime(epochDate * 1000L);
        } catch (Exception e) {
            return null;
        }
    }

    public static long ToEpochTime(DateTime dateTime) {
        return dateTime.getMillis() / 1000;
    }

    public static long ToEpochTime(long millis) {
        return millis / 1000;
    }

    public static String toDateFormatString(Date date, String pattern) {
        try {
            DateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentDate() {
        String pattern = "dd/MM/yyyy";
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date());
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        String localTime = date.format(currentLocalTime);
        return localTime;
    }

    public static DateTime ToDateTimeFromDateTimeSecFmtString(String stringDate) {
        try {
            return dateTimeSecFmt.parseDateTime(stringDate);
        } catch (Exception e) {
            return null;
        }
    }



    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
           // LOGE(TAG, "ParseException - dateFormat");
        }

        return outputDate;

    }

    public static String getTimeSince(DateTime dateTime) {
        return getTimeSince(dateTime.getMillis());
    }

    public static String getTimeSince(long timeInMillis) {
        DateTime myBirthDate = new DateTime(timeInMillis);
        DateTime now = new DateTime();
        Period period = new Period(myBirthDate, now);

        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendSeconds().appendSuffix(" seconds ago\n")
                .appendMinutes().appendSuffix(" minutes ago\n")
                .appendHours().appendSuffix(" hours ago\n")
                .appendDays().appendSuffix(" days ago\n")
                .appendWeeks().appendSuffix(" weeks ago\n")
                .appendMonths().appendSuffix(" months ago\n")
                .appendYears().appendSuffix(" years ago\n")
                .printZeroNever()
                .toFormatter();

        return formatter.print(period);


    }
}

package com.dt.baseapplication.utils;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.dt.baseapplication.R;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by askarali on 7/21/2016.
 */
public class GenUtils {
    public static void showDateTimePicker(final Activity activity, DateTime minDateTime, DateTime defaultDateTime, final OnDateSetListener i) {
        DateTime now = DateTime.now();
        if(defaultDateTime == null){
            defaultDateTime = new DateTime();
        }
        MonthAdapter.CalendarDay minDate = null;

        if(minDateTime !=null){
            minDate = new MonthAdapter.CalendarDay( minDateTime.getYear(), minDateTime.getMonthOfYear() - 1, minDateTime.getDayOfMonth() );

        }

        MonthAdapter.CalendarDay maxDate = new MonthAdapter.CalendarDay( now.getYear(), now.getMonthOfYear() - 1, now.getDayOfMonth() );

        DateTime finalDefaultDateTime = defaultDateTime;
        CalendarDatePickerDialogFragment toDatePickerDialog = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener( (dialog, year, monthOfYear, dayOfMonth) -> {
                    final DateTime selDate = DateTime.now().withYear( year ).withMonthOfYear( monthOfYear + 1 ).withDayOfMonth( dayOfMonth );
                    RadialTimePickerDialogFragment timePicker = new RadialTimePickerDialogFragment();
                    if(minDateTime == null){
                        timePicker.setPastMinutesLimit(0);
                    }else {
                        timePicker.setPastMinutesLimit( minDateTime.getMinuteOfHour() )
                                .setPastMinutesLimit( Minutes.minutesBetween( minDateTime, finalDefaultDateTime).getMinutes() );

                    }
                    timePicker.setStartTime(finalDefaultDateTime.getHourOfDay(), finalDefaultDateTime.getMinuteOfHour());
                    timePicker.setOnTimeSetListener( (dialog1, hourOfDay, minute) -> {
                        DateTime selDateTime = selDate.withHourOfDay( hourOfDay ).withMinuteOfHour( minute );
                        i.onDateSet( selDateTime );
                    } );
                    timePicker.setDoneText( activity.getString( R.string.btn_done ) );
                    timePicker.setCancelText( activity.getString( R.string.btn_cancel ) );
                    timePicker.show( ((AppCompatActivity)activity).getSupportFragmentManager(), Constants.REQUEST_DATE_PICKER );
                } )
                .setFirstDayOfWeek( Calendar.SATURDAY )
                .setPreselectedDate( defaultDateTime.getYear(), defaultDateTime.getMonthOfYear() - 1, defaultDateTime.getDayOfMonth() )
                .setDoneText( activity.getString( R.string.btn_done ) )
                .setDateRange( minDate, null )
                .setCancelText( activity.getString( R.string.btn_cancel ) );
        toDatePickerDialog.show( ((AppCompatActivity)activity).getSupportFragmentManager(), Constants.REQUEST_DATE_PICKER );

    }

    public static void showDatePicker(final Activity activity, DateTime minDateTime,DateTime maxDateTime, DateTime defaultDateTime, final OnDateSetListener i) {
        DateTime now = DateTime.now();
        if(defaultDateTime == null){
            defaultDateTime = now;
        }
        MonthAdapter.CalendarDay minDate = null;

        if(minDateTime !=null){
            minDate = new MonthAdapter.CalendarDay( minDateTime.getYear(), minDateTime.getMonthOfYear() - 1, minDateTime.getDayOfMonth() );

        }

        MonthAdapter.CalendarDay maxDate = new MonthAdapter.CalendarDay( maxDateTime.getYear(), maxDateTime.getMonthOfYear(), maxDateTime.getDayOfMonth() );

        CalendarDatePickerDialogFragment toDatePickerDialog = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener( (dialog, year, monthOfYear, dayOfMonth) -> {
                    final DateTime selDate = DateTime.now().withYear( year ).withMonthOfYear( monthOfYear + 1 ).withDayOfMonth( dayOfMonth );
                    i.onDateSet( selDate );
                } )
                .setFirstDayOfWeek( Calendar.SATURDAY )
                .setPreselectedDate( defaultDateTime.getYear(), defaultDateTime.getMonthOfYear() - 1, defaultDateTime.getDayOfMonth() )
                .setDoneText( activity.getString( R.string.btn_done ) )
                .setDateRange( minDate, null )
                .setCancelText( activity.getString( R.string.btn_cancel ) );
        toDatePickerDialog.show( ((AppCompatActivity)activity).getSupportFragmentManager(), Constants.REQUEST_DATE_PICKER );

    }


    public static void showTimePicker(final Activity activity, DateTime minDateTime, DateTime defaultDateTime, final OnDateSetListener i) {
        DateTime now = DateTime.now();
        if(defaultDateTime == null){
            defaultDateTime = now;
        }
        RadialTimePickerDialogFragment timePicker = new RadialTimePickerDialogFragment();
        if(minDateTime == null){
            timePicker.setPastMinutesLimit(0);
        }else {
            timePicker.setPastMinutesLimit( minDateTime.getMinuteOfHour() )
                    .setPastMinutesLimit( Minutes.minutesBetween( minDateTime,defaultDateTime).getMinutes() );

        }
        timePicker.setStartTime(defaultDateTime.getHourOfDay(), defaultDateTime.getMinuteOfHour());
        timePicker.setOnTimeSetListener( (dialog1, hourOfDay, minute) -> {
            DateTime selDateTime = now.withHourOfDay( hourOfDay ).withMinuteOfHour( minute );
            i.onDateSet( selDateTime );
        } );
        timePicker.setDoneText( activity.getString( R.string.btn_done ) );
        timePicker.setCancelText( activity.getString( R.string.btn_cancel ) );
        timePicker.show( ((AppCompatActivity)activity).getSupportFragmentManager(), Constants.REQUEST_DATE_PICKER );

    }

    public static String generateRandomOtp(){
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.valueOf(number);
    }


   public static String fetchOtp(String message) {
      Pattern pattern = Pattern.compile("(\\d{6})");
       Matcher matcher = pattern.matcher(message);
       String otp = "";
        if (matcher.find()) {
            otp = matcher.group();
        }
        return otp;
    }

    public interface OnDateSetListener {
        void onDateSet(DateTime selectedDate);
    }
}

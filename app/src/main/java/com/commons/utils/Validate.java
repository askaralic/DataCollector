package com.commons.utils;

import android.widget.EditText;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Collection;
import java.util.List;

/**
 * Created by askarali on 8/21/2017.
 */

public class Validate {
    public static boolean isEmpty(List<?> param) {
        return !(param != null && !param.isEmpty() && param.size() != 0);
    }

    public static boolean isEmpty(Collection param) {
        return !(param != null && !param.isEmpty() && param.size() != 0);
    }

    public static boolean isEmpty(Class<?> param) {
        return (param == null);
    }

    public static boolean isEmpty(EditText param) {
        return !(param != null && !param.getText().toString().isEmpty() && !param.getText().toString().trim().isEmpty());
    }

    public static boolean isEmpty(String param) {
        return !(param != null && !param.isEmpty() && !param.trim().isEmpty());
    }

    public static boolean isEmpty(CharSequence param) {
        return !(param != null && !param.toString().isEmpty() && !param.toString().trim().isEmpty());
    }


    public static boolean isEmpty(String[] param) {
        return !(param != null && param.length != 0);
    }

    public static boolean isEmpty(Object[] param) {
        return !(param != null && param.length != 0);
    }

    public static boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9,-]*$";
        return s.matches(pattern);
    }

    public static boolean isValidEmail(String target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidMobile(String phone, String selectedCountryCode) {

        Phonenumber.PhoneNumber userMobileNumber;

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            userMobileNumber = phoneUtil.parse(phone,
                    selectedCountryCode);
            phoneUtil.format(userMobileNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        } catch (NumberParseException e) {
            return false;
        }
        return phoneUtil.isValidNumberForRegion(userMobileNumber,selectedCountryCode);
    }

    public static String getValidMobileNumber(String phone, String selectedCountryCode) {

        Phonenumber.PhoneNumber userMobileNumber;

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();


        try {
            userMobileNumber = phoneUtil.parse(phone,
                    selectedCountryCode);
            return phoneUtil.format(userMobileNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        } catch (NumberParseException e) {
            return "";
        }
        
    }

    public static boolean isValidEmiratesID(String emiratesID) {
        String pattern = "[0-9]+";
        return emiratesID.matches(pattern) && emiratesID.length() == 15;
    }

    public static boolean isValidPassword(String text) {
        boolean isAtLeast8 = text.length() >= 8;//Checks for at least 8 characters
        boolean hasSpecial = !text.matches("[A-Za-z0-9 ]*");//Checks at least one char is not alpha numeric
        boolean hasUppercase = !text.equals(text.toLowerCase());//Checks at least one upper case letter
        return isAtLeast8 && hasSpecial && hasUppercase;
    }

    public static boolean isValidPassword(EditText text) {
        if (!isEmpty(text)) {
            boolean isAtLeast8 = text.getText().toString().trim().length() >= 8;//Checks for at least 8 characters
            boolean hasSpecial = !text.getText().toString().trim().matches("[A-Za-z0-9 ]*");//Checks at least one char is not alpha numeric
            boolean hasUppercase = !text.getText().toString().trim().equals(text.getText().toString().trim().toLowerCase());//Checks at least one upper case letter
            return isAtLeast8 && hasSpecial && hasUppercase;
        } else {
            return false;
        }
    }

    public static boolean isEqual(String param1, String param2) {
        return !(isEmpty(param1) || isEmpty(param2)) && param1.toLowerCase().trim().equals(param2.toLowerCase().trim());
    }


    public static boolean isEqual(EditText param1, String param2) {
        return !(isEmpty(param1) || isEmpty(param2)) && param1.getText().toString().toLowerCase().trim().equals(param2.toLowerCase().trim());
    }

    public static boolean isEqual(EditText param1, EditText param2) {
        return !(isEmpty(param1) || isEmpty(param2)) && param1.getText().toString().toLowerCase().trim().equals(param2.getText().toString().toLowerCase().trim());
    }

    public static boolean isContain(EditText param1, EditText param2) {
        return !(isEmpty(param1) || isEmpty(param2)) && (param1.getText().toString().toLowerCase().trim().contains(param2.getText().toString().toLowerCase().trim()) || param2.getText().toString().toLowerCase().trim().contains(param1.getText().toString().toLowerCase().trim()));
    }

    public static boolean isContain(String param1, String param2) {
        return !(isEmpty(param1) || isEmpty(param2)) && (param1.toLowerCase().trim().contains(param2.toLowerCase().trim()) || param2.toLowerCase().trim().contains(param1.toLowerCase().trim()));
    }


}

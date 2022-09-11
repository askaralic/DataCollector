package com.commons.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.app.ActivityCompat;

import com.dt.datacollector.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteOrder;
import java.util.Locale;
import java.util.TimeZone;


import com.commons.enums.NUIAttachmentId;

/**
 * Created by Wajid on 29-03-2016.
 */
public class GenUtils {

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String getDeviceGooglePlayServiceVersion(Context context) {
        try {
            PackageInfo pinfo;
            pinfo = context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return pinfo.versionName;// + "(" + pinfo.versionCode + ")";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDeviceVersionNumber(Context context) {
        String DeviceVersionNumber = "";
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            DeviceVersionNumber = String.valueOf(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return DeviceVersionNumber;
    }

    public static boolean checkPlayServices(final Activity context) {
        try {
            final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
            GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();

            int result = googleAPI.isGooglePlayServicesAvailable(context);
            if (result != ConnectionResult.SUCCESS) {
                if (googleAPI.isUserResolvableError(result)) {
                    googleAPI.getErrorDialog(context, result,
                            PLAY_SERVICES_RESOLUTION_REQUEST).show();
                }
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int getDeviceVersionCode(Context context) {
        int DeviceVersionCode = 0;
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            DeviceVersionCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return DeviceVersionCode;
    }


    /**
     * Not considering Serial Number
     *
     * @param context
     * @return IMEI number is available
     */
    public static String getDeviceIMEINumber(Context context) {
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                imei = telephonyManager.getDeviceId();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    public static String getDeviceSerialNumber(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        String SerialNumber = "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            SerialNumber = Build.SERIAL;
        } else {
            SerialNumber = Build.getSerial();
        }
        return SerialNumber;
    }

    public static String getDeviceMACAddress(Context context) {
        String macAddress = "";
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = null;
        if (wifiManager != null) {
            wInfo = wifiManager.getConnectionInfo();
            macAddress = wInfo.getMacAddress();
        }
        return macAddress;
    }

    public static void hideSoftKeyboard(Context context, IBinder token) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(token, 0);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static boolean isGPSEnabled(Context context) {
        LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locManager != null) {
            return locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            return false;
        }
    }

    public static String getDeviceDetails(Context context) {
        String text = "";
        try {

            text += "Version: " + System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")";
            text += "API Level: " + Build.VERSION.RELEASE + "(" + Build.VERSION.SDK_INT + ")";
            text += "Manufacturer: " + Build.MANUFACTURER.toUpperCase(new Locale("en"));
            text += "Model: " + Build.MODEL;
            text += "Google Play Services: " + context.getResources().getInteger(R.integer.google_play_services_version);
            text += "TimeZone: " +
                    TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT) + " (" + TimeZone.getDefault().getID() + ")";
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }

        return text;

    }

    public static String getDeviceName() {
        try {
            String manufacturer = Build.MANUFACTURER;

            String model = Build.MODEL + " ("
                    + Build.VERSION.RELEASE
                    + "-" + Build.VERSION.SDK_INT + ")";

            if (model.startsWith(manufacturer)) {
                return capitalize(model);
            } else {
                return capitalize(manufacturer) + " " + model;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String capitalize(String s) {
        try {
            if (s == null || s.length() == 0) {
                return "";
            }
            char first = s.charAt(0);
            if (Character.isUpperCase(first)) {
                return s;
            } else {
                return Character.toUpperCase(first) + s.substring(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean isManufacturerLGwithJB() {
        String manuf = Build.MANUFACTURER;
        return manuf.contentEquals("LGE") && Build.VERSION.SDK_INT == 16;
    }

    public static boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9,-]*$";
        return s.matches(pattern);
    }

    public static boolean isValidEmail(String target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public static String getGatewayIPAddress(Context context) {
        try {
            WifiManager wifiManager =
                    (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            int ipAddress = wifiManager.getDhcpInfo().gateway;
            ipAddress = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ?
                    Integer.reverseBytes(ipAddress) : ipAddress;
            byte[] ipAddressBytes = BigInteger.
                    valueOf(ipAddress).toByteArray();
            return InetAddress.
                    getByAddress(ipAddressBytes).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isAutomaticDateTimeEnabled(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT <= 16) {
            result = Settings.System.
                    getInt(context.getContentResolver(),
                            Settings.System.AUTO_TIME, 0);
        } else {
            if ((Settings.Global.getInt(context.
                    getContentResolver(), Settings.Global.
                    AUTO_TIME_ZONE, 0) > 0)
                    && (Settings.Global.getInt(context.
                    getContentResolver(), Settings.Global.AUTO_TIME, 0) > 0))
                result = 1;
        }
        return result > 0;
    }

    public static String getNationalNumber(String phone) {
        String nationalNumber = phone;
        if (!Validate.isEmpty(phone)) {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            try {
                // phone must begin with '+'
                Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phone, "");
                int countryCode = numberProto.getCountryCode();
                nationalNumber = String.valueOf(numberProto.getNationalNumber());
            } catch (NumberParseException e) {
                System.err.println("NumberParseException was thrown: " + e);
            }
        }
        return nationalNumber;
    }

    public static String getCountryCode(String phone) {
        String CountryCode = null;
        if (!Validate.isEmpty(phone)) {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            try {
                // phone must begin with '+'
                Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phone, "");
                CountryCode = "+" + numberProto.getCountryCode();
            } catch (NumberParseException e) {
                System.err.println("NumberParseException was thrown: " + e);
            }
        }
        return CountryCode;
    }

    public static String getImageURL(String baseUrl, NUIAttachmentId userAttachment, int dataUno) {
        String imageURL = null;
        URL url = null;

        try {
            url = new URL(baseUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url != null) {
            if (!Validate.isEmpty(url.getPath())) {
                String virtualDirectory = url.getPath().split("/")[1];
                imageURL = url.getProtocol() + "://" + url.getAuthority() + "/" + virtualDirectory + "/Common/Attachment?";
            }
        }
        return imageURL + "AttachmentID=" + userAttachment.getValue() + "&DataUno=" + dataUno;
    }

    /**
     * @param context
     * @return imei/serial number
     */
    public static String getDeviceIMEI(Context context) {
        String IMEI = "", SerialNumber = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                IMEI = telephonyManager.getDeviceId();

                if (Validate.isEmpty(IMEI)) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        SerialNumber = Build.SERIAL;
                    } else {
                        SerialNumber = Build.getSerial();
                    }

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Validate.isEmpty(IMEI) ? SerialNumber : IMEI;
    }

    public static ContextWrapper changeLang(Context context, String lang_code) {
        Locale sysLocale;

        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.getLocales().get(0);
        } else {
            sysLocale = config.locale;
        }
        if (!lang_code.equals("") && !sysLocale.getLanguage().equals(lang_code)) {
            Locale locale = new Locale(lang_code);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }

        return new ContextWrapper(context);
    }


    /**
     * @param context
     * @return Android ID as an unique for each user 64-bit hex string
     */
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}

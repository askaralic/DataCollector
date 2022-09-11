package com.commons.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Wajid on 01-08-2016.
 */
public class NavigationUtils {
    public static void navigate(Context context, float dropOffLatitude, float dropOffLongitude) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + dropOffLatitude + "," + dropOffLongitude));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateTo2gis(Context context, float dropOffLatitude, float dropOffLongitude) {

        try {
            if (is2gisAvailable(context)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("dgis://2gis.ru/routeSearch/rsType/car/to/" + dropOffLongitude + "," + dropOffLatitude));
                intent.setPackage("ru.dublgis.dgismobile");
                context.startActivity(intent);
                //+ dropOffLatitude + "," + dropOffLongitude
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("market://details?id=ru.dublgis.dgismobile"));

                context.startActivity(intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is2gisAvailable(Context context) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("ru.dublgis.dgismobile");
        return launchIntent != null;
    }
}

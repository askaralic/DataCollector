package com.commons.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

/**
 * Created by Wajid on 03-04-2016.
 */
public class RuntimePermissionUtils {

    private static Object object;

    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissionsGranted(Context ctx, String[] permissions) {
        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(ctx, permission)
                    != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;

    }

    public static boolean checkPermissionGranted(Context ctx, String permission) {


        return PermissionChecker.checkSelfPermission(ctx, permission) == PackageManager.PERMISSION_GRANTED;

    }

    public static void requestPermissions(Context ctx, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions((Activity) ctx, permissions, requestCode);
    }

    public static void setObject(Object object) {
        RuntimePermissionUtils.object = object;
    }

    public static Object getObject() {
        return RuntimePermissionUtils.object;
    }
}

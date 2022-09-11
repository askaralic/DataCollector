package com.commons.utils;

import android.net.Uri;
import android.webkit.MimeTypeMap;

import java.io.File;

/**
 * Created by askar ali on 1/13/2018.
 */

public class RetrofitUtils {
    public static String getFileMimeType(File file) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public static String getExtension(File file) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        return "." + extension;
    }
}



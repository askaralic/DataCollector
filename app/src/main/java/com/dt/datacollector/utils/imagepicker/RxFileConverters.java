package com.dt.datacollector.utils.imagepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxFileConverters {

    public static Observable<File> uriToFile(final Context context, final Uri uri, final File file) {
        return Observable.create( (ObservableOnSubscribe<File>) emitter -> {
            try {
                InputStream inputStream = context.getContentResolver().openInputStream(uri);
                copyInputStreamToFile(inputStream, file);
                emitter.onNext(file);
                emitter.onComplete();
            } catch (Exception e) {
                Log.e(RxFileConverters.class.getSimpleName(), "Error converting uri", e);
                emitter.onError(e);
            }
        } ).subscribeOn( Schedulers.newThread()).observeOn( AndroidSchedulers.mainThread());
    }

    public static Observable<Bitmap> uriToBitmap(final Context context, final Uri uri) {
        return Observable.create( (ObservableOnSubscribe<Bitmap>) emitter -> {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                emitter.onNext(bitmap);
                emitter.onComplete();
            } catch (IOException e) {
                Log.e(RxFileConverters.class.getSimpleName(), "Error converting uri", e);
                emitter.onError(e);
            }
        } ).subscribeOn( Schedulers.newThread()).observeOn( AndroidSchedulers.mainThread());
    }

    private static void copyInputStreamToFile(InputStream in, File file) throws IOException {
        OutputStream out = new FileOutputStream(file);
        byte[] buf = new byte[10 * 1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        in.close();
    }

}

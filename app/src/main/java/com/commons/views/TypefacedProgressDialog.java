package com.commons.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by Wajid on 24-Jun-15.
 */
class TypefacedProgressDialog extends ProgressDialog {

    private final Context context;

    public TypefacedProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = this.findViewById(android.R.id.message);
        if (view != null) {
            //     view.setTypeface(TypefaceCache.get(context.getAssets(), context.getString(R.string.app_font)));
        }
    }

}

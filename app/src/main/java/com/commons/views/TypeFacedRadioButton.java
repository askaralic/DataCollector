/**
 *
 */
package com.commons.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.dt.datacollector.R;


/**
 * @author Wajid
 */
public class TypeFacedRadioButton extends AppCompatRadioButton {


    public TypeFacedRadioButton(Context context, String font) {
        super(context);

        // Typeface.createFromAsset doesn't work in the layout editor.
        // Skipping...
        if (isInEditMode()) {
            return;
        }

        if (font != null) {

            setTypeface(TypefaceCache.get(context.getAssets(), font));
        }
    }

    public TypeFacedRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Typeface.createFromAsset doesn't work in the layout editor.
        // Skipping...
        if (isInEditMode()) {
            return;
        }
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.TypefacedTextView);
        String fontName = styledAttrs
                .getString(R.styleable.TypefacedTextView_typeface);
        styledAttrs.recycle();
        if (fontName != null) {
            setTypeface(TypefaceCache.get(context.getAssets(), fontName), Typeface.BOLD);
        }
    }

    public TypeFacedRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) {
            return;
        }
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.TypefacedTextView);
        String fontName = styledAttrs
                .getString(R.styleable.TypefacedTextView_typeface);
        styledAttrs.recycle();
        if (fontName != null) {
            setTypeface(TypefaceCache.get(context.getAssets(), fontName), Typeface.BOLD);
        }
    }

}

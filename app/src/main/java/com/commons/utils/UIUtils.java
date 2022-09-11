package com.commons.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import com.commons.views.ScreenUtils;
import com.commons.views.TypefaceCache;
import com.commons.views.TypefaceSpan;

/**
 * Created by Wajid on 16-05-2016.
 */
public class UIUtils {
    public static int getInDp(Context ctx, int px) {
        return (int) ScreenUtils.pxToDp(ctx, px);
    }

    public static int getInPx(Context ctx, int px) {
        return (int) ScreenUtils.dpToPx(ctx, px);
    }

    public static void setTypeFaceToTabs(TabLayout slidingTabs, String fontName, Context ctx) {
        ViewGroup vg = (ViewGroup) slidingTabs.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(TypefaceCache.get(ctx.getAssets(), fontName));
                }
            }
        }
    }

    public static SpannableString getSpannableString(Context context, String title, int colorResource, String font) {

        SpannableString s = new SpannableString(title);
        StyleSpan bss = new StyleSpan(Typeface.BOLD);
        s.setSpan(new TypefaceSpan(context, font)
                , 0,
                s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
     /*   s.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(this,
                        colorResource)), 0, title.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        s.setSpan(bss, 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;

    }
}

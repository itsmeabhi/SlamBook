package com.gmonetix.slambook.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class Font {

    public void setFont(Context _context, TextView textView) {
        Typeface roboto = Typeface.createFromAsset(_context.getAssets(), "font.otf");
        textView.setTypeface(roboto);
    }
}

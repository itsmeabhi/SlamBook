package com.gmonetix.slambook.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.TextView;

public class Utils {

    public void setFont(Context _context, TextView textView) {
        Typeface roboto = Typeface.createFromAsset(_context.getAssets(), "font.otf");
        textView.setTypeface(roboto);
    }

    public void showFileChooser(Activity activty, int REQUEST_CODE) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activty.startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);
    }

}

package com.jmmunoza.analisisnumerico.util;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public class KeyboardManager {
    public static void hide(Context context, IBinder windowToken){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }
}
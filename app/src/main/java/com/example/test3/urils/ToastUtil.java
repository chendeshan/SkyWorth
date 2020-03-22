package com.example.test3.urils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class ToastUtil {
    private final static String TAG = "ToastManager";
    private final static long TOAST_TIME_INTERVAL  = 2 * 1000 + 50; // 2 SECONDS

    private static long sLastToastTime      = 0;
    private static String sLastToastContent = null;

    public static void showToast(final Activity context, final String message) {

        if (!checkToastAvailable(message)) {
            Log.w(TAG, "Toast show too fast.");
            return;
        }

        Log.i(TAG, "Toast show");
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static boolean checkToastAvailable(String message) {

        if (TextUtils.isEmpty(message)) {
            return false;
        }

        if (sLastToastTime > System.currentTimeMillis()) {
            sLastToastTime = System.currentTimeMillis();
        }

        if (sLastToastTime == 0) {
            sLastToastTime = System.currentTimeMillis();
            sLastToastContent = message;
            return true;
        }

        long now = System.currentTimeMillis();
        if (now - sLastToastTime < TOAST_TIME_INTERVAL && sLastToastContent.equals(message)) {
            return false;
        }

        sLastToastTime = now;
        sLastToastContent = message;
        return true;
    }
}

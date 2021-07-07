package com.bc.appwidget.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 守护进程的广播接收器
 *
 * @author zhou
 */
public class DaemonReceiver extends BroadcastReceiver {

    private static final String TAG = "Global.DaemonReceiver";

    public static final String ACTION_DAEMON_RECEIVER = "com.bc.appwidget.action.DAEMON_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Global receiver, action: " + intent.getAction());

        if (android.os.Build.VERSION.SDK_INT >= 14) {
            // Widget仅支持4.0+
            Intent service = new Intent();
            service.setClass(context, ScheduleRemoteViews.class);
            context.startService(service);
        }
    }

}

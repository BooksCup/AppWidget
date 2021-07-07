package com.bc.appwidget.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;

/**
 * 日程窗口小部件
 *
 * @author zhou
 */
public class ScheduleAppWidget extends AppWidgetProvider {

    private static final String TAG = "ScheduleAppWidget";
    public static final String ACTION_REFRESH_MANUAL = "com.bc.appwidget.action.APPWIDGET_REFRESH_MANUAL";
    public static final String ACTION_REFRESH_AUTO = "com.bc.appwidget.action.APPWIDGET_REFRESH_AUTO";
    public static final String ACTION_JUMP_LISTITEM = "com.bc.appwidget.action.APPWIDGET_JUMP_LISTITEM";
    public static final String ACTION_JUMP_LOGO = "com.bc.appwidget.action.APPWIDGET_JUMP_LOGO";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        if (android.os.Build.VERSION.SDK_INT < 14) {
            Log.e(TAG, "Not support version less than 14!!!");
            return;
        }
        ScheduleRemoteViews scheduleRemoteViews = new ScheduleRemoteViews(context);
        scheduleRemoteViews.setOnLogoClickPendingIntent();
        scheduleRemoteViews.setOnRefreshClickPendingIntent();
        scheduleRemoteViews.bindListViewAdapter();

        appWidgetManager.updateAppWidget(appWidgetIds, scheduleRemoteViews);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}
package com.bc.appwidget.widget;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.widget.RemoteViewsService;

import com.bc.appwidget.utils.Utils;

@SuppressLint("NewApi")
public class ScheduleWidgetService extends RemoteViewsService implements Runnable {

    private static final String TAG = "ScheduleWidgetService";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ScheduleRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(this).start();
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void run() {
        // 实时发送一个更新的广播
        final String pref_key = "appwidget_news_refresh_time";
        final long updatePeriod = 10 * 60 * 1000;
        final long lastRefreshTime = Utils.getLong(this, pref_key, 0);
        final long now = System.currentTimeMillis();
        if (now - lastRefreshTime >= updatePeriod) {
            // 10分钟内不执行重复的后台更新请求
            Utils.putLong(this, pref_key, now);

            Intent refreshNowIntent = new Intent(this, ScheduleAppWidget.class);
            refreshNowIntent.setAction(ScheduleAppWidget.ACTION_REFRESH_AUTO);
            sendBroadcast(refreshNowIntent);
        }

        Intent autoRefreshIntent = new Intent(this, ScheduleAppWidget.class);
        autoRefreshIntent.setAction(ScheduleAppWidget.ACTION_REFRESH_AUTO);
        PendingIntent pending = PendingIntent.getBroadcast(ScheduleWidgetService.this, 0, autoRefreshIntent, 0);

        // 1*60秒更新一次
        final long updateTime = 1 * 60 * 1000;
        Time time = new Time();
        long nowMillis = System.currentTimeMillis();
        time.set(nowMillis + updateTime);
        long updateTimes = time.toMillis(true);

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        // Log.d(TAG, "request next update at " + updateTimes);
        // Log.d(TAG, "refresh time: " + sdf.format(new Date()));

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, updateTimes, pending);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

}
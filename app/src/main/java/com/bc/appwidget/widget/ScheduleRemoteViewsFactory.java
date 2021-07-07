package com.bc.appwidget.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.TypedValue;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bc.appwidget.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "ScheduleRemoteViewsFactory";

    private static final int VIEW_TYPE_COUNT = 1;

    private List<Schedule> mScheduleList = new ArrayList<>();

    private Context mContext;

    public ScheduleRemoteViewsFactory(Context context, Intent intent) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int getCount() {
        if (mScheduleList == null) {
            return 0;
        }

        return mScheduleList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Schedule getSchedule(int index) {
        return mScheduleList.get(index);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @SuppressLint("NewApi")
    @Override
    public RemoteViews getViewAt(int position) {
        if (getCount() == 0) {
            return null;
        }

        ScheduleRemoteViews scheduleRemoteViews = new ScheduleRemoteViews(mContext);
        scheduleRemoteViews.loadComplete();

        Schedule item = getSchedule(position);
        return scheduleRemoteViews.applyScheduleView(item);
    }

    /**
     * 设置字体大小
     *
     * @param views
     * @param viewId
     * @param textSize
     */
    @SuppressLint("NewApi")
    private void setRemoteViewsTextSize(RemoteViews views, int viewId, int textSize) {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            // 低版本不支持字体适配
            views.setTextViewTextSize(viewId, TypedValue.COMPLEX_UNIT_SP, textSize / 2);
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {
        mScheduleList.clear();
        SystemClock.sleep(2000);
        mScheduleList = getScheduleList();
    }

    private List<Schedule> getScheduleList() {
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule());
        scheduleList.add(new Schedule());
        scheduleList.add(new Schedule());
        scheduleList.add(new Schedule());
        scheduleList.add(new Schedule());
        scheduleList.add(new Schedule());
        return scheduleList;
    }

    @Override
    public void onDestroy() {
        mScheduleList.clear();
    }

}
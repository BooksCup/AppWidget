package com.bc.appwidget.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import com.bc.appwidget.R;
import com.bc.appwidget.entity.Schedule;


public class ScheduleRemoteViews extends RemoteViews {

    private Context mContext;

    private AppWidgetManager mAppWidgetManager;

    private int[] mAppWidgetIds;

    public ScheduleRemoteViews(Context context) {
        super(context.getPackageName(), R.layout.layout_widget_schedule_list);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mAppWidgetManager = AppWidgetManager.getInstance(mContext);
        this.mAppWidgetIds = getAppWidgetIds();
    }

    private Class<? extends AppWidgetProvider> getAppWidgetProvider() {
        return ScheduleAppWidget.class;
    }

    private Intent getProviderIntent() {
        return new Intent(mContext, getAppWidgetProvider());
    }

    public int[] getAppWidgetIds() {
        ComponentName provider = new ComponentName(mContext, getAppWidgetProvider());
        return mAppWidgetManager.getAppWidgetIds(provider);
    }

	public void loadComplete() {
		mAppWidgetManager.updateAppWidget(mAppWidgetIds, this);
	}

    public void setOnLogoClickPendingIntent() {
//		Intent intent = getProviderIntent();
//		intent.setAction(NewsAppWidgetProvider.ACTION_JUMP_LOGO);
//		PendingIntent logoPendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
//		setOnClickPendingIntent(R.id.widget_logo, logoPendingIntent);
    }

    public void setOnRefreshClickPendingIntent() {
//		Intent intent = getProviderIntent();
//		intent.setAction(NewsAppWidgetProvider.ACTION_REFRESH_MANUAL);
//		PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
//		setOnClickPendingIntent(R.id.widget_refresh, refreshPendingIntent);
    }

    public void bindListViewAdapter() {
        int listViewResId = R.id.lv_schedule;
        Intent serviceIntent = new Intent(mContext, ScheduleWidgetService.class);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
        // rv.setEmptyView(listViewResId, R.id.tv_empty);//????????????view??????????????????view
        setRemoteAdapter(listViewResId, serviceIntent);

        // ???????????? ListView ???intent??????
        // (01) ?????? setPendingIntentTemplate??????"intent??????"
        // (02) ??????????????????"????????????"???RemoteViewsFactory??????getViewAt()??????????????? setOnClickFillInIntent??????"?????????????????????????????????"
        Intent listItemIntent = getProviderIntent();
        listItemIntent.setAction(ScheduleAppWidget.ACTION_JUMP_LISTITEM);
        listItemIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, listViewResId);
        PendingIntent pendingIntentTemplate = PendingIntent.getBroadcast(mContext, 0, listItemIntent, 0);
        // ??????intent??????
        setPendingIntentTemplate(listViewResId, pendingIntentTemplate);
    }

    public void notifyAppWidgetViewDataChanged() {
        int[] appIds = getAppWidgetIds();
        // ??????ListView
        mAppWidgetManager.notifyAppWidgetViewDataChanged(appIds, R.id.lv_schedule);
    }

    public RemoteViews applyScheduleView(final Schedule schedule) {
        if (schedule == null) {
            return null;
        }

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.item_schedule);
//        views.setViewVisibility(R.id.tv_title, View.VISIBLE);
//        views.setViewVisibility(R.id.tv_date, View.VISIBLE);
//
//        views.setTextViewText(R.id.tv_title, schedule.getTitle());
//        views.setTextViewText(R.id.tv_date, schedule.getDate());

//		Intent fillInIntent = new Intent();
//		Bundle extras = new Bundle();
//		extras.putString(GConstants.SCHEME_DATA_KEY, news.toJSON().toString());
//		fillInIntent.putExtras(extras);
//		// ?????? ???position??????"??????"?????????????????????, api 11
//		views.setOnClickFillInIntent(R.id.news_container, fillInIntent);

        return views;
    }

}

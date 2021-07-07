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
        // rv.setEmptyView(listViewResId, R.id.tv_empty);//指定集合view为空时显示的view
        setRemoteAdapter(listViewResId, serviceIntent);

        // 设置响应 ListView 的intent模板
        // (01) 通过 setPendingIntentTemplate设置"intent模板"
        // (02) 然后在处理该"集合控件"的RemoteViewsFactory类的getViewAt()接口中通过 setOnClickFillInIntent设置"集合控件的某一项的数据"
        Intent listItemIntent = getProviderIntent();
        listItemIntent.setAction(ScheduleAppWidget.ACTION_JUMP_LISTITEM);
        listItemIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, listViewResId);
        PendingIntent pendingIntentTemplate = PendingIntent.getBroadcast(mContext, 0, listItemIntent, 0);
        // 设置intent模板
        setPendingIntentTemplate(listViewResId, pendingIntentTemplate);
    }

    public void notifyAppWidgetViewDataChanged() {
        int[] appIds = getAppWidgetIds();
        // 更新ListView
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
//		// 设置 第position位的"视图"对应的响应事件, api 11
//		views.setOnClickFillInIntent(R.id.news_container, fillInIntent);

        return views;
    }

}

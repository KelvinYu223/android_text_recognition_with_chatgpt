package com.example.mytextrecognition;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;

public class Text_Detect_Widget extends AppWidgetProvider {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.text_detect_widget);
            views.setOnClickPendingIntent(R.id.text_detect_button, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

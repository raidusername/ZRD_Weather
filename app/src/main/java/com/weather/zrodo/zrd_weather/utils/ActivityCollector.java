package com.weather.zrodo.zrd_weather.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by td on 2018/4/17.
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {

        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {

        activities.remove(activity);
    }

    public static void finishAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        //杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());

    }

}

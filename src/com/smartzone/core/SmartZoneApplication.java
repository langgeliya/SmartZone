package com.smartzone.core;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.smartzone.core.utils.CommUtils;

public class SmartZoneApplication extends Application {
	
	public static Context mContext = null;
	public ArrayList<Activity> activityList = new ArrayList<Activity>();
	private static String ScreenOrientation = "";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if(SmartZoneApplication.mContext == null)
			SmartZoneApplication.mContext = this;
	}
	
	public static Context getContext() {
		if (mContext != null)
			return mContext.getApplicationContext();
		return null;
	}
	
	public void addActivity(Activity a) {
		if (activityList != null) {
			activityList.add(a);
		} else {
			activityList = new ArrayList<Activity>();
			activityList.add(a);
		}

	}

	public void removeActivity(Activity a) {
		if (a != null && activityList != null) {
			activityList.remove(a);
		}
	}

	public void closeAllActivity() {
		if (activityList != null) {
			for (int i = 0; i < activityList.size(); i++) {
				Activity a = activityList.get(i);
				if (a != null && !a.isFinishing()) {
					a.finish();
				}
			}
			activityList.clear();
			activityList = null;
		}
	}
	public static String getScreenOrientation(Context context) {
		if (TextUtils.isEmpty(ScreenOrientation)) {
			ScreenOrientation = CommUtils.getString(context,
					"ScreenOrientation");
			if (ScreenOrientation == null) {
				ScreenOrientation = "";
			}
		}
		return ScreenOrientation;
	}
	
	public static boolean getScreenOrientation() {
		if (getScreenOrientation(mContext).equals("landscape"))
			return false;
		return true;
	}

}

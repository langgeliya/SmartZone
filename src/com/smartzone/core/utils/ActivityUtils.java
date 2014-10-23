package com.smartzone.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.smartzone.core.R;
import com.smartzone.core.lib.SmartZoneMainActivity;

public class ActivityUtils {
	
	public static Activity convertActivity(Context context) {
		try {
			Activity activity = (Activity) context;
			return activity;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	// 通用的带参数启动activity
		public static void startActivity(Context context, Intent intent) {
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);

			Activity activity = convertActivity(context);
			if (activity != null)
				activity.overridePendingTransition(R.anim.push_right_in,
						R.anim.no_anim);
		}

		// 通用的带参数启动activity
		public static void startActivityFromBottom(Context context, Intent intent) {
			context.startActivity(intent);

			Activity activity = convertActivity(context);
			if (activity != null)
				activity.overridePendingTransition(R.anim.push_bottom_in,
						R.anim.no_anim);
		}
	
	public static void startMainActivity(Context mContext) {
		Intent intent = new Intent(mContext, SmartZoneMainActivity.class);
		startActivity(mContext, intent);
	}
	
	public static void finishActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			activity.overridePendingTransition(R.anim.no_anim,
					R.anim.push_right_out);
		}
	}
}

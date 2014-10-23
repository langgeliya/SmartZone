package com.smartzone.core.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.smartzone.core.SmartZoneApplication;

public class CommUtils {

	// 得到屏幕的宽高
	private static int[] mScreenSize = { -1, -1 };

	public static void initScreenSize() {
		mScreenSize[0] = -1;
		mScreenSize[1] = -1;
	}

	public static void initScreenSize(Context context) {
		if (mScreenSize[0] == -1) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			int width = display.getWidth();
			int height = display.getHeight();
			if (SmartZoneApplication.getScreenOrientation()) {
				mScreenSize[0] = Math.min(width, height);
				mScreenSize[1] = Math.max(width, height);
			} else {
				mScreenSize[0] = Math.max(width, height);
				mScreenSize[1] = Math.min(width, height);
			}
		}
	}

	public static int getScreenWidth() {
		return mScreenSize[0];
	}

	public static int getScreenHeight() {
		return mScreenSize[1];
	}

	public static String getString(Context context, String keyName) {
		return (String) readKey(context, keyName);
	}

	/*
	 * 读主配置文件通用方法
	 */
	private static Object readKey(Context context, String keyName) {
		try {
			ApplicationInfo appInfo = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			Bundle bundle = appInfo.metaData;
			Object value = bundle.get(keyName);
			return value;
		} catch (NameNotFoundException e) {
			return null;
		}
	}

	/*
	 * 检测是否有sdcard卡
	 */
	public static boolean isHaveSdcard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}
}

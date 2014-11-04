package com.smartzone.core.utils;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.smartzone.core.SmartZoneApplication;

public class CommUtils {

	// 得到屏幕的宽高
	private static int[] mScreenSize = { -1, -1 };
	public static final String ENCODE_GBK = "gbk";
	public static final String ENCODE_UTF = "utf-8";
	
	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
	}

	public static void initScreenSize() {
		mScreenSize[0] = -1;
		mScreenSize[1] = -1;
	}
	public static String byteToString(byte[] response) {
		String result = "";
		try {
			result = EncodingUtils.getString(response, ENCODE_UTF);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			result = "";
		}
		return result;
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
	
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	public static void showToast(Context context, String message) {
		try {
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showToast(Context context, int resId) {
		try {
			Toast.makeText(context, context.getString(resId),
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void exitApp(Context context) {
		SmartZoneApplication app = (SmartZoneApplication) context
				.getApplicationContext();
		app.closeAllActivity();
		System.exit(0);//正常退出App
	}
	public static int dip2px(Context context, float dpValue) {
		if (context == null) {
			context = SmartZoneApplication.mContext;
		}
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
}

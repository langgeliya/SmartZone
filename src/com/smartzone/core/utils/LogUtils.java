package com.smartzone.core.utils;

import android.util.Log;

public class LogUtils {
	public static String TAG1 = "smartzone";
	public static boolean LOG_ON = true; // ！！！！默认为关闭，开发可以自行打开，但是切记不要提交到svn
	public static void printByTag(String tag, String content) {
		if(LOG_ON){
			Log.v(tag, content);
		}
	}
	public static void debugLog(String tag, String content) {
		if(LOG_ON){
			Log.d(tag, content);
		}
	}
}

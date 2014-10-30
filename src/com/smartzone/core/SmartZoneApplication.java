package com.smartzone.core;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.text.TextUtils;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.smartzone.core.utils.CommUtils;

public class SmartZoneApplication extends Application {
	
	public static Context mContext = null;
	public ArrayList<Activity> activityList = new ArrayList<Activity>();
	private static String ScreenOrientation = "";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if(SmartZoneApplication.mContext == null){
			SmartZoneApplication.mContext = this;
		}
		initImageLoader(getApplicationContext());
	}
	
	public static void initImageLoader(Context context) {

		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		boolean log = false;
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.bitmapConfig(getBitmapConfig()).cacheOnDisk(true)
				.considerExifParams(true).build();

		ImageLoaderConfiguration config = null;
		if (log) {
			config = new ImageLoaderConfiguration.Builder(context)
					.threadPriority(Thread.NORM_PRIORITY - 2)
					.denyCacheImageMultipleSizesInMemory()
					.diskCacheFileNameGenerator(new Md5FileNameGenerator())
					.tasksProcessingOrder(QueueProcessingType.FIFO)
					.defaultDisplayImageOptions(options).writeDebugLogs()
					.build();
		} else {
			config = new ImageLoaderConfiguration.Builder(context)
					.threadPriority(Thread.NORM_PRIORITY - 2)
					.denyCacheImageMultipleSizesInMemory()
					.diskCacheFileNameGenerator(new Md5FileNameGenerator())
					.tasksProcessingOrder(QueueProcessingType.FIFO)
					.defaultDisplayImageOptions(options).build();
		}
		com.nostra13.universalimageloader.utils.L.writeDebugLogs(log);
		com.nostra13.universalimageloader.utils.L.writeLogs(log);
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	
	private static Config getBitmapConfig() {
		if (mContext != null) {
			int memory = ((ActivityManager) mContext
					.getSystemService(Context.ACTIVITY_SERVICE))
					.getMemoryClass();
			// 如果系统内存低于128M，则使用16位位图
			if (memory < 128) {
				return Bitmap.Config.RGB_565;
			}
		}
		return Bitmap.Config.ARGB_8888;
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

package com.smartzone.core.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MainViewPager extends ViewPager {

	private int mIsDown = 1;
	private float mLastMotionX;
	private float mLastMotionY;
	private boolean enabled;

	public MainViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.enabled = false;
	}

	public MainViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		if (this.enabled) {
//			return false;
//		}
		final float x = ev.getX();
		final float y = ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);
			mIsDown = 1;
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mIsDown == 1) {
				if (x - mLastMotionX > 5 && getCurrentItem() == 0) {
					mIsDown = 0;
					getParent().requestDisallowInterceptTouchEvent(false);
				}

				if (enabled) {
					mIsDown = 0;
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		if (this.enabled) {
//			return false;
//		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		if (this.enabled) {
//			return false;
//		}
		return super.onTouchEvent(event);
	}

	@Override
	public void setAdapter(PagerAdapter arg0) {
		if (arg0.getCount() > 1) {
			onResume();
		}
		super.setAdapter(arg0);
	}

	public void onPause() {
	}

	public void onResume() {
	}

	public void setPagingEnabled(boolean enabled) {
		//true->不能滑动; false->可以滑动
		this.enabled = enabled;
	}

}

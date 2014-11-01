package com.smartzone.adapter;

import com.smartzone.core.view.JazzyViewPager;
import com.smartzone.core.view.OutlineContainer;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class BannerAdapter extends PagerAdapter {
	private Context mContext;
	private JazzyViewPager mJazzy;
	public BannerAdapter(Context mContext, JazzyViewPager mJazzy) {
		this.mContext = mContext;
		this.mJazzy = mJazzy;
	}
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		TextView text = new TextView(mContext);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(30);
		text.setTextColor(Color.WHITE);
		text.setText("Page " + position);
		text.setPadding(30, 30, 30, 30);
		int bg = Color.rgb((int) Math.floor(Math.random()*128)+64, 
				(int) Math.floor(Math.random()*128)+64,
				(int) Math.floor(Math.random()*128)+64);
		text.setBackgroundColor(bg);
		container.addView(text, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mJazzy.setObjectForPosition(text, position);
		return text;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object obj) {
		container.removeView(mJazzy.findViewFromObject(position));
	}
	@Override
	public int getCount() {
		return 5;
	}
	@Override
	public boolean isViewFromObject(View view, Object obj) {
		if (view instanceof OutlineContainer) {
			return ((OutlineContainer) view).getChildAt(0) == obj;
		} else {
			return view == obj;
		}
	}		
}

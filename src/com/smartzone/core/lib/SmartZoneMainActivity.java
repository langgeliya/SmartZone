package com.smartzone.core.lib;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartzone.core.BaseFragmentActivity;
import com.smartzone.core.R;
import com.smartzone.core.adapter.MainViewPagerAdapter;
import com.smartzone.core.utils.CommUtils;
import com.smartzone.core.utils.LogUtils;

public class SmartZoneMainActivity extends BaseFragmentActivity {
	private ViewPager viewPager = null;
	private ArrayList<Fragment> fragments = null;
	private FirstPageFragment firstPage = null;
	private SecondPageFragment secondPage = null;
	private ThirdPageFragment thirdPage = null;
	private FourthPageFragment fourthPage = null;
	private MainViewPagerAdapter pagerAdapter = null;
	// bottom tab
	private RelativeLayout tab_relativelayout1, tab_relativelayout2,
			tab_relativelayout3, tab_relativelayout4;
	private ImageView tab_imageview1, tab_imageview2, tab_imageview3,
			tab_imageview4;
	private TextView tab_txt1, tab_txt2, tab_txt3, tab_txt4;
	private View tab_bg_view, tab_bg_rangle;
	private int mSelectIndex = -1;
	private ArrayList<TextView> mTabTextList = null;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		initTitleBar();
		setLeftBtnOrNot(View.INVISIBLE);
		setPageTitle("my page");
		init();
		initListener();
		moveToSelectedPage(0);
	}

	private void init() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		tab_bg_view = (View) findViewById(R.id.tab_bg_view);
		tab_bg_rangle = (View) findViewById(R.id.tab_bg_rangle);
		tab_txt1 = (TextView) findViewById(R.id.txt_first);
		tab_txt2 = (TextView) findViewById(R.id.txt_second);
		tab_txt3 = (TextView) findViewById(R.id.txt_third);
		tab_txt4 = (TextView) findViewById(R.id.txt_fourth);
		tab_relativelayout1 = (RelativeLayout) findViewById(R.id.tab_first);
		tab_relativelayout2 = (RelativeLayout) findViewById(R.id.tab_second);
		tab_relativelayout3 = (RelativeLayout) findViewById(R.id.tab_third);
		tab_relativelayout4 = (RelativeLayout) findViewById(R.id.tab_fourth);
		mTabTextList = new ArrayList<TextView>();
		mTabTextList.add(tab_txt1);
		mTabTextList.add(tab_txt2);
		mTabTextList.add(tab_txt3);
		mTabTextList.add(tab_txt4);
		fragments = new ArrayList<Fragment>();
		firstPage = new FirstPageFragment();
		secondPage = new SecondPageFragment();
		thirdPage = new ThirdPageFragment();
		fourthPage = new FourthPageFragment();
		fragments.add(firstPage);
		fragments.add(secondPage);
		fragments.add(thirdPage);
		fragments.add(fourthPage);
		pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
		pagerAdapter.setData(fragments);
		viewPager.setAdapter(pagerAdapter);
	}

	// TAB栏切换动画
	private synchronized void startAnimation(int index) {
		if (mSelectIndex == -1)
			mSelectIndex = 0;

		int w = CommUtils.getScreenWidth() / 4;
		LogUtils.printByTag(LogUtils.TAG1, "w:" + w);
		TranslateAnimation ta = new TranslateAnimation(mSelectIndex * w, index
				* w, 0, 0);
		ta.setDuration(300);
		ta.setFillAfter(true);
		tab_bg_view.startAnimation(ta);
		tab_bg_rangle.startAnimation(ta);

		mSelectIndex = index;
		for (int i = 0; i < mTabTextList.size(); i++) {
			TextView curTv = mTabTextList.get(i);
			if (i == mSelectIndex) {
				curTv.setTextColor(getResources().getColor(R.color.tab_sel));
			} else {
				curTv.setTextColor(getResources().getColor(R.color.tab_def));
			}
		}
	}

	private void initListener() {
		tab_relativelayout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				moveToSelectedPage(0);
			}
		});
		tab_relativelayout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				moveToSelectedPage(1);
			}
		});
		tab_relativelayout3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				moveToSelectedPage(2);
			}
		});
		tab_relativelayout4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				moveToSelectedPage(3);
			}
		});
	}

	private void moveToSelectedPage(int page) {
		startAnimation(page);
		viewPager.setCurrentItem(page);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}

package com.smartzone.core.lib;

import java.util.ArrayList;

import org.apache.http.Header;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
		setPageTitle("书香苑小区");
		init();
		initListener();
		moveToSelectedPage(0);
	}

	private void init() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setOffscreenPageLimit(3);
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
		tab_imageview1 = (ImageView) findViewById(R.id.img_first);
		tab_imageview2 = (ImageView) findViewById(R.id.img_second);
		tab_imageview3 = (ImageView) findViewById(R.id.img_third);
		tab_imageview4 = (ImageView) findViewById(R.id.img_fourth);
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
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int page) {
				// TODO Auto-generated method stub
				moveToSelectedPage(page);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

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
		changeTab(page);
		viewPager.setCurrentItem(page);
	}

	private void changeTab(int page) {
		switch (page) {
		case 0:
			tab_imageview1
					.setImageResource(R.drawable.icon_home_index_selected);
			tab_txt1.setTextColor(getResources().getColor(R.color.tab_sel));
			tab_imageview2
					.setImageResource(R.drawable.icon_home_discover_normal);
			tab_txt2.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview3
					.setImageResource(R.drawable.icon_home_community_normal);
			tab_txt3.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview4.setImageResource(R.drawable.icon_home_me_normal);
			tab_txt4.setTextColor(getResources().getColor(R.color.tab_def));
			break;
		case 1:
			tab_imageview1.setImageResource(R.drawable.icon_home_index_normal);
			tab_txt1.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview2
					.setImageResource(R.drawable.icon_home_discover_selected);
			tab_txt2.setTextColor(getResources().getColor(R.color.tab_sel));
			tab_imageview3
					.setImageResource(R.drawable.icon_home_community_normal);
			tab_txt3.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview4.setImageResource(R.drawable.icon_home_me_normal);
			tab_txt4.setTextColor(getResources().getColor(R.color.tab_def));
			break;
		case 2:
			tab_imageview1.setImageResource(R.drawable.icon_home_index_normal);
			tab_txt1.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview2
					.setImageResource(R.drawable.icon_home_discover_normal);
			tab_txt2.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview3
					.setImageResource(R.drawable.icon_home_community_selected);
			tab_txt3.setTextColor(getResources().getColor(R.color.tab_sel));
			tab_imageview4.setImageResource(R.drawable.icon_home_me_normal);
			tab_txt4.setTextColor(getResources().getColor(R.color.tab_def));
			break;
		case 3:
			tab_imageview1.setImageResource(R.drawable.icon_home_index_normal);
			tab_txt1.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview2
					.setImageResource(R.drawable.icon_home_discover_normal);
			tab_txt2.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview3
					.setImageResource(R.drawable.icon_home_community_normal);
			tab_txt3.setTextColor(getResources().getColor(R.color.tab_def));
			tab_imageview4.setImageResource(R.drawable.icon_home_me_selected);
			tab_txt4.setTextColor(getResources().getColor(R.color.tab_sel));
			break;
		default:
			break;
		}
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

	private void initNetWorking() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://www.google.com", new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
				// called before request is started
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] response) {
				// called when response HTTP status is "200 OK"
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] errorResponse, Throwable e) {
				// called when response HTTP status is "4XX" (eg. 401, 403, 404)
			}

			@Override
			public void onRetry(int retryNo) {
				// called when request is retried
			}
		});
	}

}

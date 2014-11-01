package com.smartzone.core;

import com.smartzone.core.utils.ActivityUtils;
import com.smartzone.core.utils.CommUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseFragmentActivity extends FragmentActivity implements
		OnClickListener {

	// 标题栏文字
	public TextView mTitleView;
	// 标题栏左侧，右侧图标
	public ImageButton mLeftBtn, mRight1Btn, mRight2Btn;
	public RelativeLayout sec_title_layout, title_right_layout_2;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		CommUtils.initScreenSize();
		CommUtils.initScreenSize(this);
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

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
	}

	protected void initTitleBar() {
		mTitleView = (TextView) findViewById(R.id.sec_title_tv);
		mLeftBtn = (ImageButton) findViewById(R.id.title_left_img);
		mRight1Btn = (ImageButton) findViewById(R.id.title_right_img_1);
		mRight2Btn = (ImageButton) findViewById(R.id.title_right_img_2);
		sec_title_layout = (RelativeLayout) findViewById(R.id.sec_title_layout);
		title_right_layout_2 = (RelativeLayout) findViewById(R.id.title_right_layout_2);
		if (mLeftBtn != null)
			mLeftBtn.setOnClickListener(this);
		if (mRight1Btn != null)
			mRight1Btn.setOnClickListener(this);
//		if (mRight2Btn != null)
//			mRight2Btn.setOnClickListener(this);
	}

	protected void setPageTitle(String title) {
		if (mTitleView != null) {
			mTitleView.setText(title);
		}
	}
	
	
	protected void setLeftBtnOrNot(int i) {
		if(mLeftBtn != null){
			mLeftBtn.setVisibility(i);
		}
	}

	protected void setRight1Btn(int resId) {
		if (mRight1Btn != null) {
			mRight1Btn.setVisibility(View.VISIBLE);
			mRight1Btn.setImageResource(resId);
		}
	}

	protected void setRight2Btn(int resId) {
		if (mRight2Btn != null) {
			mRight2Btn.setVisibility(View.VISIBLE);
			mRight2Btn.setImageResource(resId);
		}
	}

	public void setRight1BtnClickListenser(View.OnClickListener listenser) {
		if (mRight1Btn != null) {
			mRight1Btn.setVisibility(View.VISIBLE);
			mRight1Btn.setOnClickListener(listenser);
		}
	}

	public void setRight2BtnClickListenser(View.OnClickListener listenser) {
		if(mRight2Btn != null){
			mRight2Btn.setVisibility(View.VISIBLE);	
		}
		if (title_right_layout_2 != null) {
			title_right_layout_2.setOnClickListener(listenser);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_left_img:
			ActivityUtils.finishActivity(this);
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			ActivityUtils.finishActivity(this);
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

}

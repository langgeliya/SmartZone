package com.smartzone.core.lib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartzone.core.BaseFragmentActivity;
import com.smartzone.core.R;

public class ClassifyActivity extends BaseFragmentActivity {
	private String title;
	private LinearLayout failLayout;
	private TextView failText;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_classify);
		initIntent();
		initTitleBar();
		setPageTitle(title);
		setRight2Btn(R.drawable.icon_search);
		init();
		initListener();
		refresh();
	}
	
	private void init() {
		failLayout = (LinearLayout) findViewById(R.id.failLayout);
		failText = (TextView) findViewById(R.id.failText);
	}
	
	private void initListener() {
		setRight2BtnClickListenser(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void initIntent() {
		Bundle b = getIntent().getExtras();
		if(b != null){
			title = b.getString("title");
		}
	}
	
	public void refresh() {
		failLayout.setVisibility(View.VISIBLE);
		failText.setText("网络请求失败，请重新尝试。");
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

package com.smartzone.core.lib;

import android.content.Intent;
import android.os.Bundle;

import com.smartzone.core.BaseFragmentActivity;
import com.smartzone.core.R;

public class ClassifyActivity extends BaseFragmentActivity {
	private String title;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_classify);
		initIntent();
		initTitleBar();
		setPageTitle(title);
		setRight2Btn(R.drawable.icon_search);
	}
	
	private void initIntent() {
		Bundle b = getIntent().getExtras();
		if(b != null){
			title = b.getString("title");
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
}

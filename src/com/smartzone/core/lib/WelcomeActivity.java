package com.smartzone.core.lib;

import com.smartzone.core.R;
import com.smartzone.core.R.layout;
import com.smartzone.core.R.menu;
import com.smartzone.core.utils.ActivityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		ActivityUtils.startMainActivity(getApplicationContext());
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}

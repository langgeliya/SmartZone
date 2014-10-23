package com.smartzone.core.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> fragments = null;
	public MainViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	public void setData(ArrayList<Fragment> fragments) {
		if(fragments != null)
			this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

}

package com.smartzone.core.lib;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.smartzone.adapter.BannerAdapter;
import com.smartzone.adapter.ImformationAdapter;
import com.smartzone.bean.TypeBean;
import com.smartzone.core.R;
import com.smartzone.core.view.JazzyViewPager;
import com.smartzone.core.view.JazzyViewPager.TransitionEffect;

public class ThirdPageFragment extends Fragment {
	private GridView grid;
	private ArrayList<TypeBean> mData;
	private ImformationAdapter mAdapter;
	private JazzyViewPager mJazzy;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	private void setupJazziness(View v, TransitionEffect effect) {
		mJazzy = (JazzyViewPager) v.findViewById(R.id.type_banner);
		mJazzy.setTransitionEffect(effect);
		mJazzy.setOffscreenPageLimit(5);
		mJazzy.setAdapter(new BannerAdapter(getActivity(), mJazzy));
		mJazzy.setPageMargin(30);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_imformatiogn, null);
		init(view);
		setupJazziness(view, TransitionEffect.Tablet);
		initData();
		return view;
	}

	private void init(View view) {
		mData = new ArrayList<TypeBean>();
		mAdapter = new ImformationAdapter(mData, getActivity());
		grid = (GridView)view.findViewById(R.id.gridView);
		grid.setAdapter(mAdapter);
	}
	
	private void initData() {
		TypeBean t1 = new TypeBean();
		t1.title = "外卖";
		
		TypeBean t2 = new TypeBean();
		t2.title = "生活配送";
		
		TypeBean t3 = new TypeBean();
		t3.title = "城市便利";
		
		TypeBean t4 = new TypeBean();
		t4.title = "水果";
		
		TypeBean t5 = new TypeBean();
		t5.title = "家政服务";
		
		TypeBean t6 = new TypeBean();
		t6.title = "更多";
		mData.add(t1);
		mData.add(t2);
		mData.add(t3);
		mData.add(t4);
		mData.add(t5);
		mData.add(t6);
		mAdapter.setDataSource(mData);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
}

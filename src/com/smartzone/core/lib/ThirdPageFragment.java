package com.smartzone.core.lib;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smartzone.adapter.BannerAdapter;
import com.smartzone.adapter.ImformationAdapter;
import com.smartzone.bean.TypeBean;
import com.smartzone.core.R;
import com.smartzone.core.utils.CommUtils;
import com.smartzone.core.view.JazzyViewPager;
import com.smartzone.core.view.JazzyViewPager.TransitionEffect;

public class ThirdPageFragment extends Fragment {
	private GridView grid;
	private ArrayList<TypeBean> mData;
	private ImformationAdapter mAdapter;
	private JazzyViewPager mJazzy;
	private ImageView imageView;
	private ImageView[] imageViews;
	private LinearLayout group;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_imformatiogn, null);
		init(view);
		setupJazziness(view, TransitionEffect.Tablet);
		initPagerIndicator(5);
		initListener();
		initData();
		return view;
	}

	private void init(View view) {
		group = (LinearLayout)view.findViewById(R.id.viewGroup);
		mData = new ArrayList<TypeBean>();
		mAdapter = new ImformationAdapter(mData, getActivity());
		grid = (GridView)view.findViewById(R.id.gridView);
		grid.setAdapter(mAdapter);
	}
	
	private void setupJazziness(View v, TransitionEffect effect) {
		mJazzy = (JazzyViewPager) v.findViewById(R.id.type_banner);
		mJazzy.setTransitionEffect(effect);
		mJazzy.setOffscreenPageLimit(5);
		mJazzy.setAdapter(new BannerAdapter(getActivity(), mJazzy));
		mJazzy.setPageMargin(30);
	}
	
	private void initPagerIndicator(int pageCount) {
		imageViews = new ImageView[pageCount];
		for (int i = 0; i < pageCount; i++) {
			imageView = new ImageView(getActivity());
			imageView.setLayoutParams(new LayoutParams(CommUtils.px2dip(getActivity(), 60), CommUtils.px2dip(getActivity(), 40)));
			imageView.setPadding(CommUtils.px2dip(getActivity(), 20), 0, CommUtils.px2dip(getActivity(), 20), 0);
			imageViews[i] = imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i]
						.setImageResource(R.drawable.page_indicator_focused);
			} else {
				imageViews[i].setImageResource(R.drawable.page_indicator_normal);
			}
			group.addView(imageViews[i]);
		}
	}
	
	private void initListener() {
		mJazzy.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < imageViews.length; i++) {
					imageViews[arg0]
							.setImageResource(R.drawable.page_indicator_focused);
					if (arg0 != i) {
						imageViews[i].setImageResource(R.drawable.page_indicator_normal);
					}
				}
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

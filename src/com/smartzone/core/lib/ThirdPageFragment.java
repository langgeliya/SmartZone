package com.smartzone.core.lib;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartzone.adapter.BannerAdapter;
import com.smartzone.adapter.ImformationAdapter;
import com.smartzone.bean.TypeBean;
import com.smartzone.core.R;
import com.smartzone.core.utils.CommUtils;
import com.smartzone.core.view.JazzyViewPager;
import com.smartzone.core.view.JazzyViewPager.TransitionEffect;

public class ThirdPageFragment extends Fragment {
	private ListView listView;
	private ArrayList<TypeBean> mData;
	private ImformationAdapter mAdapter;
	private JazzyViewPager mJazzy;
	private ImageView imageView;
	private ImageView[] imageViews;
	private LinearLayout group;
	private View headView;
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
		initListener();
		initData();
		return view;
	}

	private void init(View view) {
		headView = LayoutInflater.from(getActivity()).inflate(R.layout.view_third_fragment_header, null);
		group = (LinearLayout)headView.findViewById(R.id.viewGroup);
		setupJazziness(headView, TransitionEffect.Tablet);
		initPagerIndicator(5);
		mData = new ArrayList<TypeBean>();
		mAdapter = new ImformationAdapter(mData, getActivity());
		listView = (ListView)view.findViewById(R.id.listView);
		TextView tv = new TextView(getActivity());
		tv.setText("head view");
		listView.addHeaderView(headView);
		listView.setAdapter(mAdapter);
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
		t1.title0 = "招聘";
		t1.title1 = "外卖";
		t1.title2 = "家政服务";
		
		TypeBean t2 = new TypeBean();
		t2.title0 = "生活配送0";
		t2.title1 = "生活配送1";
		t2.title2 = "生活配送2";
		
		TypeBean t3 = new TypeBean();
		t3.title0 = "城市便利0";
		t3.title1 = "城市便利1";
		t3.title2 = "城市便利2";
		
		TypeBean t4 = new TypeBean();
		t4.title0 = "水果0";
		t4.title1 = "水果1";
		t4.title2 = "水果2";
		
		TypeBean t5 = new TypeBean();
		t5.title0 = "家政服务0";
		t5.title1 = "家政服务1";
		t5.title2 = "更多";
//		
//		TypeBean t6 = new TypeBean();
//		t6.title = "更多";
		mData.add(t1);
		mData.add(t2);
		mData.add(t3);
		mData.add(t4);
		mData.add(t5);
//		mData.add(t6);
//		listView.addHeaderView(headView);
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

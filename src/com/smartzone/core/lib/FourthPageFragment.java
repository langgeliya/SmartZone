package com.smartzone.core.lib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.smartzone.core.R;
import com.smartzone.core.utils.CommUtils;

public class FourthPageFragment extends Fragment implements OnClickListener {
	
	private RelativeLayout profile, boardcast, privateMsg, collection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mypage, null);
		init(view);
		initListener();
		return view;
	}
	
	private void init(View v) {
		profile = (RelativeLayout)v.findViewById(R.id.profile);
		boardcast = (RelativeLayout)v.findViewById(R.id.boardcast);
		privateMsg = (RelativeLayout)v.findViewById(R.id.privateMsg);
		collection = (RelativeLayout)v.findViewById(R.id.collection);
	}
	
	private void initListener() {
		profile.setOnClickListener(this);
		boardcast.setOnClickListener(this);
		privateMsg.setOnClickListener(this);
		collection.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.profile:
			CommUtils.showToast(getActivity(), "登录");
			break;
		case R.id.boardcast:
			CommUtils.showToast(getActivity(), "小区广播");
			break;
		case R.id.privateMsg:
			CommUtils.showToast(getActivity(), "我的私信");
			break;
		case R.id.collection:
			CommUtils.showToast(getActivity(), "我的收藏");
			break;
		default:
			break;
		}
	}

}

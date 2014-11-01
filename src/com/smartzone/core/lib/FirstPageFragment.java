package com.smartzone.core.lib;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.smartzone.adapter.MessageAdapter;
import com.smartzone.bean.SimBean;
import com.smartzone.core.R;
import com.smartzone.core.utils.CommUtils;
import com.smartzone.core.utils.CommValues;
import com.smartzone.core.utils.LogUtils;

public class FirstPageFragment extends Fragment {
	
	private ArrayList<SimBean> mData;
	private ListView listView;
	private MessageAdapter mAdapter;
	private LinearLayout failLayout;
	private TextView failText;
	private static final int MSG_REFRESH_OK = 1;
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_REFRESH_OK:
				refresh();
				break;

			default:
				break;
			}
		}
		
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		View mView = inflater.inflate(R.layout.play_list_frament, null);
		View view = inflater.inflate(R.layout.fragment_main, null);
		init(view);
		refresh();
		initNetWorking();
		return view;
	}
	
	private void init(View v) {
		mData = new ArrayList<SimBean>();
		listView = (ListView)v.findViewById(R.id.listview);
		failLayout = (LinearLayout) v.findViewById(R.id.failLayout);
		failText = (TextView) v.findViewById(R.id.failText);
		mAdapter = new MessageAdapter(getActivity(), mData);
		listView.setAdapter(mAdapter);
	}
	
	private void initListener() {
		
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
	
	public void refresh() {
		if(mAdapter != null){
			mAdapter.setDataSource(mData);
			mAdapter.notifyDataSetChanged();
		}
		if(mData != null && mData.size() == 0){
			failLayout.setVisibility(View.VISIBLE);
			failText.setText("网络请求失败，请重新尝试。");
		}else {
			failLayout.setVisibility(View.GONE);
		}
	}
	
	private void initNetWorking() {
		String url = CommValues.testUrl1;
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {

		    @Override
		    public void onStart() {
		        // called before request is started
		    	LogUtils.printByTag(LogUtils.TAG1, "start...");
		    }

		    @Override
		    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
		        // called when response HTTP status is "200 OK"
//		    	LogUtils.printByTag(LogUtils.TAG1, "statusCode:" + statusCode);
		    	if(statusCode == 200){
		    		try {
						LogUtils.printByTag(LogUtils.TAG1, new String(response, "utf8"));
						JSONArray array = new JSONArray(CommUtils.byteToString(response));
						for (int i = 0; i < array.length(); i++) {
							JSONObject jo = array.getJSONObject(i);
							SimBean bean = new SimBean();
							bean.parse(jo);
							mData.add(bean);
						}
						if(mData != null && mData.size() > 0){
							mHandler.sendEmptyMessage(MSG_REFRESH_OK);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }

		    @Override
		    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
		        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
		    	LogUtils.printByTag(LogUtils.TAG1, "statusCode:" + statusCode);
		    }

		    @Override
		    public void onRetry(int retryNo) {
		        // called when request is retried
			}
		});
	}

}

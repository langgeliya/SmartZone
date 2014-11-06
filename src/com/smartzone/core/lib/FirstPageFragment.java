package com.smartzone.core.lib;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.smartzone.adapter.MessageAdapter;
import com.smartzone.bean.AddressBean;
import com.smartzone.core.R;
import com.smartzone.core.utils.CommUtils;
import com.smartzone.core.utils.CommValues;
import com.smartzone.core.utils.JsonUtils;
import com.smartzone.core.utils.LogUtils;

public class FirstPageFragment extends Fragment {
	
	private ArrayList<AddressBean> mData;
	private PullToRefreshListView listView;
	private ListView actrueListView;
	private MessageAdapter mAdapter;
	private LinearLayout failLayout;
	private TextView failText;
	private static final int MSG_REFRESH_OK = 1;
	private static final int MSG_REFRESH_FAIL = 2;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_REFRESH_OK:
				LogUtils.printByTag(LogUtils.TAG1, "msg ok...");
				String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				listView.getLoadingLayoutProxy(false, true).setLastUpdatedLabel(label);
				listView.onRefreshComplete();
				refresh();
				break;
			case MSG_REFRESH_FAIL:
				listView.onRefreshComplete();
				showFailLayout();
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

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		View mView = inflater.inflate(R.layout.play_list_frament, null);
		View view = inflater.inflate(R.layout.fragment_main, null);
		init(view);
		refresh();
		initNetWorking();
		initListener();
		return view;
	}
	
	private void init(View v) {
		mData = new ArrayList<AddressBean>();
		listView = (PullToRefreshListView)v.findViewById(R.id.listview);
		listView.setMode(Mode.PULL_FROM_END);
		listView.getLoadingLayoutProxy(false, true).setPullLabel("加载");
		listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("加载更多");
		listView.getLoadingLayoutProxy(false, true).setReleaseLabel("释放加载");
		failLayout = (LinearLayout) v.findViewById(R.id.failLayout);
		failText = (TextView) v.findViewById(R.id.failText);
		mAdapter = new MessageAdapter(getActivity(), mData);
		actrueListView = listView.getRefreshableView();
//		TextView tv = new TextView(getActivity());
//		tv.setText("head view");
//		actrueListView.addHeaderView(tv);
		actrueListView.setAdapter(mAdapter);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initListener() {
		listView.setOnRefreshListener(new OnRefreshListener2() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				// TODO Auto-generated method stub
				LogUtils.printByTag(LogUtils.TAG1, "onLoading:" + listView.isRefreshing());
				initNetWorking();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {
				// TODO Auto-generated method stub
				LogUtils.printByTag(LogUtils.TAG1, "onLoadMore");
				initNetWorking();
			}
		});
	}

	public void refresh() {
		if(mAdapter != null){
			mAdapter.setDataSource(mData);
			mAdapter.notifyDataSetChanged();
		}
		showFailLayout();
	}
	
	private void showFailLayout() {
		if(mData != null && mData.size() == 0){
			failLayout.setVisibility(View.VISIBLE);
			failText.setText("网络请求失败，请重新尝试。");
		}else {
			failLayout.setVisibility(View.GONE);
		}
	}
	
	private void initNetWorking() {
		String url = CommValues.testUrl2;
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
		    	LogUtils.printByTag(LogUtils.TAG1, "success...");
		    	if(statusCode == 200){
		    		try {
						LogUtils.printByTag(LogUtils.TAG1, new String(response, "utf8"));
						String json = CommUtils.byteToString(response);
						JSONObject jObject = new JSONObject(json);
						JSONArray array = JsonUtils.getJSONArray(jObject, "results");
//						JSONArray array = new JSONArray(CommUtils.byteToString(response));
						for (int i = 0; i < array.length(); i++) {
							JSONObject jo = array.getJSONObject(i);
							AddressBean bean = new AddressBean();
//							SimBean bean = new SimBean();
							bean.parse(jo);
							mData.add(bean);
						}
						if(mData != null && mData.size() > 0){
							mHandler.sendEmptyMessage(MSG_REFRESH_OK);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if(mHandler != null){
				    		mHandler.sendEmptyMessage(MSG_REFRESH_FAIL);
				    	}
					}
		    	}
		    }

		    @Override
		    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
		        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
		    	LogUtils.printByTag(LogUtils.TAG1, "statusCode:" + statusCode);
		    	if(mHandler != null){
		    		mHandler.sendEmptyMessage(MSG_REFRESH_FAIL);
		    	}
		    }

		    @Override
		    public void onRetry(int retryNo) {
		        // called when request is retried
			}
		});
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

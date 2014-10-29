package com.smartzone.core.lib;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.smartzone.bean.SimBean;
import com.smartzone.core.R;
import com.smartzone.core.utils.CommUtils;
import com.smartzone.core.utils.LogUtils;

public class FirstPageFragment extends Fragment {
	
	private ArrayList<SimBean> mData;
	private ListView listView;

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
		initNetWorking();
		return view;
	}
	
	private void init(View v) {
		listView = (ListView)v.findViewById(R.id.listview);
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
	
	private void initNetWorking() {
		String url = "http://58.68.225.142/sqtest/jsontest.php?a=list";
		mData = new ArrayList<SimBean>();
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
		    	LogUtils.printByTag(LogUtils.TAG1, "statusCode:" + statusCode);
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

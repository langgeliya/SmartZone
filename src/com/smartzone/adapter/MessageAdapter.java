package com.smartzone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartzone.core.R;

public class MessageAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater = null;
	public MessageAdapter(Context mContext) {
		this.mContext = mContext;
		this.inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(contentView == null){
			contentView = inflater.inflate(R.layout.message_item, null);
			holder = new ViewHolder();
			holder.tv = (TextView)contentView.findViewById(R.id.title);
			contentView.setTag(holder);
		}else {
			holder = (ViewHolder)contentView.getTag();
		}
		return contentView;
	}
	
	public static class ViewHolder {
		public TextView tv;
	}

}

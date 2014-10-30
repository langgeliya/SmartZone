package com.smartzone.adapter;

import java.util.ArrayList;

import com.smartzone.bean.SimBean;
import com.smartzone.core.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimeLineAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<SimBean> dataList;
	private LayoutInflater inflater;
	public TimeLineAdapter(Context mContext, ArrayList<SimBean> mData) {
		this.mContext = mContext;
		this.dataList = mData;
		this.inflater = LayoutInflater.from(mContext);
	}
	
	public void setDataSource(ArrayList<SimBean> mData) {
		this.dataList = mData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.dataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		SimBean bean = dataList.get(position);
		if(contentView == null){
			contentView = inflater.inflate(R.layout.timeline_item, null);
			holder = new ViewHolder();
			holder.main = (RelativeLayout)contentView.findViewById(R.id.main);
			holder.name = (TextView)contentView.findViewById(R.id.name);
			holder.logo = (ImageView)contentView.findViewById(R.id.person_logo);
			holder.time = (TextView)contentView.findViewById(R.id.time);
			holder.title = (TextView)contentView.findViewById(R.id.title);
			holder.content = (TextView)contentView.findViewById(R.id.subTitle);
			contentView.setTag(holder);
		}else {
			holder = (ViewHolder)contentView.getTag();
		}
		if(position % 2 == 0){
			holder.logo.setImageResource(R.drawable.icon_share_weixin);
		}else {
			holder.logo.setImageResource(R.drawable.icon_share_xinlang);
		}
		holder.name.setText(bean.k2);
		holder.time.setText(bean.time);
		holder.title.setText(bean.title);
		holder.content.setText(bean.contents);
		return contentView;
	}
	
	public static class ViewHolder {
		public RelativeLayout main;
		public TextView name;
		public TextView title;
		public TextView content;
		public TextView time;
		public ImageView logo;
	}

}

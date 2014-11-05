package com.smartzone.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartzone.bean.AddressBean;
import com.smartzone.bean.SimBean;
import com.smartzone.core.R;
import com.smartzone.core.utils.CommUtils;

public class MessageAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater = null;
	private ArrayList<AddressBean> dataList = null;
	public MessageAdapter(Context mContext, ArrayList<AddressBean> dataList) {
		this.mContext = mContext;
		this.dataList = dataList;
		this.inflater = LayoutInflater.from(mContext);
	}
	
	public void setDataSource(ArrayList<AddressBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.dataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final AddressBean bean = dataList.get(position);
		ViewHolder holder = null;
		if(contentView == null){
			contentView = inflater.inflate(R.layout.message_item, null);
			holder = new ViewHolder();
			holder.main = (RelativeLayout)contentView.findViewById(R.id.main);
			holder.name = (TextView)contentView.findViewById(R.id.name);
			holder.title = (TextView)contentView.findViewById(R.id.title);
			holder.time = (TextView)contentView.findViewById(R.id.time);
			holder.logo = (ImageView)contentView.findViewById(R.id.person_logo);
			contentView.setTag(holder);
		}else {
			holder = (ViewHolder)contentView.getTag();
		}
		if(position % 2 == 0){
			holder.logo.setImageResource(R.drawable.icon_share_weixin);
		}else {
			holder.logo.setImageResource(R.drawable.icon_share_xinlang);
		}
		holder.name.setText(bean.name);
		holder.title.setText(bean.address);
		holder.time.setText(bean.telephone);
		holder.main.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CommUtils.showToast(mContext, bean.location_lng + ", " + bean.location_lat);
			}
		});
		
		return contentView;
	}
	
	public static class ViewHolder {
		public RelativeLayout main;
		public TextView name;
		public TextView title;
		public TextView time;
		public ImageView logo;
	}

}

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

import com.smartzone.bean.TypeBean;
import com.smartzone.core.R;
import com.smartzone.core.utils.ActivityUtils;

public class ImformationAdapter extends BaseAdapter {
	
	private ArrayList<TypeBean> mData;
	private Context mContext;
	private LayoutInflater inflater;
	
	public ImformationAdapter(ArrayList<TypeBean> data, Context mContext) {
		this.mData = data;
		this.mContext = mContext;
		inflater = LayoutInflater.from(mContext);
	}
	
	public void setDataSource(ArrayList<TypeBean> data) {
		this.mData = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final TypeBean bean = mData.get(position);
		ViewHolder holder = null;
		if(contentView == null){
			holder = new ViewHolder();
			contentView = inflater.inflate(R.layout.fragment_imformation_grid_item, null);
			holder.main = (RelativeLayout)contentView.findViewById(R.id.main);
			holder.logo = (ImageView)contentView.findViewById(R.id.type_logo);
			holder.title = (TextView)contentView.findViewById(R.id.type_title);
			contentView.setTag(holder);
		}else {
			holder = (ViewHolder)contentView.getTag();
		}
		if(position % 2 == 0){
			holder.logo.setImageResource(R.drawable.icon_share_weixin);
		}else {
			holder.logo.setImageResource(R.drawable.icon_share_xinlang);
		}
		holder.title.setText(bean.title);
		holder.main.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ActivityUtils.startClassifyActivity(mContext, bean);
			}
		});
		return contentView;
	}
	
	public static class ViewHolder{
		public RelativeLayout main;
		public ImageView logo;
		public TextView title;
	}

}

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
			holder.main0 = (RelativeLayout)contentView.findViewById(R.id.main0);
			holder.logo0 = (ImageView)contentView.findViewById(R.id.type_logo0);
			holder.title0 = (TextView)contentView.findViewById(R.id.type_title0);
			holder.main1 = (RelativeLayout)contentView.findViewById(R.id.main1);
			holder.logo1 = (ImageView)contentView.findViewById(R.id.type_logo1);
			holder.title1 = (TextView)contentView.findViewById(R.id.type_title1);
			holder.main2 = (RelativeLayout)contentView.findViewById(R.id.main2);
			holder.logo2 = (ImageView)contentView.findViewById(R.id.type_logo2);
			holder.title2 = (TextView)contentView.findViewById(R.id.type_title2);
			contentView.setTag(holder);
		}else {
			holder = (ViewHolder)contentView.getTag();
		}
		if(position % 2 == 0){
			holder.logo0.setImageResource(R.drawable.icon_share_weixin);
			holder.logo1.setImageResource(R.drawable.icon_share_weixin);
			holder.logo2.setImageResource(R.drawable.icon_share_weixin);
		}else {
			holder.logo0.setImageResource(R.drawable.icon_share_xinlang);
			holder.logo1.setImageResource(R.drawable.icon_share_xinlang);
			holder.logo2.setImageResource(R.drawable.icon_share_xinlang);
		}
		holder.title0.setText(bean.title0);
		holder.title1.setText(bean.title1);
		holder.title2.setText(bean.title2);
		holder.main0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ActivityUtils.startClassifyActivity(mContext, bean.title0);
			}
		});
		holder.main1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ActivityUtils.startClassifyActivity(mContext, bean.title1);
			}
		});
		holder.main2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ActivityUtils.startClassifyActivity(mContext, bean.title2);
			}
		});
		return contentView;
	}
	
	public static class ViewHolder{
		public RelativeLayout main0;
		public ImageView logo0;
		public TextView title0;
		public RelativeLayout main1;
		public ImageView logo1;
		public TextView title1;
		public RelativeLayout main2;
		public ImageView logo2;
		public TextView title2;
	}

}

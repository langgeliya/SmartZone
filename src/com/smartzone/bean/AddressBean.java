package com.smartzone.bean;

import java.io.Serializable;

import org.json.JSONObject;

import com.smartzone.core.utils.JsonUtils;

public class AddressBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String uid;
	public String street_id;
	public String telephone;
	public String address;
	public String location_lng;
	public String location_lat;
	public String name;
	
	public void parse(JSONObject jo) {
		if(jo != null){
			JSONObject loc = JsonUtils.getJSONObject(jo, "location");
			location_lng = JsonUtils.getString(loc, "lng");
			location_lat = JsonUtils.getString(loc, "lat");
			uid = JsonUtils.getString(jo, "uid");
			street_id = JsonUtils.getString(jo, "street_id");
			telephone = JsonUtils.getString(jo, "telephone");
			address = JsonUtils.getString(jo, "address");
			name = JsonUtils.getString(jo, "name");
		}
	}
}

package com.smartzone.bean;

import java.io.Serializable;

import org.json.JSONObject;

import com.smartzone.core.utils.JsonUtils;

public class SimBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String time;
	public String title;
	public String k1;
	public String k2;
	public String contents;
	
	public void parse(JSONObject jo) {
		if(jo != null){
			title = JsonUtils.getString(jo, "title");
			time = JsonUtils.getString(jo, "time");
			k1 = JsonUtils.getString(jo, "k1");
			k2 = JsonUtils.getString(jo, "k2");
			contents = JsonUtils.getString(jo, "contents");
		}
	}

}

package com.wanchain.iwanj.lib.converts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.alibaba.fastjson.JSON;  
import com.alibaba.fastjson.JSONArray;  
import com.alibaba.fastjson.JSONObject;  

import com.wanchain.iwanj.lib.ResultConvert;
import com.wanchain.iwanj.lib.Utils;

public class RCArray implements ResultConvert {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void convert(String src, CompletableFuture<?> future){
		
		JSONObject jsonResult = JSON.parseObject(src);
		if (jsonResult.containsKey("result")) {			
			// parser result info
			JSONArray rsltArray = jsonResult.getJSONArray("result");
			
			List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
			
			for(int i=0; i<rsltArray.size(); i++){
				JSONObject row = rsltArray.getJSONObject(i);
				String strObj = row.toJSONString();
				
				Map<String, Object> item = Utils.jsonToMap(strObj);				
				itemList.add(item);
			}
			
			// complete future data
			((CompletableFuture<List>)future).complete(itemList);
			
		} else {
			Object err = jsonResult.get("error");
			Exception ex = null;
			if (err == null) {
				ex = new Exception("Unknown error type");    				
			} else {
				ex = new Exception(err.toString());
			}
			
			future.completeExceptionally(ex);
		}
	}
}

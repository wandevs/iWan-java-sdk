package com.wanchain.iwanj.lib.converts;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.alibaba.fastjson.JSON;   
import com.alibaba.fastjson.JSONObject;  

import com.wanchain.iwanj.lib.ResultConvert;
import com.wanchain.iwanj.lib.Utils;

public class RCMap implements ResultConvert {
		
	@SuppressWarnings("unchecked")
	public void convert(String src, CompletableFuture<?> future){
		
		JSONObject jsonResult = JSON.parseObject(src);
		if (jsonResult.containsKey("result")) {			
			// parser result info
			Object rslt = jsonResult.get("result");				
			String strObj = rslt.toString();
			
			Map<String, Object> mapRslt = new HashMap<String, Object>();
			mapRslt = Utils.jsonToMap(strObj);
			
			// complete future data
			((CompletableFuture<Map<String, Object>>)future).complete(mapRslt);
			
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

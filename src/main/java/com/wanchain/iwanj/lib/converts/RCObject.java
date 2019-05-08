package com.wanchain.iwanj.lib.converts;

import java.util.concurrent.CompletableFuture;

import com.alibaba.fastjson.JSON;   
import com.alibaba.fastjson.JSONObject;  

import com.wanchain.iwanj.lib.ResultConvert;

public class RCObject implements ResultConvert {
		
	@SuppressWarnings("unchecked")
	public void convert(String src, CompletableFuture<?> future){
		
		JSONObject jsonResult = JSON.parseObject(src);
		if (jsonResult.containsKey("result")) {			
			// parser result info
			Object rslt = jsonResult.get("result");	
			
			// complete future data
			((CompletableFuture<Object>)future).complete(rslt);
			
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

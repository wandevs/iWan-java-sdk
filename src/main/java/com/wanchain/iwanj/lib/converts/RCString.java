package com.wanchain.iwanj.lib.converts;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.wanchain.iwanj.lib.ResultConvert;
import com.wanchain.iwanj.lib.Utils;

public class RCString implements ResultConvert {
	
	@SuppressWarnings("unchecked")
	public void convert(String src, CompletableFuture<?> future){
		
		Map<String, Object> map = Utils.jsonToMap(src);
		
		if (map.containsKey("result")) {
			Object rs = map.get("result");

			String str = null;
			if (rs.getClass().getSimpleName().indexOf("Map") >= 0) {
				str = Utils.mapToJson((Map)rs);
			} else {
				str = rs.toString();
			}
			
			((CompletableFuture<String>)future).complete(str);
			
		} else {
			Object err = map.get("error");
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

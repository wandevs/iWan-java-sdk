package com.wanchain.iwanj.lib;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

public class WsInstance {
	/**apiKey to connect wanchain server*/
	private String _apiKey;
	/**secrtKey to connect wanchain server*/
	private byte[] _secretKey;
	/**The parameters how to connect wanchain server and handle data*/
	private Map<String, String> _mapOption;
	/**to create a single id for a message*/
	private AtomicLong atomicLong = new AtomicLong(0);
	/**The whole url to connect wanchain server*/
	private String _url;
	
	@SuppressWarnings("static-access")
	public WsInstance(String apiKey, String secretKey, Map<String, String> option) {
		_apiKey = apiKey;
		try {
			_secretKey = secretKey.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_mapOption = new HashMap<String, String>();
		if((null != option) && (option.containsKey("socketUrl"))) {
			_mapOption.put("socketUrl", option.get("socketUrl"));
		}else {
			_mapOption.put("socketUrl", "wss://api.wanchain.org:8443");
		}
		
		if((null != option) && (option.containsKey("apiFlag"))) {
			_mapOption.put("apiFlag", option.get("apiFlag"));
		}else {
			_mapOption.put("apiFlag", "ws");
		}
		
		if((null != option) && (option.containsKey("apiVersion"))) {
			_mapOption.put("apiVersion", option.get("apiVersion"));
		}else {
			_mapOption.put("apiVersion", "v3");
		}
		
		if((null != option) && (option.containsKey("encoding.enc"))) {
			_mapOption.put("encoding.enc", option.get("encoding.enc"));
		}else {
			_mapOption.put("encoding.enc", "HmacSHA256");
		}
		
		if((null != option) && (option.containsKey("encoding.base64"))) {
			_mapOption.put("encoding.base64", option.get("encoding.base64"));
		}else {
			_mapOption.put("encoding.base64", "base64");
		}
		
		if((null != option) && (option.containsKey("encoding.hex"))) {
			_mapOption.put("encoding.hex", option.get("encoding.hex"));
		}else {
			_mapOption.put("encoding.hex", "hex");
		}

		_url = _mapOption.get("socketUrl");
		_url += '/' + _mapOption.get("apiFlag");
		_url += '/' + _mapOption.get("apiVersion") + '/' + _apiKey;
        
		if (!WebsocketClient.isOpened()) {
			WebsocketClient.startRequest(_url);
		}
	}
		
	/***
	 * check the message if right
	 * @param map
	 * @throws Exception
	 */
	private void checkFormat(Map<String, Object> map) throws Exception {
		 if (!map.containsKey("method")) {
			 throw new Exception("Failed to find the item: method");
		 }
		 if (!map.containsKey("params")) {
			 throw new Exception("Failed to find the item params or error value type");
		 }
	}
	
	/**
	 * 
	 * @param map
	 * @param type
	 * @param future
	 * @throws Exception
	 */
	public void sendMessage(Map<String, Object> map, ResultType type, CompletableFuture<?> future) throws Exception {
		try {
			checkFormat(map);
			map.put("jsonrpc", "2.0");
			map.put("id", atomicLong.incrementAndGet());
			
			WebsocketClient.sendMessage(map, (long)map.get("id"), type, future, _secretKey, _mapOption.get("encoding.enc"));						
		} catch (Exception ex) {
			throw ex;
		}
	}
}

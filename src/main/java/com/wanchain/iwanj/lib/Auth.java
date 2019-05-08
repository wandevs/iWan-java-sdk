package com.wanchain.iwanj.lib;

import java.util.Calendar;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Auth {
	/**
	 * generate a signature for a message.
	 * @param encryptText the message string
	 * @return the signature
	 * @throws Exception
	 */
	public static String hmacEncrypt(String encryptText, byte[] key, String enc) throws Exception {
		System.out.println(encryptText);		
		SecretKey secretKey = new SecretKeySpec(key, enc);
		Mac mac = Mac.getInstance(enc); 
		mac.init(secretKey);  
		byte[] text = encryptText.getBytes("utf-8");  
		return Utils.base64Encode(mac.doFinal(text));  

	}
	/**
	 * Conver a message from map to json string and add static parameters. 
	 * @param map the soure message
	 * @return the json string
	 * @throws Exception
	 */
	public static String integrateJson(Map<String, Object> map, byte[] key, String enc) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>)map.get("params");
		params.put("timestamp", Calendar.getInstance().getTimeInMillis());		
		params.put("signature", hmacEncrypt(Utils.mapToJson(map), key, enc));
		
		return Utils.mapToJson(map);
	}
}

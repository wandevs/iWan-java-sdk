package com.wanchain.iwanj.lib;

import java.math.BigInteger;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * common tools
 */
public class Utils {
	/**
	 * convert byte array to hex string
	 * @param bytes 
	 * @return hex string
	 */
	public static String byteToHex(byte[] bytes){
		String strHex = "";
	    StringBuilder sb = new StringBuilder("");
	    for (int n = 0; n < bytes.length; n++) {
	    	strHex = Integer.toHexString(bytes[n] & 0xFF);
	    	sb.append((strHex.length() == 1) ? "0" + strHex : strHex);
	    }
	    
	    return sb.toString().trim();
	}
	
	public static byte[] hexToByte(String hex){
		if (hex.startsWith("0x")) {
			hex = hex.substring(2);
		}
		
        int m = 0, n = 0;
        int byteLen = hex.length() / 2;
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        
        return ret;
    }
	
	public static Map<String, Object> jsonToMap(String json) {
		return JSON.parseObject(json, new TypeReference<HashMap<String, Object>>(){});
	}
	
	public static Map<String, BigInteger> jsonToBigNumberMap(String json) {
		return JSON.parseObject(json, new TypeReference<HashMap<String, BigInteger>>(){});
	}
	
	public static String mapToJson(Map<String, Object> map) {
		return JSON.toJSONString(map);
	}
	
	public static String base64Encode(byte[] bys) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(bys);
	}
	
	public static byte[] base64Decode(String str) {
		Base64.Decoder decoder = Base64.getDecoder();
		return decoder.decode(str);
	}
}

package com.wanchain.iwanj;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;


import com.wanchain.iwanj.lib.apis.Wanj;
import com.wanchain.iwanj.lib.ResultConvert;
import com.wanchain.iwanj.lib.ResultType;
import com.wanchain.iwanj.lib.Utils;
import com.wanchain.iwanj.lib.WebsocketClient;
import com.wanchain.iwanj.lib.WsInstance;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.math.BigInteger;

import com.wanchain.iwanj.lib.converts.RCString;
import com.wanchain.iwanj.lib.converts.RCBigInteger;
import com.wanchain.iwanj.lib.converts.RCBoolean;
/**
 * Hello world!
 *
 */
public class ApiTest
{	
    public static void main( String[] args ) throws IOException
    {
        
		Properties prop = new Properties();
		ApiTest api = new ApiTest();
		InputStream is = api.getClass().getResourceAsStream("/iwan.properties");

		try {
			prop.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map<String, String> option = new HashMap<>();
		option.put("socketUrl", prop.getProperty("socketUrl"));
		option.put("apiFlag", prop.getProperty("apiFlag"));
		option.put("apiVersion", prop.getProperty("apiVersion"));
		option.put("encoding.enc", prop.getProperty("encoding.enc"));
		option.put("encoding.base64", prop.getProperty("encoding.base64"));
		option.put("encoding.hex", prop.getProperty("encoding.hex"));	     

		String apiKey = "47f0102e75a41dccd836c849b0d16291e33522358ab8ba146cb17709161614b1";
		String secretKey = "b803eed271c927719a72e9e729bb016c8de2770896abbc84278549a2385c0572";

		Wanj javaApi = new Wanj(apiKey, secretKey, option);
		System.out.println(secretKey);
				

		//CompletableFuture<?> future = javaApi.GetBlockByHash("WAN","0xa4562bb06da1372d195f8112ed73b8ee21937a7fe007d711d928880d96343d50").sendAsync();

		Object rltObj = null;
		// GetBlockByHash
		try {
				rltObj = javaApi.getBlockByHash("WAN","0xa4562bb06da1372d195f8112ed73b8ee21937a7fe007d711d928880d96343d50").send();
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("GetBlockByHash sync: \n" + rltObj);

		CompletableFuture<?> future = javaApi.getBlockByHash("WAN","0xa4562bb06da1372d195f8112ed73b8ee21937a7fe007d711d928880d96343d50").sendAsync();
		try {
			
			System.out.println("GetBlockByHash async: \n" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//javaApi.getBlockByNumber
		try {
				rltObj = javaApi.getBlockByNumber("WAN", 3075645).send();
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("GetBlockByNumber: \n" + rltObj);
				
		future = javaApi.getBlockByNumber("WAN", 3075645).sendAsync();
		try {
			System.out.println("GetBlockByNumber: \n" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//javaApi.getBlockNumber
		try {
				rltObj = javaApi.getBlockNumber("WAN").send();
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("GetBlockNumber: \n" + rltObj);

		future = javaApi.getBlockNumber("WAN").sendAsync();
		try {
			System.out.println("GetBlockNumber: \n" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		//javaApi.getBlockTransactionCount
		try {
				rltObj = javaApi.getBlockTransactionCount("WAN","0xa4562bb06da1372d195f8112ed73b8ee21937a7fe007d711d928880d96343d50").send();
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("GetBlockTransactionCount: \n" + rltObj);

		future = javaApi.getBlockTransactionCount("WAN","0xa4562bb06da1372d195f8112ed73b8ee21937a7fe007d711d928880d96343d50").sendAsync();
		try {
			System.out.println("GetBlockTransactionCount: \n" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

    }
}

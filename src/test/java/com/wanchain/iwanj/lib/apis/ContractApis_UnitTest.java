package com.wanchain.iwanj.lib.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.wanchain.iwanj.lib.apis.Wanj;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Hello world!
 *
 */
public class ContractApis_UnitTest extends TestCase {	
	
	Wanj javaApi = null; 

	String qlPath = null;
	String qlData = null;
	JSONObject qlJson = null;
	JSONArray qlABI = null;

	String smgPath = null;
	String smgData = null;
	JSONObject smgJson = null;
    JSONArray smgABI = null;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ContractApis_UnitTest( String testName )
    {
        super( testName );

        String apiKey = "47f0102e75a41dccd836c849b0d16291e33522358ab8ba146cb17709161614b1";
        String secretKey = "b803eed271c927719a72e9e729bb016c8de2770896abbc84278549a2385c0572";
        
        javaApi = new Wanj(apiKey, secretKey, null); 
        
        {
	        smgPath = ContractApis_UnitTest.class.getClassLoader().getResource("StoremanGroup.json").toString();		
			smgPath = smgPath.replace("\\", "/");
			if (smgPath.contains(":")) {
				smgPath = smgPath.replace("file:","");
			}
	//		System.out.println("scPath: " + smgPath);
			
			smgData = readJsonFile(smgPath);			
			smgJson = JSON.parseObject(smgData);
	        smgABI = smgJson.getJSONArray("abi");
	//		System.out.println("smgABI: " + smgABI);
        }

        {
			qlPath = ContractApis_UnitTest.class.getClassLoader().getResource("QuotaLedger.json").toString();		
			qlPath = qlPath.replace("\\", "/");
			if (qlPath.contains(":")) {
				qlPath = qlPath.replace("file:","");
			}
	//		System.out.println("qlPath: " + qlPath);
			
			qlData = readJsonFile(qlPath);			
			qlJson = JSON.parseObject(qlData);		
	        qlABI = qlJson.getJSONArray("abi");
	//		System.out.println("qlABI: " + qlABI);
        }
    }    

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(ContractApis_UnitTest.class );
    }   

		
	///////////////////////
	// callScFunc 
	///////////////////////
    public void testCallScFunc() {
    	
		Object[] params = new Object[2];
		params[0] = "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359";
		params[1] = "0x484a7cd86806eefaadccfc7b717edc45c04f99c0";
		
		try {
			Object rslt = javaApi.callScFunc("WAN", "0xddb09c3af165b83fa8f280225a6866786cc38971", "isStoremanGroup", params, qlABI).send();
			
			System.out.println("\n[sync]callScFunc: isStoremanGroup: " + rslt);
			System.out.println("\n[sync]callScFunc: rslt class: " + rslt.getClass());
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {

			CompletableFuture<Object> rsltFuture = javaApi.callScFunc("WAN", "0xddb09c3af165b83fa8f280225a6866786cc38971", "isStoremanGroup", params, qlABI).sendAsync();
						
			Object rslt =  rsltFuture.get();
			System.out.println("\n[async]callScFunc: isStoremanGroup: " + rslt);
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
    
	///////////////////////
	// getScMap 
	///////////////////////
    public void testGetScMap() {
		try {
			Object rslt = javaApi.getScMap("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "mapDepositorInfo", "0xa19444ffba0478655e5e07fb2cc4eb260df74a22",smgABI).send();
			
			System.out.println("\n[sync]getScMap: rslt class: " + rslt.getClass());
			System.out.println("\n[sync]getScMap: rslt: " + rslt);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		CompletableFuture<Object> rsltFuture = javaApi.getScMap("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "mapDepositorInfo", "0xa19444ffba0478655e5e07fb2cc4eb260df74a22",smgABI).sendAsync();
		
		try {
			Object rslt =  rsltFuture.get();
			System.out.println("\n[async]getScMap: rslt: " + rslt);

		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
		
	///////////////////////
	// getScVar 
	///////////////////////
    public void testGetScVar() {
		try {
			Object var = javaApi.getScVar("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "isReachedMaxDeposit",smgABI).send();
			
			System.out.println("\n[sync]getScVar: var: " + var);
			System.out.println("\n[sync]getScVar: var class: " + var.getClass());
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		CompletableFuture<Object> rsltFuture = javaApi.getScVar("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "isReachedMaxDeposit",smgABI).sendAsync();
		
		try {
			Object var =  rsltFuture.get();
			System.out.println("\n[async]getScVar: var: " + var);
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }

	///////////////////////
	// getScEvent 
	///////////////////////
    public void testGetScEvent() {
    	
		String[] topics =  new String[2];
		topics[0] = "0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4";
		topics[1] = "0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2";
		
		try {
			List eventList = javaApi.getScEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topics).send();
//			System.out.println("\n[sync] getScEvent eventList: " + eventList);
			
			for(int i=0; i < eventList.size(); i++) {
				Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
				
				Object blockNumber = eventInfo.get("blockNumber");
				System.out.println("\n[sync] getScEvent blockNumber: " + blockNumber);
				
				Object transactionHash = eventInfo.get("transactionHash");
				System.out.println("[sync] getScEvent transactionHash: " + transactionHash);
				
				Object address = eventInfo.get("address");
				System.out.println("[sync] getScEvent address: " + address);
				
				Object data = eventInfo.get("data");
				System.out.println("[sync] getScEvent data: " + data);				
			}	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		CompletableFuture<List> rsltFuture = javaApi.getScEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topics).sendAsync();
	
		try {	
			List eventList =  rsltFuture.get();		
			for(int i=0; i < eventList.size(); i++) {
				Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
				
				Object blockNumber = eventInfo.get("blockNumber");
				System.out.println("\n[async] getScEvent blockNumber: " + blockNumber);
				
				Object transactionHash = eventInfo.get("transactionHash");
				System.out.println("[async] getScEvent transactionHash: " + transactionHash);
				
				Object address = eventInfo.get("address");
				System.out.println("[async] getScEvent address: " + address);
				
				Object data = eventInfo.get("data");
				System.out.println("[async] getScEvent data: " + data);				
			}	
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }

    /*
	///////////////////////
	// monitorEvent 
	///////////////////////	
    public void testMonitorEvent() {
		String[] topicsMon =  new String[1];
		topicsMon[0] = "0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4";
		try {
			List eventList = javaApi.monitorEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topicsMon).send();
			
			for(int i=0; i < eventList.size(); i++) {
				Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
				
				Object blockNumber = eventInfo.get("blockNumber");
				System.out.println("\n[sync] monitorEvent blockNumber: " + blockNumber);
				
				Object transactionHash = eventInfo.get("transactionHash");
				System.out.println("[sync] monitorEvent transactionHash: " + transactionHash);
				
				Object address = eventInfo.get("address");
				System.out.println("[sync] monitorEvent address: " + address);
				
				Object data = eventInfo.get("data");
				System.out.println("[sync] monitorEvent data: " + data);				
			}	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		CompletableFuture<List> rsltFuture = javaApi.monitorEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topicsMon).sendAsync();
		
		try {
			List eventList =  rsltFuture.get();		
			for(int i=0; i < eventList.size(); i++) {
				Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
				
				Object blockNumber = eventInfo.get("blockNumber");
				System.out.println("\n[async] monitorEvent blockNumber: " + blockNumber);
				
				Object transactionHash = eventInfo.get("transactionHash");
				System.out.println("[async] monitorEvent transactionHash: " + transactionHash);
				
				Object address = eventInfo.get("address");
				System.out.println("[async] monitorEvent address: " + address);
				
				Object data = eventInfo.get("data");
				System.out.println("[async] monitorEvent data: " + data);				
			}	
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
    }
    */
    
}

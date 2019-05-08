package com.wanchain.iwanj.lib.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import com.wanchain.iwanj.AppTest;
import com.wanchain.iwanj.lib.apis.Wanj;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Hello world!
 *
 */
public class AccountApis_UnitTest extends TestCase
{	
	Wanj javaApi = null;  	
    String addresses[] = null; 	

	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AccountApis_UnitTest( String testName )
    {
        super( testName );     

        String apiKey = "47f0102e75a41dccd836c849b0d16291e33522358ab8ba146cb17709161614b1";
        String secretKey = "b803eed271c927719a72e9e729bb016c8de2770896abbc84278549a2385c0572";
        
        javaApi = new Wanj(apiKey, secretKey, null); 
        
        addresses = new String[2];
        addresses[0] = "0x8456711c9d3dce1b8a6fcfa372277b95acb404c9";
        addresses[1] = "0x2cc79fa3b80c5b9b02051facd02478ea88a7802d";
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AccountApis_UnitTest.class );
    }
    
    
    ////////////////////////////////
    // getBalance
    ////////////////////////////////
    public void testGetBalance() {
		
		try {
			BigInteger rslt = javaApi.getBalance("WAN", addresses[0]).send();
			
			System.out.println("\n[sync]getBalance : " + rslt);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		CompletableFuture<BigInteger> rsltFuture = javaApi.getBalance("WAN", addresses[0]).sendAsync();		
		/*
		 could do others things
		 */	
		try {			
			BigInteger rslt = rsltFuture.get();
			
			System.out.println("\n[async]getBalance : " + rslt);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
    }

	////////////////////////////////
	// getMultiBalances
	////////////////////////////////
    public void testGetMultiBalance() {
		try {
			Map<String, BigInteger> mapRslt = javaApi.getMultiBalances("WAN", addresses).send();

			System.out.println("\n[async]addresses[0] balance : " + mapRslt.get(addresses[0]));
			System.out.println("\n[async]addresses[1] balance: " + mapRslt.get(addresses[1]));
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CompletableFuture<Map<String, BigInteger>> rsltFuture = javaApi.getMultiBalances("WAN", addresses).sendAsync();
			
		try {	
			Map<String, BigInteger> mapRslt = rsltFuture.get(); 			
//			System.out.println("\n[async]getBalance : " + mapRslt);
			
			System.out.println("\n[async]addresses[0] balance : " + mapRslt.get(addresses[0]));
			System.out.println("\n[async]addresses[1] balance: " + mapRslt.get(addresses[1]));
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    

    ////////////////////////////////
    // getNonce
    ////////////////////////////////
    public void testGetNonce() {	
		try {
			String nonce = javaApi.getNonce("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").send();			
			System.out.println("\n[sync]getNonce : " + nonce);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CompletableFuture<String> nonceFuture = javaApi.getNonce("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").sendAsync();
		try {
			String nonce = nonceFuture.get();
			System.out.println("\n[async]getNonce : " + nonce);
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    ////////////////////////////////
    // getNonceIncludePending
    ////////////////////////////////
    public void testGetNonceIncludePending() {			
		try {
			String nonce = javaApi.getNonceIncludePending("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").send();
			
			System.out.println("\n[sync]getNonceIncludePending : " + nonce);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CompletableFuture<String> nonceFuture = javaApi.getNonceIncludePending("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").sendAsync();
		try {
			String nonce = nonceFuture.get();			
			System.out.println("\n[async]getNonceIncludePending : " + nonce);
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    ////////////////////////////////
    // importAddress
    ////////////////////////////////
    public void testImportAddress() {
		try {
			String rslt = javaApi.importAddress("BTC", "1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX").send();	
			
			Assert.assertEquals("success", rslt);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CompletableFuture<String> rsltFuture = javaApi.importAddress("BTC", "1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX").sendAsync();
		
		try {			
			String rslt = rsltFuture.get(); 
			Assert.assertEquals("success", rslt);
	        
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    ////////////////////////////////
    // getUTXO
    ////////////////////////////////
    public void testGetUTXO() {
		String[] addArray = new String[2];
		addArray[0] = "1FZeVAnjdTK4Upu3b1CjhvwkrgfzvF6YUS";
		addArray[1] = "1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX";		
		
		try {
			List utxoRslt = javaApi.getUTXO("BTC", 0, 100000, addArray).send();
			System.out.println("\n[sync]getUTXO: " + utxoRslt);
			
			for(int i=0; i < utxoRslt.size(); i++) {
				Map<String, Object> utxoItem = (Map<String, Object>) utxoRslt.get(i);
				
				Object txid = utxoItem.get("txid");
				System.out.println("\n[sync]getUTXO: txid: " + txid);
	
				Object scriptPubKey = utxoItem.get("scriptPubKey");
				System.out.println("[sync]getUTXO: scriptPubKey: " + scriptPubKey);
				
				Object safe = utxoItem.get("safe");
				System.out.println("[sync]getUTXO: safe: " + safe);
				
				Object value = utxoItem.get("value");
				System.out.println("[sync]getUTXO: value: : " + value);	
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		CompletableFuture<List> utxoFuture = javaApi.getUTXO("BTC", 0, 100000, addArray).sendAsync();		
		
		try {
			
			List utxoList = utxoFuture.get();
	
			for(int i=0; i < utxoList.size(); i++) {
				Map<String, Object> utxoItem = (Map<String, Object>) utxoList.get(i);
				
				Object txid = utxoItem.get("txid");
				System.out.println("\n[async]getUTXO: txid: " + txid);
	
				Object scriptPubKey = utxoItem.get("scriptPubKey");
				System.out.println("[async]getUTXO: scriptPubKey: " + scriptPubKey);
				
				Object safe = utxoItem.get("safe");
				System.out.println("[async]getUTXO: safe: " + safe);
				
				Object value = utxoItem.get("value");
				System.out.println("[async]getUTXO: value: : " + value);	
			}
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
    ////////////////////////////////
    // getGasPrice
    ////////////////////////////////
    public void testGetGasPrice() {
		try {
			String rslt = javaApi.getGasPrice("WAN").send();			
			System.out.println("\n[sync]getGasPrice : " + rslt);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			CompletableFuture<String> rsltFuture = javaApi.getGasPrice("WAN").sendAsync();
			
			String rslt = rsltFuture.get(); 
			System.out.println("\n[async]getGasPrice : " + rslt);
	        
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
}

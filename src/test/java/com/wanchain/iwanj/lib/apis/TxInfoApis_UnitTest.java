package com.wanchain.iwanj.lib.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import java.io.IOException;
import java.io.InputStream;

import com.wanchain.iwanj.lib.apis.Wanj;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Hello world!
 *
 */
public class TxInfoApis_UnitTest  extends TestCase
{	
	
	
	Wanj javaApi = null;  

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TxInfoApis_UnitTest( String testName )
    {
        super( testName );     

        String apiKey = "47f0102e75a41dccd836c849b0d16291e33522358ab8ba146cb17709161614b1";
        String secretKey = "b803eed271c927719a72e9e729bb016c8de2770896abbc84278549a2385c0572";
        
        javaApi = new Wanj(apiKey, secretKey, null); 
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(TxInfoApis_UnitTest.class );
    }       
       		        
	         
	//////////////////////////////////////////////////////////////
	// Transactions: getTransByAddress
	//////////////////////////////////////////////////////////////
    public void testGetTransByAddress() {
		try {
			List txList = javaApi.getTransByAddress("WAN", "0x731d8fdc53039f07efd87be3c387e747da92911f").send();

//			System.out.println("\n[sync] txList: " + txList);
			
			for(int i=0; i<txList.size(); i++) {
				Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
				
				Object blockHash = txInfo.get("blockHash");
				System.out.println("\n[sync]getTransByAddress: blockHash: " + blockHash);
					
				Object blockNumber = txInfo.get("blockNumber");
				System.out.println("[sync]getTransByAddress: blockNumber: " + blockNumber);

				Object from = txInfo.get("from");
				System.out.println("[sync]getTransByAddress: from: " + from);
				
				Object to = txInfo.get("to");
				System.out.println("[sync]getTransByAddress: to: : " + to);
				
				Object value = txInfo.get("value");
				System.out.println("[sync]getTransByAddress: value: : " + value);						
			}
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<List> txListFuture = javaApi.getTransByAddress("WAN", "0x731d8fdc53039f07efd87be3c387e747da92911f").sendAsync();
			
			List txList = txListFuture.get();
//			System.out.println("\n[async] txList: " + txList);

			for(int i=0; i<txList.size(); i++) {
				Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
				
				Object blockHash = txInfo.get("blockHash");
				System.out.println("\n[async]getTransByAddress: blockHash: " + blockHash);
					
				Object blockNumber = txInfo.get("blockNumber");
				System.out.println("[async]getTransByAddress: blockNumber: " + blockNumber);

				Object from = txInfo.get("from");
				System.out.println("[async]getTransByAddress: from: " + from);
				
				Object to = txInfo.get("to");
				System.out.println("[async]getTransByAddress: to: : " + to);
				
				Object value = txInfo.get("value");
				System.out.println("[async]getTransByAddress: value: : " + value);					
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
	
       
	//////////////////////////////////////////////////////////////
	// Transactions: getTransByAddressBetweenBlocks
	//////////////////////////////////////////////////////////////
    public void testGetTransByAddressBetweenBlocks() {
		try {
			List txList = javaApi.getTransByAddressBetweenBlocks("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82",670730, 670735).send();

//			System.out.println("\n[sync] txList: " + txList);
			
			for(int i=0; i<txList.size(); i++) {
				Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
			
				Object blockHash = txInfo.get("blockHash");
				System.out.println("\n[sync]getTransByAddressBetweenBlocks: blockHash: " + blockHash);
					
				Object blockNumber = txInfo.get("blockNumber");
				System.out.println("[sync]getTransByAddressBetweenBlocks: blockNumber: " + blockNumber);

				Object from = txInfo.get("from");
				System.out.println("[sync]getTransByAddressBetweenBlocks: from: " + from);
				
				Object to = txInfo.get("to");
				System.out.println("[sync]getTransByAddressBetweenBlocks: to: : " + to);
				
				Object value = txInfo.get("value");
				System.out.println("[sync]getTransByAddressBetweenBlocks: value: : " + value);					
			}
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<List> txListFuture = javaApi.getTransByAddressBetweenBlocks("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82", 670730, 670735).sendAsync();
			
			List txList = txListFuture.get();
//			System.out.println("\n[async] txList: " + txList.toString());

			for(int i=0; i<txList.size(); i++) {
				Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
				
				Object blockHash = txInfo.get("blockHash");
				System.out.println("\n[async]getTransByAddressBetweenBlocks: blockHash: " + blockHash);
					
				Object blockNumber = txInfo.get("blockNumber");
				System.out.println("[async]getTransByAddressBetweenBlocks: blockNumber: " + blockNumber);

				Object from = txInfo.get("from");
				System.out.println("[async]getTransByAddressBetweenBlocks: from: " + from);
					
				Object to = txInfo.get("to");
				System.out.println("[async]getTransByAddressBetweenBlocks: to: : " + to);
				
				Object value = txInfo.get("value");
				System.out.println("[async]getTransByAddressBetweenBlocks: value: : " + value);				
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
        
	//////////////////////////////////////////////////////////////
	// Transactions: getTransByBlock
	//////////////////////////////////////////////////////////////
    public void testGetTransByBlock() {
		try {
			List txList = javaApi.getTransByBlock("WAN", "0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").send();
//			System.out.println("\n[sync] getTransByBlock: " + txList);
			
			for(int i=0; i<txList.size(); i++) {
				Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
				
				Object blockHash = txInfo.get("blockHash");
				System.out.println("\n[sync]getTransByBlock: blockHash: " + blockHash);
				
				Object blockNumber = txInfo.get("blockNumber");
				System.out.println("[sync]getTransByBlock: blockNumber: " + blockNumber);

				Object from = txInfo.get("from");
				System.out.println("[sync]getTransByBlock: from: " + from);
				
				Object to = txInfo.get("to");
				System.out.println("[sync]getTransByBlock: to: : " + to);
				
				Object value = txInfo.get("value");
				System.out.println("[sync]getTransByBlock: value: : " + value);					
			}
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<List> txListFuture = javaApi.getTransByBlock("WAN", "0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").sendAsync();
			
			List txList = txListFuture.get();

			for(int i=0; i<txList.size(); i++) {
				Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
				
				Object blockHash = txInfo.get("blockHash");
				System.out.println("\n[async]getTransByBlock: blockHash: " + blockHash);
				
				Object blockNumber = txInfo.get("blockNumber");
				System.out.println("[async]getTransByBlock: blockNumber: " + blockNumber);

				Object from = txInfo.get("from");
				System.out.println("[async]getTransByBlock: from: " + from);
				
				Object to = txInfo.get("to");
				System.out.println("[async]getTransByBlock: to: : " + to);
				
				Object value = txInfo.get("value");
				System.out.println("[async]getTransByBlock: value: : " + value);				
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
        
	//////////////////////////////////////////////////////////////
	// Transactions: getTransactionConfirm
	//////////////////////////////////////////////////////////////
    public void testGetTransactionConfirm() {
    	
		try {
			Map<String, Object> txInfo = javaApi.getTransactionConfirm("WAN", 2, "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").send();
//			System.out.println("\n[sync]getTransactionConfirm: txInfo: " + txInfo);
							
			Object blockHash = txInfo.get("blockHash");
			System.out.println("\n[sync]getTransactionConfirm: blockHash: " + blockHash);
			
			Object blockNumber = txInfo.get("blockNumber");
			System.out.println("[sync]getTransactionConfirm: blockNumber: " + blockNumber);

			Object from = txInfo.get("from");
			System.out.println("[sync]getTransactionConfirm: from: " + from);
			
			Object to = txInfo.get("to");
			System.out.println("[sync]getTransactionConfirm: to: : " + to);
			
			Object logs = txInfo.get("logs");
			System.out.println("[sync]getTransactionConfirm: logs: : " + logs);	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<Map<String, Object>> txFuture = javaApi.getTransactionConfirm("WAN", 2, "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").sendAsync();
			
			Map<String, Object> txInfo = txFuture.get();
			
			Object blockHash = txInfo.get("blockHash");
			System.out.println("\n[async]getTransactionConfirm: blockHash: " + blockHash);
			
			Object blockNumber = txInfo.get("blockNumber");
			System.out.println("[async]getTransactionConfirm: blockNumber: " + blockNumber);

			Object from = txInfo.get("from");
			System.out.println("[async]getTransactionConfirm: from: " + from);
			
			Object to = txInfo.get("to");
			System.out.println("[async]getTransactionConfirm: to: : " + to);
			
			Object logs = txInfo.get("logs");
			System.out.println("[async]getTransactionConfirm: logs: : " + logs);	
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
       
	//////////////////////////////////////////////////////////////
	// Transactions: getTransactionReceipt
	//////////////////////////////////////////////////////////////
    public void testGetTransactionReceipt() {
		try {
			Map<String,Object> txReceipt = javaApi.getTransactionReceipt("WAN", "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").send();
//			System.out.println("\n[sync]getTransactionReceipt: txReceipt: " + txReceipt);
							
			Object blockHash = txReceipt.get("blockHash");
			System.out.println("\n[sync]getTransactionReceipt: blockHash: " + blockHash);
			
			Object blockNumber = txReceipt.get("blockNumber");
			System.out.println("[sync]getTransactionReceipt: blockNumber: " + blockNumber);

			Object from = txReceipt.get("from");
			System.out.println("[sync]getTransactionReceipt: from: " + from);
			
			Object to = txReceipt.get("to");
			System.out.println("[sync]getTransactionReceipt: to : " + to);
			
			Object status = txReceipt.get("status");
			System.out.println("[sync]getTransactionReceipt: status : " + status);	
			
			Object logs = txReceipt.get("logs");
			System.out.println("[sync]getTransactionReceipt: logs : " + logs);	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<Map<String,Object>> txReceiptFuture = javaApi.getTransactionReceipt("WAN", "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").sendAsync();
			
			Map<String,Object> txReceipt = txReceiptFuture.get();
				
			Object blockHash = txReceipt.get("blockHash");
			System.out.println("\n[async]getTransactionReceipt: blockHash: " + blockHash);
			
			Object blockNumber = txReceipt.get("blockNumber");
			System.out.println("[async]getTransactionReceipt: blockNumber: " + blockNumber);

			Object from = txReceipt.get("from");
			System.out.println("[async]getTransactionReceipt: from: " + from);
			
			Object to = txReceipt.get("to");
			System.out.println("[async]getTransactionReceipt: to: " + to);
			
			Object status = txReceipt.get("status");
			System.out.println("[async]getTransactionReceipt: status: " + status);	
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
		
	//////////////////////////////////////////////////////////////
	// Transactions: getTxInfo
	//////////////////////////////////////////////////////////////
    public void testGetTxInfo() {
		try {
			Map<String,Object> txInfo = javaApi.getTxInfo("WAN", "0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283", null).send();
//			System.out.println("\n[sync]getTxInfo:  " + txInfo);
							
			Object blockHash = txInfo.get("blockHash");
			System.out.println("\n[sync]getTxInfo: blockHash: " + blockHash);
				
			Object blockNumber = txInfo.get("blockNumber");
			System.out.println("[sync]getTxInfo: blockNumber: " + blockNumber);

			Object from = txInfo.get("from");
			System.out.println("[sync]getTxInfo: from: " + from);
			
			Object to = txInfo.get("to");
			System.out.println("[sync]getTxInfo: to: : " + to);
			
			Object value = txInfo.get("value");
			System.out.println("[sync]getTxInfo: value: : " + value);	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<Map<String,Object>> txFuture = javaApi.getTxInfo("WAN", "0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283", null).sendAsync();
			
			Map<String, Object> txInfo =  txFuture.get();
			
			Object blockHash = txInfo.get("blockHash");
			System.out.println("\n[async]getTxInfo: blockHash: " + blockHash);
				
			Object blockNumber = txInfo.get("blockNumber");
			System.out.println("[async]getTxInfo: blockNumber: " + blockNumber);

			Object from = txInfo.get("from");
			System.out.println("[async]getTxInfo: from: " + from);
			
			Object to = txInfo.get("to");
			System.out.println("[async]getTxInfo: to: : " + to);
			
			Object value = txInfo.get("value");
			System.out.println("[async]getTxInfo: value: : " + value);	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 	
    }
		
    /*
	//////////////////////////////////////////////////////////////
	// Transactions: sendRawTransaction
	//////////////////////////////////////////////////////////////
    public void testSendRawTransaction() {
		try {
			String strRslt = javaApi.sendRawTransaction("WAN", "0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356").send();
							
			System.out.println("[sync]sendRawTransaction: strRslt: " + strRslt);	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<String> txFuture = javaApi.sendRawTransaction("WAN", "0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356").sendAsync();
			
			String strRslt =  txFuture.get();
			System.out.println("[async]sendRawTransaction: strRslt: " + strRslt);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }*/
    
}

package com.wanchain.iwanj.lib.apis;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import com.wanchain.iwanj.lib.apis.Wanj;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Hello world!
 *
 */
public class BlockApis_UnitTest extends TestCase
{	
	Wanj javaApi = null;  

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BlockApis_UnitTest( String testName )
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
        return new TestSuite(BlockApis_UnitTest.class );
    }
       	
	//////////////////////////////////////////
	// GetBlockByHash
	//////////////////////////////////////////
    public void testGetBlockByHash() {
		try {
			Map<String,Object> blockInfo = javaApi.getBlockByHash("WAN","0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").send();
//			System.out.println("\n[sync]getBlockByHash: " + blockInfo);
								
			Object blockNumber = blockInfo.get("number");
			System.out.println("\n[sync]getBlockByHash: blockNumber: " + blockNumber);

			Object transactions = blockInfo.get("transactions");
			System.out.println("[sync]getBlockByHash: transactions: " + transactions);
			
			Object timestamp = blockInfo.get("timestamp");
			System.out.println("[sync]getBlockByHash: timestamp: " + timestamp);
			
			Object miner = blockInfo.get("miner");
			System.out.println("[sync]getBlockByHash: miner: " + miner);	
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		CompletableFuture<Map<String,Object>> future = javaApi.getBlockByHash("WAN","0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").sendAsync();
		
		try {			
			Map<String,Object> blockInfo = future.get();	
			
			Object blockNumber = blockInfo.get("number");
			System.out.println("\n[async]getBlockByHash: blockNumber: " + blockNumber);

			Object transactions = blockInfo.get("transactions");
			System.out.println("[async]getBlockByHash: transactions: " + transactions);
			
			Object timestamp = blockInfo.get("timestamp");
			System.out.println("[async]getBlockByHash: timestamp: " + timestamp);
			
			Object miner = blockInfo.get("miner");
			System.out.println("[async]getBlockByHash: miner: " + miner);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
	//////////////////////////////////////////
	// getBlockByNumber
	//////////////////////////////////////////
    public void testGetBlockByNumber() {
		try {
			Map<String,Object> blockInfo = javaApi.getBlockByNumber("WAN", 1362889).send();
//			System.out.println("\n[sync]testGetBlockByNumber: " + blockInfo);
								
			Object hash = blockInfo.get("hash");
			System.out.println("\n[sync]getBlockByNumber: hash: " + hash);

			Object transactions = blockInfo.get("transactions");
			System.out.println("[sync]getBlockByNumber: transactions: " + transactions);
			
			Object timestamp = blockInfo.get("timestamp");
			System.out.println("[sync]getBlockByNumber: timestamp: " + timestamp);
			
			Object miner = blockInfo.get("miner");
			System.out.println("[sync]getBlockByNumber: miner: " + miner);	
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		CompletableFuture<Map<String,Object>> future = javaApi.getBlockByNumber("WAN",1362889).sendAsync();
		
		try {			
			Map<String,Object> blockInfo = future.get();	
			
			Object hash = blockInfo.get("hash");
			System.out.println("\n[async]getBlockByNumber: hash: " + hash);

			Object transactions = blockInfo.get("transactions");
			System.out.println("[async]getBlockByNumber: transactions: " + transactions);
			
			Object timestamp = blockInfo.get("timestamp");
			System.out.println("[async]getBlockByNumber: timestamp: " + timestamp);
			
			Object miner = blockInfo.get("miner");
			System.out.println("[async]getBlockByNumber: miner: " + miner);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
		
	//////////////////////////////////////////
	// getBlockNumber
	//////////////////////////////////////////
    public void testGetBlockNumber() {
    	try {
			BigInteger blockNo = javaApi.getBlockNumber("WAN").send();							
			System.out.println("\n[sync]getBlockNumber: blockNo: " + blockNo);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CompletableFuture<BigInteger> future = javaApi.getBlockNumber("WAN").sendAsync();
		
		try {						
			BigInteger blockNo = future.get();				
			System.out.println("\n[async]getBlockNumber: blockNo: " + blockNo);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
	//////////////////////////////////////////
	// getBlockTransactionCount
	//////////////////////////////////////////
    public void testGetBlockTransactionCount() {
		try {
			BigInteger txCount = javaApi.getBlockTransactionCount("WAN", "1362889").send();							
			System.out.println("\n[sync]getBlockTransactionCount: txCount: " + txCount);
			
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		CompletableFuture<BigInteger> future = javaApi.getBlockTransactionCount("WAN", "1362889").sendAsync();
		
		try {			
			BigInteger txCount = future.get();				
			System.out.println("\n[async]getBlockTransactionCount: txCount: " + txCount);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

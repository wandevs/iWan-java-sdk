package com.wanchain.iwanj.lib.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

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
public class CrossChainApis_UnitTest extends TestCase
{	
	
	Wanj javaApi = null;  

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CrossChainApis_UnitTest( String testName )
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
        return new TestSuite(CrossChainApis_UnitTest.class );
    }
       		   
	///////////////////////////////////
	// getCoin2WanRatio
	///////////////////////////////////  
    public void testGetCoin2WanRatio() {
		try {
			BigInteger ratioValue = javaApi.getCoin2WanRatio("ETH").send();
			
			System.out.println("\n[sync]getCoin2WanRatio : " + ratioValue);
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			CompletableFuture<BigInteger> ratioFuture = javaApi.getCoin2WanRatio("ETH").sendAsync();
			
			BigInteger ratioValue = ratioFuture.get();			
			System.out.println("\n[async]getCoin2WanRatio : " + ratioValue);
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		   
	///////////////////////////////////
	// getRegTokens
	///////////////////////////////////  
    public void testGetRegTokens() {
		try {
			List tokenList = javaApi.getRegTokens("ETH").send();
//			System.out.println("\n[sync] getRegTokens tokenList: " + tokenList);
			
			for(int i=0; i < tokenList.size(); i++) {
				Map<String, Object> tokenInfo = (Map<String, Object>) tokenList.get(i);
				
				Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
				System.out.println("\n[sync] getRegTokens tokenOrigAddr: " + tokenOrigAddr);
				
				Object tokenWanAddr = tokenInfo.get("tokenWanAddr");
				System.out.println("[sync] getRegTokens tokenWanAddr: " + tokenWanAddr);
				
				Object ratio = tokenInfo.get("ratio");
				System.out.println("[sync] getRegTokens ratio: " + ratio);
				
				Object minDeposit = tokenInfo.get("minDeposit");
				System.out.println("[sync] getRegTokens minDeposit: " + minDeposit);				
			}			
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		try {
			CompletableFuture<List> tokenFuture = javaApi.getRegTokens("ETH").sendAsync();
			
			List tokenList = tokenFuture.get();			
			for(int i=0; i < tokenList.size(); i++) {
				Map<String, Object> tokenInfo = (Map<String, Object>) tokenList.get(i);
				
				Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
				System.out.println("\n[async] getRegTokens tokenOrigAddr: " + tokenOrigAddr);
				
				Object tokenWanAddr = tokenInfo.get("tokenWanAddr");
				System.out.println("[async] getRegTokens tokenWanAddr: " + tokenWanAddr);
				
				Object ratio = tokenInfo.get("ratio");
				System.out.println("[async] getRegTokens ratio: " + ratio);
				
				Object minDeposit = tokenInfo.get("minDeposit");
				System.out.println("[async] getRegTokens minDeposit: " + minDeposit);				
			}	
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
		
		   
	///////////////////////////////////
	// getStoremanGroups
	///////////////////////////////////
    public void testGetStoremanGroups() {
		try {
			List smgList = javaApi.getStoremanGroups("ETH").send();
//			System.out.println("\n[sync] getStoremanGroups smgList: " + smgList);
			
			for(int i=0; i < smgList.size(); i++) {
				Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
				
				Object wanAddress = tokenInfo.get("wanAddress");
				System.out.println("\n[sync] getStoremanGroups wanAddress: " + wanAddress);
				
				Object ethAddress = tokenInfo.get("ethAddress");
				System.out.println("[sync] getStoremanGroups ethAddress: " + ethAddress);
				
				Object txFeeRatio = tokenInfo.get("txFeeRatio");
				System.out.println("[sync] getStoremanGroups txFeeRatio: " + txFeeRatio);
				
				Object deposit = tokenInfo.get("deposit");
				System.out.println("[sync] getStoremanGroups deposit: " + deposit);	
				
				Object receivable = tokenInfo.get("receivable");
				System.out.println("[sync] getStoremanGroups receivable: " + receivable);			
				
				Object payable = tokenInfo.get("payable");
				System.out.println("[sync] getStoremanGroups payable: " + payable);					
				
				Object debt = tokenInfo.get("debt");
				System.out.println("[sync] getStoremanGroups debt: " + debt);						
			}			
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		try {
			CompletableFuture<List> smgFuture = javaApi.getStoremanGroups("ETH").sendAsync();
			
			List smgList = smgFuture.get();	
			for(int i=0; i < smgList.size(); i++) {
				Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
				
				Object wanAddress = tokenInfo.get("wanAddress");
				System.out.println("\n[async] getStoremanGroups wanAddress: " + wanAddress);
				
				Object ethAddress = tokenInfo.get("ethAddress");
				System.out.println("[async] getStoremanGroups ethAddress: " + ethAddress);
				
				Object txFeeRatio = tokenInfo.get("txFeeRatio");
				System.out.println("[async] getStoremanGroups txFeeRatio: " + txFeeRatio);
				
				Object deposit = tokenInfo.get("deposit");
				System.out.println("[async] getStoremanGroups deposit: " + deposit);	
				
				Object receivable = tokenInfo.get("receivable");
				System.out.println("[async] getStoremanGroups receivable: " + receivable);			
				
				Object payable = tokenInfo.get("payable");
				System.out.println("[async] getStoremanGroups payable: " + payable);					
				
				Object debt = tokenInfo.get("debt");
				System.out.println("[async] getStoremanGroups debt: " + debt);						
			}			
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
		
	///////////////////////////////////
	// getToken2WanRatio
	///////////////////////////////////  
    public void testGetToken2WanRatio() {
		try {
			BigInteger ratioValue = javaApi.getToken2WanRatio("ETH", "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").send();
			
			System.out.println("\n[sync]getCoin2WanRatio : " + ratioValue);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		try {
			CompletableFuture<BigInteger> ratioFuture = javaApi.getToken2WanRatio("ETH", "0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814").sendAsync();
			
			BigInteger ratioValue = ratioFuture.get();			
			System.out.println("\n[async]getCoin2WanRatio : " + ratioValue);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
		
		   
	///////////////////////////////////
	// getTokenStoremanGroups
	///////////////////////////////////  
    public void testGetTokensStoremanGroups() {
		try {
			List smgList = javaApi.getTokenStoremanGroups("ETH", "0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814").send();
//			System.out.println("\n[sync] getTokenStoremanGroups smgList: " + smgList);
			
			for(int i=0; i < smgList.size(); i++) {
				Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
				
				Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
				System.out.println("\n[sync] getTokenStoremanGroups tokenOrigAddr: " + tokenOrigAddr);
				
				Object smgWanAddr = tokenInfo.get("smgWanAddr");
				System.out.println("[sync] getTokenStoremanGroups smgWanAddr: " + smgWanAddr);
				
				Object smgOrigAddr = tokenInfo.get("smgOrigAddr");
				System.out.println("[sync] getTokenStoremanGroups smgOrigAddr: " + smgOrigAddr);
				
				Object wanDeposit = tokenInfo.get("wanDeposit");
				System.out.println("[sync] getTokenStoremanGroups wanDeposit: " + wanDeposit);	
				
				Object quota = tokenInfo.get("quota");
				System.out.println("[sync] getTokenStoremanGroups quota: " + quota);	
				
				Object receivable = tokenInfo.get("receivable");
				System.out.println("[sync] getTokenStoremanGroups receivable: " + receivable);			
				
				Object payable = tokenInfo.get("payable");
				System.out.println("[sync] getTokenStoremanGroups payable: " + payable);					
				
				Object debt = tokenInfo.get("debt");
				System.out.println("[sync] getTokenStoremanGroups debt: " + debt);						
			}			
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			CompletableFuture<List> smgFuture = javaApi.getTokenStoremanGroups("ETH", "0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814").sendAsync();
			
			List smgList = smgFuture.get();	
			for(int i=0; i < smgList.size(); i++) {
				Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
				
				Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
				System.out.println("\n[async] getTokenStoremanGroups tokenOrigAddr: " + tokenOrigAddr);
				
				Object smgWanAddr = tokenInfo.get("smgWanAddr");
				System.out.println("[async] getTokenStoremanGroups smgWanAddr: " + smgWanAddr);
				
				Object smgOrigAddr = tokenInfo.get("smgOrigAddr");
				System.out.println("[async] getTokenStoremanGroups smgOrigAddr: " + smgOrigAddr);
				
				Object wanDeposit = tokenInfo.get("wanDeposit");
				System.out.println("[async] getTokenStoremanGroups wanDeposit: " + wanDeposit);	
				
				Object quota = tokenInfo.get("quota");
				System.out.println("[async] getTokenStoremanGroups quota: " + quota);	
				
				Object receivable = tokenInfo.get("receivable");
				System.out.println("[async] getTokenStoremanGroups receivable: " + receivable);			
				
				Object payable = tokenInfo.get("payable");
				System.out.println("[async] getTokenStoremanGroups payable: " + payable);					
				
				Object debt = tokenInfo.get("debt");
				System.out.println("[async] getTokenStoremanGroups debt: " + debt);								
			}			
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

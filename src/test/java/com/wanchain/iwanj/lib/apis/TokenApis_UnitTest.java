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
public class TokenApis_UnitTest  extends TestCase
{	
	
	Wanj javaApi = null;  
	String[] accoutArray = null;
	String[] tokenArray = null;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TokenApis_UnitTest( String testName )
    {
        super( testName );     

        String apiKey = "47f0102e75a41dccd836c849b0d16291e33522358ab8ba146cb17709161614b1";
        String secretKey = "b803eed271c927719a72e9e729bb016c8de2770896abbc84278549a2385c0572";
        
        javaApi = new Wanj(apiKey, secretKey, null); 
        
        accoutArray = new String[3];
        accoutArray[0] = "0x13827c4200a764fb6f193a3f6e33b9efa3b4073d";
        accoutArray[1] = "0xc7a04e264c5a0fe542ddccbddc2df6d31cd6b22a";
        accoutArray[2] = "0x860306e69efcb22852421608ae1f546e222c948c";
        
        tokenArray = new String[3];
        tokenArray[0] = "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359";
        tokenArray[1] = "0x514910771af9ca656af840dff83e8264ecf986ca";
        tokenArray[2] = "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48";
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(TokenApis_UnitTest.class );
    }        		
       
    
	//////////////////////////////////////////////////////////////
	// Tokens: getMultiTokenBalance
	//////////////////////////////////////////////////////////////
    public void testGetMultiTokenBalance() {
		try {
			Map<String, BigInteger> tokenBalance = javaApi.getMultiTokenBalance("ETH", accoutArray, "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").send();
			System.out.println("\n[sync]getMultiTokenBalance:  " + tokenBalance);
							
			BigInteger accoutA = tokenBalance.get(accoutArray[0]);
			System.out.println("\n[sync]getMultiTokenBalance: accoutA: " + accoutA);
			
			BigInteger accoutB = tokenBalance.get(accoutArray[1]);
			System.out.println("[sync]getMultiTokenBalance: accoutB: " + accoutB);
			
			BigInteger accoutC = tokenBalance.get(accoutArray[2]);
			System.out.println("[sync]getMultiTokenBalance: accoutC: " + accoutC);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<Map<String, BigInteger>> tokenFuture = javaApi.getMultiTokenBalance("ETH", accoutArray, "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").sendAsync();
			
			Map<String, BigInteger> tokenBalance = tokenFuture.get();
			BigInteger accoutA = tokenBalance.get(accoutArray[0]);
			System.out.println("\n[async]getMultiTokenBalance: accoutA: " + accoutA);
			
			BigInteger accoutB = tokenBalance.get(accoutArray[1]);
			System.out.println("[async]getMultiTokenBalance: accoutB: " + accoutB);
			
			BigInteger accoutC = tokenBalance.get(accoutArray[2]);
			System.out.println("[async]getMultiTokenBalance: accoutC: " + accoutC);				
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
	//////////////////////////////////////////////////////////////
	// Tokens: getMultiTokenInfo
	//////////////////////////////////////////////////////////////
    public void testGetMultiTokenInfo() {
        
		try {
			Map<String, Object> tokenInfo = javaApi.getMultiTokenInfo("ETH", tokenArray).send();
//			System.out.println("\n[sync]getMultiTokenInfo: " + tokenInfo);
							
			Object tokenA = tokenInfo.get(tokenArray[0]);
			System.out.println("\n[sync]getMultiTokenInfo: tokenA: " + tokenA);
			
			Object tokenB = tokenInfo.get(tokenArray[1]);
			System.out.println("[sync]getMultiTokenInfo: tokenB: " + tokenB);
			
			Object tokenC = tokenInfo.get(tokenArray[2]);
			System.out.println("[sync]getMultiTokenInfo: tokenC: " + tokenC);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<Map<String, Object>> tokenFuture = javaApi.getMultiTokenInfo("ETH", tokenArray).sendAsync();
			
			Map<String, Object> tokenInfo = tokenFuture.get();
			
			Object tokenA = tokenInfo.get(tokenArray[0]);
			System.out.println("\n[async]getMultiTokenInfo: tokenA: " + tokenA);
			
			Object tokenB = tokenInfo.get(tokenArray[1]);
			System.out.println("[async]getMultiTokenInfo: tokenB: " + tokenB);
			
			Object tokenC = tokenInfo.get(tokenArray[2]);
			System.out.println("[async]getMultiTokenInfo: tokenC: " + tokenC);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
	//////////////////////////////////////////////////////////////
	// Tokens: getTokenBalance
	//////////////////////////////////////////////////////////////
    public void testGetTokenBalance() {
		try {
			BigInteger tokenBalance = javaApi.getTokenBalance("ETH", accoutArray[1], "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").send();
							
			System.out.println("\n[sync]getTokenBalance: tokenBalance: " + tokenBalance);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<BigInteger> tokenFuture = javaApi.getTokenBalance("ETH", accoutArray[2], "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").sendAsync();
			
			BigInteger tokenBalance = tokenFuture.get();
			System.out.println("\n[async]getTokenBalance: accout: " + tokenBalance);			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	//////////////////////////////////////////////////////////////
	// Tokens: getTokenInfo
	////////////////////////////////////////////////////////////// 
    public void testGetTokenInfo() {
		try {
			Map<String, Object> tokenInfo = javaApi.getTokenInfo("ETH", tokenArray[0]).send();
//			System.out.println("\n[sync]getTokenInfo: " + tokenInfo);
							
			Object symbol = tokenInfo.get("symbol");
			System.out.println("\n[sync]getTokenInfo: symbol: " + symbol);
			
			Object decimals = tokenInfo.get("decimals");
			System.out.println("[sync]getTokenInfo: decimals: " + decimals);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<Map<String, Object>> tokenFuture = javaApi.getTokenInfo("ETH", tokenArray[1]).sendAsync();
			
			Map<String, Object> tokenInfo = tokenFuture.get();
			Object symbol = tokenInfo.get("symbol");
			System.out.println("\n[async]getTokenInfo: symbol: " + symbol);
			
			Object decimals = tokenInfo.get("decimals");
			System.out.println("[async]getTokenInfo: decimals: " + decimals);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
	//////////////////////////////////////////////////////////////
	// Tokens: getTokenSupply
	//////////////////////////////////////////////////////////////
    public void testGetTokenSupply() {
		try {
			BigInteger tokenBalance = javaApi.getTokenSupply("ETH", tokenArray[1]).send();
							
			System.out.println("\n[sync]getTokenSupply: accout: " + tokenBalance);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<BigInteger> tokenFuture = javaApi.getTokenSupply("ETH", tokenArray[2]).sendAsync();
			
			BigInteger tokenBalance = tokenFuture.get();
			System.out.println("\n[async]getTokenSupply: accout: " + tokenBalance);			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*	
	//////////////////////////////////////////////////////////////
	// Tokens: getTokenAllowance  
    // notice: this api data source from apitest network
	//////////////////////////////////////////////////////////////
	public void testGetTokenAllowance(){
		try {
			BigInteger tokenAllowance = javaApi.getTokenAllowance("ETH", "0xc5bc855056d99ef4bda0a4ae937065315e2ae11a", "0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8", "0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1").send();
							
			System.out.println("\n[sync]getTokenAllowance: tokenAllowance: " + tokenAllowance);
				
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		try {
			CompletableFuture<BigInteger> tokenFuture = javaApi.getTokenAllowance("ETH", "0xc5bc855056d99ef4bda0a4ae937065315e2ae11a", "0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8", "0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1").sendAsync();
			
			BigInteger tokenAllowance = tokenFuture.get();
			System.out.println("\n[async]getTokenAllowance: tokenAllowance: " + tokenAllowance);			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
}

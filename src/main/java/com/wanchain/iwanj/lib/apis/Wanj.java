package com.wanchain.iwanj.lib.apis;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.wanchain.iwanj.lib.ResultConvert;
import com.wanchain.iwanj.lib.ResultType;
import com.wanchain.iwanj.lib.WanRequest;
import com.wanchain.iwanj.lib.WebsocketClient;
import com.wanchain.iwanj.lib.WsInstance;

import com.wanchain.iwanj.lib.converts.RCArray;
import com.wanchain.iwanj.lib.converts.RCBigInteger;
import com.wanchain.iwanj.lib.converts.RCBigIntegerMap;
import com.wanchain.iwanj.lib.converts.RCBoolean;
import com.wanchain.iwanj.lib.converts.RCMap;
import com.wanchain.iwanj.lib.converts.RCObject;
import com.wanchain.iwanj.lib.converts.RCString;


/**
* iWan-java-SDK
*/ 

/**
 * class Wanj is iWan-SDK for java language.
 * @version v1.0.1
 */
public class Wanj  extends WsInstance {
	
	public static void main(String[] args) {
		
	}
	
	/**
     * Wanj class constructor function. 
     *
	 * @param apiKey: your apiKey 
	 * @param secretKey: your secretKey 
	 * @param option: initial iwan-sdk option, could be set to null to use default parameter  
     */
    public Wanj(String apiKey, String secretKey, Map<String, String> option) {
    	
		super(apiKey, secretKey, option);
		// TODO Auto-generated constructor stub		

    	ResultConvert rcString = new RCString();
    	ResultConvert rcBigInteger = new RCBigInteger();
    	ResultConvert rcBoolean = new RCBoolean();
    	ResultConvert rcArray = new RCArray();
    	ResultConvert rcBigIntegerMap = new RCBigIntegerMap();
    	ResultConvert rcMap = new RCMap();
    	ResultConvert rcObj = new RCObject();
    	
    	WebsocketClient.addResultType(ResultType.STRING, rcString);
    	WebsocketClient.addResultType(ResultType.BIGNUMBER, rcBigInteger);
    	WebsocketClient.addResultType(ResultType.BOOLEAN, rcBoolean);
    	WebsocketClient.addResultType(ResultType.ARRAYMAP, rcArray);
    	WebsocketClient.addResultType(ResultType.BIGINTEGERMAP, rcBigIntegerMap);
    	WebsocketClient.addResultType(ResultType.MAP, rcMap);
    	WebsocketClient.addResultType(ResultType.OBJECT, rcObj);
	}
    
    /**
     * Check whether is a valid hash value.
     * 
     * @param strHash the string value need to check
     * @return isMatch true is a valid hash string, false is not a valid hash string 
     */
    private boolean checkHash(String strHash) {
        // check if it has the basic requirements of a hash
    	String strPattern = "^(0x)?[0-9a-fA-F]{64}$";
    	boolean isMatch = Pattern.matches(strPattern, strHash);
    	
        return isMatch;
    }
	

    /**
    *
    * @apiName getBalance
    * @apiGroup Accounts
    * @api {CONNECT} /ws/v3/YOUR-API-KEY getBalance
    * @apiVersion 1.0.0
    * @apiDescription Get balance for a single address.
    *
    * @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
    * @apiParam (Input Parameter) {String} address The account being queried.
    * 
	* @apiParam (Return) {BigInteger} response  The response is the balance of queried account, which type is <code>BigInteger</code>. Please refer to examples above.
	* 
    * @apiParamExample {String} JSON-RPC over websocket
    * {"jsonrpc":"2.0","method":"getBalance","params":{"address": "0x8456711c9d3dce1b8a6fcfa372277b95acb404c9","chainType":"WAN"},"id":1}
    *
    * 
    * @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
    *	try {
	*		BigInteger balance = javaApi.getBalance("WAN", "0x8456711c9d3dce1b8a6fcfa372277b95acb404c9").send();
	*		System.out.println("\n[sync]getBalance : " + balance);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
    *
    * @apiExample {java} Example by async mode: 
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *  	
	*	CompletableFuture<BigInteger> rsltFuture = javaApi.getBalance("WAN", "0x8456711c9d3dce1b8a6fcfa372277b95acb404c9").sendAsync();	
	*
	*	try {			
	*		BigInteger balance = rsltFuture.get();	*		
	*		System.out.println("\n[async]getBalance : " + balance);
	*
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}	
    *
    * @apiSuccessExample {BigInteger} Successful Response
    *	63545814080091111700
    *
    */
	public WanRequest<BigInteger> getBalance(String chainType, String address) {
                  
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("address", address);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>(); 
        mapRequest.put("method","getBalance");
        mapRequest.put("params",mapParams);

        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
    }

	/**
	*
	* @apiName getMultiBalances
	* @apiGroup Accounts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getMultiBalances
	* @apiVersion 1.0.0
	* @apiDescription Get balance for multiple Addresses in a single call.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String[]} addressArray An array of addresses being queried.
	* 
	* @apiParam (Return) {Map} response  The response type is <code>Map<String, BigInteger></code>, and you can get the balance of queried account by address. Please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getMultiBalances","params":{"address": ["0x8456711c9d3dce1b8a6fcfa372277b95acb404c9","0x2cc79fa3b80c5b9b02051facd02478ea88a78e2d"],"chainType":"WAN"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	* 	addresses = new String[2];
	*	addresses[0] = "0x8456711c9d3dce1b8a6fcfa372277b95acb404c9";
	*	addresses[1] = "0x2cc79fa3b80c5b9b02051facd02478ea88a7802d";
	*   
	*	try {
	*		Map<String, BigInteger> mapRslt = javaApi.getMultiBalances("WAN", addresses).send();
	*
	*		System.out.println("\n[sync]addresses[0] balance : " + mapRslt.get(addresses[0]));
	*		System.out.println("\n[sync]addresses[1] balance: " + mapRslt.get(addresses[1]));
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode: 
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	addresses = new String[2];
	*	addresses[0] = "0x8456711c9d3dce1b8a6fcfa372277b95acb404c9";
	*	addresses[1] = "0x2cc79fa3b80c5b9b02051facd02478ea88a7802d";
	*     	
	*	CompletableFuture<Map<String, BigInteger>> rsltFuture = javaApi.getMultiBalances("WAN", addresses).sendAsync();
	*		
	*	try {	
	*		Map<String, BigInteger> mapRslt = rsltFuture.get(); 
	*		
	*		System.out.println("\n[async]addresses[0] balance : " + mapRslt.get(addresses[0]));
	*		System.out.println("\n[async]addresses[1] balance: " + mapRslt.get(addresses[1]));
	*		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {Map} Successful Response 
	* {
	* 	0x8456711c9d3dce1b8a6fcfa372277b95acb404c9=63545814080091111700, 
	* 	0x2cc79fa3b80c5b9b02051facd02478ea88a7802d=0
	* }
	*
	*/
	public WanRequest<Map<String, BigInteger>> getMultiBalances( String chainType, String[] addrArray) {
	      
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("address", addrArray);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getMultiBalances");
        mapRequest.put("params",mapParams);

        return new WanRequest<Map<String, BigInteger>>(mapRequest, ResultType.BIGINTEGERMAP, this);
    }
	
	/**
	*
	* @apiName getNonce
	* @apiGroup Accounts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getNonce
	* @apiVersion 1.0.0
	* @apiDescription Get the nonce of an account.
	* 
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} address The account being queried.
	*
	* @apiParam (Return) {String} response  The response is the nonce of queried account, and the type is <code>String</code>. Please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getNonce","params":{"address": "0x731bd7289b4191706b00f6f1877662b5e8697e82","chainType":"WAN"},"id":1}
    * 
    * @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
    *	try {
	*		String nonce = javaApi.getNonce("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").send();
	*		System.out.println("\n[sync]getNonce : " + nonce);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
    *
    * @apiExample {java} Example by async mode:   
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *	
	*	CompletableFuture<String> nonceFuture = javaApi.getNonce("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").sendAsync();	
	*
	*	try {
	*		String nonce = nonceFuture.get();		
	*		System.out.println("\n[async]getNonce : " + nonce);
	*
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {String} Successful Response
	*	"0x6ac"
	*
	*/
	public WanRequest<String> getNonce(String chainType, String address) {
        
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("address", address);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getNonce");
        mapRequest.put("params",mapParams);

        return new WanRequest<String>(mapRequest, ResultType.STRING, this);
    }

	
	/**
	*
	* @apiName getNonceIncludePending
	* @apiGroup Accounts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getNonceIncludePending
	* @apiVersion 1.0.0
	* @apiDescription Get the pending nonce of an account.
	* 
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} address The account being queried.
	*
	* @apiParam (Return) {String} response  The response is the pending nonce of queried account, and type is <code>String</code>. Please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getNonceIncludePending","params":{"address": "0x731bd7289b4191706b00f6f1877662b5e8697e82","chainType":"WAN"}, "id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		String nonce = javaApi.getNonceIncludePending("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").send();		
	*		System.out.println("\n[sync]getNonceIncludePending : " + nonce);
	*		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:	
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *  
	*	CompletableFuture<String> nonceFuture = javaApi.getNonceIncludePending("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82").sendAsync();
	*
	*	try {
	*		String nonce = nonceFuture.get();			
	*		System.out.println("\n[async]getNonceIncludePending : " + nonce);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {String} Successful Response
	*	"0x6ac"
	*
	*/
	public WanRequest<String> getNonceIncludePending(String chainType, String address) {
        
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("address", address);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getNonceIncludePending");
        mapRequest.put("params",mapParams);

        return new WanRequest<String>(mapRequest, ResultType.STRING, this);
    }

	/**
	*
	* @apiName getUTXO
	* @apiGroup Accounts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getUTXO
	* @apiVersion 1.0.0
	* @apiDescription Get the detail BTC UTXO info for BTC.
	* 
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"BTC"</code>.
	* @apiParam (Input Parameter) {Number} minconf The min confirm number of BTC UTXO, usually 0.
	* @apiParam (Input Parameter) {Number} maxconf The max confirm number of BTC UTXO, usually the confirmed blocks you want to wait for the UTXO.
	* @apiParam (Input Parameter) {String[]} address The address array that you want to search.
	*
	* @apiParam (Return) {List} response  The response is the detail BTC UTXO array, and the type is <code>List<Map<String, Object>></code> which element is one utxo info. Please refer to examples above.
	* 
	* @apiParamExample {string} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getUTXO","params":{"chainType":"BTC", "minconf":0, "maxconf":100000, "address":["1FZeVAnjdTK4Upu3b1CjhvwkrgfzvF6YUS","1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX"]},"id":1}
	*
	* 
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	String[] addArray = new String[2];
	*	addArray[0] = "1FZeVAnjdTK4Upu3b1CjhvwkrgfzvF6YUS";
	*	addArray[1] = "1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX";
	*
	*	try {
	*		List utxoRslt = javaApi.getUTXO("BTC", 0, 100000, addArray).send();
	*		
	*		for(int i=0; i < utxoRslt.size(); i++) {
	*			Map<String, Object> utxoItem = (Map<String, Object>) utxoRslt.get(i);
	*			
	*			Object txid = utxoItem.get("txid");
	*			System.out.println("\n[sync]getUTXO: txid: " + txid);
	*
	*			Object scriptPubKey = utxoItem.get("scriptPubKey");
	*			System.out.println("[sync]getUTXO: scriptPubKey: " + scriptPubKey);
	*			
	*			Object safe = utxoItem.get("safe");
	*			System.out.println("[sync]getUTXO: safe: " + safe);
	*			
	*			Object value = utxoItem.get("value");
	*			System.out.println("[sync]getUTXO: value: : " + value);	
	*		}			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	String[] addArray = new String[2];
	*	addArray[0] = "1FZeVAnjdTK4Upu3b1CjhvwkrgfzvF6YUS";
	*	addArray[1] = "1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX";
	*
	*	CompletableFuture<List> utxoFuture = javaApi.getUTXO("BTC", 0, 100000, addArray).sendAsync();		
	*	
	*	try {
	*		List utxoList = utxoFuture.get();
	*
	*		for(int i=0; i < utxoList.size(); i++) {
	*			Map<String, Object> utxoItem = (Map<String, Object>) utxoList.get(i);
	*			
	*			Object txid = utxoItem.get("txid");
	*			System.out.println("\n[async]getUTXO: txid: " + txid);
	*
	*			Object scriptPubKey = utxoItem.get("scriptPubKey");
	*			System.out.println("[async]getUTXO: scriptPubKey: " + scriptPubKey);
	*			
	*			Object safe = utxoItem.get("safe");
	*			System.out.println("[async]getUTXO: safe: " + safe);
	*			
	*			Object value = utxoItem.get("value");
	*			System.out.println("[async]getUTXO: value: : " + value);	
	*		}				
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {List<Map>} Successful Response
	*[
	  {
	    scriptPubKey=76a914ac752b86edfa7362776f198ca551fd5276d13f0788ac, 
	    amount=0.0016223, 
	    spendable=false, 
	    solvable=false, 
	    address=1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX, 
	    safe=true, 
	    txid=a97e182cd46280737bea0782adb1ae7fb5e5d146e3036a2653c8a18a5771dce7, 
	    label=, 
	    confirmations=4855, 
	    value=0.0016223, 
	    vout=1
	  }
	*]
	*
	*/
	public WanRequest<List> getUTXO(String chainType, Number minConf, Number maxConf, String[] addrArray) {
    
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("minconf", minConf);
        mapParams.put("maxconf", maxConf);
        mapParams.put("address", addrArray);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getUTXO");
        mapRequest.put("params",mapParams);

        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
    }

	/**
	*
	* @apiName importAddress
	* @apiGroup Accounts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY importAddress
	* @apiVersion 1.0.0
	* @apiDescription Send a <code>'import address'</code> command to BTC.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"BTC"</code>.
	* @apiParam (Input Parameter) {String} address The BTC account address you want to import to the node to scan transactions.
	*
	* @apiParam (Return) {String} response  The response is the result of importing address operation, and is <code>String</code> type. Please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"importAddress","params":{"chainType":"BTC","address":"1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		String rslt = javaApi.importAddress("BTC", "1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX").send();
	*		Assert.assertEquals("success", rslt);
	*		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<String> rsltFuture = javaApi.importAddress("BTC", "1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX").sendAsync();
	*	
	*	try {			
	*		String rslt = rsltFuture.get(); 
	*		Assert.assertEquals("success", rslt);
	*       
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {String} Successful Response
	*	"success"
	*
	*/
	public WanRequest<String> importAddress(String chainType, String address) {
        
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("address", address);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","importAddress");
        mapRequest.put("params",mapParams);

        return new WanRequest<String>(mapRequest, ResultType.STRING, this);
    }
	

	/**
	*
	* @apiName getBlockByHash
	* @apiGroup Blocks
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getBlockByHash
	* @apiVersion 1.0.0
	* @apiDescription Get the block information about a block by block hash on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code> or <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} blockHash The blockHash you want to search.
	*
	* @apiParam (Return) {Map} response  The response is <code>Map<String, Object></code> type, and is queried block info from which you can get related property value by property name. Please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getBlockByHash","params":{"chainType":"WAN", "blockHash":"0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		Map<String,Object> blockInfo = javaApi.getBlockByHash("WAN","0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").send();
	*							
	*		Object blockNumber = blockInfo.get("number");
	*		System.out.println("\n[sync]getBlockByHash: blockNumber: " + blockNumber);
	*
	*		Object transactions = blockInfo.get("transactions");
	*		System.out.println("[sync]getBlockByHash: transactions: " + transactions);
	*		
	*		Object timestamp = blockInfo.get("timestamp");
	*		System.out.println("[sync]getBlockByHash: timestamp: " + timestamp);
	*		
	*		Object miner = blockInfo.get("miner");
	*		System.out.println("[sync]getBlockByHash: miner: " + miner);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode: 
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    * 
	*	CompletableFuture<Map<String,Object>> future = javaApi.getBlockByHash("WAN","0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").sendAsync();
	*	
	*	try {			
	*		Map<String,Object> blockInfo = future.get();	
	*		
	*		Object blockNumber = blockInfo.get("number");
	*		System.out.println("\n[async]getBlockByHash: blockNumber: " + blockNumber);
	*
	*		Object transactions = blockInfo.get("transactions");
	*		System.out.println("[async]getBlockByHash: transactions: " + transactions);
	*		
	*		Object timestamp = blockInfo.get("timestamp");
	*		System.out.println("[async]getBlockByHash: timestamp: " + timestamp);
	*		
	*		Object miner = blockInfo.get("miner");
	*		System.out.println("[async]getBlockByHash: miner: " + miner);
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {Map} Successful Response
	  {
	    logsBloom=0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 
	    totalDifficulty=9599458746581, 
	    receiptsRoot=0xcfad9c26ff4b1c03ec6fe24dc6766624fadb92641b900c07b90ba5559f59ba5c, 
	    extraData=0xd583010003846765746885676f312e39856c696e7578000000000000000000009853fb8c79ba693ce77a9a74f3f063a5f4b430e6bcfcb59093ffc57c3772f77d6e17dd4192e75832f9f7d51439b372496db471326631f745bd12d649e29496ef01, 
	    transactions=["0x4376314060f34286cfd35fb78f212a0fbc11e1a0946eaa6cc7ffaf145c31fbd7",
			"0x7ecf01d67b73dc40a7d39d7b919f9c9ca2f4ce9023f70401381c279d29cbc42b",
			"0xba0ceed770df4a6e330a643ec7f3b48096aed0ed393517caa81f06e748eb55b0",
			"0x2ff180f5fae10dbb87b29b0632e37e76fbe266e74022313b7fbc0728f4b37fa8",
			"0xa3084f20656d566ba1a60486a004d9f10515007b25200541f3291c431a3e1ef5",
			"0x72d448523cea9235851ac9bf37d47a270625fea8be5a9af7a526ade79847084a",
			"0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd",
			"0x8002c67669d420525b829b111a0947910acbce232ec68614e314cfc70c044690",
			"0x85be3736d8b8e2a97598255dc18012c4cd8f89803d846030db735e13cade0c68",
			"0x087b91305f336dbe8bf0972637fbed747629dca3e479920fcb432d1102670cad",
			"0x6b4c4c8ee8b1c3f7f2bf4ec58610be369907f0a9fbd3e79d686ca6c4edf7660a",
			"0x662cc0ded8ab79e85b74fc789c99607b66aef823d354c02a075fb79d18b50922",
			"0x3c4cd10545eab675c87c11026ad1dac5e8270297d2973f856b03855b8ea854a3",
			"0x1fbf01c7d32d24488979741f927fdb2e25ff8760f0a54673f0f139dd58af120d",
			"0xc0e52622158cb8b05dfcadb12d412e95a79b794b08ecbdf5bdf3ebf940a04106"], 
	    nonce=0x21fb481a9525e00f, 
	    miner=0x4df69093933afc9ae4f665028ce56cd7008610fc,
	    difficulty=7068264, 
	    gasLimit=4712388, 
	    number=1362889, 
	    gasUsed=315000, 
	    uncles=[], 
	    size=2299, 
	    sha3Uncles=0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347, 
	    transactionsRoot=0x6fc40a312dbd1854cf131342e87380625e11008d132dfbbb2b1ef7aa395a261a, 
	    stateRoot=0x8b4411457f93d0694e6e05adc4ec51696f6b4e16ff65378784bc2ce5ff9e3949,
	    mixHash=0x36e505466927db2f555c83046f155c2b26fee896c0bfee8ecfe95ba5cebbf69a, 
	    parentHash=0x505d86c91cfb51dcdf106f5c810627c2d2e44779339f10c143134ee20ac39224, 
	    hash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, 
	    timestamp=1533296957
	  }
	*
	*
	*/
	public WanRequest<Map<String, Object>> getBlockByHash(String chainType, String blockHash) {
        
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("blockHash", blockHash);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getBlockByHash");
        mapRequest.put("params",mapParams);

        return new WanRequest<Map<String, Object>>(mapRequest, ResultType.MAP, this);
    }

	/**
	*
	* @apiName getBlockByNumber
	* @apiGroup Blocks
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getBlockByNumber
	* @apiVersion 1.0.0
	* @apiDescription Get the block information about a block by block number on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code> or <code>"ETH"</code>.
	* @apiParam (Input Parameter) {Number} blockNumber The blockNumber you want to search.
	*
	* @apiParam (Return) {Map} response  The response is <code>Map<String, Object></code> type, and is queried block info from which you can get related property value by property name. Please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getBlockByNumber","params":{"chainType":"WAN", "blockNumber":"670731"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		Map<String,Object> blockInfo = javaApi.getBlockByNumber("WAN", 1362889).send();
	*							
	*		Object hash = blockInfo.get("hash");
	*		System.out.println("\n[sync]getBlockByNumber: hash: " + hash);
	*
	*		Object transactions = blockInfo.get("transactions");
	*		System.out.println("[sync]getBlockByNumber: transactions: " + transactions);
	*		
	*		Object timestamp = blockInfo.get("timestamp");
	*		System.out.println("[sync]getBlockByNumber: timestamp: " + timestamp);
	*		
	*		Object miner = blockInfo.get("miner");
	*		System.out.println("[sync]getBlockByNumber: miner: " + miner);	
	*		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<Map<String,Object>> future = javaApi.getBlockByNumber("WAN",1362889).sendAsync();
	*	
	*	try {			
	*		Map<String,Object> blockInfo = future.get();	
	*		
	*		Object hash = blockInfo.get("hash");
	*		System.out.println("\n[async]getBlockByNumber: hash: " + hash);
	*
	*		Object transactions = blockInfo.get("transactions");
	*		System.out.println("[async]getBlockByNumber: transactions: " + transactions);
	*		
	*		Object timestamp = blockInfo.get("timestamp");
	*		System.out.println("[async]getBlockByNumber: timestamp: " + timestamp);
	*		
	*		Object miner = blockInfo.get("miner");
	*		System.out.println("[async]getBlockByNumber: miner: " + miner);
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}	
	*
	* @apiSuccessExample {Map} Successful Response
	  {
	    logsBloom=0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 
	    totalDifficulty=9599458746581, 
	    receiptsRoot=0xcfad9c26ff4b1c03ec6fe24dc6766624fadb92641b900c07b90ba5559f59ba5c, 
	    extraData=0xd583010003846765746885676f312e39856c696e7578000000000000000000009853fb8c79ba693ce77a9a74f3f063a5f4b430e6bcfcb59093ffc57c3772f77d6e17dd4192e75832f9f7d51439b372496db471326631f745bd12d649e29496ef01, 
	    transactions=["0x4376314060f34286cfd35fb78f212a0fbc11e1a0946eaa6cc7ffaf145c31fbd7",
			"0x7ecf01d67b73dc40a7d39d7b919f9c9ca2f4ce9023f70401381c279d29cbc42b",
			"0xba0ceed770df4a6e330a643ec7f3b48096aed0ed393517caa81f06e748eb55b0",
			"0x2ff180f5fae10dbb87b29b0632e37e76fbe266e74022313b7fbc0728f4b37fa8",
			"0xa3084f20656d566ba1a60486a004d9f10515007b25200541f3291c431a3e1ef5",
			"0x72d448523cea9235851ac9bf37d47a270625fea8be5a9af7a526ade79847084a",
			"0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd",
			"0x8002c67669d420525b829b111a0947910acbce232ec68614e314cfc70c044690",
			"0x85be3736d8b8e2a97598255dc18012c4cd8f89803d846030db735e13cade0c68",
			"0x087b91305f336dbe8bf0972637fbed747629dca3e479920fcb432d1102670cad",
			"0x6b4c4c8ee8b1c3f7f2bf4ec58610be369907f0a9fbd3e79d686ca6c4edf7660a",
			"0x662cc0ded8ab79e85b74fc789c99607b66aef823d354c02a075fb79d18b50922",
			"0x3c4cd10545eab675c87c11026ad1dac5e8270297d2973f856b03855b8ea854a3",
			"0x1fbf01c7d32d24488979741f927fdb2e25ff8760f0a54673f0f139dd58af120d",
			"0xc0e52622158cb8b05dfcadb12d412e95a79b794b08ecbdf5bdf3ebf940a04106"], 
	    nonce=0x21fb481a9525e00f, 
	    miner=0x4df69093933afc9ae4f665028ce56cd7008610fc,
	    difficulty=7068264, 
	    gasLimit=4712388, 
	    number=1362889, 
	    gasUsed=315000, 
	    uncles=[], 
	    size=2299, 
	    sha3Uncles=0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347, 
	    transactionsRoot=0x6fc40a312dbd1854cf131342e87380625e11008d132dfbbb2b1ef7aa395a261a, 
	    stateRoot=0x8b4411457f93d0694e6e05adc4ec51696f6b4e16ff65378784bc2ce5ff9e3949,
	    mixHash=0x36e505466927db2f555c83046f155c2b26fee896c0bfee8ecfe95ba5cebbf69a, 
	    parentHash=0x505d86c91cfb51dcdf106f5c810627c2d2e44779339f10c143134ee20ac39224, 
	    hash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, 
	    timestamp=1533296957
	  }
	*
	*/
	public WanRequest<Map<String, Object>> getBlockByNumber(String chainType, Number blockNumber) {
        
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("blockNumber", blockNumber);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getBlockByNumber");
        mapRequest.put("params",mapParams);

        return new WanRequest<Map<String, Object>>(mapRequest, ResultType.MAP, this);
    }
	
	/**
	*
	* @apiName getBlockNumber
	* @apiGroup Blocks
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getBlockNumber
	* @apiVersion 1.0.0
	* @apiDescription Get the current latest block number.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code> or <code>"ETH"</code> or <code>"BTC"</code>.
	*
	* @apiParam (Return) {BigInteger} response  The response is the latest block number, and its type is <code>BigInteger</code>. Please refer to examples above.
    * 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getBlockNumber","params":{"chainType":"WAN"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		BigInteger blockNo = javaApi.getBlockNumber("WAN").send();							
	*		System.out.println("\n[sync]getBlockNumber: blockNo: " + blockNo);
	*		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<BigInteger> future = javaApi.getBlockNumber("WAN").sendAsync();
	*	
	*	try {						
	*		BigInteger blockNo = future.get();				
	*		System.out.println("\n[async]getBlockNumber: blockNo: " + blockNo);
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {BigInteger} Successful Response
	*	119858
	*
	*/
	public WanRequest<BigInteger> getBlockNumber(String chainType) {     
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getBlockNumber");
        mapRequest.put("params",mapParams);

        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
    }

	/**
	*
	* @apiName getBlockTransactionCount
	* @apiGroup Blocks
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getBlockTransactionCount
	* @apiVersion 1.0.0
	* @apiDescription Get the number of transaction in a given block by block number or block hash on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code> or <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} blockHashOrBlockNumber The blockHash or the blockNumber you want to search.
	*
	* @apiParam (Return) {BigInteger} response  The response is the count of tx packaged in queried block, and its type is <code>BigInteger</code>. Please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getBlockTransactionCount","params":{"chainType":"WAN", "blockNumber":"670731"},"id":1}
	* or
	* {"jsonrpc":"2.0","method":"getBlockTransactionCount","params":{"chainType":"WAN", "blockHash":"0xeb3b437d765d4da9210481c2dd612fa9d0c51e0e83120ee7f573ed9d6296e9a8"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		BigInteger txCount = javaApi.getBlockTransactionCount("WAN", "1362889").send();							
	*		System.out.println("\n[sync]getBlockTransactionCount: txCount: " + txCount);
	*		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<BigInteger> future = javaApi.getBlockTransactionCount("WAN", "0xeb3b437d765d4da9210481c2dd612fa9d0c51e0e83120ee7f573ed9d6296e9a8").sendAsync();
	*	
	*	try {			
	*		BigInteger txCount = future.get();				
	*		System.out.println("\n[async]getBlockTransactionCount: txCount: " + txCount);
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {BigInteger} Successful Response
	*	13
	*
	*/
	public WanRequest<BigInteger> getBlockTransactionCount(String chainType, String blockHashOrBlockNumber) {   
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);

        if (checkHash(blockHashOrBlockNumber)) {
            mapParams.put("blockHash", blockHashOrBlockNumber);
        } else {
            mapParams.put("blockNumber", blockHashOrBlockNumber);
        }
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getBlockTransactionCount");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
    }

	/**
	*
	* @apiName callScFunc
	* @apiGroup Contracts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY callScFunc
	* @apiVersion 1.0.0
	* @apiDescription Call the specific public function of one contract on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} scAddr The token contract address for the specified token.
	* @apiParam (Input Parameter) {String} name The name of the specific contract public function.
	* @apiParam (Input Parameter) {Object[]} args The parameters array a of the specific contract public function.
	* @apiParam (Input Parameter) {JSONArray} abi The abi of the specific contract.
	*
	* @apiParam (Return) {Object} response  The response is the value of contract function return, and its type is <code>Object</code>.Note: the value returned is different according specific sc function, and also may be no any returns. Please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"callScFunc","params":{"chainType": "WAN", "scAddr": "0xddb09c3af165b83fa8f280225a6866786cc38971", "name": "getPriAddress", "args": [], "abi": [/The Abi of the contracts/]},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	Object[] params = new Object[2];
	*	params[0] = "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359";
	*	params[1] = "0x484a7cd86806eefaadccfc7b717edc45c04f99c0";
	*	
	*	try {
	*		Object rslt = javaApi.callScFunc("WAN", "0xddb09c3af165b83fa8f280225a6866786cc38971", "isStoremanGroup", params, qlABI).send();	*	
	*		System.out.println("\n[sync]callScFunc: isStoremanGroup: " + rslt);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	Object[] params = new Object[2];
	*	params[0] = "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359";
	*	params[1] = "0x484a7cd86806eefaadccfc7b717edc45c04f99c0";
    *
	*	CompletableFuture<String> rsltFuture = javaApi.callScFunc("WAN", "0xddb09c3af165b83fa8f280225a6866786cc38971", "isStoremanGroup", params, qlABI).sendAsync();
	*	
	*	try {
	*		Object rslt =  rsltFuture.get();
	*		System.out.println("\n[async]callScFunc: isStoremanGroup: " + rslt);
	*
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {Object} Successful Response
	*	"true"
	*
	*/
	public WanRequest<Object> callScFunc(String chainType, 
											String scAddr, 
											String funcName, 
											Object[] args, 
											JSONArray abi){		   
        // fill params    
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("scAddr", scAddr);
        mapParams.put("name", funcName);
        mapParams.put("args", args);
        mapParams.put("abi", abi);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","callScFunc");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Object>(mapRequest, ResultType.OBJECT, this);
	}

	/**
	*
	* @apiName getScMap
	* @apiGroup Contracts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getScMap
	* @apiVersion 1.0.0
	* @apiDescription Get the specific public map value of one contract on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} scAddr The token contract address for the specified token.
	* @apiParam (Input Parameter) {String} name The name of the specific contract public map.
	* @apiParam (Input Parameter) {String} key The key of parameter of the specific contract public map.
	* @apiParam (Input Parameter) {JSONArray} abi The abi of the specific contract.
	*
	* @apiParam (Return) {Object} response  The response is related value of queried map by the specific key, and its type is  <code>Object</code>, because it may be a simple type, also may be a array or map, etc, according to specific map define. please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getScMap","params":{"chainType": "WAN", "scAddr": "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "name": "mapDepositorInfo", "key": "", "abi": [/The Abi of the contracts/]},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		Object rslt = javaApi.getScMap("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "mapDepositorInfo", "0xa19444ffba0478655e5e07fb2cc4eb260df74a22",smgABI).send();
	*		
	*		System.out.println("\n[sync]getScMap: rslt class: " + rslt.getClass());
	*		System.out.println("\n[sync]getScMap: rslt: " + rslt);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<String> rsltFuture = javaApi.getScMap("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "mapDepositorInfo", "0xa19444ffba0478655e5e07fb2cc4eb260df74a22",smgABI).sendAsync();
	*
	*	try {
	*		Object rslt =  rsltFuture.get();
	*
	*		System.out.println("\n[async]getScMap: rslt class: " + rslt.getClass());
	*		System.out.println("\n[async]getScMap: rslt: " + rslt);
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {Object} Successful Response
	*	 ["9.5999882459922e+22","1","5e+21","0","0","0",false,"1553277287"]
	*
	*/
	public WanRequest<Object> getScMap(String chainType, 
										String scAddr, 
										String mapName, 
										String key, 
										JSONArray abi){  
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("scAddr", scAddr);
        mapParams.put("name", mapName);
        mapParams.put("key", key);
        mapParams.put("abi", abi);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getScMap");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Object>(mapRequest, ResultType.OBJECT, this);
	}

	/**
	*
	* @apiName getScVar
	* @apiGroup Contracts
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getScVar
	* @apiVersion 1.0.0
	* @apiDescription Get the specific public parameter value of one contract on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} scAddr The token contract address for the specified token.
	* @apiParam (Input Parameter) {String} name The name of the specific contract parameter.
	* @apiParam (Input Parameter) {JSONArray} abi The abi of the specific contract.
	*
	* @apiParam (Return) {Object} response  The response type is <code>Object</code>, because it may be a simple type, also may be a array or map, etc, according to specific map define, please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getScVar","params":{"chainType": "WAN", "scAddr": "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "name": "isReachedMaxDeposit", "abi": [/The Abi of the contracts/]},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		Object var = javaApi.getScVar("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "isReachedMaxDeposit",smgABI).send();
	*
	*		System.out.println("\n[sync]getScVar: var class: " + var.getClass());		
	*		System.out.println("\n[sync]getScVar: var: " + var);			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<String> rsltFuture = javaApi.getScVar("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "isReachedMaxDeposit",smgABI).sendAsync();
	*	
	*	try {
	*		Object var =  rsltFuture.get();
	*
	*		System.out.println("\n[async]getScVar: var class: " + var.getClass());
	*		System.out.println("\n[async]getScVar: var: " + var);
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {Object} Successful Response
	* 	"true"
	*
	*/
	public WanRequest<Object> getScVar(String chainType, 
										String scAddr, 
										String varName, 
										JSONArray abi){   
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("scAddr", scAddr);
        mapParams.put("name", varName);
        mapParams.put("abi", abi);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getScVar");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Object>(mapRequest, ResultType.OBJECT, this);
	}

	/**
	*
	* @apiName getCoin2WanRatio
	* @apiGroup CrossChain
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getCoin2WanRatio
	* @apiVersion 1.0.0
	* @apiDescription Coin exchange ratio,such as 1 ETH to 880 WANs in ICO period, the precision is 10000, the ratio is 880*precision = 880,0000. The ratio would be changed according to the market value ratio periodically.
	*
	* @apiParam (Input Parameter) {String} crossChain The cross-chain native coin name that you want to search, should be <code>"ETH"</code> or <code>"BTC"</code>.
	*
	* @apiParam (Return) {BigInteger} response  The response is the coin exchange ratio, which type is <code>BigInteger</code>, please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getCoin2WanRatio","params":{"crossChain":"ETH"},"id":1}
	*
	* @apiExample {java} Example by sync mode:  
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    * 
	*	try {
	*		BigInteger ratioValue = javaApi.getCoin2WanRatio("ETH").send();		
	*		System.out.println("\n[sync]getCoin2WanRatio : " + ratioValue);	
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<BigInteger> ratioFuture = javaApi.getCoin2WanRatio("ETH").sendAsync();
	*	
	*	try {
	*		BigInteger ratioValue = ratioFuture.get();			
	*		System.out.println("\n[async]getCoin2WanRatio : " + ratioValue);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {BigInteger} Successful Response
	*	20
	*
	*/
	public WanRequest<BigInteger> getCoin2WanRatio(String crossChain){   
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("crossChain", crossChain);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getCoin2WanRatio");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
	}

	/**
	*
	* @apiName getRegTokens
	* @apiGroup CrossChain
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getRegTokens
	* @apiVersion 1.0.0
	* @apiDescription Get the information of tokens which are supported for cross-chain ability.
	*
	* @apiParam (Input Parameter) {String} crossChain The cross-chain name that you want to search, should be <code>"ETH"</code>.
	*
	* @apiParam (Return) {List} response  The response is the registered token info array which type is <code>List<Map<String,Object>></code>, each element is related to a token info, please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getRegTokens","params":{"crossChain":"ETH"},"id":1}
	*
	* @apiExample {java} Example by sync mode:   
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		List tokenList = javaApi.getRegTokens("ETH").send();
	*		
	*		for(int i=0; i < tokenList.size(); i++) {
	*			Map<String, Object> tokenInfo = (Map<String, Object>) tokenList.get(i);
	*			
	*			Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
	*			System.out.println("\n[sync] getRegTokens tokenOrigAddr: " + tokenOrigAddr);
	*			
	*			Object tokenWanAddr = tokenInfo.get("tokenWanAddr");
	*			System.out.println("[sync] getRegTokens tokenWanAddr: " + tokenWanAddr);
	*			
	*			Object ratio = tokenInfo.get("ratio");
	*			System.out.println("[sync] getRegTokens ratio: " + ratio);
	*			
	*			Object minDeposit = tokenInfo.get("minDeposit");
	*			System.out.println("[sync] getRegTokens minDeposit: " + minDeposit);				
	*		}			
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<List> tokenFuture = javaApi.getRegTokens("ETH").sendAsync();
	*	
	*	try {
	*		List tokenList = tokenFuture.get();			
	*		for(int i=0; i < tokenList.size(); i++) {
	*			Map<String, Object> tokenInfo = (Map<String, Object>) tokenList.get(i);
	*			
	*			Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
	*			System.out.println("\n[async] getRegTokens tokenOrigAddr: " + tokenOrigAddr);
	*			
	*			Object tokenWanAddr = tokenInfo.get("tokenWanAddr");
	*			System.out.println("[async] getRegTokens tokenWanAddr: " + tokenWanAddr);
	*			
	*			Object ratio = tokenInfo.get("ratio");
	*			System.out.println("[async] getRegTokens ratio: " + ratio);
	*			
	*			Object minDeposit = tokenInfo.get("minDeposit");
	*			System.out.println("[async] getRegTokens minDeposit: " + minDeposit);				
	*		}	
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {List} Successful Response
	  [
	   {
		tokenWanAddr=0xa26a6698b95144ce27714d4657f153dc48e676d5, 
		tokenOrigAddr=0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48, 
		origHtlc=0xa4becceba748f8a2b0e6c2ed69e1079a9a5062ab, 
		withdrawDelayTime=259200, 
		minDeposit=10000000000000000000, 
		wanHtlc=0x71d23563729f81fc535cbb772e52660ca5be755e, 
		tokenHash=0xd6f77cda01e07203755735d472c988bf495247447f02e1d6a04c00e43566e612, 
		ratio=50000
	   }, 
	   {
		 tokenWanAddr=0x79562c955dea8a801f057a386c9aa9f5d9bf0f92, 
		 tokenOrigAddr=0x0000000000085d4780b73119b644ae5ecd22b376, 
		 origHtlc=0xa4becceba748f8a2b0e6c2ed69e1079a9a5062ab, 
		 withdrawDelayTime=259200, 
		 minDeposit=10000000000000000000, 
		 wanHtlc=0x71d23563729f81fc535cbb772e52660ca5be755e, 
		 tokenHash=0xebd66c6bbe2f4326043633bbbc0df201670a27c5bebfa12ad0567f60f7aaa9f4, 
		 ratio=50000
 	   }
 	  ]
	*
	*/
	public WanRequest<List> getRegTokens(String crossChain){   
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("crossChain", crossChain);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getRegTokens");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName getStoremanGroups
	* @apiGroup CrossChain
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getStoremanGroups
	* @apiVersion 1.0.0
	* @apiDescription Get the detailed cross-chain storemanGroup info for one cross-chain native coin, like the quota, etc.
	*
	* @apiParam (Input Parameter) {String} crossChain The cross-chain name that you want to search, should be <code>"ETH"</code> or <code>"BTC"</code>.
	*
	* @apiParam (Return) {List} response  The response is the registered storemanGroup array, which type is <code>List<Map<String,Object>></code>, each element is related to a storemanGroup info, please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getStoremanGroups","params":{"crossChain":"ETH"},"id":1}
	*
	* @apiExample {java} Example by sync mode:   
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		List smgList = javaApi.getStoremanGroups("ETH").send();
	*		
	*		for(int i=0; i < smgList.size(); i++) {
	*			Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
	*			
	*			Object wanAddress = tokenInfo.get("wanAddress");
	*			System.out.println("\n[sync] getStoremanGroups wanAddress: " + wanAddress);
	*			
	*			Object ethAddress = tokenInfo.get("ethAddress");
	*			System.out.println("[sync] getStoremanGroups ethAddress: " + ethAddress);
	*			
	*			Object txFeeRatio = tokenInfo.get("txFeeRatio");
	*			System.out.println("[sync] getStoremanGroups txFeeRatio: " + txFeeRatio);
	*			
	*			Object deposit = tokenInfo.get("deposit");
	*			System.out.println("[sync] getStoremanGroups deposit: " + deposit);	
	*			
	*			Object receivable = tokenInfo.get("receivable");
	*			System.out.println("[sync] getStoremanGroups receivable: " + receivable);			
	*			
	*			Object payable = tokenInfo.get("payable");
	*			System.out.println("[sync] getStoremanGroups payable: " + payable);					
	*			
	*			Object debt = tokenInfo.get("debt");
	*			System.out.println("[sync] getStoremanGroups debt: " + debt);						
	*		}		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<List> smgFuture = javaApi.getStoremanGroups("ETH").sendAsync();
	*	
	*	try {
	*		
	*		List smgList = smgFuture.get();	
	*		for(int i=0; i < smgList.size(); i++) {
	*			Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
	*			
	*			Object wanAddress = tokenInfo.get("wanAddress");
	*			System.out.println("\n[async] getStoremanGroups wanAddress: " + wanAddress);
	*			
	*			Object ethAddress = tokenInfo.get("ethAddress");
	*			System.out.println("[async] getStoremanGroups ethAddress: " + ethAddress);
	*			
	*			Object txFeeRatio = tokenInfo.get("txFeeRatio");
	*			System.out.println("[async] getStoremanGroups txFeeRatio: " + txFeeRatio);
	*			
	*			Object deposit = tokenInfo.get("deposit");
	*			System.out.println("[async] getStoremanGroups deposit: " + deposit);	
	*			
	*			Object receivable = tokenInfo.get("receivable");
	*			System.out.println("[async] getStoremanGroups receivable: " + receivable);			
	*			
	*			Object payable = tokenInfo.get("payable");
	*			System.out.println("[async] getStoremanGroups payable: " + payable);					
	*			
	*			Object debt = tokenInfo.get("debt");
	*			System.out.println("[async] getStoremanGroups debt: " + debt);						
	*		}			
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {List} Successful Response
	[{
		wanAddress=0x484a7cd86806eefaadccfc7b717edc45c04f99c0, 
		txFeeRatio=10, 
		payable=11003000000000000, 
		quota=1000000000000000000000, 
		deposit=400000000000000000000000, 
		outboundQuota=874425136982471134, 
		receivable=0, 
		ethAddress=0x45aff9950f2b174028a88ce8648c49b6ae1e7765, 
		inboundQuota=999114571863017528866, 
		debt=885428136982471134
	}]
	*
	*/
	public WanRequest<List> getStoremanGroups(String crossChain){   
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("crossChain", crossChain);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getStoremanGroups");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName getToken2WanRatio
	* @apiGroup CrossChain
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getToken2WanRatio
	* @apiVersion 1.0.0
	* @apiDescription Token exchange ratio,such as 1 token to 880 WANs, the precision is 10000, the ratio is 880*precision = 880,0000. The ratio would be changed accoring to the market value ratio periodically.
	*
	* @apiParam (Input Parameter) {String} crossChain The cross-chain name that you want to search, should be <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} tokenScAddr The token contract address for the specified token.
	*
	* @apiParam (Return) {BigInteger} response  The response is the queried token exchange ratio, which type is <code>BigInteger</code>, please refer to examples above.
	*
	* @apiParamExample {string} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getToken2WanRatio","params":{"crossChain":"ETH", "tokenScAddr":"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359"},"id":1}
	*
	* @apiExample {java} Example by sync mode:  
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    * 
	*	try {
	*		BigInteger ratioValue = javaApi.getToken2WanRatio("ETH", "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").send();		
	*		System.out.println("\n[sync]getCoin2WanRatio : " + ratioValue);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<BigInteger> ratioFuture = javaApi.getToken2WanRatio("ETH", "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").sendAsync();
	*
	*	try {	
	*		BigInteger ratioValue = ratioFuture.get();			
	*		System.out.println("\n[async]getCoin2WanRatio : " + ratioValue);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {BigInteger} Successful Response
	*	3000
	*
	*/
	public WanRequest<BigInteger> getToken2WanRatio(String crossChain, String tokenScAddr){   
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("crossChain", crossChain);
        mapParams.put("tokenScAddr", tokenScAddr);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getToken2WanRatio");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
	}

	/**
	*
	* @apiName getTokenStoremanGroups
	* @apiGroup CrossChain
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTokenStoremanGroups
	* @apiVersion 1.0.0
	* @apiDescription Get the detail cross-chain storemanGroup info for one specific token contract, like the quota, etc.
	*
	* @apiParam (Input Parameter) {String} crossChain The cross-chain name that you want to search, should be <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} tokenScAddr The token contract address for the specified token.
	*
	* @apiParam (Return) {List} response  The response data type is <code>List<Map<String, Object>></code>, each element is queried storemanGroup info from which you can get related property, please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTokenStoremanGroups","params":{"crossChain":"ETH", "tokenScAddr":"0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814"},"id":1}
	*
	* @apiExample {java} Example by sync mode:    
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		List smgList = javaApi.getTokenStoremanGroups("ETH", "0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814").send();
	*		
	*		for(int i=0; i < smgList.size(); i++) {
	*			Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
	*			
	*			Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
	*			System.out.println("\n[sync] getTokenStoremanGroups tokenOrigAddr: " + tokenOrigAddr);
	*			
	*			Object smgWanAddr = tokenInfo.get("smgWanAddr");
	*			System.out.println("[sync] getTokenStoremanGroups smgWanAddr: " + smgWanAddr);
	*			
	*			Object smgOrigAddr = tokenInfo.get("smgOrigAddr");
	*			System.out.println("[sync] getTokenStoremanGroups smgOrigAddr: " + smgOrigAddr);
	*			
	*			Object wanDeposit = tokenInfo.get("wanDeposit");
	*			System.out.println("[sync] getTokenStoremanGroups wanDeposit: " + wanDeposit);	
	*			
	*			Object quota = tokenInfo.get("quota");
	*			System.out.println("[sync] getTokenStoremanGroups quota: " + quota);	
	*			
	*			Object receivable = tokenInfo.get("receivable");
	*			System.out.println("[sync] getTokenStoremanGroups receivable: " + receivable);			
	*			
	*			Object payable = tokenInfo.get("payable");
	*			System.out.println("[sync] getTokenStoremanGroups payable: " + payable);					
	*			
	*			Object debt = tokenInfo.get("debt");
	*			System.out.println("[sync] getTokenStoremanGroups debt: " + debt);						
	*		}	
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<List> smgFuture = javaApi.getTokenStoremanGroups("ETH", "0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814").sendAsync();
	*	
	*	try {
	*		List smgList = smgFuture.get();	
	*		for(int i=0; i < smgList.size(); i++) {
	*			Map<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);
	*			
	*			Object tokenOrigAddr = tokenInfo.get("tokenOrigAddr");
	*			System.out.println("\n[async] getTokenStoremanGroups tokenOrigAddr: " + tokenOrigAddr);
	*			
	*			Object smgWanAddr = tokenInfo.get("smgWanAddr");
	*			System.out.println("[async] getTokenStoremanGroups smgWanAddr: " + smgWanAddr);
	*			
	*			Object smgOrigAddr = tokenInfo.get("smgOrigAddr");
	*			System.out.println("[async] getTokenStoremanGroups smgOrigAddr: " + smgOrigAddr);
	*			
	*			Object wanDeposit = tokenInfo.get("wanDeposit");
	*			System.out.println("[async] getTokenStoremanGroups wanDeposit: " + wanDeposit);	
	*			
	*			Object quota = tokenInfo.get("quota");
	*			System.out.println("[async] getTokenStoremanGroups quota: " + quota);	
	*			
	*			Object receivable = tokenInfo.get("receivable");
	*			System.out.println("[async] getTokenStoremanGroups receivable: " + receivable);			
	*			
	*			Object payable = tokenInfo.get("payable");
	*			System.out.println("[async] getTokenStoremanGroups payable: " + payable);					
	*			
	*			Object debt = tokenInfo.get("debt");
	*			System.out.println("[async] getTokenStoremanGroups debt: " + debt);								
	*		}			
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {List} Successful Response
	 [{
		tokenOrigAddr=0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814, 
		smgWanAddr=0x484a7cd86806eefaadccfc7b717edc45c04f99c0, 
		txFeeRatio=10, 
		payable=1800003000000000000, 
		quota=10000000000000000000000, 
		wanDeposit=1000000000000000000000, 
		outboundQuota=2380002000000000000, 
		smgOrigAddr=0x45aff9950f2b174028a88ce8648c49b6ae1e7765, 
		receivable=0, 
		inboundQuota=9995819995000000000000, 
		debt=4180005000000000000
	 }]
	*
	*/
	public WanRequest<List> getTokenStoremanGroups(String crossChain, String tokenScAddr){   
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("crossChain", crossChain);
        mapParams.put("tokenScAddr", tokenScAddr);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTokenStoremanGroups");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName getScEvent
	* @apiGroup Events
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getScEvent
	* @apiVersion 1.0.0
	* @apiDescription Get smart contract event log via topics.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} address The contract address.
	* @apiParam (Input Parameter) {String[]} topics An array of string values which must each appear in the log entries. The order is important, if you want to leave topics out use null, e.g. [null, '0x00...'].
	*
	* @apiParam (Return) {List} response  The response data type is <code>List<Map<String, Object>></code>, each element is queried sc event object and you can get related property, please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getScEvent","params":{"chainType":"WAN", "address": "0xda5b90dc89be59365ec44f3f2d7af8b6700d1167", "topics": ["0xa4345d0839b39e5a6622a55c68bd8f83ac8a68fad252a8363a2c09dbaf85c793", "0x0000000000000000000000000000000000000000000000000000000000000000"]},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	String[] topics =  new String[2];
	*	topics[0] = "0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4";
	*	topics[1] = "0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2";
	*	
	*	try {
	*		List eventList = javaApi.getScEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topics).send();
	*		
	*		for(int i=0; i < eventList.size(); i++) {
	*			Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
	*			
	*			Object blockNumber = eventInfo.get("blockNumber");
	*			System.out.println("\n[sync] getScEvent blockNumber: " + blockNumber);
	*			
	*			Object transactionHash = eventInfo.get("transactionHash");
	*			System.out.println("[sync] getScEvent transactionHash: " + transactionHash);
	*			
	*			Object address = eventInfo.get("address");
	*			System.out.println("[sync] getScEvent address: " + address);
	*			
	*			Object data = eventInfo.get("data");
	*			System.out.println("[sync] getScEvent data: " + data);				
	*		}	
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	String[] topics =  new String[2];
	*	topics[0] = "0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4";
	*	topics[1] = "0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2";
	*
	*	CompletableFuture<List> rsltFuture = javaApi.getScEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topics).sendAsync();
	*
	*	try {	
	*		List eventList =  rsltFuture.get();		
	*		for(int i=0; i < eventList.size(); i++) {
	*			Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
	*			
	*			Object blockNumber = eventInfo.get("blockNumber");
	*			System.out.println("\n[async] getScEvent blockNumber: " + blockNumber);
	*			
	*			Object transactionHash = eventInfo.get("transactionHash");
	*			System.out.println("[async] getScEvent transactionHash: " + transactionHash);
	*			
	*			Object address = eventInfo.get("address");
	*			System.out.println("[async] getScEvent address: " + address);
	*			
	*			Object data = eventInfo.get("data");
	*			System.out.println("[async] getScEvent data: " + data);				
	*		}	
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {List} Successful Response
	  [{
		blockHash=0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503, 
		address=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, 
		logIndex=0, 
		data=0x00000000000000000000000000000000000000000000010ff5e03b33183c6000000000000000000000000000000000000000000000000000000000005c8aeb0a00000000000000000000000000000000000000000000010ff5e03b33183c600000000000000000000000000000000000000000000000010ff5e03b33183c6000, 
		removed=false, 
		topics=["0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4",
		"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2"], 
		blockNumber=2887621, 
		transactionIndex=0, 
		transactionHash=0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35
	  }, {
		blockHash=0x6691d2ec8d521f548e5a1aefbca08d607c95e719839883dc8d190e1a3259a4f6, 
		address=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, 
		logIndex=0, 
		data=0x00000000000000000000000000000000000000000000000c8db29cc7e0bf1800000000000000000000000000000000000000000000000000000000005ca0313d00000000000000000000000000000000000000000000011c8392d7faf8fb7800000000000000000000000000000000000000000000035983945057efa2cdda00, 
		removed=false, 
		topics=[
		"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4",
		"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2"],
		blockNumber=2997739, 
		transactionIndex=0, 
		transactionHash=0x7982fef5f1ea21ad0c15d5bf164e29926e1ba459b14bd0dd3ee7f96906c34e48
	  }]
	*
	*/
	public WanRequest<List> getScEvent(String chainType, String address, String[] topics){
   
        // fill params 
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("address", address);
        mapParams.put("topics", topics);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getScEvent");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName monitorEvent
	* @apiGroup Events
	* @api {CONNECT} /ws/v3/YOUR-API-KEY monitorEvent
	* @apiVersion 1.0.0
	* @apiDescription Subscribe to a smart contract event monitor. The server will push the event to the subscriber when the event occurs. 
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} address The contract address.
	* @apiParam (Input Parameter) {String[]} topics Array of values which must each appear in the log entries. The order is important, if you want to leave topics out use null, e.g. [null, '0x00...'].
	*
	* @apiParam (Return) {List} response  The response data type is <code>List<Map<String, Object>></code>, each element is queried sc event object and you can get related property, please refer to examples above.
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"monitorEvent","params":{"chainType":"WAN", "address": "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", "topics": ["0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4"]},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	String[] topicsMon =  new String[1];
	*	topicsMon[0] = "0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4";
	*
	*	try {
	*		List eventList = javaApi.monitorEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topicsMon).send();
	*		
	*		for(int i=0; i < eventList.size(); i++) {
	*			Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
	*			
	*			Object blockNumber = eventInfo.get("blockNumber");
	*			System.out.println("\n[sync] monitorEvent blockNumber: " + blockNumber);
	*			
	*			Object transactionHash = eventInfo.get("transactionHash");
	*			System.out.println("[sync] monitorEvent transactionHash: " + transactionHash);
	*			
	*			Object address = eventInfo.get("address");
	*			System.out.println("[sync] monitorEvent address: " + address);
	*			
	*			Object data = eventInfo.get("data");
	*			System.out.println("[sync] monitorEvent data: " + data);				
	*		}	
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	String[] topicsMon =  new String[1];
	*	topicsMon[0] = "0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4";
	*
	*	CompletableFuture<List> rsltFuture = javaApi.monitorEvent("WAN", "0x33fc0c6b7fc83691e42855b14832d486c9a09e67", topicsMon).sendAsync();
	*	
	*	try {
	*		List eventList =  rsltFuture.get();		
	*		for(int i=0; i < eventList.size(); i++) {
	*			Map<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);
	*			
	*			Object blockNumber = eventInfo.get("blockNumber");
	*			System.out.println("\n[async] monitorEvent blockNumber: " + blockNumber);
	*			
	*			Object transactionHash = eventInfo.get("transactionHash");
	*			System.out.println("[async] monitorEvent transactionHash: " + transactionHash);
	*			
	*			Object address = eventInfo.get("address");
	*			System.out.println("[async] monitorEvent address: " + address);
	*			
	*			Object data = eventInfo.get("data");
	*			System.out.println("[async] monitorEvent data: " + data);				
	*		}	
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}		
	*
	* @apiSuccessExample {List} Successful Response
	  [{
		blockHash=0x6691d2ec8d521f548e5a1aefbca08d607c95e719839883dc8d190e1a3259a4f6, 
		address=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, 
		logIndex=0, 
		data=0x00000000000000000000000000000000000000000000000c8db29cc7e0bf1800000000000000000000000000000000000000000000000000000000005ca0313d00000000000000000000000000000000000000000000011c8392d7faf8fb7800000000000000000000000000000000000000000000035983945057efa2cdda00, 
		removed=false, 
		topics=[
		"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4",
		"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2"],
		blockNumber=2997739, 
		transactionIndex=0, 
		transactionHash=0x7982fef5f1ea21ad0c15d5bf164e29926e1ba459b14bd0dd3ee7f96906c34e48
	  }]
	*
	*/
	public WanRequest<List> monitorEvent(String chainType, String address, String[] topics){
   
        // fill params 
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("address", address);
        mapParams.put("topics", topics);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","monitorEvent");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName getGasPrice
	* @apiGroup Status
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getGasPrice
	* @apiVersion 1.0.0
	* @apiDescription Get a bigNumber of the current gas price in wei.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	*
	* @apiParam (Return) {String} response  The response data is the current gas price, which type is <code>String</code>, please refer to examples above. 
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getGasPrice","params":{"chainType":"WAN"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		String rslt = javaApi.getGasPrice("WAN").send();			
	*		System.out.println("\n[sync]getGasPrice : " + rslt);
	*		
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<String> rsltFuture = javaApi.getGasPrice("WAN").sendAsync();
	*	
	*	try {
	*		String rslt = rsltFuture.get(); 
	*		System.out.println("\n[async]getGasPrice : " + rslt);
	*       
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}	
	*
	* @apiSuccessExample {String} Successful Response
	*	"180000000000"
	*
	*/
	public WanRequest<String> getGasPrice(String chainType){
   
        // fill params 
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getGasPrice");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<String>(mapRequest, ResultType.STRING, this);
	}

	/**
	*
	* @apiName getMultiTokenBalance
	* @apiGroup Tokens
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getMultiTokenBalance
	* @apiVersion 1.0.0
	* @apiDescription Gets token balance for multiple addresses of specified token on Wanchain in a single call.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>, default: <code>"WAN"</code>.
	* @apiParam (Input Parameter) {String[]} addressArray An array of addresses being queried.
	* @apiParam (Input Parameter) {String} tokenScAddr The token contract address for specified token. I.e., If chainType is <code>"WAN"</code>, it should be the token address for <code>"WETH"</code> or <code>"WBTC"</code>.
	* 
	* @apiParam (Return) {Map} response  The response data type is <code>Map<String, BigInteger></code>, you can get the balance of the queried token by token address, please refer to examples above. 
	*
	* @apiParamExample {string} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getMultiTokenBalance","params":{"address": ["0xfac95c16da814d24cc64b3186348afecf527324f","0xfac95c16da814d24cc64b3186348afecf527324e"],"tokenScAddr" : "0x63eed4943abaac5f43f657d8eec098ca6d6a546e"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
    *	String[] accoutArray = new String[3];
    *	accoutArray[0] = "0x13827c4200a764fb6f193a3f6e33b9efa3b4073d";
    *	accoutArray[1] = "0xc7a04e264c5a0fe542ddccbddc2df6d31cd6b22a";
    *	accoutArray[2] = "0x860306e69efcb22852421608ae1f546e222c948c";
	*
	*	try {
	*		Map<String, BigInteger> tokenBalanceArray = javaApi.getMultiTokenBalance("ETH", accoutArray, "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").send();
	*						
	*		BigInteger accoutA = tokenBalanceArray.get(accoutArray[0]);
	*		System.out.println("\n[sync]getMultiTokenBalance: accoutA: " + accoutA);
	*		
	*		BigInteger accoutB = tokenBalanceArray.get(accoutArray[1]);
	*		System.out.println("[sync]getMultiTokenBalance: accoutB: " + accoutB);
	*		
	*		BigInteger accoutC = tokenBalanceArray.get(accoutArray[2]);
	*		System.out.println("[sync]getMultiTokenBalance: accoutC: " + accoutC);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
    *	String[] accoutArray = new String[3];
    *	accoutArray[0] = "0x13827c4200a764fb6f193a3f6e33b9efa3b4073d";
    *	accoutArray[1] = "0xc7a04e264c5a0fe542ddccbddc2df6d31cd6b22a";
    *	accoutArray[2] = "0x860306e69efcb22852421608ae1f546e222c948c";
	*
	*	CompletableFuture<Map<String, BigInteger>> tokenFuture = javaApi.getMultiTokenBalance("ETH", accoutArray, "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").sendAsync();
	*	
	*	try {
	*		Map<String, BigInteger> tokenBalanceArray = tokenFuture.get();
	*
	*		BigInteger accoutA = tokenBalanceArray.get(accoutArray[0]);
	*		System.out.println("\n[async]getMultiTokenBalance: accoutA: " + accoutA);
	*		
	*		BigInteger accoutB = tokenBalanceArray.get(accoutArray[1]);
	*		System.out.println("[async]getMultiTokenBalance: accoutB: " + accoutB);
	*		
	*		BigInteger accoutC = tokenBalanceArray.get(accoutArray[2]);
	*		System.out.println("[async]getMultiTokenBalance: accoutC: " + accoutC);				
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {Map} Successful Response
	  {
		0xc7a04e264c5a0fe542ddccbddc2df6d31cd6b22a=5600000000000000000, 
		0x860306e69efcb22852421608ae1f546e222c948c=999991000000000000, 
		0x13827c4200a764fb6f193a3f6e33b9efa3b4073d=0
	  }
	*
	*/
	public WanRequest<Map<String, BigInteger>> getMultiTokenBalance(String chainType, 
													String[] addrArray, 
													String tokenScAddr){
      
        // fill params        
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("address", addrArray);
        mapParams.put("tokenScAddr", tokenScAddr);
        mapParams.put("chainType", chainType);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getMultiTokenBalance");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Map<String, BigInteger>>(mapRequest, ResultType.BIGINTEGERMAP, this);
	}

	/**
	*
	* @apiName getMultiTokenInfo
	* @apiGroup Tokens
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getMultiTokenInfo
	* @apiVersion 1.0.0
	* @apiDescription Get the information of multiple tokens.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String[]} tokenScAddrArray The token address array for the certain token that you want to find.
	* 
	* @apiParam (Return) {Map} response  The response data type is <code>Map<String, Object></code>, you can get the detail info of queried token, please refer to examples above. 
	*
	* @apiParamExample {string} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getMultiTokenInfo","params":{"tokenScAddrArray":["0xc5bc855056d99ef4bda0a4ae937065315e2ae11a","0x7017500899433272b4088afe34c04d742d0ce7df"],"chainType":"ETH"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
    *	String[] tokenArray = new String[3];
    *	tokenArray[0] = "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359";
    *	tokenArray[1] = "0x514910771af9ca656af840dff83e8264ecf986ca";
    *	tokenArray[2] = "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48";
    *	    
	*	try {
	*		Map<String, Object> tokenInfo = javaApi.getMultiTokenInfo("ETH", tokenArray).send();
	*						
	*		Object tokenA = tokenInfo.get(tokenArray[0]);
	*		System.out.println("\n[sync]getMultiTokenInfo: tokenA: " + tokenA);
	*		
	*		Object tokenB = tokenInfo.get(tokenArray[1]);
	*		System.out.println("[sync]getMultiTokenInfo: tokenB: " + tokenB);
	*		
	*		Object tokenC = tokenInfo.get(tokenArray[2]);
	*		System.out.println("[sync]getMultiTokenInfo: tokenC: " + tokenC);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
    *	String[] tokenArray = new String[3];
    *	tokenArray[0] = "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359";
    *	tokenArray[1] = "0x514910771af9ca656af840dff83e8264ecf986ca";
    *	tokenArray[2] = "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48";
    *
	*	CompletableFuture<Map<String, Object>> tokenFuture = javaApi.getMultiTokenInfo("ETH", tokenArray).sendAsync();
	*
	*	try {	
	*		Map<String, Object> tokenInfo = tokenFuture.get();
	*		
	*		Object tokenA = tokenInfo.get(tokenArray[0]);
	*		System.out.println("\n[async]getMultiTokenInfo: tokenA: " + tokenA);
	*		
	*		Object tokenB = tokenInfo.get(tokenArray[1]);
	*		System.out.println("[async]getMultiTokenInfo: tokenB: " + tokenB);
	*		
	*		Object tokenC = tokenInfo.get(tokenArray[2]);
	*		System.out.println("[async]getMultiTokenInfo: tokenC: " + tokenC);
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {Map} Successful Response
	  {
		0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48={"symbol":"USDC","decimals":"6"}, 
		0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359={"symbol":"DAI","decimals":"18"}, 
		0x514910771af9ca656af840dff83e8264ecf986ca={"symbol":"LINK","decimals":"18"}
	  }
	*
	*/
	public WanRequest<Map<String, Object>> getMultiTokenInfo(String chainType,  String[] tokenScAddrArray){
   
        // fill params 
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("tokenScAddrArray", tokenScAddrArray);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getMultiTokenInfo");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Map<String, Object>>(mapRequest, ResultType.MAP, this);
	}

	/**
	*
	* @apiName getTokenAllowance
	* @apiGroup Tokens
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTokenAllowance
	* @apiVersion 1.0.0
	* @apiDescription Get the token allowance for one specific account on one contract for one specific spender account on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} tokenScAddr The token contract address for the specified token.
	* @apiParam (Input Parameter) {String} ownerAddr The owner address on the certain contract.
	* @apiParam (Input Parameter) {String} spenderAddr The spender address on the certain contract.
	* 
	* @apiParam (Return) {BigInteger} response  The response data type is <code>BigInteger</code>, you can get the allowance of queried token , please refer to examples above. 
	*  
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTokenAllowance","params":{"chainType":"ETH", "tokenScAddr":"0xc5bc855056d99ef4bda0a4ae937065315e2ae11a", "ownerAddr":"0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8", "spenderAddr":"0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		BigInteger tokenAllowance = javaApi.getTokenAllowance("ETH", "0xc5bc855056d99ef4bda0a4ae937065315e2ae11a", "0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8", "0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1").send();
	*		System.out.println("\n[sync]getTokenAllowance: tokenAllowance: " + tokenAllowance);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:	
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *	
	*	CompletableFuture<BigInteger> tokenFuture = javaApi.getTokenAllowance("ETH", "0xc5bc855056d99ef4bda0a4ae937065315e2ae11a", "0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8", "0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1").sendAsync();
	*		
	*	try {
	*		BigInteger tokenAllowance = tokenFuture.get();
	*		System.out.println("\n[async]getTokenAllowance: tokenAllowance: " + tokenAllowance);			
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccess (Return Parameter) {BigInteger} The queried token allowance
	* @apiSuccessExample {BigInteger} Successful Response
	*	999999999999980000000000000
	*
	*/
	public WanRequest<BigInteger> getTokenAllowance(String chainType, 
												String tokenScAddr, 
												String ownerAddr,
												String spenderAddr){   
        // fill params   
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("tokenScAddr", tokenScAddr);
        mapParams.put("ownerAddr", ownerAddr);
        mapParams.put("spenderAddr", spenderAddr);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTokenAllowance");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
	}

	/**
	*
	* @apiName getTokenBalance
	* @apiGroup Tokens
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTokenBalance
	* @apiVersion 1.0.0
	* @apiDescription Get token balance for a single address of certain token on Wanchain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>, default: <code>"WAN"</code>.
	* @apiParam (Input Parameter) {String} address The account being queried.
	* @apiParam (Input Parameter) {String} tokenScAddr The token contract address for specified token. I.e., If chainType is <code>"WAN"</code>, it should be the token address for <code>"WETH"</code> or <code>"WBTC"</code>.
	*
	* @apiParam (Return) {BigInteger} response  The response data type is <code>BigInteger</code>, you can get the balance of queried token from it, please refer to examples above. 
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTokenBalance","params":{"address": "0x2cc79fa3b80c5b9b02051facd02478ea88a78e2c","tokenScAddr" : "0x63eed4943abaac5f43f657d8eec098ca6d6a546e"},"id":1}
	*
	* @apiExample {java} Example by sync mode
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		BigInteger tokenBalance = javaApi.getTokenBalance("ETH", accoutArray[1], "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").send();	*						
	*		System.out.println("\n[sync]getTokenBalance: tokenBalance: " + tokenBalance);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<BigInteger> tokenFuture = javaApi.getTokenBalance("ETH", accoutArray[2], "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359").sendAsync();
	*				
	*	try {
	*		BigInteger tokenBalance = tokenFuture.get();
	*		System.out.println("\n[async]getTokenBalance: accout: " + tokenBalance);			
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {BigInteger} Successful Response
	*	999991000000000000
	*
	*/
	public WanRequest<BigInteger> getTokenBalance(String chainType, String address, String tokenScAddr){
   
        // fill params  
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("address", address);
        mapParams.put("tokenScAddr", tokenScAddr);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTokenBalance");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
	}


	/**
	*
	* @apiName getTokenInfo
	* @apiGroup Tokens
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTokenInfo
	* @apiVersion 1.0.0
	* @apiDescription Get the info of token contract, like symbol and decimals, on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} tokenScAddr The token contract address for the specified token.
	*
	* @apiParam (Return) {Map} response  The response data type is <code>Map</code>, you can get related property value of queried token from it, please refer to examples above. 
	* 
	* @apiParamExample {string} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTokenInfo","params":{"chainType":"ETH", "tokenScAddr":"0xc5bc855056d99ef4bda0a4ae937065315e2ae11a"},"id":1}
	*
	* @apiExample {java} Example by sync mode:  
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *    
	*	try {
	*		Map<String, Object> tokenInfo = javaApi.getTokenInfo("ETH", tokenArray[0]).send();
	*						
	*		Object symbol = tokenInfo.get("symbol");
	*		System.out.println("\n[sync]getTokenInfo: symbol: " + symbol);
	*		
	*		Object decimals = tokenInfo.get("decimals");
	*		System.out.println("[sync]getTokenInfo: decimals: " + decimals);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<Map<String, Object>> tokenFuture = javaApi.getTokenInfo("ETH", tokenArray[1]).sendAsync();
	*	
	*	try {
	*		Map<String, Object> tokenInfo = tokenFuture.get();
	*		Object symbol = tokenInfo.get("symbol");
	*		System.out.println("\n[async]getTokenInfo: symbol: " + symbol);
	*		
	*		Object decimals = tokenInfo.get("decimals");
	*		System.out.println("[async]getTokenInfo: decimals: " + decimals);
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {Map} Successful Response
		{symbol=DAI, decimals=18}
	*/	
	public WanRequest<Map<String, Object>> getTokenInfo(String chainType, String tokenScAddr){
      
        // fill params  
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("tokenScAddr", tokenScAddr);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTokenInfo");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Map<String, Object>>(mapRequest, ResultType.MAP, this);
	}

	/**
	*
	* @apiName getTokenSupply
	* @apiGroup Tokens
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTokenSupply
	* @apiVersion 1.0.0
	* @apiDescription Get total amount of certain token on Wanchain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>. 
	* <br><strong>Note</strong> : In case of <code>chainType</code> set to <code>null</code>, it will take default value <code>"WAN"</code>.
	* @apiParam (Input Parameter) {String} tokenScAddr The token contract address for the specified token.
	*
	* @apiParam (Return) {BigInteger} response  The response data type is <code>BigInteger</code>, you can get queried token supply, please refer to examples above.
	* 
    * 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTokenSupply","params":{"tokenScAddr" : "0x63eed4943abaac5f43f657d8eec098ca6d6a546e"},"id":1}
	* or
	* {"jsonrpc":"2.0","method":"getTokenSupply","params":{"chainType":"WAN", "tokenScAddr" : "0x63eed4943abaac5f43f657d8eec098ca6d6a546e"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		BigInteger tokenSupply = javaApi.getTokenSupply("ETH", tokenArray[1]).send();						
	*		System.out.println("\n[sync]getTokenSupply: " + tokenSupply);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<BigInteger> tsFuture = javaApi.getTokenSupply("ETH", tokenArray[2]).sendAsync();
	*	
	*	try {
	*		BigInteger tokenSupply = tsFuture.get();
	*		System.out.println("\n[async]getTokenSupply: " + tokenSupply);			
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {BigInteger} Successful Response
	*	30000000000000000000000
	*
	*/
	public WanRequest<BigInteger> getTokenSupply(String chainType, String tokenScAddr){
      
        // fill params  
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("tokenScAddr", tokenScAddr);        
        if(null == chainType) {
            mapParams.put("chainType", "WAN");
        }else {
            mapParams.put("chainType", chainType);
        }
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTokenSupply");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<BigInteger>(mapRequest, ResultType.BIGNUMBER, this);
	}


	/**
	*
	* @apiName getTransByAddress
	* @apiGroup Transactions
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTransByAddress
	* @apiVersion 1.0.0
	* @apiDescription Get transaction information via the specified address on certain chain.
	*
	* @apiParam (Input Parameter){String} chainType The chain name that you want to search, should be <code>"WAN"</code>.
	* @apiParam (Input Parameter){String} address The account's address that you want to search.
	*
	* @apiParam (Return) {List} response  The response data type is <code>List<Map<String,Object>></code>, each element of list is one tx info which is formed as <code><Map<String,Object></code>. you can get queried tx info, please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTransByAddress","params":{"chainType":"WAN", "address":"0xbb9003ca8226f411811dd16a3f1a2c1b3f71825d"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		List txList = javaApi.getTransByAddress("WAN", "0x731d8fdc53039f07efd87be3c387e747da92911f").send();
	*		
	*		for(int i=0; i<txList.size(); i++) {
	*			Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
	*			
	*			Object blockHash = txInfo.get("blockHash");
	*			System.out.println("\n[sync]getTransByAddress: blockHash: " + blockHash);
	*				
	*			Object blockNumber = txInfo.get("blockNumber");
	*			System.out.println("[sync]getTransByAddress: blockNumber: " + blockNumber);
	*
	*			Object from = txInfo.get("from");
	*			System.out.println("[sync]getTransByAddress: from: " + from);
	*			
	*			Object to = txInfo.get("to");
	*			System.out.println("[sync]getTransByAddress: to: : " + to);
	*			
	*			Object value = txInfo.get("value");
	*			System.out.println("[sync]getTransByAddress: value: : " + value);						
	*		}			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:	
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *	
	*	CompletableFuture<List> txListFuture = javaApi.getTransByAddress("WAN", "0x731d8fdc53039f07efd87be3c387e747da92911f").sendAsync();
	*		
	*	try {
	*		List txList = txListFuture.get();
	*
	*		for(int i=0; i<txList.size(); i++) {
	*			Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
	*			
	*			Object blockHash = txInfo.get("blockHash");
	*			System.out.println("\n[async]getTransByAddress: blockHash: " + blockHash);
	*				
	*			Object blockNumber = txInfo.get("blockNumber");
	*			System.out.println("[async]getTransByAddress: blockNumber: " + blockNumber);
	*
	*			Object from = txInfo.get("from");
	*			System.out.println("[async]getTransByAddress: from: " + from);
	*			
	*			Object to = txInfo.get("to");
	*			System.out.println("[async]getTransByAddress: to: : " + to);
	*			
	*			Object value = txInfo.get("value");
	*			System.out.println("[async]getTransByAddress: value: : " + value);					
	*		}
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 	
	* @apiSuccessExample {List} Successful Response
	  [{
		blockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, 
		transactionIndex=6, 
		txType=0x1, 
		nonce=0, 
		input=0x, 
		r=0x7dfe37a3835ca20fbfe44e7baefdda60e7105dc05dc1ff75a3ef39e60d3e3b8a, 
		s=0x36368d24661e3f4cdc210c6d427ec54f7b89549eb88319053c65edd40651cd04, 
		v=0x26, 
		blockNumber=1362889, 
		gas=21000, 
		from=0x731d8fdc53039f07efd87be3c387e747da92911f, 
		to=0x731bd7289b4191706b00f6f1877662b5e8697e82,
		value=216890000000000000000, 
		hash=0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd, 
		gasPrice=200000000000
	  }, {
		blockHash=0xf2f8fe13cbcec0a68de9ab1a55025eed85166e8810a95340012b8d882c7b8266, 
		transactionIndex=0, 
		txType=0x1, 
		nonce=4403, 
		input=0x, 
		r=0x84bb821de7dd9b211755b8798dd5304190593c5f3f49d736e56d14246e70f9b1, 
		s=0x49685c8017bb839524e0027244c37813a3df779692108e645fadb540294e43c1, 
		v=0x25, 
		blockNumber=670731, 
		gas=21000, 
		from=0x731bd7289b4191706b00f6f1877662b5e8697e82, 
		to=0x731d8fdc53039f07efd87be3c387e747da92911f, 
		value=216900000000000000000, 
		hash=0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283, 
		gasPrice=200000000000
	  }]
	*
	*/
	public WanRequest<List> getTransByAddress(String chainType,  String address){
   
        // fill params  
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("address", address);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTransByAddress");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName getTransByAddressBetweenBlocks
	* @apiGroup Transactions
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTransByAddressBetweenBlocks
	* @apiVersion 1.0.0
	* @apiDescription Get transaction information via the specified address between the specified startBlockNo and endBlockNo on certain chain.
	* <br>Comments:
	* <br>&nbsp;&nbsp;&nbsp;&nbsp;if no startBlockNo given, startBlockNo will be set to 0;
	* <br>&nbsp;&nbsp;&nbsp;&nbsp;if no endBlockNo given, endBlockNo will be set to the newest blockNumber.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code>.
	* @apiParam (Input Parameter) {String} address The account's address that you want to search.
	* @apiParam (Input Parameter) {Number} startBlockNo The startBlockNo that you want to search from.
	* @apiParam (Input Parameter) {Number} endBlockNo The endBlockNo that you want to search to.
	*
	* @apiParam (Return) {List} response  The response data type is <code>List<Map<String,Object>></code>, each element of list is one tx info which is formed as <code><Map<String,Object></code>. you can get queried tx info, please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTransByAddressBetweenBlocks","params":{"chainType":"WAN", "address":"0x731bd7289b4191706b00f6f1877662b5e8697e82", "startBlockNo":670730, "endBlockNo":670735},"id":1}
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTransByAddress","params":{"chainType":"WAN", "address":"0x731bd7289b4191706b00f6f1877662b5e8697e82"},"id":1}
	*
	* 
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		List txList = javaApi.getTransByAddressBetweenBlocks("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82",670730, 670735).send();
	*		
	*		for(int i=0; i<txList.size(); i++) {
	*			Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
	*		
	*			Object blockHash = txInfo.get("blockHash");
	*			System.out.println("\n[sync]getTransByAddressBetweenBlocks: blockHash: " + blockHash);
	*				
	*			Object blockNumber = txInfo.get("blockNumber");
	*			System.out.println("[sync]getTransByAddressBetweenBlocks: blockNumber: " + blockNumber);
	*
	*			Object from = txInfo.get("from");
	*			System.out.println("[sync]getTransByAddressBetweenBlocks: from: " + from);
	*			
	*			Object to = txInfo.get("to");
	*			System.out.println("[sync]getTransByAddressBetweenBlocks: to: : " + to);
	*			
	*			Object value = txInfo.get("value");
	*			System.out.println("[sync]getTransByAddressBetweenBlocks: value: : " + value);					
	*		}
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<List> txListFuture = javaApi.getTransByAddressBetweenBlocks("WAN", "0x731bd7289b4191706b00f6f1877662b5e8697e82", 670730, 670735).sendAsync();
	*		
	*	try {		
	*		List txList = txListFuture.get();
	*
	*		for(int i=0; i<txList.size(); i++) {
	*			Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
	*			
	*			Object blockHash = txInfo.get("blockHash");
	*			System.out.println("\n[async]getTransByAddressBetweenBlocks: blockHash: " + blockHash);
	*				
	*			Object blockNumber = txInfo.get("blockNumber");
	*			System.out.println("[async]getTransByAddressBetweenBlocks: blockNumber: " + blockNumber);
	*
	*			Object from = txInfo.get("from");
	*			System.out.println("[async]getTransByAddressBetweenBlocks: from: " + from);
	*				
	*			Object to = txInfo.get("to");
	*			System.out.println("[async]getTransByAddressBetweenBlocks: to: : " + to);
	*			
	*			Object value = txInfo.get("value");
	*			System.out.println("[async]getTransByAddressBetweenBlocks: value: : " + value);				
	*		}			
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	* 
	* @apiSuccessExample {List} Successful Response
	  [{
		blockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, 
		transactionIndex=6, 
		txType=0x1, 
		nonce=0, 
		input=0x, 
		r=0x7dfe37a3835ca20fbfe44e7baefdda60e7105dc05dc1ff75a3ef39e60d3e3b8a, 
		s=0x36368d24661e3f4cdc210c6d427ec54f7b89549eb88319053c65edd40651cd04, 
		v=0x26, 
		blockNumber=1362889, 
		gas=21000, 
		from=0x731d8fdc53039f07efd87be3c387e747da92911f, 
		to=0x731bd7289b4191706b00f6f1877662b5e8697e82,
		value=216890000000000000000, 
		hash=0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd, 
		gasPrice=200000000000
	  }, {
		blockHash=0xf2f8fe13cbcec0a68de9ab1a55025eed85166e8810a95340012b8d882c7b8266, 
		transactionIndex=0, 
		txType=0x1, 
		nonce=4403, 
		input=0x, 
		r=0x84bb821de7dd9b211755b8798dd5304190593c5f3f49d736e56d14246e70f9b1, 
		s=0x49685c8017bb839524e0027244c37813a3df779692108e645fadb540294e43c1, 
		v=0x25, 
		blockNumber=670731, 
		gas=21000, 
		from=0x731bd7289b4191706b00f6f1877662b5e8697e82, 
		to=0x731d8fdc53039f07efd87be3c387e747da92911f, 
		value=216900000000000000000, 
		hash=0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283, 
		gasPrice=200000000000
	  }]
	*
	*/
	public WanRequest<List> getTransByAddressBetweenBlocks(String chainType,  
															String address, 
															long startBlockNo, 
															long endBlockNo){   
        // fill params 
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("address", address);
        mapParams.put("startBlockNo", startBlockNo);
        mapParams.put("endBlockNo", endBlockNo);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTransByAddressBetweenBlocks");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName getTransByBlock
	* @apiGroup Transactions
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTransByBlock
	* @apiVersion 1.0.0
	* @apiDescription Get transaction information in a given block by block number or block hash on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code>.
	* @apiParam (Input Parameter) {String} blockHashOrBlockNumber The blockHash or the blockNumber you want to search.
	*
	* @apiParam (Return) {List} response  The response data type is <code>List<Map<String,Object>></code>, each element of list is one tx info which is formed as <code><Map<String,Object></code>. you can get queried tx info, please refer to examples above.
	* 
	*
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTransByBlock","params":{"chainType":"WAN", "blockNumber":"984133"},"id":1}
	* or
	* {"jsonrpc":"2.0","method":"getTransByBlock","params":{"chainType":"WAN", "blockHash":"0xaa0fc2a8a868566f2e4888b2942ec05c47c2254e8b81e43d3ea87420a09126c2"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		List txList = javaApi.getTransByBlock("WAN", "0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").send();
	*		
	*		for(int i=0; i<txList.size(); i++) {
	*			Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
	*			
	*			Object blockHash = txInfo.get("blockHash");
	*			System.out.println("\n[sync]getTransByBlock: blockHash: " + blockHash);
	*			
	*			Object blockNumber = txInfo.get("blockNumber");
	*			System.out.println("[sync]getTransByBlock: blockNumber: " + blockNumber);
	*
	*			Object from = txInfo.get("from");
	*			System.out.println("[sync]getTransByBlock: from: " + from);
	*			
	*			Object to = txInfo.get("to");
	*			System.out.println("[sync]getTransByBlock: to: : " + to);
	*			
	*			Object value = txInfo.get("value");
	*			System.out.println("[sync]getTransByBlock: value: : " + value);					
	*		}				
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<List> txListFuture = javaApi.getTransByBlock("WAN", "0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457").sendAsync();
	*		
	*	try {
	*		List txList = txListFuture.get();
	*
	*		for(int i=0; i<txList.size(); i++) {
	*			Map<String, Object> txInfo =  (Map<String, Object>) txList.get(i);
	*			
	*			Object blockHash = txInfo.get("blockHash");
	*			System.out.println("\n[async]getTransByBlock: blockHash: " + blockHash);
	*			
	*			Object blockNumber = txInfo.get("blockNumber");
	*			System.out.println("[async]getTransByBlock: blockNumber: " + blockNumber);
	*
	*			Object from = txInfo.get("from");
	*			System.out.println("[async]getTransByBlock: from: " + from);
	*			
	*			Object to = txInfo.get("to");
	*			System.out.println("[async]getTransByBlock: to: : " + to);
	*			
	*			Object value = txInfo.get("value");
	*			System.out.println("[async]getTransByBlock: value: : " + value);				
	*		}			
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {List} Successful Response
	  [{
		blockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, 
		transactionIndex=2, 
		txType=0x1, 
		nonce=0, 
		input=0x, 
		r=0x61282bb5d07a3e4445735f96d579feede4a29f6ce603fbc6c15617536d80fe06, 
		s=0x17cba9a4ad00fb2b695ee1af6db770c0e3200a6fb369048f8b31ee47df4514de, 
		v=0x26, 
		blockNumber=1362889, 
		gas=21000, 
		from=0x9dc65fbf97b61173f44bf2203a98b53fd96b1624, 
		to=0x731bd7289b4191706b00f6f1877662b5e8697e82, 
		value=211990000000000000000, 
		hash=0xba0ceed770df4a6e330a643ec7f3b48096aed0ed393517caa81f06e748eb55b0, 
		gasPrice=200000000000
	  }, {
		blockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, 
		transactionIndex=12, 
		txType=0x1, 
		nonce=0, 
		input=0x, 
		r=0xf813cc0f2c420377433f3d7fc2d4f0fffaac3577da760fa5c13596bc67fbb7aa, 
		s=0x6018616b0c80048819ca020a900b7b398c245666c24d6b49cdea1c96770b6253, 
		v=0x26, 
		blockNumber=1362889, 
		gas=21000, 
		from=0x4d3987b16f9a3a9427baf32cedc32a050aa07b28, 
		to=0x731bd7289b4191706b00f6f1877662b5e8697e82, 
		value=213990000000000000000, 
		hash=0x3c4cd10545eab675c87c11026ad1dac5e8270297d2973f856b03855b8ea854a3, 
		gasPrice=200000000000
	  }]
	*
	*/
	public WanRequest<List> getTransByBlock(String chainType,  String blockHashOrBlockNumber){
    
        // fill params   
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        
        if (checkHash(blockHashOrBlockNumber)) {
            mapParams.put("blockHash", blockHashOrBlockNumber);
        } else {
            mapParams.put("blockNumber", blockHashOrBlockNumber);
        }
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTransByBlock");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<List>(mapRequest, ResultType.ARRAYMAP, this);
	}

	/**
	*
	* @apiName getTransactionConfirm
	* @apiGroup Transactions
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTransactionConfirm
	* @apiVersion 1.0.0
	* @apiDescription Get the transaction mined result on certain chain. 
	* When the receipt not existed, return directly with 'no receipt was found';
	* If receipt existed, the receipt will be returned after confirm-block-number blocks.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {Number} waitBlocks The confirm-block-number you want to set.
	* @apiParam (Input Parameter) {String} txHash The txHash you want to search.
	*
	* @apiParam (Return) {Map} response  The response data is <code>Map<String,Object></code> type, and you can get related property value of the queried tx. Please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTransactionConfirm","params":{"chainType":"WAN", "waitBlocks": 6, "txHash": "0xd2a5b1f403594dbc881e466d46a4cac3d6cf202476b1277876f0b24923d032da"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		Map<String, Object> txInfo = javaApi.getTransactionConfirm("WAN", 2, "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").send();
	*						
	*		Object blockHash = txInfo.get("blockHash");
	*		System.out.println("\n[sync]getTransactionConfirm: blockHash: " + blockHash);
	*		
	*		Object blockNumber = txInfo.get("blockNumber");
	*		System.out.println("[sync]getTransactionConfirm: blockNumber: " + blockNumber);
	*
	*		Object from = txInfo.get("from");
	*		System.out.println("[sync]getTransactionConfirm: from: " + from);
	*		
	*		Object to = txInfo.get("to");
	*		System.out.println("[sync]getTransactionConfirm: to: : " + to);
	*		
	*		Object logs = txInfo.get("logs");
	*		System.out.println("[sync]getTransactionConfirm: logs: : " + logs);	
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<Map<String, Object>> txFuture = javaApi.getTransactionConfirm("WAN", 2, "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").sendAsync();
	*		
	*	try {
	*		Map<String, Object> txInfo = txFuture.get();
	*		
	*		Object blockHash = txInfo.get("blockHash");
	*		System.out.println("\n[async]getTransactionConfirm: blockHash: " + blockHash);
	*		
	*		Object blockNumber = txInfo.get("blockNumber");
	*		System.out.println("[async]getTransactionConfirm: blockNumber: " + blockNumber);
	*
	*		Object from = txInfo.get("from");
	*		System.out.println("[async]getTransactionConfirm: from: " + from);
	*		
	*		Object to = txInfo.get("to");
	*		System.out.println("[async]getTransactionConfirm: to: : " + to);
	*		
	*		Object logs = txInfo.get("logs");
	*		System.out.println("[async]getTransactionConfirm: logs: : " + logs);	
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {Map} Successful Response
	  {
		blockHash=0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503, 
		logsBloom=0x00000000000000004000000100000000000000000000000000000000000000000000000000000000000000000000000000080020000000000000000000000000040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000000000000000000200000000000000000000008000000000000000008000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 
		gasUsed=90374, 
		blockNumber=2887621, 
		cumulativeGasUsed=90374, 
		from=0x58541bca9583b131684499b2e14636e1665e9bd2, 
		transactionIndex=0, 
		to=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, 
		logs=[{"blockHash":"0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503",
			"address":"0x33fc0c6b7fc83691e42855b14832d486c9a09e67",
			"logIndex":0,
			"data":"0x00000000000000000000000000000000000000000000010ff5e03b33183c6000000000000000000000000000000000000000000000000000000000005c8aeb0a00000000000000000000000000000000000000000000010ff5e03b33183c600000000000000000000000000000000000000000000000010ff5e03b33183c6000",
			"removed":false,
			"topics":["0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4","0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2"],
			"blockNumber":2887621,
			"transactionIndex":0,
			"transactionHash":"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35"}], 		
		transactionHash=0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35, 
		status=0x1
	  }
	*
	*/
	public WanRequest<Map<String, Object>> getTransactionConfirm(String chainType, long waitBlocks, String txHash){
   
        // fill params   
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("waitBlocks", waitBlocks);
        mapParams.put("txHash", txHash);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTransactionConfirm");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Map<String, Object>>(mapRequest, ResultType.MAP, this);
	}

	/**
	*
	* @apiName getTransactionReceipt
	* @apiGroup Transactions
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTransactionReceipt
	* @apiVersion 1.0.0
	* @apiDescription Get the receipt of a transaction by transaction hash on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain being queried, currently supports <code>"WAN"</code> and <code>"ETH"</code>.
	* @apiParam (Input Parameter) {String} txHash The txHash you want to search.
	*
	* @apiParam (Return) {Map} response  The response data is <code>Map<String,Object></code> type , you can get queried tx receipt info, please refer to examples above.
	* 
	* @apiParamExample {string} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTransactionReceipt","params":{"chainType":"WAN", "txHash":"0xc18c4bdf0d40c4bb2f34f0273eaf4dc674171fbf33c3301127e1d4c85c574ebe"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		Map<String,Object> txReceipt = javaApi.getTransactionReceipt("WAN", "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").send();
	*						
	*		Object blockHash = txReceipt.get("blockHash");
	*		System.out.println("\n[sync]getTransactionReceipt: blockHash: " + blockHash);
	*		
	*		Object blockNumber = txReceipt.get("blockNumber");
	*		System.out.println("[sync]getTransactionReceipt: blockNumber: " + blockNumber);
	*
	*		Object from = txReceipt.get("from");
	*		System.out.println("[sync]getTransactionReceipt: from: " + from);
	*		
	*		Object to = txReceipt.get("to");
	*		System.out.println("[sync]getTransactionReceipt: to : " + to);
	*		
	*		Object status = txReceipt.get("status");
	*		System.out.println("[sync]getTransactionReceipt: status : " + status);	
	*		
	*		Object logs = txReceipt.get("logs");
	*		System.out.println("[sync]getTransactionReceipt: logs : " + logs);	
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<Map<String,Object>> txReceiptFuture = javaApi.getTransactionReceipt("WAN", "0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35").sendAsync();
	*		
	*	try {
	*		Map<String,Object> txReceipt = txReceiptFuture.get();
	*			
	*		Object blockHash = txReceipt.get("blockHash");
	*		System.out.println("\n[async]getTransactionReceipt: blockHash: " + blockHash);
	*		
	*		Object blockNumber = txReceipt.get("blockNumber");
	*		System.out.println("[async]getTransactionReceipt: blockNumber: " + blockNumber);
	*
	*		Object from = txReceipt.get("from");
	*		System.out.println("[async]getTransactionReceipt: from: " + from);
	*		
	*		Object to = txReceipt.get("to");
	*		System.out.println("[async]getTransactionReceipt: to: " + to);
	*		
	*		Object status = txReceipt.get("status");
	*		System.out.println("[async]getTransactionReceipt: status: " + status);	
	*		
	*	} catch (InterruptedException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} catch (ExecutionException e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} 
	*
	* @apiSuccessExample {Map} Successful Response
	  {
		blockHash=0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503, 
		logsBloom=0x00000000000000004000000100000000000000000000000000000000000000000000000000000000000000000000000000080020000000000000000000000000040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000000000000000000200000000000000000000008000000000000000008000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 
		gasUsed=90374, 
		blockNumber=2887621, 
		cumulativeGasUsed=90374, 
		from=0x58541bca9583b131684499b2e14636e1665e9bd2, 
		transactionIndex=0, 
		to=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, 
		logs=[{"blockHash":"0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503",
			"address":"0x33fc0c6b7fc83691e42855b14832d486c9a09e67",
			"logIndex":0,
			"data":"0x00000000000000000000000000000000000000000000010ff5e03b33183c6000000000000000000000000000000000000000000000000000000000005c8aeb0a00000000000000000000000000000000000000000000010ff5e03b33183c600000000000000000000000000000000000000000000000010ff5e03b33183c6000",
			"removed":false,
			"topics":["0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4","0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2"],
			"blockNumber":2887621,
			"transactionIndex":0,
			"transactionHash":"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35"}], 
		transactionHash=0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35, 
		status=0x1
	  }
	*
	*/
	public WanRequest<Map<String, Object>> getTransactionReceipt(String chainType, String txHash){
   
        // fill params     
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("txHash", txHash);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTransactionReceipt");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Map<String, Object>>(mapRequest, ResultType.MAP, this);
	}

	/**
	*
	* @apiName getTxInfo
	* @apiGroup Transactions
	* @api {CONNECT} /ws/v3/YOUR-API-KEY getTxInfo
	* @apiVersion 1.0.0
	* @apiDescription Get the transaction detail via txHash on certain chain.
	*
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code> or <code>"ETH"</code> or <code>"BTC"</code>.
	* @apiParam (Input Parameter) {String} txHash The txHash you want to search.
	* @apiParam (Input Parameter) {Boolean} format Whether to get the serialized or decoded transaction. 
	* <br>In the case of <code>chainType</code> is set as <code>"WAN"</code> or <code>"ETH"</code>, just set <code>format</code> to <code>null</code>; 
	* <br>In the case of <code>chainType</code> is set as <code>"BTC"</code>:
	* <br>&nbsp;&nbsp;&nbsp;&nbsp;
	* Set to <code>false</code> (the default) to return the serialized transaction as hex.
	* <br>&nbsp;&nbsp;&nbsp;&nbsp;
	* Set to <code>true</code> to return a decoded transaction.
	*
	* @apiParam (Return) {Map} response  The response data is <code>Map<String,Object></code> type , you can get queried tx related property, please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"getTxInfo","params":{"chainType":"WAN", "txHash":"0xd2a5b1f403594dbc881e466d46a4cac3d6cf202476b1277876f0b24923d032da"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		Map<String,Object> txInfo = javaApi.getTxInfo("WAN", "0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283").send();
	*						
	*		Object blockHash = txInfo.get("blockHash");
	*		System.out.println("\n[sync]getTxInfo: blockHash: " + blockHash);
	*			
	*		Object blockNumber = txInfo.get("blockNumber");
	*		System.out.println("[sync]getTxInfo: blockNumber: " + blockNumber);
	*
	*		Object from = txInfo.get("from");
	*		System.out.println("[sync]getTxInfo: from: " + from);
	*		
	*		Object to = txInfo.get("to");
	*		System.out.println("[sync]getTxInfo: to: : " + to);
	*		
	*		Object value = txInfo.get("value");
	*		System.out.println("[sync]getTxInfo: value: : " + value);				
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by async mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	CompletableFuture<Map<String,Object>> txFuture = javaApi.getTxInfo("WAN", "0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283").sendAsync();
	*				
	*	try {
	*		Map<String, Object> txInfo =  txFuture.get();
	*		
	*		Object blockHash = txInfo.get("blockHash");
	*		System.out.println("\n[async]getTxInfo: blockHash: " + blockHash);
	*			
	*		Object blockNumber = txInfo.get("blockNumber");
	*		System.out.println("[async]getTxInfo: blockNumber: " + blockNumber);
	*
	*		Object from = txInfo.get("from");
	*		System.out.println("[async]getTxInfo: from: " + from);
	*		
	*		Object to = txInfo.get("to");
	*		System.out.println("[async]getTxInfo: to: : " + to);
	*		
	*		Object value = txInfo.get("value");
	*		System.out.println("[async]getTxInfo: value: : " + value);				
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	} 			
	*
	* @apiSuccessExample {Map} Successful Response
	  {
		blockHash=0xf2f8fe13cbcec0a68de9ab1a55025eed85166e8810a95340012b8d882c7b8266, 
		transactionIndex=0, 
		txType=0x1, 
		nonce=4403, 
		input=0x, 
		r=0x84bb821de7dd9b211755b8798dd5304190593c5f3f49d736e56d14246e70f9b1, 
		s=0x49685c8017bb839524e0027244c37813a3df779692108e645fadb540294e43c1, 
		v=0x25, 
		blockNumber=670731, 
		gas=21000, 
		from=0x731bd7289b4191706b00f6f1877662b5e8697e82, 
		to=0x731d8fdc53039f07efd87be3c387e747da92911f, 
		value=216900000000000000000, 
		hash=0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283, 
		gasPrice=200000000000
	  }
	*
	*/
	public WanRequest<Map<String, Object>> getTxInfo(String chainType, String txHash, Boolean format){
   
        // fill params  
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("txHash", txHash);
        
        if(null == format) {
        	if("BTC" == chainType) {
                mapParams.put("format", false);
        	}
        }else {
            mapParams.put("format", format);
        }
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","getTxInfo");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<Map<String, Object>>(mapRequest, ResultType.MAP, this);
	}

	/**
	*
	* @apiName sendRawTransaction
	* @apiGroup Transactions
	* @api {CONNECT} /ws/v3/YOUR-API-KEY sendRawTransaction
	* @apiVersion 1.0.0
	* @apiDescription Submit a pre-signed transaction for broadcast to certain chain.
	* 
	* @apiParam (Input Parameter) {String} chainType The chain name that you want to search, should be <code>"WAN"</code> or <code>"ETH"</code> or <code>"BTC"</code>.
	* @apiParam (Input Parameter) {String} signedTx The signedTx you want to send.
	* 
	* @apiParam (Return) {String} response  The response data is <code>String</code> type , please refer to examples above.
	* 
	* @apiParamExample {String} JSON-RPC over websocket
	* {"jsonrpc":"2.0","method":"sendRawTransaction","params":{"chainType":"WAN", "signedTx":"0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356"},"id":1}
	*
	* @apiExample {java} Example by sync mode:
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *
	*	try {
	*		String strRslt = javaApi.sendRawTransaction("WAN", "0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356").send();
	*		System.out.println("[sync]sendRawTransaction: strRslt: " + strRslt);	
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiExample {java} Example by sync mode:	
    *	Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);
    *	
	*	CompletableFuture<String> txFuture = javaApi.sendRawTransaction("WAN", "0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356").sendAsync();
	*		
	*	try {
	*		String strRslt =  txFuture.get();
	*		System.out.println("[async]sendRawTransaction: strRslt: " + strRslt);
	*			
	*	}catch (Exception e) {
	*		// TODO Auto-generated catch block
	*		e.printStackTrace();
	*	}
	*
	* @apiSuccessExample {String} Successful Response
	*	"0x4dcfc82728b5a9307f249ac095c8e6fcc436db4f85a094a0c5a457255c20f80f"
	*
	*/
	public WanRequest<String> sendRawTransaction(String chainType, String signedTx){
	      
        // fill params   
        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("chainType", chainType);
        mapParams.put("signedTx", signedTx);
        
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        mapRequest.put("method","sendRawTransaction");
        mapRequest.put("params",mapParams);
        
        return new WanRequest<String>(mapRequest, ResultType.STRING, this);
	}
	

}

package com.wanchain.iwanj.lib;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WanRequest<T>{

    private Map<String, Object> mapParams;
    private ResultType rltType;  
    private WsInstance wsInstance;

    public WanRequest(Map<String, Object> params, ResultType rltType, WsInstance wsInstance) {
		 this.mapParams = params;
		 this.rltType = rltType;
		 this.wsInstance = wsInstance;
	}

    public T send() throws IOException {
	    	 
	    try {	    	
		     return sendAsync().get();
		     
		} catch (InterruptedException e) {
		     Thread.interrupted();
		     throw new IOException("Interrupted WebSocket request", e);
		     
		} catch (ExecutionException e) {
		     if (e.getCause() instanceof IOException) {
		         throw (IOException) e.getCause();
		     }
		
		     throw new RuntimeException("Unexpected exception", e.getCause());
		}
    }

    public CompletableFuture<T> sendAsync() {

        CompletableFuture<T> future = new CompletableFuture<>();
        try {
        	this.wsInstance.sendMessage(this.mapParams, this.rltType, future);
   		 	
        }  catch (Throwable e) {
        	future.completeExceptionally(e);
        }

        return future;
    }
}
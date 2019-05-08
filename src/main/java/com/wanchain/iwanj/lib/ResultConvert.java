package com.wanchain.iwanj.lib;

import java.util.concurrent.CompletableFuture;

/** 
 * Receive the string from websocket and convert them to designed data type, 
 * then use CompletableFuture return then to user.
 */
public interface ResultConvert {
	void convert(String src, CompletableFuture<?> future);
}

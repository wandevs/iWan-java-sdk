package com.wanchain.iwanj.lib;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebsocketClient {
	// private static final Logger log = LoggerFactory.getLogger(WebsocketClient.class);

	/**The status code to close web socket.*/
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    /**The max number of messages waiting in web socket, 
     * if more message, the websocket will not send more
     *  and wait until the queue size decrease to less than this number*/
	private static final int MAX_SENDED_QUEUE = 50;
	/**If there is too much message in web socket queue,
	 *  the following task will wait in threadSendPool.
	 * this number is the max value in this thread pool.*/
	private static final int MAX_WAITING_QUEUE = 10000;
	/**If web socket has error and emit WebSocketListener.onFailure,
	 * it will try to open the socket again, this value is the max time to try,
	 * if failed times is more than this value, it will give to open the socket again.*/
	private static final int MAX_RETRIED_TIME = 3;
	/**The max time for message to wait in web socket, 
	 * if more than this time, it will throw exception*/
	private static final int MAX_SOCKET_WAITING_TIME = 600 * 1000;
	/**The max time for messages to wait the web socket to open*/
	private static final int MAX_CONNECT_WAITING_TIME = 10;
	
	/**OkHttpClient*/
    private static OkHttpClient sClient;
    /**WebSocket*/
    private static WebSocket sWebSocket;
    
    /**The messages info that have sent and wait return result,
     * Map<id, TypeFuture>*/
    private static Map<Long, TypeFuture> mapFuture = new ConcurrentHashMap<>(MAX_SENDED_QUEUE);
    /**If the web socket is opened.*/
    private static boolean bOpened = false;
    /**to record the serious failed times for opening socket*/
    private static int iRetriedTime = 0;
    /**the url to connect the web server.*/
    private static String urlWebSocket = null;
    /**The map for the last result data type and the interface's
     *  implement to convert the json string to the last data.*/
    private static Map<ResultType, ResultConvert> mapConvert = new HashMap<>();
    /**The thread pool to run tasks to send message to web socket.*/
    private static ExecutorService threadSendPool =  new ThreadPoolExecutor(1,
			10, 1000, TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<Runnable>(MAX_WAITING_QUEUE),
			Executors.defaultThreadFactory(),
			new ThreadPoolExecutor.AbortPolicy());
    /**The thread pool to clear out time's message form mapFuture and send exception to final users*/
    //private static ExecutorService threadClearPool = Executors.newSingleThreadExecutor();
    private static Thread threadClear = null;
    /**The thread pool to receive result from web socket and convert it to final result and send to users.*/
    //private static ExecutorService threadResultPool = Executors.newFixedThreadPool(3);
    
    /***
     * Register result type and its implementation. It should be called in initial function. 
	 * @param type the enum value from ResultType.
	 * @param rc the interface's implementation to convert json to final result.
	 */
    public static void addResultType(ResultType type, ResultConvert rc) {
    	mapConvert.put(type, rc);
    }
    
    /*** 
     * @return if the web socket is opened.
     */
    public static boolean isOpened() {
    	return bOpened;
    }
    
    /***
     * init the web socket
     * @param url the url to connect the web socket
     */
    public static synchronized void startRequest(String url) {
    	// log.info("Start to open web socket");
    	
    	urlWebSocket = url;
        if (sClient == null) {
            sClient = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
            Runnable r = (() -> {
            	while (true) {
	            	try {
						Thread.sleep(15 * 1000);
		            	long dt = (new Date()).getTime();
						for (long id : mapFuture.keySet()) {
							if (dt - mapFuture.get(id).dtCreate > MAX_SOCKET_WAITING_TIME) {
								TypeFuture tf = mapFuture.remove(id);
								tf.future.completeExceptionally(new Exception("Time out."));
								// log.error("Delete a message due to wait too much time, id=" + Long.toString(id));
							}
						}
						
						if (sWebSocket == null) {
							createWebSocket();
						} else if (bOpened) {
							//sWebSocket.send("{\"id\": 0}");
						}
					} catch (Exception e) {
						// log.error("threadClear", e);
					}
            	}
            });
            
            threadClear = new Thread(r);
            threadClear.start();
        }
        
        if (sWebSocket == null) {
            createWebSocket();
        }
        
    //    log.info("End to open web socket");
    }
    
    /**
     * Add a message to a task that will run in thread pool to send this message to web socket server.
     * @param text the message will send to web socket server
     * @param id the id to identify the messages. 
     * @param rt the result type needed by the final users.
     * @param future CompletableFuture<?>, send the final results to users.
     */
    public static void sendMessage(Map<String, Object> msg, Long id, ResultType rt, CompletableFuture<?> future, byte[] key, String enc) {
    	// log.info("Start to send message. id=" + Long.toString(id));

    	Long dtCreate = (new Date()).getTime();
    	Runnable r = new Runnable() {
    		public void run() {
				try {
        			while(true) {
        				
        				if ((new Date()).getTime() - dtCreate > MAX_SOCKET_WAITING_TIME) {
        					future.completeExceptionally(new Exception("Time out."));
        					return;
        				}

        				if (mapFuture.size() < MAX_SENDED_QUEUE) break;
        				
        				Thread.sleep(1);
    				}
        				
					int count = 0;
					while (true) {
						if (bOpened) break;
						if ((sWebSocket == null || bOpened == false) && count > MAX_CONNECT_WAITING_TIME) {
							future.completeExceptionally(new Exception("Failed to send due to web socket"));
							return;
						}
						
						Thread.sleep(1000);
						count++;
					}	

					if (!sendMsg(msg, id, rt, future, dtCreate, key, enc)) {
   						future.completeExceptionally(new Exception("Failed to send message."));
    					return;
    				}
   					// log.info("Close the thread to send message.");
    			} catch (Exception ex) {
					// log.error("ThreadSend", ex);
					future.completeExceptionally(ex);
				}
    		}
    	};
    	try {
    		threadSendPool.execute(r);
    	} catch (Exception ex) {
    		// log.error("sendMessage", ex);
    		future.completeExceptionally(ex);
    		return;
    	}
    	// log.info("End to send message.");
    }
    
    /**
     * Close web socket.
     */
    public static synchronized void closeWebSocket() {
    	// log.info("closeWebSocket");
        if (sWebSocket != null) {
            sWebSocket.close(NORMAL_CLOSURE_STATUS, null);
            sWebSocket = null;
            bOpened = false;
        }
    }
    
    /**
     * destory
     */
    public static synchronized void destroy() {
    	// log.info("destroy");
        if (sClient != null) {
            sClient.dispatcher().executorService().shutdown();
            sClient = null;
            sWebSocket = null;
            bOpened = false;
        }
    }
    
    /**
     * reconnect the web socket when web socket has error and closed.
     */
    private static synchronized void resetWebSocket() {
    	// log.info("resetWebSocket");
        sWebSocket = null;
        bOpened = false;
                    
        if (iRetriedTime >= MAX_RETRIED_TIME) {
        	// log.error("Retried and failed too much time to open web socket.");
        } else {
        	createWebSocket();
        	iRetriedTime++;
        }
    }
    
    /**
     * run in thread pool as a task and send message to web socket.
     * @param text the message will send to web socket server
     * @param id the id to identify the messages. 
     * @param rt the result type needed by the final users.
     * @param future CompletableFuture<?>, send the final results to users.
     * @return if send successfully
     */
    private static boolean sendMsg(Map<String, Object> msg, Long id, ResultType type,
    		CompletableFuture<?> future, long dt, byte[] key, String enc) {
    	try {
	    	String text = Auth.integrateJson(msg, key, enc);
	    	// log.info("sendMsg\t" + text);
	        if (!sWebSocket.send(text)) {	        	
	    		return false;
	        }
	       
	        mapFuture.put(id, new TypeFuture(type, future, dt));
	        return true;
		} catch (Exception ex) {
			future.completeExceptionally(ex);
			return false;
		}
    }
    
    /**
     * create a new web socket connection
     */
    private static synchronized void createWebSocket() {
    	if (sWebSocket != null) return;
    	
        Request request = new Request.Builder().url(urlWebSocket).build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        sWebSocket = sClient.newWebSocket(request, listener);
        // log.info("Finished to create a new web scoket");
    }
    
    public static class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
        	iRetriedTime = 0;
        	bOpened = true;
            // log.info("EchoWebSocketListener.Opened");
        }
        
        @SuppressWarnings("static-access")
		@Override
        public void onMessage(WebSocket webSocket, String text) {
            // log.info("EchoWebSocketListener.onMessage");
            try {
	            Map<String, Object> map = Utils.jsonToMap(text);
	            Object obj = map.get("id");
	            if (obj.getClass().getSimpleName().indexOf("Integer") < 0) {
	            	// log.error(text);
	            	return;
	            }
	            long id = (int) obj;
	            TypeFuture tf = mapFuture.remove(id);
	            ResultConvert rc = mapConvert.get(tf.type);
	            if (rc == null) {
	            	tf.future.completeExceptionally(new Exception("Failed to get ResultType " + tf.type.toString()));
	            } else {
	            	rc.convert(text, tf.future);
	            }
            } catch (Exception ex) {
            	// log.error("EchoWebSocketListener.onMessage", ex);
            }            	
        }
        
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            // log.info("EchoWebSocketListener.onClosing");
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            resetWebSocket();
        }
        
        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
        	bOpened = false;
        	// log.info("EchoWebSocketListener.onClosed");
        }
        
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        	// log.error("EchoWebSocketListener.onFailure", t);
        	bOpened = false;
            resetWebSocket();
        }
    }
   
    /**
     * interal class to save message's id, future
     */
    public static class TypeFuture {
    	public TypeFuture(ResultType r, CompletableFuture<?> f, long dt) {
    		type = r;
    		future = f;
    		dtCreate = dt;
    	}
    	/**The return result's type*/
    	public ResultType type;
    	/**The future to send the result to user*/
    	public CompletableFuture<?> future;
    	/**The time to create this object*/
    	public long dtCreate;
    }
}
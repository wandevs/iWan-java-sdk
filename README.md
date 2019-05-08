# iWan-js-sdk

[![GitHub License][license]][license-url]

### Java SDK for iWan RPC Server

## Install
You can get iwan-java-sdk by two ways:
1) By cloning the java sdk source code and packaging the jar by "maven package" cmd;
```bash
	git clone https://github.com/wandevs/iWan-java-sdk.git

	cd wanchain-js-sdk

	maven install
```

2) By downloading the jar we have packaged already:
```bash
[iWan-java-sdk](examples/lib/iwanj-0.5.0.jar)
```

then you can integrate the jar into you application. 

## Initial iWan Java Sdk Instance
After installation, the iWan SDK can be used to connect to the iWan RPC server to call a method such as `getBalance`. The default config can be used or custom config parameters can be passed using the `option` object.

### Import the iWan-java-sdk
```bash
import com.wanchain.iwanj.lib.apis.Wanj;
```

### Initial Wanj instance by default configuration
If you won't config the URL, that the 3rd parameter is set to "null", the SDK will connect to `"api.wanchain.org:8443"' by default:
```bash
Wanj javaApi = new Wanj(YourApiKey, YourSecretKey, null);
```
### Initial Wanj instance by specific configuration
A different URL can be specified in the `option` object which is subject to [iWan](https://iwan.wanchain.org).

```bash
//Subject to https://iwan.wanchain.org
Map<String, String> option = new HashMap<>();
option.put("socketUrl", "wss://apitest.wanchain.org:8443");
option.put("apiFlag", "ws");
option.put("apiVersion", "v3");

Wanj javaApi = new Wanj(YourApiKey, YourSecretKey, option);
```

Instead of using the iWan SDK for connecting to the iWan RPC server, a raw WebSocket API can also be used, for more information, please see the documentation [iWan RPC API](https://iwan.wanchain.org/static/apidoc/docs.html). However, we strongly recommend using the iWan SDK.

### Details about `option`
The SDK object can accept an `option` object. See below for examples of usage.

- `option` {Object}
  - `socketUrl` {String}  The RPC server URL, default is 'api.wanchain.org:8443'.
  - `apiFlag` {String} The flag to connect the iWan RPC server, default is 'ws'.
  - `apiVersion` {String} The RPC method version, default is 'v3'.

### ApiKey and SecretKey
In order to get an `ApiKey`, sign up at [iWan](https://iwan.wanchain.org). Then create a new project to get a new `ApiKey` and `SecretKey` key pair.

## Basic Usage
Both `send()` and `sendAsync()` are supported: 

- `send()` : Api method is called by synchronous mode. 

- `sendAsync()` : Api method is called by asynchronous mode.


The method `getBalance` is used as an example below to show the use of `send()` and `sendAsync()` in the iWan-java-SDK :

### Synchronous Call Mode: send()
`callback` can be used for asynchronous mode:

```bash
try {
	BigInteger rslt = javaApi.getBalance("WAN", addresses[0]).send();	
	System.out.println("\n[sync]getBalance : " + rslt);

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
```

### Asynchronous Call Mode: sendAsync()
`Promise` can be used for synchronous mode:

```bash
CompletableFuture<BigInteger> rsltFuture = javaApi.getBalance("WAN", addresses[0]).sendAsync();	
	
try {			
	BigInteger rslt = rsltFuture.get();	
	System.out.println("\n[async]getBalance : " + rslt);

}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}	
```

### Examples

- [Simple Usage](examples/Sample.java)


## Documentation

[iWan Java SDK API](https://wanchain.github.io/iWan-java-sdk/) : API details about iWan SDK

[license]: https://img.shields.io/badge/license-GNUGPL3-blue.svg
[license-url]:https://github.com/wandevs/iWan-java-sdk/blob/master/LICENSE

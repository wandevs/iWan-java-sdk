define({ "api": [
  {
    "name": "getBalance",
    "group": "Accounts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getBalance",
    "version": "0.5.0",
    "description": "<p>Get balance for a single address.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The account being queried.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the balance of queried account, which type is <code>BigInteger</code>. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getBalance\",\"params\":{\"address\": \"0x8456711c9d3dce1b8a6fcfa372277b95acb404c9\",\"chainType\":\"WAN\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger balance = javaApi.getBalance(\"WAN\", \"0x8456711c9d3dce1b8a6fcfa372277b95acb404c9\").send();\n\tSystem.out.println(\"\\n[sync]getBalance : \" + balance);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode: ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\t\nCompletableFuture<BigInteger> rsltFuture = javaApi.getBalance(\"WAN\", \"0x8456711c9d3dce1b8a6fcfa372277b95acb404c9\").sendAsync();\t\n\ntry {\t\t\t\n\tBigInteger balance = rsltFuture.get();\t*\t\t\n\tSystem.out.println(\"\\n[async]getBalance : \" + balance);\n\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "63545814080091111700",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Accounts"
  },
  {
    "name": "getMultiBalances",
    "group": "Accounts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getMultiBalances",
    "version": "0.5.0",
    "description": "<p>Get balance for multiple Addresses in a single call.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String[]",
            "optional": false,
            "field": "addressArray",
            "description": "<p>An array of addresses being queried.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response type is <code>Map&lt;String, BigInteger&gt;</code>, and you can get the balance of queried account by address. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getMultiBalances\",\"params\":{\"address\": [\"0x8456711c9d3dce1b8a6fcfa372277b95acb404c9\",\"0x2cc79fa3b80c5b9b02051facd02478ea88a78e2d\"],\"chainType\":\"WAN\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\naddresses = new String[2];\naddresses[0] = \"0x8456711c9d3dce1b8a6fcfa372277b95acb404c9\";\naddresses[1] = \"0x2cc79fa3b80c5b9b02051facd02478ea88a7802d\";\n \ntry {\n\tMap<String, BigInteger> mapRslt = javaApi.getMultiBalances(\"WAN\", addresses).send();\n\n\tSystem.out.println(\"\\n[sync]addresses[0] balance : \" + mapRslt.get(addresses[0]));\n\tSystem.out.println(\"\\n[sync]addresses[1] balance: \" + mapRslt.get(addresses[1]));\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode: ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\naddresses = new String[2];\naddresses[0] = \"0x8456711c9d3dce1b8a6fcfa372277b95acb404c9\";\naddresses[1] = \"0x2cc79fa3b80c5b9b02051facd02478ea88a7802d\";\n   \t\nCompletableFuture<Map<String, BigInteger>> rsltFuture = javaApi.getMultiBalances(\"WAN\", addresses).sendAsync();\n\t\ntry {\t\n\tMap<String, BigInteger> mapRslt = rsltFuture.get(); \n\t\n\tSystem.out.println(\"\\n[async]addresses[0] balance : \" + mapRslt.get(addresses[0]));\n\tSystem.out.println(\"\\n[async]addresses[1] balance: \" + mapRslt.get(addresses[1]));\n\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response ",
          "content": "{\n\t0x8456711c9d3dce1b8a6fcfa372277b95acb404c9=63545814080091111700, \n\t0x2cc79fa3b80c5b9b02051facd02478ea88a7802d=0\n}",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Accounts"
  },
  {
    "name": "getNonce",
    "group": "Accounts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getNonce",
    "version": "0.5.0",
    "description": "<p>Get the nonce of an account.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The account being queried.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "String",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the nonce of queried account, and the type is <code>String</code>. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getNonce\",\"params\":{\"address\": \"0x731bd7289b4191706b00f6f1877662b5e8697e82\",\"chainType\":\"WAN\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tString nonce = javaApi.getNonce(\"WAN\", \"0x731bd7289b4191706b00f6f1877662b5e8697e82\").send();\n\tSystem.out.println(\"\\n[sync]getNonce : \" + nonce);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:   ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<String> nonceFuture = javaApi.getNonce(\"WAN\", \"0x731bd7289b4191706b00f6f1877662b5e8697e82\").sendAsync();\t\n\ntry {\n\tString nonce = nonceFuture.get();\t\t\n\tSystem.out.println(\"\\n[async]getNonce : \" + nonce);\n\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\"0x6ac\"",
          "type": "String"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Accounts"
  },
  {
    "name": "getNonceIncludePending",
    "group": "Accounts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getNonceIncludePending",
    "version": "0.5.0",
    "description": "<p>Get the pending nonce of an account.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The account being queried.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "String",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the pending nonce of queried account, and type is <code>String</code>. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getNonceIncludePending\",\"params\":{\"address\": \"0x731bd7289b4191706b00f6f1877662b5e8697e82\",\"chainType\":\"WAN\"}, \"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tString nonce = javaApi.getNonceIncludePending(\"WAN\", \"0x731bd7289b4191706b00f6f1877662b5e8697e82\").send();\t\t\n\tSystem.out.println(\"\\n[sync]getNonceIncludePending : \" + nonce);\n\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:\t",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<String> nonceFuture = javaApi.getNonceIncludePending(\"WAN\", \"0x731bd7289b4191706b00f6f1877662b5e8697e82\").sendAsync();\n\ntry {\n\tString nonce = nonceFuture.get();\t\t\t\n\tSystem.out.println(\"\\n[async]getNonceIncludePending : \" + nonce);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\"0x6ac\"",
          "type": "String"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Accounts"
  },
  {
    "name": "getUTXO",
    "group": "Accounts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getUTXO",
    "version": "0.5.0",
    "description": "<p>Get the detail BTC UTXO info for BTC.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;BTC&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Number",
            "optional": false,
            "field": "minconf",
            "description": "<p>The min confirm number of BTC UTXO, usually 0.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Number",
            "optional": false,
            "field": "maxconf",
            "description": "<p>The max confirm number of BTC UTXO, usually the confirmed blocks you want to wait for the UTXO.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String[]",
            "optional": false,
            "field": "address",
            "description": "<p>The address array that you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the detail BTC UTXO array, and the type is <code>List&lt;Map&lt;String, Object&gt;&gt;</code> which element is one utxo info. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getUTXO\",\"params\":{\"chainType\":\"BTC\", \"minconf\":0, \"maxconf\":100000, \"address\":[\"1FZeVAnjdTK4Upu3b1CjhvwkrgfzvF6YUS\",\"1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX\"]},\"id\":1}",
          "type": "string"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] addArray = new String[2];\naddArray[0] = \"1FZeVAnjdTK4Upu3b1CjhvwkrgfzvF6YUS\";\naddArray[1] = \"1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX\";\n\ntry {\n\tList utxoRslt = javaApi.getUTXO(\"BTC\", 0, 100000, addArray).send();\n\t\n\tfor(int i=0; i < utxoRslt.size(); i++) {\n\t\tMap<String, Object> utxoItem = (Map<String, Object>) utxoRslt.get(i);\n\t\t\n\t\tObject txid = utxoItem.get(\"txid\");\n\t\tSystem.out.println(\"\\n[sync]getUTXO: txid: \" + txid);\n\n\t\tObject scriptPubKey = utxoItem.get(\"scriptPubKey\");\n\t\tSystem.out.println(\"[sync]getUTXO: scriptPubKey: \" + scriptPubKey);\n\t\t\n\t\tObject safe = utxoItem.get(\"safe\");\n\t\tSystem.out.println(\"[sync]getUTXO: safe: \" + safe);\n\t\t\n\t\tObject value = utxoItem.get(\"value\");\n\t\tSystem.out.println(\"[sync]getUTXO: value: : \" + value);\t\n\t}\t\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] addArray = new String[2];\naddArray[0] = \"1FZeVAnjdTK4Upu3b1CjhvwkrgfzvF6YUS\";\naddArray[1] = \"1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX\";\n\nCompletableFuture<List> utxoFuture = javaApi.getUTXO(\"BTC\", 0, 100000, addArray).sendAsync();\t\t\n\ntry {\n\tList utxoList = utxoFuture.get();\n\n\tfor(int i=0; i < utxoList.size(); i++) {\n\t\tMap<String, Object> utxoItem = (Map<String, Object>) utxoList.get(i);\n\t\t\n\t\tObject txid = utxoItem.get(\"txid\");\n\t\tSystem.out.println(\"\\n[async]getUTXO: txid: \" + txid);\n\n\t\tObject scriptPubKey = utxoItem.get(\"scriptPubKey\");\n\t\tSystem.out.println(\"[async]getUTXO: scriptPubKey: \" + scriptPubKey);\n\t\t\n\t\tObject safe = utxoItem.get(\"safe\");\n\t\tSystem.out.println(\"[async]getUTXO: safe: \" + safe);\n\t\t\n\t\tObject value = utxoItem.get(\"value\");\n\t\tSystem.out.println(\"[async]getUTXO: value: : \" + value);\t\n\t}\t\t\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "{List<Map>} Successful Response",
          "content": "[\n\t  {\n\t    scriptPubKey=76a914ac752b86edfa7362776f198ca551fd5276d13f0788ac, \n\t    amount=0.0016223, \n\t    spendable=false, \n\t    solvable=false, \n\t    address=1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX, \n\t    safe=true, \n\t    txid=a97e182cd46280737bea0782adb1ae7fb5e5d146e3036a2653c8a18a5771dce7, \n\t    label=, \n\t    confirmations=4855, \n\t    value=0.0016223, \n\t    vout=1\n\t  }\n]",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Accounts"
  },
  {
    "name": "importAddress",
    "group": "Accounts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "importAddress",
    "version": "0.5.0",
    "description": "<p>Send a <code>'import address'</code> command to BTC.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;BTC&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The BTC account address you want to import to the node to scan transactions.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "String",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the result of importing address operation, and is <code>String</code> type. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"importAddress\",\"params\":{\"chainType\":\"BTC\",\"address\":\"1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tString rslt = javaApi.importAddress(\"BTC\", \"1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX\").send();\n\tAssert.assertEquals(\"success\", rslt);\n\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<String> rsltFuture = javaApi.importAddress(\"BTC\", \"1Gisb7SesumKajcF7NTxA8QvDwnvQykiiX\").sendAsync();\n\ntry {\t\t\t\n\tString rslt = rsltFuture.get(); \n\tAssert.assertEquals(\"success\", rslt);\n     \n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\"success\"",
          "type": "String"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Accounts"
  },
  {
    "name": "getBlockByHash",
    "group": "Blocks",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getBlockByHash",
    "version": "0.5.0",
    "description": "<p>Get the block information about a block by block hash on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code> or <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "blockHash",
            "description": "<p>The blockHash you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response is <code>Map&lt;String, Object&gt;</code> type, and is queried block info from which you can get related property value by property name. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getBlockByHash\",\"params\":{\"chainType\":\"WAN\", \"blockHash\":\"0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tMap<String,Object> blockInfo = javaApi.getBlockByHash(\"WAN\",\"0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457\").send();\n\t\t\t\t\t\t\n\tObject blockNumber = blockInfo.get(\"number\");\n\tSystem.out.println(\"\\n[sync]getBlockByHash: blockNumber: \" + blockNumber);\n\n\tObject transactions = blockInfo.get(\"transactions\");\n\tSystem.out.println(\"[sync]getBlockByHash: transactions: \" + transactions);\n\t\n\tObject timestamp = blockInfo.get(\"timestamp\");\n\tSystem.out.println(\"[sync]getBlockByHash: timestamp: \" + timestamp);\n\t\n\tObject miner = blockInfo.get(\"miner\");\n\tSystem.out.println(\"[sync]getBlockByHash: miner: \" + miner);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode: ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<Map<String,Object>> future = javaApi.getBlockByHash(\"WAN\",\"0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457\").sendAsync();\n\ntry {\t\t\t\n\tMap<String,Object> blockInfo = future.get();\t\n\t\n\tObject blockNumber = blockInfo.get(\"number\");\n\tSystem.out.println(\"\\n[async]getBlockByHash: blockNumber: \" + blockNumber);\n\n\tObject transactions = blockInfo.get(\"transactions\");\n\tSystem.out.println(\"[async]getBlockByHash: transactions: \" + transactions);\n\t\n\tObject timestamp = blockInfo.get(\"timestamp\");\n\tSystem.out.println(\"[async]getBlockByHash: timestamp: \" + timestamp);\n\t\n\tObject miner = blockInfo.get(\"miner\");\n\tSystem.out.println(\"[async]getBlockByHash: miner: \" + miner);\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  {\n    logsBloom=0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, \n    totalDifficulty=9599458746581, \n    receiptsRoot=0xcfad9c26ff4b1c03ec6fe24dc6766624fadb92641b900c07b90ba5559f59ba5c, \n    extraData=0xd583010003846765746885676f312e39856c696e7578000000000000000000009853fb8c79ba693ce77a9a74f3f063a5f4b430e6bcfcb59093ffc57c3772f77d6e17dd4192e75832f9f7d51439b372496db471326631f745bd12d649e29496ef01, \n    transactions=[\"0x4376314060f34286cfd35fb78f212a0fbc11e1a0946eaa6cc7ffaf145c31fbd7\",\n\t\t\"0x7ecf01d67b73dc40a7d39d7b919f9c9ca2f4ce9023f70401381c279d29cbc42b\",\n\t\t\"0xba0ceed770df4a6e330a643ec7f3b48096aed0ed393517caa81f06e748eb55b0\",\n\t\t\"0x2ff180f5fae10dbb87b29b0632e37e76fbe266e74022313b7fbc0728f4b37fa8\",\n\t\t\"0xa3084f20656d566ba1a60486a004d9f10515007b25200541f3291c431a3e1ef5\",\n\t\t\"0x72d448523cea9235851ac9bf37d47a270625fea8be5a9af7a526ade79847084a\",\n\t\t\"0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd\",\n\t\t\"0x8002c67669d420525b829b111a0947910acbce232ec68614e314cfc70c044690\",\n\t\t\"0x85be3736d8b8e2a97598255dc18012c4cd8f89803d846030db735e13cade0c68\",\n\t\t\"0x087b91305f336dbe8bf0972637fbed747629dca3e479920fcb432d1102670cad\",\n\t\t\"0x6b4c4c8ee8b1c3f7f2bf4ec58610be369907f0a9fbd3e79d686ca6c4edf7660a\",\n\t\t\"0x662cc0ded8ab79e85b74fc789c99607b66aef823d354c02a075fb79d18b50922\",\n\t\t\"0x3c4cd10545eab675c87c11026ad1dac5e8270297d2973f856b03855b8ea854a3\",\n\t\t\"0x1fbf01c7d32d24488979741f927fdb2e25ff8760f0a54673f0f139dd58af120d\",\n\t\t\"0xc0e52622158cb8b05dfcadb12d412e95a79b794b08ecbdf5bdf3ebf940a04106\"], \n    nonce=0x21fb481a9525e00f, \n    miner=0x4df69093933afc9ae4f665028ce56cd7008610fc,\n    difficulty=7068264, \n    gasLimit=4712388, \n    number=1362889, \n    gasUsed=315000, \n    uncles=[], \n    size=2299, \n    sha3Uncles=0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347, \n    transactionsRoot=0x6fc40a312dbd1854cf131342e87380625e11008d132dfbbb2b1ef7aa395a261a, \n    stateRoot=0x8b4411457f93d0694e6e05adc4ec51696f6b4e16ff65378784bc2ce5ff9e3949,\n    mixHash=0x36e505466927db2f555c83046f155c2b26fee896c0bfee8ecfe95ba5cebbf69a, \n    parentHash=0x505d86c91cfb51dcdf106f5c810627c2d2e44779339f10c143134ee20ac39224, \n    hash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, \n    timestamp=1533296957\n  }",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Blocks"
  },
  {
    "name": "getBlockByNumber",
    "group": "Blocks",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getBlockByNumber",
    "version": "0.5.0",
    "description": "<p>Get the block information about a block by block number on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code> or <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Number",
            "optional": false,
            "field": "blockNumber",
            "description": "<p>The blockNumber you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response is <code>Map&lt;String, Object&gt;</code> type, and is queried block info from which you can get related property value by property name. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getBlockByNumber\",\"params\":{\"chainType\":\"WAN\", \"blockNumber\":\"670731\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tMap<String,Object> blockInfo = javaApi.getBlockByNumber(\"WAN\", 1362889).send();\n\t\t\t\t\t\t\n\tObject hash = blockInfo.get(\"hash\");\n\tSystem.out.println(\"\\n[sync]getBlockByNumber: hash: \" + hash);\n\n\tObject transactions = blockInfo.get(\"transactions\");\n\tSystem.out.println(\"[sync]getBlockByNumber: transactions: \" + transactions);\n\t\n\tObject timestamp = blockInfo.get(\"timestamp\");\n\tSystem.out.println(\"[sync]getBlockByNumber: timestamp: \" + timestamp);\n\t\n\tObject miner = blockInfo.get(\"miner\");\n\tSystem.out.println(\"[sync]getBlockByNumber: miner: \" + miner);\t\n\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<Map<String,Object>> future = javaApi.getBlockByNumber(\"WAN\",1362889).sendAsync();\n\ntry {\t\t\t\n\tMap<String,Object> blockInfo = future.get();\t\n\t\n\tObject hash = blockInfo.get(\"hash\");\n\tSystem.out.println(\"\\n[async]getBlockByNumber: hash: \" + hash);\n\n\tObject transactions = blockInfo.get(\"transactions\");\n\tSystem.out.println(\"[async]getBlockByNumber: transactions: \" + transactions);\n\t\n\tObject timestamp = blockInfo.get(\"timestamp\");\n\tSystem.out.println(\"[async]getBlockByNumber: timestamp: \" + timestamp);\n\t\n\tObject miner = blockInfo.get(\"miner\");\n\tSystem.out.println(\"[async]getBlockByNumber: miner: \" + miner);\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  {\n    logsBloom=0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, \n    totalDifficulty=9599458746581, \n    receiptsRoot=0xcfad9c26ff4b1c03ec6fe24dc6766624fadb92641b900c07b90ba5559f59ba5c, \n    extraData=0xd583010003846765746885676f312e39856c696e7578000000000000000000009853fb8c79ba693ce77a9a74f3f063a5f4b430e6bcfcb59093ffc57c3772f77d6e17dd4192e75832f9f7d51439b372496db471326631f745bd12d649e29496ef01, \n    transactions=[\"0x4376314060f34286cfd35fb78f212a0fbc11e1a0946eaa6cc7ffaf145c31fbd7\",\n\t\t\"0x7ecf01d67b73dc40a7d39d7b919f9c9ca2f4ce9023f70401381c279d29cbc42b\",\n\t\t\"0xba0ceed770df4a6e330a643ec7f3b48096aed0ed393517caa81f06e748eb55b0\",\n\t\t\"0x2ff180f5fae10dbb87b29b0632e37e76fbe266e74022313b7fbc0728f4b37fa8\",\n\t\t\"0xa3084f20656d566ba1a60486a004d9f10515007b25200541f3291c431a3e1ef5\",\n\t\t\"0x72d448523cea9235851ac9bf37d47a270625fea8be5a9af7a526ade79847084a\",\n\t\t\"0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd\",\n\t\t\"0x8002c67669d420525b829b111a0947910acbce232ec68614e314cfc70c044690\",\n\t\t\"0x85be3736d8b8e2a97598255dc18012c4cd8f89803d846030db735e13cade0c68\",\n\t\t\"0x087b91305f336dbe8bf0972637fbed747629dca3e479920fcb432d1102670cad\",\n\t\t\"0x6b4c4c8ee8b1c3f7f2bf4ec58610be369907f0a9fbd3e79d686ca6c4edf7660a\",\n\t\t\"0x662cc0ded8ab79e85b74fc789c99607b66aef823d354c02a075fb79d18b50922\",\n\t\t\"0x3c4cd10545eab675c87c11026ad1dac5e8270297d2973f856b03855b8ea854a3\",\n\t\t\"0x1fbf01c7d32d24488979741f927fdb2e25ff8760f0a54673f0f139dd58af120d\",\n\t\t\"0xc0e52622158cb8b05dfcadb12d412e95a79b794b08ecbdf5bdf3ebf940a04106\"], \n    nonce=0x21fb481a9525e00f, \n    miner=0x4df69093933afc9ae4f665028ce56cd7008610fc,\n    difficulty=7068264, \n    gasLimit=4712388, \n    number=1362889, \n    gasUsed=315000, \n    uncles=[], \n    size=2299, \n    sha3Uncles=0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347, \n    transactionsRoot=0x6fc40a312dbd1854cf131342e87380625e11008d132dfbbb2b1ef7aa395a261a, \n    stateRoot=0x8b4411457f93d0694e6e05adc4ec51696f6b4e16ff65378784bc2ce5ff9e3949,\n    mixHash=0x36e505466927db2f555c83046f155c2b26fee896c0bfee8ecfe95ba5cebbf69a, \n    parentHash=0x505d86c91cfb51dcdf106f5c810627c2d2e44779339f10c143134ee20ac39224, \n    hash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, \n    timestamp=1533296957\n  }",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Blocks"
  },
  {
    "name": "getBlockNumber",
    "group": "Blocks",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getBlockNumber",
    "version": "0.5.0",
    "description": "<p>Get the current latest block number.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code> or <code>&quot;ETH&quot;</code> or <code>&quot;BTC&quot;</code>.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the latest block number, and its type is <code>BigInteger</code>. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getBlockNumber\",\"params\":{\"chainType\":\"WAN\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger blockNo = javaApi.getBlockNumber(\"WAN\").send();\t\t\t\t\t\t\t\n\tSystem.out.println(\"\\n[sync]getBlockNumber: blockNo: \" + blockNo);\n\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<BigInteger> future = javaApi.getBlockNumber(\"WAN\").sendAsync();\n\ntry {\t\t\t\t\t\t\n\tBigInteger blockNo = future.get();\t\t\t\t\n\tSystem.out.println(\"\\n[async]getBlockNumber: blockNo: \" + blockNo);\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "119858",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Blocks"
  },
  {
    "name": "getBlockTransactionCount",
    "group": "Blocks",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getBlockTransactionCount",
    "version": "0.5.0",
    "description": "<p>Get the number of transaction in a given block by block number or block hash on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code> or <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "blockHashOrBlockNumber",
            "description": "<p>The blockHash or the blockNumber you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the count of tx packaged in queried block, and its type is <code>BigInteger</code>. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getBlockTransactionCount\",\"params\":{\"chainType\":\"WAN\", \"blockNumber\":\"670731\"},\"id\":1}\nor\n{\"jsonrpc\":\"2.0\",\"method\":\"getBlockTransactionCount\",\"params\":{\"chainType\":\"WAN\", \"blockHash\":\"0xeb3b437d765d4da9210481c2dd612fa9d0c51e0e83120ee7f573ed9d6296e9a8\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger txCount = javaApi.getBlockTransactionCount(\"WAN\", \"1362889\").send();\t\t\t\t\t\t\t\n\tSystem.out.println(\"\\n[sync]getBlockTransactionCount: txCount: \" + txCount);\n\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<BigInteger> future = javaApi.getBlockTransactionCount(\"WAN\", \"0xeb3b437d765d4da9210481c2dd612fa9d0c51e0e83120ee7f573ed9d6296e9a8\").sendAsync();\n\ntry {\t\t\t\n\tBigInteger txCount = future.get();\t\t\t\t\n\tSystem.out.println(\"\\n[async]getBlockTransactionCount: txCount: \" + txCount);\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "13",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Blocks"
  },
  {
    "name": "callScFunc",
    "group": "Contracts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "callScFunc",
    "version": "0.5.0",
    "description": "<p>Call the specific public function of one contract on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "scAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>The name of the specific contract public function.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Object[]",
            "optional": false,
            "field": "args",
            "description": "<p>The parameters array a of the specific contract public function.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "JSONArray",
            "optional": false,
            "field": "abi",
            "description": "<p>The abi of the specific contract.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Object",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the value of contract function return, and its type is <code>Object</code>.Note: the value returned is different according specific sc function, and also may be no any returns. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"callScFunc\",\"params\":{\"chainType\": \"WAN\", \"scAddr\": \"0xddb09c3af165b83fa8f280225a6866786cc38971\", \"name\": \"getPriAddress\", \"args\": [], \"abi\": [/The Abi of the contracts/]},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nObject[] params = new Object[2];\nparams[0] = \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\";\nparams[1] = \"0x484a7cd86806eefaadccfc7b717edc45c04f99c0\";\n\ntry {\n\tObject rslt = javaApi.callScFunc(\"WAN\", \"0xddb09c3af165b83fa8f280225a6866786cc38971\", \"isStoremanGroup\", params, qlABI).send();\t*\t\n\tSystem.out.println(\"\\n[sync]callScFunc: isStoremanGroup: \" + rslt);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nObject[] params = new Object[2];\nparams[0] = \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\";\nparams[1] = \"0x484a7cd86806eefaadccfc7b717edc45c04f99c0\";\n\nCompletableFuture<String> rsltFuture = javaApi.callScFunc(\"WAN\", \"0xddb09c3af165b83fa8f280225a6866786cc38971\", \"isStoremanGroup\", params, qlABI).sendAsync();\n\ntry {\n\tObject rslt =  rsltFuture.get();\n\tSystem.out.println(\"\\n[async]callScFunc: isStoremanGroup: \" + rslt);\n\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\"true\"",
          "type": "Object"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Contracts"
  },
  {
    "name": "getScMap",
    "group": "Contracts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getScMap",
    "version": "0.5.0",
    "description": "<p>Get the specific public map value of one contract on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "scAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>The name of the specific contract public map.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "key",
            "description": "<p>The key of parameter of the specific contract public map.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "JSONArray",
            "optional": false,
            "field": "abi",
            "description": "<p>The abi of the specific contract.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Object",
            "optional": false,
            "field": "response",
            "description": "<p>The response is related value of queried map by the specific key, and its type is  <code>Object</code>, because it may be a simple type, also may be a array or map, etc, according to specific map define. please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getScMap\",\"params\":{\"chainType\": \"WAN\", \"scAddr\": \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", \"name\": \"mapDepositorInfo\", \"key\": \"\", \"abi\": [/The Abi of the contracts/]},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tObject rslt = javaApi.getScMap(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", \"mapDepositorInfo\", \"0xa19444ffba0478655e5e07fb2cc4eb260df74a22\",smgABI).send();\n\t\n\tSystem.out.println(\"\\n[sync]getScMap: rslt class: \" + rslt.getClass());\n\tSystem.out.println(\"\\n[sync]getScMap: rslt: \" + rslt);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<String> rsltFuture = javaApi.getScMap(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", \"mapDepositorInfo\", \"0xa19444ffba0478655e5e07fb2cc4eb260df74a22\",smgABI).sendAsync();\n\ntry {\n\tObject rslt =  rsltFuture.get();\n\n\tSystem.out.println(\"\\n[async]getScMap: rslt class: \" + rslt.getClass());\n\tSystem.out.println(\"\\n[async]getScMap: rslt: \" + rslt);\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "[\"9.5999882459922e+22\",\"1\",\"5e+21\",\"0\",\"0\",\"0\",false,\"1553277287\"]",
          "type": "Object"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Contracts"
  },
  {
    "name": "getScVar",
    "group": "Contracts",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getScVar",
    "version": "0.5.0",
    "description": "<p>Get the specific public parameter value of one contract on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "scAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>The name of the specific contract parameter.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "JSONArray",
            "optional": false,
            "field": "abi",
            "description": "<p>The abi of the specific contract.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Object",
            "optional": false,
            "field": "response",
            "description": "<p>The response type is <code>Object</code>, because it may be a simple type, also may be a array or map, etc, according to specific map define, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getScVar\",\"params\":{\"chainType\": \"WAN\", \"scAddr\": \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", \"name\": \"isReachedMaxDeposit\", \"abi\": [/The Abi of the contracts/]},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tObject var = javaApi.getScVar(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", \"isReachedMaxDeposit\",smgABI).send();\n\n\tSystem.out.println(\"\\n[sync]getScVar: var class: \" + var.getClass());\t\t\n\tSystem.out.println(\"\\n[sync]getScVar: var: \" + var);\t\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<String> rsltFuture = javaApi.getScVar(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", \"isReachedMaxDeposit\",smgABI).sendAsync();\n\ntry {\n\tObject var =  rsltFuture.get();\n\n\tSystem.out.println(\"\\n[async]getScVar: var class: \" + var.getClass());\n\tSystem.out.println(\"\\n[async]getScVar: var: \" + var);\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\"true\"",
          "type": "Object"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Contracts"
  },
  {
    "name": "getCoin2WanRatio",
    "group": "CrossChain",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getCoin2WanRatio",
    "version": "0.5.0",
    "description": "<p>Coin exchange ratio,such as 1 ETH to 880 WANs in ICO period, the precision is 10000, the ratio is 880*precision = 880,0000. The ratio would be changed according to the market value ratio periodically.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "crossChain",
            "description": "<p>The cross-chain native coin name that you want to search, should be <code>&quot;ETH&quot;</code> or <code>&quot;BTC&quot;</code>.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the coin exchange ratio, which type is <code>BigInteger</code>, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getCoin2WanRatio\",\"params\":{\"crossChain\":\"ETH\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:  ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger ratioValue = javaApi.getCoin2WanRatio(\"ETH\").send();\t\t\n\tSystem.out.println(\"\\n[sync]getCoin2WanRatio : \" + ratioValue);\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<BigInteger> ratioFuture = javaApi.getCoin2WanRatio(\"ETH\").sendAsync();\n\ntry {\n\tBigInteger ratioValue = ratioFuture.get();\t\t\t\n\tSystem.out.println(\"\\n[async]getCoin2WanRatio : \" + ratioValue);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "20",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "CrossChain"
  },
  {
    "name": "getRegTokens",
    "group": "CrossChain",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getRegTokens",
    "version": "0.5.0",
    "description": "<p>Get the information of tokens which are supported for cross-chain ability.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "crossChain",
            "description": "<p>The cross-chain name that you want to search, should be <code>&quot;ETH&quot;</code>.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the registered token info array which type is <code>List&lt;Map&lt;String,Object&gt;&gt;</code>, each element is related to a token info, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getRegTokens\",\"params\":{\"crossChain\":\"ETH\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:   ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tList tokenList = javaApi.getRegTokens(\"ETH\").send();\n\t\n\tfor(int i=0; i < tokenList.size(); i++) {\n\t\tMap<String, Object> tokenInfo = (Map<String, Object>) tokenList.get(i);\n\t\t\n\t\tObject tokenOrigAddr = tokenInfo.get(\"tokenOrigAddr\");\n\t\tSystem.out.println(\"\\n[sync] getRegTokens tokenOrigAddr: \" + tokenOrigAddr);\n\t\t\n\t\tObject tokenWanAddr = tokenInfo.get(\"tokenWanAddr\");\n\t\tSystem.out.println(\"[sync] getRegTokens tokenWanAddr: \" + tokenWanAddr);\n\t\t\n\t\tObject ratio = tokenInfo.get(\"ratio\");\n\t\tSystem.out.println(\"[sync] getRegTokens ratio: \" + ratio);\n\t\t\n\t\tObject minDeposit = tokenInfo.get(\"minDeposit\");\n\t\tSystem.out.println(\"[sync] getRegTokens minDeposit: \" + minDeposit);\t\t\t\t\n\t}\t\t\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<List> tokenFuture = javaApi.getRegTokens(\"ETH\").sendAsync();\n\ntry {\n\tList tokenList = tokenFuture.get();\t\t\t\n\tfor(int i=0; i < tokenList.size(); i++) {\n\t\tMap<String, Object> tokenInfo = (Map<String, Object>) tokenList.get(i);\n\t\t\n\t\tObject tokenOrigAddr = tokenInfo.get(\"tokenOrigAddr\");\n\t\tSystem.out.println(\"\\n[async] getRegTokens tokenOrigAddr: \" + tokenOrigAddr);\n\t\t\n\t\tObject tokenWanAddr = tokenInfo.get(\"tokenWanAddr\");\n\t\tSystem.out.println(\"[async] getRegTokens tokenWanAddr: \" + tokenWanAddr);\n\t\t\n\t\tObject ratio = tokenInfo.get(\"ratio\");\n\t\tSystem.out.println(\"[async] getRegTokens ratio: \" + ratio);\n\t\t\n\t\tObject minDeposit = tokenInfo.get(\"minDeposit\");\n\t\tSystem.out.println(\"[async] getRegTokens minDeposit: \" + minDeposit);\t\t\t\t\n\t}\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\t  [\n\t   {\n\t\ttokenWanAddr=0xa26a6698b95144ce27714d4657f153dc48e676d5, \n\t\ttokenOrigAddr=0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48, \n\t\torigHtlc=0xa4becceba748f8a2b0e6c2ed69e1079a9a5062ab, \n\t\twithdrawDelayTime=259200, \n\t\tminDeposit=10000000000000000000, \n\t\twanHtlc=0x71d23563729f81fc535cbb772e52660ca5be755e, \n\t\ttokenHash=0xd6f77cda01e07203755735d472c988bf495247447f02e1d6a04c00e43566e612, \n\t\tratio=50000\n\t   }, \n\t   {\n\t\t tokenWanAddr=0x79562c955dea8a801f057a386c9aa9f5d9bf0f92, \n\t\t tokenOrigAddr=0x0000000000085d4780b73119b644ae5ecd22b376, \n\t\t origHtlc=0xa4becceba748f8a2b0e6c2ed69e1079a9a5062ab, \n\t\t withdrawDelayTime=259200, \n\t\t minDeposit=10000000000000000000, \n\t\t wanHtlc=0x71d23563729f81fc535cbb772e52660ca5be755e, \n\t\t tokenHash=0xebd66c6bbe2f4326043633bbbc0df201670a27c5bebfa12ad0567f60f7aaa9f4, \n\t\t ratio=50000\n \t   }\n \t  ]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "CrossChain"
  },
  {
    "name": "getStoremanGroups",
    "group": "CrossChain",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getStoremanGroups",
    "version": "0.5.0",
    "description": "<p>Get the detailed cross-chain storemanGroup info for one cross-chain native coin, like the quota, etc.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "crossChain",
            "description": "<p>The cross-chain name that you want to search, should be <code>&quot;ETH&quot;</code> or <code>&quot;BTC&quot;</code>.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the registered storemanGroup array, which type is <code>List&lt;Map&lt;String,Object&gt;&gt;</code>, each element is related to a storemanGroup info, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getStoremanGroups\",\"params\":{\"crossChain\":\"ETH\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:   ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tList smgList = javaApi.getStoremanGroups(\"ETH\").send();\n\t\n\tfor(int i=0; i < smgList.size(); i++) {\n\t\tMap<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);\n\t\t\n\t\tObject wanAddress = tokenInfo.get(\"wanAddress\");\n\t\tSystem.out.println(\"\\n[sync] getStoremanGroups wanAddress: \" + wanAddress);\n\t\t\n\t\tObject ethAddress = tokenInfo.get(\"ethAddress\");\n\t\tSystem.out.println(\"[sync] getStoremanGroups ethAddress: \" + ethAddress);\n\t\t\n\t\tObject txFeeRatio = tokenInfo.get(\"txFeeRatio\");\n\t\tSystem.out.println(\"[sync] getStoremanGroups txFeeRatio: \" + txFeeRatio);\n\t\t\n\t\tObject deposit = tokenInfo.get(\"deposit\");\n\t\tSystem.out.println(\"[sync] getStoremanGroups deposit: \" + deposit);\t\n\t\t\n\t\tObject receivable = tokenInfo.get(\"receivable\");\n\t\tSystem.out.println(\"[sync] getStoremanGroups receivable: \" + receivable);\t\t\t\n\t\t\n\t\tObject payable = tokenInfo.get(\"payable\");\n\t\tSystem.out.println(\"[sync] getStoremanGroups payable: \" + payable);\t\t\t\t\t\n\t\t\n\t\tObject debt = tokenInfo.get(\"debt\");\n\t\tSystem.out.println(\"[sync] getStoremanGroups debt: \" + debt);\t\t\t\t\t\t\n\t}\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<List> smgFuture = javaApi.getStoremanGroups(\"ETH\").sendAsync();\n\ntry {\n\t\n\tList smgList = smgFuture.get();\t\n\tfor(int i=0; i < smgList.size(); i++) {\n\t\tMap<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);\n\t\t\n\t\tObject wanAddress = tokenInfo.get(\"wanAddress\");\n\t\tSystem.out.println(\"\\n[async] getStoremanGroups wanAddress: \" + wanAddress);\n\t\t\n\t\tObject ethAddress = tokenInfo.get(\"ethAddress\");\n\t\tSystem.out.println(\"[async] getStoremanGroups ethAddress: \" + ethAddress);\n\t\t\n\t\tObject txFeeRatio = tokenInfo.get(\"txFeeRatio\");\n\t\tSystem.out.println(\"[async] getStoremanGroups txFeeRatio: \" + txFeeRatio);\n\t\t\n\t\tObject deposit = tokenInfo.get(\"deposit\");\n\t\tSystem.out.println(\"[async] getStoremanGroups deposit: \" + deposit);\t\n\t\t\n\t\tObject receivable = tokenInfo.get(\"receivable\");\n\t\tSystem.out.println(\"[async] getStoremanGroups receivable: \" + receivable);\t\t\t\n\t\t\n\t\tObject payable = tokenInfo.get(\"payable\");\n\t\tSystem.out.println(\"[async] getStoremanGroups payable: \" + payable);\t\t\t\t\t\n\t\t\n\t\tObject debt = tokenInfo.get(\"debt\");\n\t\tSystem.out.println(\"[async] getStoremanGroups debt: \" + debt);\t\t\t\t\t\t\n\t}\t\t\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "[{\n\twanAddress=0x484a7cd86806eefaadccfc7b717edc45c04f99c0, \n\ttxFeeRatio=10, \n\tpayable=11003000000000000, \n\tquota=1000000000000000000000, \n\tdeposit=400000000000000000000000, \n\toutboundQuota=874425136982471134, \n\treceivable=0, \n\tethAddress=0x45aff9950f2b174028a88ce8648c49b6ae1e7765, \n\tinboundQuota=999114571863017528866, \n\tdebt=885428136982471134\n}]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "CrossChain"
  },
  {
    "name": "getToken2WanRatio",
    "group": "CrossChain",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getToken2WanRatio",
    "version": "0.5.0",
    "description": "<p>Token exchange ratio,such as 1 token to 880 WANs, the precision is 10000, the ratio is 880*precision = 880,0000. The ratio would be changed accoring to the market value ratio periodically.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "crossChain",
            "description": "<p>The cross-chain name that you want to search, should be <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "tokenScAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response is the queried token exchange ratio, which type is <code>BigInteger</code>, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getToken2WanRatio\",\"params\":{\"crossChain\":\"ETH\", \"tokenScAddr\":\"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\"},\"id\":1}",
          "type": "string"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:  ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger ratioValue = javaApi.getToken2WanRatio(\"ETH\", \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\").send();\t\t\n\tSystem.out.println(\"\\n[sync]getCoin2WanRatio : \" + ratioValue);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<BigInteger> ratioFuture = javaApi.getToken2WanRatio(\"ETH\", \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\").sendAsync();\n\ntry {\t\n\tBigInteger ratioValue = ratioFuture.get();\t\t\t\n\tSystem.out.println(\"\\n[async]getCoin2WanRatio : \" + ratioValue);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "3000",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "CrossChain"
  },
  {
    "name": "getTokenStoremanGroups",
    "group": "CrossChain",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTokenStoremanGroups",
    "version": "0.5.0",
    "description": "<p>Get the detail cross-chain storemanGroup info for one specific token contract, like the quota, etc.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "crossChain",
            "description": "<p>The cross-chain name that you want to search, should be <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "tokenScAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>List&lt;Map&lt;String, Object&gt;&gt;</code>, each element is queried storemanGroup info from which you can get related property, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTokenStoremanGroups\",\"params\":{\"crossChain\":\"ETH\", \"tokenScAddr\":\"0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:    ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tList smgList = javaApi.getTokenStoremanGroups(\"ETH\", \"0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814\").send();\n\t\n\tfor(int i=0; i < smgList.size(); i++) {\n\t\tMap<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);\n\t\t\n\t\tObject tokenOrigAddr = tokenInfo.get(\"tokenOrigAddr\");\n\t\tSystem.out.println(\"\\n[sync] getTokenStoremanGroups tokenOrigAddr: \" + tokenOrigAddr);\n\t\t\n\t\tObject smgWanAddr = tokenInfo.get(\"smgWanAddr\");\n\t\tSystem.out.println(\"[sync] getTokenStoremanGroups smgWanAddr: \" + smgWanAddr);\n\t\t\n\t\tObject smgOrigAddr = tokenInfo.get(\"smgOrigAddr\");\n\t\tSystem.out.println(\"[sync] getTokenStoremanGroups smgOrigAddr: \" + smgOrigAddr);\n\t\t\n\t\tObject wanDeposit = tokenInfo.get(\"wanDeposit\");\n\t\tSystem.out.println(\"[sync] getTokenStoremanGroups wanDeposit: \" + wanDeposit);\t\n\t\t\n\t\tObject quota = tokenInfo.get(\"quota\");\n\t\tSystem.out.println(\"[sync] getTokenStoremanGroups quota: \" + quota);\t\n\t\t\n\t\tObject receivable = tokenInfo.get(\"receivable\");\n\t\tSystem.out.println(\"[sync] getTokenStoremanGroups receivable: \" + receivable);\t\t\t\n\t\t\n\t\tObject payable = tokenInfo.get(\"payable\");\n\t\tSystem.out.println(\"[sync] getTokenStoremanGroups payable: \" + payable);\t\t\t\t\t\n\t\t\n\t\tObject debt = tokenInfo.get(\"debt\");\n\t\tSystem.out.println(\"[sync] getTokenStoremanGroups debt: \" + debt);\t\t\t\t\t\t\n\t}\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<List> smgFuture = javaApi.getTokenStoremanGroups(\"ETH\", \"0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814\").sendAsync();\n\ntry {\n\tList smgList = smgFuture.get();\t\n\tfor(int i=0; i < smgList.size(); i++) {\n\t\tMap<String, Object> tokenInfo = (Map<String, Object>) smgList.get(i);\n\t\t\n\t\tObject tokenOrigAddr = tokenInfo.get(\"tokenOrigAddr\");\n\t\tSystem.out.println(\"\\n[async] getTokenStoremanGroups tokenOrigAddr: \" + tokenOrigAddr);\n\t\t\n\t\tObject smgWanAddr = tokenInfo.get(\"smgWanAddr\");\n\t\tSystem.out.println(\"[async] getTokenStoremanGroups smgWanAddr: \" + smgWanAddr);\n\t\t\n\t\tObject smgOrigAddr = tokenInfo.get(\"smgOrigAddr\");\n\t\tSystem.out.println(\"[async] getTokenStoremanGroups smgOrigAddr: \" + smgOrigAddr);\n\t\t\n\t\tObject wanDeposit = tokenInfo.get(\"wanDeposit\");\n\t\tSystem.out.println(\"[async] getTokenStoremanGroups wanDeposit: \" + wanDeposit);\t\n\t\t\n\t\tObject quota = tokenInfo.get(\"quota\");\n\t\tSystem.out.println(\"[async] getTokenStoremanGroups quota: \" + quota);\t\n\t\t\n\t\tObject receivable = tokenInfo.get(\"receivable\");\n\t\tSystem.out.println(\"[async] getTokenStoremanGroups receivable: \" + receivable);\t\t\t\n\t\t\n\t\tObject payable = tokenInfo.get(\"payable\");\n\t\tSystem.out.println(\"[async] getTokenStoremanGroups payable: \" + payable);\t\t\t\t\t\n\t\t\n\t\tObject debt = tokenInfo.get(\"debt\");\n\t\tSystem.out.println(\"[async] getTokenStoremanGroups debt: \" + debt);\t\t\t\t\t\t\t\t\n\t}\t\t\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": " [{\n\ttokenOrigAddr=0xcdcfc0f66c522fd086a1b725ea3c0eeb9f9e8814, \n\tsmgWanAddr=0x484a7cd86806eefaadccfc7b717edc45c04f99c0, \n\ttxFeeRatio=10, \n\tpayable=1800003000000000000, \n\tquota=10000000000000000000000, \n\twanDeposit=1000000000000000000000, \n\toutboundQuota=2380002000000000000, \n\tsmgOrigAddr=0x45aff9950f2b174028a88ce8648c49b6ae1e7765, \n\treceivable=0, \n\tinboundQuota=9995819995000000000000, \n\tdebt=4180005000000000000\n }]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "CrossChain"
  },
  {
    "name": "getScEvent",
    "group": "Events",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getScEvent",
    "version": "0.5.0",
    "description": "<p>Get smart contract event log via topics.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The contract address.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String[]",
            "optional": false,
            "field": "topics",
            "description": "<p>An array of string values which must each appear in the log entries. The order is important, if you want to leave topics out use null, e.g. [null, '0x00...'].</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>List&lt;Map&lt;String, Object&gt;&gt;</code>, each element is queried sc event object and you can get related property, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getScEvent\",\"params\":{\"chainType\":\"WAN\", \"address\": \"0xda5b90dc89be59365ec44f3f2d7af8b6700d1167\", \"topics\": [\"0xa4345d0839b39e5a6622a55c68bd8f83ac8a68fad252a8363a2c09dbaf85c793\", \"0x0000000000000000000000000000000000000000000000000000000000000000\"]},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] topics =  new String[2];\ntopics[0] = \"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\";\ntopics[1] = \"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2\";\n\ntry {\n\tList eventList = javaApi.getScEvent(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", topics).send();\n\t\n\tfor(int i=0; i < eventList.size(); i++) {\n\t\tMap<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);\n\t\t\n\t\tObject blockNumber = eventInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"\\n[sync] getScEvent blockNumber: \" + blockNumber);\n\t\t\n\t\tObject transactionHash = eventInfo.get(\"transactionHash\");\n\t\tSystem.out.println(\"[sync] getScEvent transactionHash: \" + transactionHash);\n\t\t\n\t\tObject address = eventInfo.get(\"address\");\n\t\tSystem.out.println(\"[sync] getScEvent address: \" + address);\n\t\t\n\t\tObject data = eventInfo.get(\"data\");\n\t\tSystem.out.println(\"[sync] getScEvent data: \" + data);\t\t\t\t\n\t}\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] topics =  new String[2];\ntopics[0] = \"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\";\ntopics[1] = \"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2\";\n\nCompletableFuture<List> rsltFuture = javaApi.getScEvent(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", topics).sendAsync();\n\ntry {\t\n\tList eventList =  rsltFuture.get();\t\t\n\tfor(int i=0; i < eventList.size(); i++) {\n\t\tMap<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);\n\t\t\n\t\tObject blockNumber = eventInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"\\n[async] getScEvent blockNumber: \" + blockNumber);\n\t\t\n\t\tObject transactionHash = eventInfo.get(\"transactionHash\");\n\t\tSystem.out.println(\"[async] getScEvent transactionHash: \" + transactionHash);\n\t\t\n\t\tObject address = eventInfo.get(\"address\");\n\t\tSystem.out.println(\"[async] getScEvent address: \" + address);\n\t\t\n\t\tObject data = eventInfo.get(\"data\");\n\t\tSystem.out.println(\"[async] getScEvent data: \" + data);\t\t\t\t\n\t}\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  [{\n\tblockHash=0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503, \n\taddress=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, \n\tlogIndex=0, \n\tdata=0x00000000000000000000000000000000000000000000010ff5e03b33183c6000000000000000000000000000000000000000000000000000000000005c8aeb0a00000000000000000000000000000000000000000000010ff5e03b33183c600000000000000000000000000000000000000000000000010ff5e03b33183c6000, \n\tremoved=false, \n\ttopics=[\"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\",\n\t\"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2\"], \n\tblockNumber=2887621, \n\ttransactionIndex=0, \n\ttransactionHash=0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35\n  }, {\n\tblockHash=0x6691d2ec8d521f548e5a1aefbca08d607c95e719839883dc8d190e1a3259a4f6, \n\taddress=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, \n\tlogIndex=0, \n\tdata=0x00000000000000000000000000000000000000000000000c8db29cc7e0bf1800000000000000000000000000000000000000000000000000000000005ca0313d00000000000000000000000000000000000000000000011c8392d7faf8fb7800000000000000000000000000000000000000000000035983945057efa2cdda00, \n\tremoved=false, \n\ttopics=[\n\t\"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\",\n\t\"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2\"],\n\tblockNumber=2997739, \n\ttransactionIndex=0, \n\ttransactionHash=0x7982fef5f1ea21ad0c15d5bf164e29926e1ba459b14bd0dd3ee7f96906c34e48\n  }]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Events"
  },
  {
    "name": "monitorEvent",
    "group": "Events",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "monitorEvent",
    "version": "0.5.0",
    "description": "<p>Subscribe to a smart contract event monitor. The server will push the event to the subscriber when the event occurs.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The contract address.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String[]",
            "optional": false,
            "field": "topics",
            "description": "<p>Array of values which must each appear in the log entries. The order is important, if you want to leave topics out use null, e.g. [null, '0x00...'].</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>List&lt;Map&lt;String, Object&gt;&gt;</code>, each element is queried sc event object and you can get related property, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"monitorEvent\",\"params\":{\"chainType\":\"WAN\", \"address\": \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", \"topics\": [\"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\"]},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] topicsMon =  new String[1];\ntopicsMon[0] = \"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\";\n\ntry {\n\tList eventList = javaApi.monitorEvent(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", topicsMon).send();\n\t\n\tfor(int i=0; i < eventList.size(); i++) {\n\t\tMap<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);\n\t\t\n\t\tObject blockNumber = eventInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"\\n[sync] monitorEvent blockNumber: \" + blockNumber);\n\t\t\n\t\tObject transactionHash = eventInfo.get(\"transactionHash\");\n\t\tSystem.out.println(\"[sync] monitorEvent transactionHash: \" + transactionHash);\n\t\t\n\t\tObject address = eventInfo.get(\"address\");\n\t\tSystem.out.println(\"[sync] monitorEvent address: \" + address);\n\t\t\n\t\tObject data = eventInfo.get(\"data\");\n\t\tSystem.out.println(\"[sync] monitorEvent data: \" + data);\t\t\t\t\n\t}\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] topicsMon =  new String[1];\ntopicsMon[0] = \"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\";\n\nCompletableFuture<List> rsltFuture = javaApi.monitorEvent(\"WAN\", \"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\", topicsMon).sendAsync();\n\ntry {\n\tList eventList =  rsltFuture.get();\t\t\n\tfor(int i=0; i < eventList.size(); i++) {\n\t\tMap<String, Object> eventInfo = (Map<String, Object>) eventList.get(i);\n\t\t\n\t\tObject blockNumber = eventInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"\\n[async] monitorEvent blockNumber: \" + blockNumber);\n\t\t\n\t\tObject transactionHash = eventInfo.get(\"transactionHash\");\n\t\tSystem.out.println(\"[async] monitorEvent transactionHash: \" + transactionHash);\n\t\t\n\t\tObject address = eventInfo.get(\"address\");\n\t\tSystem.out.println(\"[async] monitorEvent address: \" + address);\n\t\t\n\t\tObject data = eventInfo.get(\"data\");\n\t\tSystem.out.println(\"[async] monitorEvent data: \" + data);\t\t\t\t\n\t}\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  [{\n\tblockHash=0x6691d2ec8d521f548e5a1aefbca08d607c95e719839883dc8d190e1a3259a4f6, \n\taddress=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, \n\tlogIndex=0, \n\tdata=0x00000000000000000000000000000000000000000000000c8db29cc7e0bf1800000000000000000000000000000000000000000000000000000000005ca0313d00000000000000000000000000000000000000000000011c8392d7faf8fb7800000000000000000000000000000000000000000000035983945057efa2cdda00, \n\tremoved=false, \n\ttopics=[\n\t\"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\",\n\t\"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2\"],\n\tblockNumber=2997739, \n\ttransactionIndex=0, \n\ttransactionHash=0x7982fef5f1ea21ad0c15d5bf164e29926e1ba459b14bd0dd3ee7f96906c34e48\n  }]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Events"
  },
  {
    "name": "getGasPrice",
    "group": "Status",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getGasPrice",
    "version": "0.5.0",
    "description": "<p>Get a bigNumber of the current gas price in wei.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "String",
            "optional": false,
            "field": "response",
            "description": "<p>The response data is the current gas price, which type is <code>String</code>, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getGasPrice\",\"params\":{\"chainType\":\"WAN\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tString rslt = javaApi.getGasPrice(\"WAN\").send();\t\t\t\n\tSystem.out.println(\"\\n[sync]getGasPrice : \" + rslt);\n\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<String> rsltFuture = javaApi.getGasPrice(\"WAN\").sendAsync();\n\ntry {\n\tString rslt = rsltFuture.get(); \n\tSystem.out.println(\"\\n[async]getGasPrice : \" + rslt);\n     \n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\"180000000000\"",
          "type": "String"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Status"
  },
  {
    "name": "getMultiTokenBalance",
    "group": "Tokens",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getMultiTokenBalance",
    "version": "0.5.0",
    "description": "<p>Gets token balance for multiple addresses of specified token on Wanchain in a single call.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>, default: <code>&quot;WAN&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String[]",
            "optional": false,
            "field": "addressArray",
            "description": "<p>An array of addresses being queried.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "tokenScAddr",
            "description": "<p>The token contract address for specified token. I.e., If chainType is <code>&quot;WAN&quot;</code>, it should be the token address for <code>&quot;WETH&quot;</code> or <code>&quot;WBTC&quot;</code>.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>Map&lt;String, BigInteger&gt;</code>, you can get the balance of the queried token by token address, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getMultiTokenBalance\",\"params\":{\"address\": [\"0xfac95c16da814d24cc64b3186348afecf527324f\",\"0xfac95c16da814d24cc64b3186348afecf527324e\"],\"tokenScAddr\" : \"0x63eed4943abaac5f43f657d8eec098ca6d6a546e\"},\"id\":1}",
          "type": "string"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] accoutArray = new String[3];\naccoutArray[0] = \"0x13827c4200a764fb6f193a3f6e33b9efa3b4073d\";\naccoutArray[1] = \"0xc7a04e264c5a0fe542ddccbddc2df6d31cd6b22a\";\naccoutArray[2] = \"0x860306e69efcb22852421608ae1f546e222c948c\";\n\ntry {\n\tMap<String, BigInteger> tokenBalanceArray = javaApi.getMultiTokenBalance(\"ETH\", accoutArray, \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\").send();\n\t\t\t\t\t\n\tBigInteger accoutA = tokenBalanceArray.get(accoutArray[0]);\n\tSystem.out.println(\"\\n[sync]getMultiTokenBalance: accoutA: \" + accoutA);\n\t\n\tBigInteger accoutB = tokenBalanceArray.get(accoutArray[1]);\n\tSystem.out.println(\"[sync]getMultiTokenBalance: accoutB: \" + accoutB);\n\t\n\tBigInteger accoutC = tokenBalanceArray.get(accoutArray[2]);\n\tSystem.out.println(\"[sync]getMultiTokenBalance: accoutC: \" + accoutC);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] accoutArray = new String[3];\naccoutArray[0] = \"0x13827c4200a764fb6f193a3f6e33b9efa3b4073d\";\naccoutArray[1] = \"0xc7a04e264c5a0fe542ddccbddc2df6d31cd6b22a\";\naccoutArray[2] = \"0x860306e69efcb22852421608ae1f546e222c948c\";\n\nCompletableFuture<Map<String, BigInteger>> tokenFuture = javaApi.getMultiTokenBalance(\"ETH\", accoutArray, \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\").sendAsync();\n\ntry {\n\tMap<String, BigInteger> tokenBalanceArray = tokenFuture.get();\n\n\tBigInteger accoutA = tokenBalanceArray.get(accoutArray[0]);\n\tSystem.out.println(\"\\n[async]getMultiTokenBalance: accoutA: \" + accoutA);\n\t\n\tBigInteger accoutB = tokenBalanceArray.get(accoutArray[1]);\n\tSystem.out.println(\"[async]getMultiTokenBalance: accoutB: \" + accoutB);\n\t\n\tBigInteger accoutC = tokenBalanceArray.get(accoutArray[2]);\n\tSystem.out.println(\"[async]getMultiTokenBalance: accoutC: \" + accoutC);\t\t\t\t\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  {\n\t0xc7a04e264c5a0fe542ddccbddc2df6d31cd6b22a=5600000000000000000, \n\t0x860306e69efcb22852421608ae1f546e222c948c=999991000000000000, \n\t0x13827c4200a764fb6f193a3f6e33b9efa3b4073d=0\n  }",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Tokens"
  },
  {
    "name": "getMultiTokenInfo",
    "group": "Tokens",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getMultiTokenInfo",
    "version": "0.5.0",
    "description": "<p>Get the information of multiple tokens.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String[]",
            "optional": false,
            "field": "tokenScAddrArray",
            "description": "<p>The token address array for the certain token that you want to find.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>Map&lt;String, Object&gt;</code>, you can get the detail info of queried token, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getMultiTokenInfo\",\"params\":{\"tokenScAddrArray\":[\"0xc5bc855056d99ef4bda0a4ae937065315e2ae11a\",\"0x7017500899433272b4088afe34c04d742d0ce7df\"],\"chainType\":\"ETH\"},\"id\":1}",
          "type": "string"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] tokenArray = new String[3];\ntokenArray[0] = \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\";\ntokenArray[1] = \"0x514910771af9ca656af840dff83e8264ecf986ca\";\ntokenArray[2] = \"0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48\";\n    \ntry {\n\tMap<String, Object> tokenInfo = javaApi.getMultiTokenInfo(\"ETH\", tokenArray).send();\n\t\t\t\t\t\n\tObject tokenA = tokenInfo.get(tokenArray[0]);\n\tSystem.out.println(\"\\n[sync]getMultiTokenInfo: tokenA: \" + tokenA);\n\t\n\tObject tokenB = tokenInfo.get(tokenArray[1]);\n\tSystem.out.println(\"[sync]getMultiTokenInfo: tokenB: \" + tokenB);\n\t\n\tObject tokenC = tokenInfo.get(tokenArray[2]);\n\tSystem.out.println(\"[sync]getMultiTokenInfo: tokenC: \" + tokenC);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nString[] tokenArray = new String[3];\ntokenArray[0] = \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\";\ntokenArray[1] = \"0x514910771af9ca656af840dff83e8264ecf986ca\";\ntokenArray[2] = \"0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48\";\n\nCompletableFuture<Map<String, Object>> tokenFuture = javaApi.getMultiTokenInfo(\"ETH\", tokenArray).sendAsync();\n\ntry {\t\n\tMap<String, Object> tokenInfo = tokenFuture.get();\n\t\n\tObject tokenA = tokenInfo.get(tokenArray[0]);\n\tSystem.out.println(\"\\n[async]getMultiTokenInfo: tokenA: \" + tokenA);\n\t\n\tObject tokenB = tokenInfo.get(tokenArray[1]);\n\tSystem.out.println(\"[async]getMultiTokenInfo: tokenB: \" + tokenB);\n\t\n\tObject tokenC = tokenInfo.get(tokenArray[2]);\n\tSystem.out.println(\"[async]getMultiTokenInfo: tokenC: \" + tokenC);\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  {\n\t0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48={\"symbol\":\"USDC\",\"decimals\":\"6\"}, \n\t0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359={\"symbol\":\"DAI\",\"decimals\":\"18\"}, \n\t0x514910771af9ca656af840dff83e8264ecf986ca={\"symbol\":\"LINK\",\"decimals\":\"18\"}\n  }",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Tokens"
  },
  {
    "name": "getTokenAllowance",
    "group": "Tokens",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTokenAllowance",
    "version": "0.5.0",
    "description": "<p>Get the token allowance for one specific account on one contract for one specific spender account on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "tokenScAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "ownerAddr",
            "description": "<p>The owner address on the certain contract.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "spenderAddr",
            "description": "<p>The spender address on the certain contract.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>BigInteger</code>, you can get the allowance of queried token , please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTokenAllowance\",\"params\":{\"chainType\":\"ETH\", \"tokenScAddr\":\"0xc5bc855056d99ef4bda0a4ae937065315e2ae11a\", \"ownerAddr\":\"0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8\", \"spenderAddr\":\"0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger tokenAllowance = javaApi.getTokenAllowance(\"ETH\", \"0xc5bc855056d99ef4bda0a4ae937065315e2ae11a\", \"0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8\", \"0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1\").send();\n\tSystem.out.println(\"\\n[sync]getTokenAllowance: tokenAllowance: \" + tokenAllowance);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:\t",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<BigInteger> tokenFuture = javaApi.getTokenAllowance(\"ETH\", \"0xc5bc855056d99ef4bda0a4ae937065315e2ae11a\", \"0xc27ecd85faa4ae80bf5e28daf91b605db7be1ba8\", \"0xcdc96fea7e2a6ce584df5dc22d9211e53a5b18b1\").sendAsync();\n\t\ntry {\n\tBigInteger tokenAllowance = tokenFuture.get();\n\tSystem.out.println(\"\\n[async]getTokenAllowance: tokenAllowance: \" + tokenAllowance);\t\t\t\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "fields": {
        "Return Parameter": [
          {
            "group": "Return Parameter",
            "type": "BigInteger",
            "optional": false,
            "field": "The",
            "description": "<p>queried token allowance</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Successful Response",
          "content": "999999999999980000000000000",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Tokens"
  },
  {
    "name": "getTokenBalance",
    "group": "Tokens",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTokenBalance",
    "version": "0.5.0",
    "description": "<p>Get token balance for a single address of certain token on Wanchain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>, default: <code>&quot;WAN&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The account being queried.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "tokenScAddr",
            "description": "<p>The token contract address for specified token. I.e., If chainType is <code>&quot;WAN&quot;</code>, it should be the token address for <code>&quot;WETH&quot;</code> or <code>&quot;WBTC&quot;</code>.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>BigInteger</code>, you can get the balance of queried token from it, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTokenBalance\",\"params\":{\"address\": \"0x2cc79fa3b80c5b9b02051facd02478ea88a78e2c\",\"tokenScAddr\" : \"0x63eed4943abaac5f43f657d8eec098ca6d6a546e\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger tokenBalance = javaApi.getTokenBalance(\"ETH\", accoutArray[1], \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\").send();\t*\t\t\t\t\t\t\n\tSystem.out.println(\"\\n[sync]getTokenBalance: tokenBalance: \" + tokenBalance);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<BigInteger> tokenFuture = javaApi.getTokenBalance(\"ETH\", accoutArray[2], \"0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359\").sendAsync();\n\t\t\t\ntry {\n\tBigInteger tokenBalance = tokenFuture.get();\n\tSystem.out.println(\"\\n[async]getTokenBalance: accout: \" + tokenBalance);\t\t\t\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "999991000000000000",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Tokens"
  },
  {
    "name": "getTokenInfo",
    "group": "Tokens",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTokenInfo",
    "version": "0.5.0",
    "description": "<p>Get the info of token contract, like symbol and decimals, on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "tokenScAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>Map</code>, you can get related property value of queried token from it, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTokenInfo\",\"params\":{\"chainType\":\"ETH\", \"tokenScAddr\":\"0xc5bc855056d99ef4bda0a4ae937065315e2ae11a\"},\"id\":1}",
          "type": "string"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:  ",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n  \ntry {\n\tMap<String, Object> tokenInfo = javaApi.getTokenInfo(\"ETH\", tokenArray[0]).send();\n\t\t\t\t\t\n\tObject symbol = tokenInfo.get(\"symbol\");\n\tSystem.out.println(\"\\n[sync]getTokenInfo: symbol: \" + symbol);\n\t\n\tObject decimals = tokenInfo.get(\"decimals\");\n\tSystem.out.println(\"[sync]getTokenInfo: decimals: \" + decimals);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<Map<String, Object>> tokenFuture = javaApi.getTokenInfo(\"ETH\", tokenArray[1]).sendAsync();\n\ntry {\n\tMap<String, Object> tokenInfo = tokenFuture.get();\n\tObject symbol = tokenInfo.get(\"symbol\");\n\tSystem.out.println(\"\\n[async]getTokenInfo: symbol: \" + symbol);\n\t\n\tObject decimals = tokenInfo.get(\"decimals\");\n\tSystem.out.println(\"[async]getTokenInfo: decimals: \" + decimals);\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "{symbol=DAI, decimals=18}",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Tokens"
  },
  {
    "name": "getTokenSupply",
    "group": "Tokens",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTokenSupply",
    "version": "0.5.0",
    "description": "<p>Get total amount of certain token on Wanchain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>. <br><strong>Note</strong> : In case of <code>chainType</code> set to <code>null</code>, it will take default value <code>&quot;WAN&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "tokenScAddr",
            "description": "<p>The token contract address for the specified token.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "BigInteger",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>BigInteger</code>, you can get queried token supply, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTokenSupply\",\"params\":{\"tokenScAddr\" : \"0x63eed4943abaac5f43f657d8eec098ca6d6a546e\"},\"id\":1}\nor\n{\"jsonrpc\":\"2.0\",\"method\":\"getTokenSupply\",\"params\":{\"chainType\":\"WAN\", \"tokenScAddr\" : \"0x63eed4943abaac5f43f657d8eec098ca6d6a546e\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tBigInteger tokenSupply = javaApi.getTokenSupply(\"ETH\", tokenArray[1]).send();\t\t\t\t\t\t\n\tSystem.out.println(\"\\n[sync]getTokenSupply: \" + tokenSupply);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<BigInteger> tsFuture = javaApi.getTokenSupply(\"ETH\", tokenArray[2]).sendAsync();\n\ntry {\n\tBigInteger tokenSupply = tsFuture.get();\n\tSystem.out.println(\"\\n[async]getTokenSupply: \" + tokenSupply);\t\t\t\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "30000000000000000000000",
          "type": "BigInteger"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Tokens"
  },
  {
    "name": "getTransByAddress",
    "group": "Transactions",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTransByAddress",
    "version": "0.5.0",
    "description": "<p>Get transaction information via the specified address on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The account's address that you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>List&lt;Map&lt;String,Object&gt;&gt;</code>, each element of list is one tx info which is formed as <code>&lt;Map&lt;String,Object&gt;</code>. you can get queried tx info, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTransByAddress\",\"params\":{\"chainType\":\"WAN\", \"address\":\"0xbb9003ca8226f411811dd16a3f1a2c1b3f71825d\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tList txList = javaApi.getTransByAddress(\"WAN\", \"0x731d8fdc53039f07efd87be3c387e747da92911f\").send();\n\t\n\tfor(int i=0; i<txList.size(); i++) {\n\t\tMap<String, Object> txInfo =  (Map<String, Object>) txList.get(i);\n\t\t\n\t\tObject blockHash = txInfo.get(\"blockHash\");\n\t\tSystem.out.println(\"\\n[sync]getTransByAddress: blockHash: \" + blockHash);\n\t\t\t\n\t\tObject blockNumber = txInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"[sync]getTransByAddress: blockNumber: \" + blockNumber);\n\n\t\tObject from = txInfo.get(\"from\");\n\t\tSystem.out.println(\"[sync]getTransByAddress: from: \" + from);\n\t\t\n\t\tObject to = txInfo.get(\"to\");\n\t\tSystem.out.println(\"[sync]getTransByAddress: to: : \" + to);\n\t\t\n\t\tObject value = txInfo.get(\"value\");\n\t\tSystem.out.println(\"[sync]getTransByAddress: value: : \" + value);\t\t\t\t\t\t\n\t}\t\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:\t",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<List> txListFuture = javaApi.getTransByAddress(\"WAN\", \"0x731d8fdc53039f07efd87be3c387e747da92911f\").sendAsync();\n\t\ntry {\n\tList txList = txListFuture.get();\n\n\tfor(int i=0; i<txList.size(); i++) {\n\t\tMap<String, Object> txInfo =  (Map<String, Object>) txList.get(i);\n\t\t\n\t\tObject blockHash = txInfo.get(\"blockHash\");\n\t\tSystem.out.println(\"\\n[async]getTransByAddress: blockHash: \" + blockHash);\n\t\t\t\n\t\tObject blockNumber = txInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"[async]getTransByAddress: blockNumber: \" + blockNumber);\n\n\t\tObject from = txInfo.get(\"from\");\n\t\tSystem.out.println(\"[async]getTransByAddress: from: \" + from);\n\t\t\n\t\tObject to = txInfo.get(\"to\");\n\t\tSystem.out.println(\"[async]getTransByAddress: to: : \" + to);\n\t\t\n\t\tObject value = txInfo.get(\"value\");\n\t\tSystem.out.println(\"[async]getTransByAddress: value: : \" + value);\t\t\t\t\t\n\t}\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  [{\n\tblockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, \n\ttransactionIndex=6, \n\ttxType=0x1, \n\tnonce=0, \n\tinput=0x, \n\tr=0x7dfe37a3835ca20fbfe44e7baefdda60e7105dc05dc1ff75a3ef39e60d3e3b8a, \n\ts=0x36368d24661e3f4cdc210c6d427ec54f7b89549eb88319053c65edd40651cd04, \n\tv=0x26, \n\tblockNumber=1362889, \n\tgas=21000, \n\tfrom=0x731d8fdc53039f07efd87be3c387e747da92911f, \n\tto=0x731bd7289b4191706b00f6f1877662b5e8697e82,\n\tvalue=216890000000000000000, \n\thash=0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd, \n\tgasPrice=200000000000\n  }, {\n\tblockHash=0xf2f8fe13cbcec0a68de9ab1a55025eed85166e8810a95340012b8d882c7b8266, \n\ttransactionIndex=0, \n\ttxType=0x1, \n\tnonce=4403, \n\tinput=0x, \n\tr=0x84bb821de7dd9b211755b8798dd5304190593c5f3f49d736e56d14246e70f9b1, \n\ts=0x49685c8017bb839524e0027244c37813a3df779692108e645fadb540294e43c1, \n\tv=0x25, \n\tblockNumber=670731, \n\tgas=21000, \n\tfrom=0x731bd7289b4191706b00f6f1877662b5e8697e82, \n\tto=0x731d8fdc53039f07efd87be3c387e747da92911f, \n\tvalue=216900000000000000000, \n\thash=0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283, \n\tgasPrice=200000000000\n  }]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Transactions"
  },
  {
    "name": "getTransByAddressBetweenBlocks",
    "group": "Transactions",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTransByAddressBetweenBlocks",
    "version": "0.5.0",
    "description": "<p>Get transaction information via the specified address between the specified startBlockNo and endBlockNo on certain chain. <br>Comments: <br>    if no startBlockNo given, startBlockNo will be set to 0; <br>    if no endBlockNo given, endBlockNo will be set to the newest blockNumber.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>The account's address that you want to search.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Number",
            "optional": false,
            "field": "startBlockNo",
            "description": "<p>The startBlockNo that you want to search from.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Number",
            "optional": false,
            "field": "endBlockNo",
            "description": "<p>The endBlockNo that you want to search to.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>List&lt;Map&lt;String,Object&gt;&gt;</code>, each element of list is one tx info which is formed as <code>&lt;Map&lt;String,Object&gt;</code>. you can get queried tx info, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTransByAddressBetweenBlocks\",\"params\":{\"chainType\":\"WAN\", \"address\":\"0x731bd7289b4191706b00f6f1877662b5e8697e82\", \"startBlockNo\":670730, \"endBlockNo\":670735},\"id\":1}",
          "type": "String"
        },
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTransByAddress\",\"params\":{\"chainType\":\"WAN\", \"address\":\"0x731bd7289b4191706b00f6f1877662b5e8697e82\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tList txList = javaApi.getTransByAddressBetweenBlocks(\"WAN\", \"0x731bd7289b4191706b00f6f1877662b5e8697e82\",670730, 670735).send();\n\t\n\tfor(int i=0; i<txList.size(); i++) {\n\t\tMap<String, Object> txInfo =  (Map<String, Object>) txList.get(i);\n\t\n\t\tObject blockHash = txInfo.get(\"blockHash\");\n\t\tSystem.out.println(\"\\n[sync]getTransByAddressBetweenBlocks: blockHash: \" + blockHash);\n\t\t\t\n\t\tObject blockNumber = txInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"[sync]getTransByAddressBetweenBlocks: blockNumber: \" + blockNumber);\n\n\t\tObject from = txInfo.get(\"from\");\n\t\tSystem.out.println(\"[sync]getTransByAddressBetweenBlocks: from: \" + from);\n\t\t\n\t\tObject to = txInfo.get(\"to\");\n\t\tSystem.out.println(\"[sync]getTransByAddressBetweenBlocks: to: : \" + to);\n\t\t\n\t\tObject value = txInfo.get(\"value\");\n\t\tSystem.out.println(\"[sync]getTransByAddressBetweenBlocks: value: : \" + value);\t\t\t\t\t\n\t}\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<List> txListFuture = javaApi.getTransByAddressBetweenBlocks(\"WAN\", \"0x731bd7289b4191706b00f6f1877662b5e8697e82\", 670730, 670735).sendAsync();\n\t\ntry {\t\t\n\tList txList = txListFuture.get();\n\n\tfor(int i=0; i<txList.size(); i++) {\n\t\tMap<String, Object> txInfo =  (Map<String, Object>) txList.get(i);\n\t\t\n\t\tObject blockHash = txInfo.get(\"blockHash\");\n\t\tSystem.out.println(\"\\n[async]getTransByAddressBetweenBlocks: blockHash: \" + blockHash);\n\t\t\t\n\t\tObject blockNumber = txInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"[async]getTransByAddressBetweenBlocks: blockNumber: \" + blockNumber);\n\n\t\tObject from = txInfo.get(\"from\");\n\t\tSystem.out.println(\"[async]getTransByAddressBetweenBlocks: from: \" + from);\n\t\t\t\n\t\tObject to = txInfo.get(\"to\");\n\t\tSystem.out.println(\"[async]getTransByAddressBetweenBlocks: to: : \" + to);\n\t\t\n\t\tObject value = txInfo.get(\"value\");\n\t\tSystem.out.println(\"[async]getTransByAddressBetweenBlocks: value: : \" + value);\t\t\t\t\n\t}\t\t\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  [{\n\tblockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, \n\ttransactionIndex=6, \n\ttxType=0x1, \n\tnonce=0, \n\tinput=0x, \n\tr=0x7dfe37a3835ca20fbfe44e7baefdda60e7105dc05dc1ff75a3ef39e60d3e3b8a, \n\ts=0x36368d24661e3f4cdc210c6d427ec54f7b89549eb88319053c65edd40651cd04, \n\tv=0x26, \n\tblockNumber=1362889, \n\tgas=21000, \n\tfrom=0x731d8fdc53039f07efd87be3c387e747da92911f, \n\tto=0x731bd7289b4191706b00f6f1877662b5e8697e82,\n\tvalue=216890000000000000000, \n\thash=0xa25c0f4fa28bf688abc68f63beed9aa0916ceb1d5886c626dd13acb943ec2fbd, \n\tgasPrice=200000000000\n  }, {\n\tblockHash=0xf2f8fe13cbcec0a68de9ab1a55025eed85166e8810a95340012b8d882c7b8266, \n\ttransactionIndex=0, \n\ttxType=0x1, \n\tnonce=4403, \n\tinput=0x, \n\tr=0x84bb821de7dd9b211755b8798dd5304190593c5f3f49d736e56d14246e70f9b1, \n\ts=0x49685c8017bb839524e0027244c37813a3df779692108e645fadb540294e43c1, \n\tv=0x25, \n\tblockNumber=670731, \n\tgas=21000, \n\tfrom=0x731bd7289b4191706b00f6f1877662b5e8697e82, \n\tto=0x731d8fdc53039f07efd87be3c387e747da92911f, \n\tvalue=216900000000000000000, \n\thash=0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283, \n\tgasPrice=200000000000\n  }]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Transactions"
  },
  {
    "name": "getTransByBlock",
    "group": "Transactions",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTransByBlock",
    "version": "0.5.0",
    "description": "<p>Get transaction information in a given block by block number or block hash on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "blockHashOrBlockNumber",
            "description": "<p>The blockHash or the blockNumber you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "List",
            "optional": false,
            "field": "response",
            "description": "<p>The response data type is <code>List&lt;Map&lt;String,Object&gt;&gt;</code>, each element of list is one tx info which is formed as <code>&lt;Map&lt;String,Object&gt;</code>. you can get queried tx info, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTransByBlock\",\"params\":{\"chainType\":\"WAN\", \"blockNumber\":\"984133\"},\"id\":1}\nor\n{\"jsonrpc\":\"2.0\",\"method\":\"getTransByBlock\",\"params\":{\"chainType\":\"WAN\", \"blockHash\":\"0xaa0fc2a8a868566f2e4888b2942ec05c47c2254e8b81e43d3ea87420a09126c2\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tList txList = javaApi.getTransByBlock(\"WAN\", \"0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457\").send();\n\t\n\tfor(int i=0; i<txList.size(); i++) {\n\t\tMap<String, Object> txInfo =  (Map<String, Object>) txList.get(i);\n\t\t\n\t\tObject blockHash = txInfo.get(\"blockHash\");\n\t\tSystem.out.println(\"\\n[sync]getTransByBlock: blockHash: \" + blockHash);\n\t\t\n\t\tObject blockNumber = txInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"[sync]getTransByBlock: blockNumber: \" + blockNumber);\n\n\t\tObject from = txInfo.get(\"from\");\n\t\tSystem.out.println(\"[sync]getTransByBlock: from: \" + from);\n\t\t\n\t\tObject to = txInfo.get(\"to\");\n\t\tSystem.out.println(\"[sync]getTransByBlock: to: : \" + to);\n\t\t\n\t\tObject value = txInfo.get(\"value\");\n\t\tSystem.out.println(\"[sync]getTransByBlock: value: : \" + value);\t\t\t\t\t\n\t}\t\t\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<List> txListFuture = javaApi.getTransByBlock(\"WAN\", \"0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457\").sendAsync();\n\t\ntry {\n\tList txList = txListFuture.get();\n\n\tfor(int i=0; i<txList.size(); i++) {\n\t\tMap<String, Object> txInfo =  (Map<String, Object>) txList.get(i);\n\t\t\n\t\tObject blockHash = txInfo.get(\"blockHash\");\n\t\tSystem.out.println(\"\\n[async]getTransByBlock: blockHash: \" + blockHash);\n\t\t\n\t\tObject blockNumber = txInfo.get(\"blockNumber\");\n\t\tSystem.out.println(\"[async]getTransByBlock: blockNumber: \" + blockNumber);\n\n\t\tObject from = txInfo.get(\"from\");\n\t\tSystem.out.println(\"[async]getTransByBlock: from: \" + from);\n\t\t\n\t\tObject to = txInfo.get(\"to\");\n\t\tSystem.out.println(\"[async]getTransByBlock: to: : \" + to);\n\t\t\n\t\tObject value = txInfo.get(\"value\");\n\t\tSystem.out.println(\"[async]getTransByBlock: value: : \" + value);\t\t\t\t\n\t}\t\t\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  [{\n\tblockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, \n\ttransactionIndex=2, \n\ttxType=0x1, \n\tnonce=0, \n\tinput=0x, \n\tr=0x61282bb5d07a3e4445735f96d579feede4a29f6ce603fbc6c15617536d80fe06, \n\ts=0x17cba9a4ad00fb2b695ee1af6db770c0e3200a6fb369048f8b31ee47df4514de, \n\tv=0x26, \n\tblockNumber=1362889, \n\tgas=21000, \n\tfrom=0x9dc65fbf97b61173f44bf2203a98b53fd96b1624, \n\tto=0x731bd7289b4191706b00f6f1877662b5e8697e82, \n\tvalue=211990000000000000000, \n\thash=0xba0ceed770df4a6e330a643ec7f3b48096aed0ed393517caa81f06e748eb55b0, \n\tgasPrice=200000000000\n  }, {\n\tblockHash=0xd92f8e71266f238341c298544a6ac0e45d8afc24aee7279e3792a032020a8457, \n\ttransactionIndex=12, \n\ttxType=0x1, \n\tnonce=0, \n\tinput=0x, \n\tr=0xf813cc0f2c420377433f3d7fc2d4f0fffaac3577da760fa5c13596bc67fbb7aa, \n\ts=0x6018616b0c80048819ca020a900b7b398c245666c24d6b49cdea1c96770b6253, \n\tv=0x26, \n\tblockNumber=1362889, \n\tgas=21000, \n\tfrom=0x4d3987b16f9a3a9427baf32cedc32a050aa07b28, \n\tto=0x731bd7289b4191706b00f6f1877662b5e8697e82, \n\tvalue=213990000000000000000, \n\thash=0x3c4cd10545eab675c87c11026ad1dac5e8270297d2973f856b03855b8ea854a3, \n\tgasPrice=200000000000\n  }]",
          "type": "List"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Transactions"
  },
  {
    "name": "getTransactionConfirm",
    "group": "Transactions",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTransactionConfirm",
    "version": "0.5.0",
    "description": "<p>Get the transaction mined result on certain chain. When the receipt not existed, return directly with 'no receipt was found'; If receipt existed, the receipt will be returned after confirm-block-number blocks.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Number",
            "optional": false,
            "field": "waitBlocks",
            "description": "<p>The confirm-block-number you want to set.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "txHash",
            "description": "<p>The txHash you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response data is <code>Map&lt;String,Object&gt;</code> type, and you can get related property value of the queried tx. Please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTransactionConfirm\",\"params\":{\"chainType\":\"WAN\", \"waitBlocks\": 6, \"txHash\": \"0xd2a5b1f403594dbc881e466d46a4cac3d6cf202476b1277876f0b24923d032da\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tMap<String, Object> txInfo = javaApi.getTransactionConfirm(\"WAN\", 2, \"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35\").send();\n\t\t\t\t\t\n\tObject blockHash = txInfo.get(\"blockHash\");\n\tSystem.out.println(\"\\n[sync]getTransactionConfirm: blockHash: \" + blockHash);\n\t\n\tObject blockNumber = txInfo.get(\"blockNumber\");\n\tSystem.out.println(\"[sync]getTransactionConfirm: blockNumber: \" + blockNumber);\n\n\tObject from = txInfo.get(\"from\");\n\tSystem.out.println(\"[sync]getTransactionConfirm: from: \" + from);\n\t\n\tObject to = txInfo.get(\"to\");\n\tSystem.out.println(\"[sync]getTransactionConfirm: to: : \" + to);\n\t\n\tObject logs = txInfo.get(\"logs\");\n\tSystem.out.println(\"[sync]getTransactionConfirm: logs: : \" + logs);\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<Map<String, Object>> txFuture = javaApi.getTransactionConfirm(\"WAN\", 2, \"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35\").sendAsync();\n\t\ntry {\n\tMap<String, Object> txInfo = txFuture.get();\n\t\n\tObject blockHash = txInfo.get(\"blockHash\");\n\tSystem.out.println(\"\\n[async]getTransactionConfirm: blockHash: \" + blockHash);\n\t\n\tObject blockNumber = txInfo.get(\"blockNumber\");\n\tSystem.out.println(\"[async]getTransactionConfirm: blockNumber: \" + blockNumber);\n\n\tObject from = txInfo.get(\"from\");\n\tSystem.out.println(\"[async]getTransactionConfirm: from: \" + from);\n\t\n\tObject to = txInfo.get(\"to\");\n\tSystem.out.println(\"[async]getTransactionConfirm: to: : \" + to);\n\t\n\tObject logs = txInfo.get(\"logs\");\n\tSystem.out.println(\"[async]getTransactionConfirm: logs: : \" + logs);\t\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  {\n\tblockHash=0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503, \n\tlogsBloom=0x00000000000000004000000100000000000000000000000000000000000000000000000000000000000000000000000000080020000000000000000000000000040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000000000000000000200000000000000000000008000000000000000008000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, \n\tgasUsed=90374, \n\tblockNumber=2887621, \n\tcumulativeGasUsed=90374, \n\tfrom=0x58541bca9583b131684499b2e14636e1665e9bd2, \n\ttransactionIndex=0, \n\tto=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, \n\tlogs=[{\"blockHash\":\"0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503\",\n\t\t\"address\":\"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\",\n\t\t\"logIndex\":0,\n\t\t\"data\":\"0x00000000000000000000000000000000000000000000010ff5e03b33183c6000000000000000000000000000000000000000000000000000000000005c8aeb0a00000000000000000000000000000000000000000000010ff5e03b33183c600000000000000000000000000000000000000000000000010ff5e03b33183c6000\",\n\t\t\"removed\":false,\n\t\t\"topics\":[\"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\",\"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2\"],\n\t\t\"blockNumber\":2887621,\n\t\t\"transactionIndex\":0,\n\t\t\"transactionHash\":\"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35\"}], \t\t\n\ttransactionHash=0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35, \n\tstatus=0x1\n  }",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Transactions"
  },
  {
    "name": "getTransactionReceipt",
    "group": "Transactions",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTransactionReceipt",
    "version": "0.5.0",
    "description": "<p>Get the receipt of a transaction by transaction hash on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain being queried, currently supports <code>&quot;WAN&quot;</code> and <code>&quot;ETH&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "txHash",
            "description": "<p>The txHash you want to search.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response data is <code>Map&lt;String,Object&gt;</code> type , you can get queried tx receipt info, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTransactionReceipt\",\"params\":{\"chainType\":\"WAN\", \"txHash\":\"0xc18c4bdf0d40c4bb2f34f0273eaf4dc674171fbf33c3301127e1d4c85c574ebe\"},\"id\":1}",
          "type": "string"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tMap<String,Object> txReceipt = javaApi.getTransactionReceipt(\"WAN\", \"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35\").send();\n\t\t\t\t\t\n\tObject blockHash = txReceipt.get(\"blockHash\");\n\tSystem.out.println(\"\\n[sync]getTransactionReceipt: blockHash: \" + blockHash);\n\t\n\tObject blockNumber = txReceipt.get(\"blockNumber\");\n\tSystem.out.println(\"[sync]getTransactionReceipt: blockNumber: \" + blockNumber);\n\n\tObject from = txReceipt.get(\"from\");\n\tSystem.out.println(\"[sync]getTransactionReceipt: from: \" + from);\n\t\n\tObject to = txReceipt.get(\"to\");\n\tSystem.out.println(\"[sync]getTransactionReceipt: to : \" + to);\n\t\n\tObject status = txReceipt.get(\"status\");\n\tSystem.out.println(\"[sync]getTransactionReceipt: status : \" + status);\t\n\t\n\tObject logs = txReceipt.get(\"logs\");\n\tSystem.out.println(\"[sync]getTransactionReceipt: logs : \" + logs);\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<Map<String,Object>> txReceiptFuture = javaApi.getTransactionReceipt(\"WAN\", \"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35\").sendAsync();\n\t\ntry {\n\tMap<String,Object> txReceipt = txReceiptFuture.get();\n\t\t\n\tObject blockHash = txReceipt.get(\"blockHash\");\n\tSystem.out.println(\"\\n[async]getTransactionReceipt: blockHash: \" + blockHash);\n\t\n\tObject blockNumber = txReceipt.get(\"blockNumber\");\n\tSystem.out.println(\"[async]getTransactionReceipt: blockNumber: \" + blockNumber);\n\n\tObject from = txReceipt.get(\"from\");\n\tSystem.out.println(\"[async]getTransactionReceipt: from: \" + from);\n\t\n\tObject to = txReceipt.get(\"to\");\n\tSystem.out.println(\"[async]getTransactionReceipt: to: \" + to);\n\t\n\tObject status = txReceipt.get(\"status\");\n\tSystem.out.println(\"[async]getTransactionReceipt: status: \" + status);\t\n\t\n} catch (InterruptedException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n} catch (ExecutionException e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  {\n\tblockHash=0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503, \n\tlogsBloom=0x00000000000000004000000100000000000000000000000000000000000000000000000000000000000000000000000000080020000000000000000000000000040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000000000000000000200000000000000000000008000000000000000008000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, \n\tgasUsed=90374, \n\tblockNumber=2887621, \n\tcumulativeGasUsed=90374, \n\tfrom=0x58541bca9583b131684499b2e14636e1665e9bd2, \n\ttransactionIndex=0, \n\tto=0x33fc0c6b7fc83691e42855b14832d486c9a09e67, \n\tlogs=[{\"blockHash\":\"0xc11b7dfe1428d667347089fbab7130f98ff20587019b2282a5b01cf19dae8503\",\n\t\t\"address\":\"0x33fc0c6b7fc83691e42855b14832d486c9a09e67\",\n\t\t\"logIndex\":0,\n\t\t\"data\":\"0x00000000000000000000000000000000000000000000010ff5e03b33183c6000000000000000000000000000000000000000000000000000000000005c8aeb0a00000000000000000000000000000000000000000000010ff5e03b33183c600000000000000000000000000000000000000000000000010ff5e03b33183c6000\",\n\t\t\"removed\":false,\n\t\t\"topics\":[\"0xc20f316b11dd795635146cfac675b697062e789e5c33b400f13a4a02db2c9ae4\",\"0x00000000000000000000000058541bca9583b131684499b2e14636e1665e9bd2\"],\n\t\t\"blockNumber\":2887621,\n\t\t\"transactionIndex\":0,\n\t\t\"transactionHash\":\"0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35\"}], \n\ttransactionHash=0x1cc2d3a79e6d1511d59d100540792cdb0bb2bc6be305607bcc8151800bcced35, \n\tstatus=0x1\n  }",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Transactions"
  },
  {
    "name": "getTxInfo",
    "group": "Transactions",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "getTxInfo",
    "version": "0.5.0",
    "description": "<p>Get the transaction detail via txHash on certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code> or <code>&quot;ETH&quot;</code> or <code>&quot;BTC&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "txHash",
            "description": "<p>The txHash you want to search.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "format",
            "description": "<p>Whether to get the serialized or decoded transaction. <br>In the case of <code>chainType</code> is set as <code>&quot;WAN&quot;</code> or <code>&quot;ETH&quot;</code>, just set <code>format</code> to <code>null</code>; <br>In the case of <code>chainType</code> is set as <code>&quot;BTC&quot;</code>: <br>     Set to <code>false</code> (the default) to return the serialized transaction as hex. <br>     Set to <code>true</code> to return a decoded transaction.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "Map",
            "optional": false,
            "field": "response",
            "description": "<p>The response data is <code>Map&lt;String,Object&gt;</code> type , you can get queried tx related property, please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"getTxInfo\",\"params\":{\"chainType\":\"WAN\", \"txHash\":\"0xd2a5b1f403594dbc881e466d46a4cac3d6cf202476b1277876f0b24923d032da\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tMap<String,Object> txInfo = javaApi.getTxInfo(\"WAN\", \"0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283\").send();\n\t\t\t\t\t\n\tObject blockHash = txInfo.get(\"blockHash\");\n\tSystem.out.println(\"\\n[sync]getTxInfo: blockHash: \" + blockHash);\n\t\t\n\tObject blockNumber = txInfo.get(\"blockNumber\");\n\tSystem.out.println(\"[sync]getTxInfo: blockNumber: \" + blockNumber);\n\n\tObject from = txInfo.get(\"from\");\n\tSystem.out.println(\"[sync]getTxInfo: from: \" + from);\n\t\n\tObject to = txInfo.get(\"to\");\n\tSystem.out.println(\"[sync]getTxInfo: to: : \" + to);\n\t\n\tObject value = txInfo.get(\"value\");\n\tSystem.out.println(\"[sync]getTxInfo: value: : \" + value);\t\t\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by async mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<Map<String,Object>> txFuture = javaApi.getTxInfo(\"WAN\", \"0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283\").sendAsync();\n\t\t\t\ntry {\n\tMap<String, Object> txInfo =  txFuture.get();\n\t\n\tObject blockHash = txInfo.get(\"blockHash\");\n\tSystem.out.println(\"\\n[async]getTxInfo: blockHash: \" + blockHash);\n\t\t\n\tObject blockNumber = txInfo.get(\"blockNumber\");\n\tSystem.out.println(\"[async]getTxInfo: blockNumber: \" + blockNumber);\n\n\tObject from = txInfo.get(\"from\");\n\tSystem.out.println(\"[async]getTxInfo: from: \" + from);\n\t\n\tObject to = txInfo.get(\"to\");\n\tSystem.out.println(\"[async]getTxInfo: to: : \" + to);\n\t\n\tObject value = txInfo.get(\"value\");\n\tSystem.out.println(\"[async]getTxInfo: value: : \" + value);\t\t\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "  {\n\tblockHash=0xf2f8fe13cbcec0a68de9ab1a55025eed85166e8810a95340012b8d882c7b8266, \n\ttransactionIndex=0, \n\ttxType=0x1, \n\tnonce=4403, \n\tinput=0x, \n\tr=0x84bb821de7dd9b211755b8798dd5304190593c5f3f49d736e56d14246e70f9b1, \n\ts=0x49685c8017bb839524e0027244c37813a3df779692108e645fadb540294e43c1, \n\tv=0x25, \n\tblockNumber=670731, \n\tgas=21000, \n\tfrom=0x731bd7289b4191706b00f6f1877662b5e8697e82, \n\tto=0x731d8fdc53039f07efd87be3c387e747da92911f, \n\tvalue=216900000000000000000, \n\thash=0xfaa684af371e8f8830f47c0ca776bed96119a205d535abbf2f5fc71263d5c283, \n\tgasPrice=200000000000\n  }",
          "type": "Map"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Transactions"
  },
  {
    "name": "sendRawTransaction",
    "group": "Transactions",
    "type": "CONNECT",
    "url": "/ws/v3/YOUR-API-KEY",
    "title": "sendRawTransaction",
    "version": "0.5.0",
    "description": "<p>Submit a pre-signed transaction for broadcast to certain chain.</p>",
    "parameter": {
      "fields": {
        "Input Parameter": [
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "chainType",
            "description": "<p>The chain name that you want to search, should be <code>&quot;WAN&quot;</code> or <code>&quot;ETH&quot;</code> or <code>&quot;BTC&quot;</code>.</p>"
          },
          {
            "group": "Input Parameter",
            "type": "String",
            "optional": false,
            "field": "signedTx",
            "description": "<p>The signedTx you want to send.</p>"
          }
        ],
        "Return": [
          {
            "group": "Return",
            "type": "String",
            "optional": false,
            "field": "response",
            "description": "<p>The response data is <code>String</code> type , please refer to examples above.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "JSON-RPC over websocket",
          "content": "{\"jsonrpc\":\"2.0\",\"method\":\"sendRawTransaction\",\"params\":{\"chainType\":\"WAN\", \"signedTx\":\"0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356\"},\"id\":1}",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Example by sync mode:",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\ntry {\n\tString strRslt = javaApi.sendRawTransaction(\"WAN\", \"0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356\").send();\n\tSystem.out.println(\"[sync]sendRawTransaction: strRslt: \" + strRslt);\t\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      },
      {
        "title": "Example by sync mode:\t",
        "content": "Wanj javaApi = new Wanj(YOUR_API_KEY,YOUR_SECRET_KEY,null);\n\nCompletableFuture<String> txFuture = javaApi.sendRawTransaction(\"WAN\", \"0xf86e0109852e90edd000832dc6c0946ed9c11cbd8a6ae8355fa62ebca48493da572661880de0b6b3a7640000801ca0bd349ec9f51dd171eb5c59df9a6b8c5656eacb6793bed945a7ec69135f191abfa0359da11e8a4fdd51b52a8752ac32f9125d168441546d011406736bce67b8a356\").sendAsync();\n\t\ntry {\n\tString strRslt =  txFuture.get();\n\tSystem.out.println(\"[async]sendRawTransaction: strRslt: \" + strRslt);\n\t\t\n}catch (Exception e) {\n\t// TODO Auto-generated catch block\n\te.printStackTrace();\n}",
        "type": "java"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "Successful Response",
          "content": "\"0x4dcfc82728b5a9307f249ac095c8e6fcc436db4f85a094a0c5a457255c20f80f\"",
          "type": "String"
        }
      ]
    },
    "filename": "src/main/java/com/wanchain/iwanj/lib/apis/Wanj.java",
    "groupTitle": "Transactions"
  }
] });

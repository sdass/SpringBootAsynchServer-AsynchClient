Two springboot webapps. start server first on default port 8080. Next start client-webapp (set port7979)
1-RestController Server [appname: spring-asynch2] (asynchronous method with @AsynchEnable configure)
2. RestClient [appname: spring-asynch-client] Consumer using AsyncRestTemplate. Critical method is consumeAsynchReal().
Method returns DeferredResult<String>. Followed 4 steps to make asynch (proved in log). steps are: 
(1) declare result container. avoid anonymous effectively final DeferredResult<String> deferedResult
(2) //2 regular call setting input output except LHS ListenableFuture<ResponseEntity<String>>
(3) No prepare callbackhandler implementing interface and override onSuccess, onFailure methods.
(4) hook the handler to LHS object in step 2. //only then Spring makes the call.
Now watching log shows it is acting as ajax call
Java OAuth2 Server for playframework
===================

This project is based on [oauth2-server](https://github.com/yoichiro/oauth2-server).

Current supported Grant types
-----------------------------

* Authorization Code Grant
* Resource Owner Password Credentials Grant
* Client Credentials Grant

Current supported token types
-----------------------------

* Bearer(http://tools.ietf.org/html/rfc6750)

How to use
----------

Implement DataHandler interface.

Implement DataHandlerFactory interface.

Classes you have to implement are only above.

The way to use the Token class is simple. You can use it as the following snippet:

```java
Http.Request request = request();//playframework request
PlayRequestAdapter adapter = new PlayRequestAdapter(request);

Token token = new Token();
token.setDataHandlerFactory(new MyDataHandlerFactory());
token.setClientCredentialFetcher(new ClientCredentialFetcherImpl());
token.setGrantHandlerProvider(new DefaultGrantHandlerProvider());

Token.Response response = token.handleRequest(adapter);
int code = response.getCode();
String body = response.getBody();
```

To check the request to access to each API endpoints, you can use
ProtectedResource class. The following code snippet represents how to use its
class:

```java
Http.Request request = request();//playframework request
PlayRequestAdapter adapter = new PlayRequestAdapter(request);
ProtectedResource protectedResource = ...; // Injected
try {
    ProtectedResource.Response response = protectedResource.handleRequest(adapter);
    String remoteUser = response.getRemoteUser(); // User ID
    String clientId = response.getClientId(); // Client ID
    String scope = response.getScope(); // Scope string delimited by whitespace
    // do something
} catch(OAuthError e) {
    int httpStatusCode = e.getCode();
    String errorType = e.getType();
    String description = e.getDescription();
    // do something
}
```

Sample playframework code (2.5.x): [playframework oauth2 server](https://github.com/dacduong/play-oauth2-server)
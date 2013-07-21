package com.sergiandreplace.rarest.util;

public class HttpPetitionHeaders {
	//Content-Types that are acceptable
	public final static String ACCEPT="Accept";
	
	//Character sets that are acceptable
	public final static String ACCEPT_CHARSET="Accept-Charset";
	
	//Acceptable encodings. See�HTTP compression.
	public final static String ACCEPT_ENCODING="Accept-Encoding";
	
	//Acceptable languages for response
	public final static String ACCEPT_LANGUAGE="Accept-Language";
	
	//Acceptable version in time
	public final static String ACCEPT_DATETIME="Accept-Datetime";
	
	//Authentication credentials for HTTP authentication
	public final static String AUTHORIZATION="Authorization";
	
	//Used to specify directives that MUST be obeyed by all caching mechanisms along the request/response chain
	public final static String CACHE_CONTROL="Cache-Control";
	
	//What type of connection the user-agent would prefer
	public final static String CONNECTION="Connection";
	
	//an HTTP cookie previously sent by the server with�Set-Cookie�(below)
	public final static String COOKIE="Cookie";
	
	//The length of the request body in�octets�(8-bit bytes)
	public final static String CONTENT_LENGTH="Content-Length";
	
	//A�Base64-encoded binary�MD5�sum of the content of the request body
	public final static String CONTENT_MD5="Content-MD5";
	
	//The�MIME type�of the body of the request (used with POST and PUT requests)
	public final static String CONTENT_TYPE="Content-Type";
	
	//The date and time that the message was sent
	public final static String DATE="Date";
	
	//Indicates that particular server behaviors are required by the client
	public final static String EXPECT="Expect";
	
	//The email address of the user making the request
	public final static String FROM="From";
	
	//The domain name of the server (for�virtual hosting), and the�TCP port�number on which the server is listening. The�port�number may be omitted if the port is the standard port for the service requested.[5]�Mandatory since HTTP/1.1. Although domain name are specified as case-insensitive,[6][7]�it is not specified whether the contents of the Host field should be interpreted in a case-insensitive manner[8]�and in practice some implementations of virtual hosting interpret the contents of the Host field in a case-sensitive manner.[citation needed]
	public final static String HOST="Host";
	
	//Only perform the action if the client supplied entity matches the same entity on the server. This is mainly for methods like PUT to only update a resource if it has not been modified since the user last updated it.
	public final static String IF_MATCH="If-Match";
	
	//Allows a�304 Not Modified�to be returned if content is unchanged
	public final static String IF_MODIFIED_SINCE="If-Modified-Since";
	
	//Allows a�304 Not Modified�to be returned if content is unchanged, see�HTTP ETag
	public final static String IF_NONE_MATCH="If-None-Match";
	
	//If the entity is unchanged, send me the part(s) that I am missing; otherwise, send me the entire new entity
	public final static String IF_RANGE="If-Range";
	
	//Only send the response if the entity has not been modified since a specific time.
	public final static String IF_UNMODIFIED_SINCE="If-Unmodified-Since";
	
	//Limit the number of times the message can be forwarded through proxies or gateways.
	public final static String MAX_FORWARDS="Max-Forwards";
	
	//Implementation-specific headers that may have various effects anywhere along the request-response chain.
	public final static String PRAGMA="Pragma";
	
	//Authorization credentials for connecting to a proxy.
	public final static String PROXY_AUTHORIZATION="Proxy-Authorization";
	
	//Request only part of an entity. Bytes are numbered from 0.
	public final static String RANGE="Range";
	
	//This is the address of the previous web page from which a link to the currently requested page was followed. (The word �referrer� is misspelled in the RFC as well as in most implementations.)
	public final static String REFERER="Referer";
	
	//The transfer encodings the user agent is willing to accept: the same values as for the response header Transfer-Encoding can be used, plus the "trailers" value (related to the "chunked" transfer method) to notify the server it expects to receive additional headers (the trailers) after the last, zero-sized, chunk.
	public final static String TE="TE";
	
	//Ask the server to upgrade to another protocol.
	public final static String UPGRADE="Upgrade";
	
	//The�user agent string�of the user agent
	public final static String USER_AGENT="User-Agent";
	
	//Informs the server of proxies through which the request was sent.
	public final static String VIA="Via";
	
	//A general warning about possible problems with the entity body.
	public final static String WARNING="Warning";

	


}

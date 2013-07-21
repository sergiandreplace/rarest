package com.sergiandreplace.rarest.util;

public class HttpResponseHeaders {

	//Specifying which web sites can participate in�cross-origin resource sharing
	public final static String ACCESS_CONTROL_ALLOW_ORIGIN="Access-Control-Allow-Origin";

	//What partial content range types this server supports
	public final static String ACCEPT_RANGES="Accept-Ranges";

	//The age the object has been in a�proxy cache�in seconds
	public final static String AGE="Age";

	//Valid actions for a specified resource. To be used for a�405 Method not allowed
	public final static String ALLOW="Allow";

	//Tells all caching mechanisms from server to client whether they may cache this object. It is measured in seconds
	public final static String CACHE_CONTROL="Cache-Control";

	//Options that are desired for the connection[20]
	public final static String CONNECTION="Connection";

	//The type of encoding used on the data. See�HTTP compression.
	public final static String CONTENT_ENCODING="Content-Encoding";

	//The language the content is in
	public final static String CONTENT_LANGUAGE="Content-Language";

	//The length of the response body in�octets�(8-bit bytes)
	public final static String CONTENT_LENGTH="Content-Length";

	//An alternate location for the returned data
	public final static String CONTENT_LOCATION="Content-Location";

	//A�Base64-encoded binary�MD5�sum of the content of the response
	public final static String CONTENT_MD5="Content-MD5";

	//An opportunity to raise a "File Download" dialogue box for a known MIME type with binary format or suggest a filename for dynamic content. Quotes are necessary with special characters.
	public final static String CONTENT_DISPOSITION="Content-Disposition";

	//Where in a full body message this partial message belongs
	public final static String CONTENT_RANGE="Content-Range";

	//The�MIME type�of this content
	public final static String CONTENT_TYPE="Content-Type";

	//The date and time that the message was sent
	public final static String DATE="Date";

	//An identifier for a specific version of a resource, often a�message digest
	public final static String ETAG="ETag";

	//Gives the date/time after which the response is considered stale
	public final static String EXPIRES="Expires";

	//The last modified date for the requested object, in�RFC 2822format
	public final static String LAST_MODIFIED="Last-Modified";

	//Used to express a typed relationship with another resource, where the relation type is defined by�RFC 5988
	public final static String LINK="Link";

	//Used in redirection, or when a new resource has been created.
	public final static String LOCATION="Location";

	//This header is supposed to set�P3P�policy, in the form ofP3P:CP="your_compact_policy". However, P3P did not take off,[25]�most browsers have never fully implemented it, a lot of websites set this header with fake policy text, that was enough to fool browsers the existence of P3P policy and grant permissions for�third party cookies.
	public final static String P3P="P3P";

	//Implementation-specific headers that may have various effects anywhere along the request-response chain.
	public final static String PRAGMA="Pragma";

	//Request authentication to access the proxy.
	public final static String PROXY_AUTHENTICATE="Proxy-Authenticate";

	//Used in redirection, or when a new resource has been created. This refresh redirects after 5 seconds. This is a proprietary, non-standard header extension introduced by Netscape and supported by most web browsers.
	public final static String REFRESH="Refresh";

	//If an entity is temporarily unavailable, this instructs the client to try again after a specified period of time (seconds).
	public final static String RETRY_AFTER="Retry-After";

	//A name for the server
	public final static String SERVER="Server";

	//an�HTTP cookie
	public final static String SET_COOKIE="Set-Cookie";

	//A HSTS Policy informing the HTTP client how long to cache the HTTPS only policy and whether this applies to subdomains.
	public final static String STRICT_TRANSPORT_SECURITY="Strict-Transport-Security";

	//The Trailer general field value indicates that the given set of header fields is present in the trailer of a message encoded withchunked transfer-coding.
	public final static String TRAILER="Trailer";

	//The form of encoding used to safely transfer the entity to the user.Currently defined methods�are:�chunked, compress, deflate, gzip, identity.
	public final static String TRANSFER_ENCODING="Transfer-Encoding";

	//Tells downstream proxies how to match future request headers to decide whether the cached response can be used rather than requesting a fresh one from the origin server.
	public final static String VARY="Vary";

	//Informs the client of proxies through which the response was sent.
	public final static String VIA="Via";

	//A general warning about possible problems with the entity body.
	public final static String WARNING="Warning";

	//Indicates the authentication scheme that should be used to access the requested entity.
	public final static String WWW_AUTHENTICATE="WWW-Authenticate";

}

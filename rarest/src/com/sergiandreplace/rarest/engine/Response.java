package com.sergiandreplace.rarest.engine;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * This object will contain the result of an RaRest service execution. It contains all the info, even if a problem arised on the way.
 *
 * @author Sergi Mart�nez
 */
public class Response {
    private String body;
    private int status;
    private String errorMessage;
    private String errorType;
    private Map<String, String> headers = new HashMap<String, String>();

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    /**
     * Plain constructor
     */
    public Response() {
    }

    /**
     * Constructs the object from the data on the apache response
     *
     * @param httpResponse the object received from Apache
     */
    public Response(HttpResponse httpResponse) {
        setHttpResponse(httpResponse);
    }


    /**
     * Constructs the response object from an Exception data
     *
     * @param e The exception produced BEFORE creating the Response object
     */
    public Response(Exception e) {
        errorMessage = e.getMessage();
        errorType = e.getClass().getName();
    }

    /**
     * The body of the response in plain text
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * The response code from HTTP petition (200 for ok, etc);
     *
     * @return
     */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * The error message produced if any
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Name of the exception if any
     *
     * @return
     */
    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * @return true if the response contains an error
     */
    public boolean isError() {
        return errorType != null;
    }

    /**
     * Builds the response from an HttpResponse
     *
     * @param httpResponse the current response from an Http Action acroding to apache library
     */
    public void setHttpResponse(HttpResponse httpResponse) {
        status = httpResponse.getStatusLine().getStatusCode();
        try {
            body = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        } catch (Exception e) {
            errorMessage = e.getMessage();
            errorType = e.getClass().getName();
        }
        for (Header header : httpResponse.getAllHeaders()) {
            headers.put(header.getName(), header.getValue());
        }
    }

//	/**
//	 * Serializes the plain text body into an object. It will be parsed according to the content-type header of the response
//	 * @param bodyClass The class of the object to serialize
//	 * @return a serialized object or null if any error;
//	 */
//	public <T> T getBody(Class<T> bodyClass) {
//		String mime=MimeTypes.JSON;
//		if (headers.containsKey(HttpResponseHeaders.CONTENT_TYPE)) {
//			mime=headers.get(HttpResponseHeaders.CONTENT_TYPE);
//		}
//		if (mime.contains(MimeTypes.JSON)) {
//			return getBodyJson(bodyClass);
//		}else if(mime.contains(MimeTypes.XML)) {
//			return getBodyXml(bodyClass);
//		}else{
//			return null;
//		}
//	}
//	/**
//	 * Serializes the plain text body into an object using JSON parser (Jackson)
//	 * @param bodyClass The class of the object to serialize
//	 * @return a serialized object or null if any error;
//	 */
//	public <T> T getBodyJson(Class<T> bodyClass)  {
//		T bodyObject=null;
//		if (body!=null) {
//			ObjectMapper mapper = new ObjectMapper();
//			try {
//				bodyObject= mapper.readValue(body,bodyClass);
//			} catch (Exception e) {
//				errorMessage=e.getMessage();
//				errorType=e.getClass().getName();			}
//		}
//		return bodyObject;
//
//	}
//	/**
//	 * Serializes the plain text body into an object using XML parser (Simple XML)
//	 * @param bodyClass The class of the object to serialize
//	 * @return a serialized object or null if any error;
//	 */
//	public <T> T getBodyXml(Class<T> bodyClass)  {
//		T bodyObject=null;
//		if (body!=null) {
//			Serializer serializer=new Persister();
//			try {
//				bodyObject= serializer.read(bodyClass, body);
//			} catch (Exception e) {
//				errorMessage=e.getMessage();
//				errorType=e.getClass().getName();			}
//		}
//		return bodyObject;
//
//	}


}

package com.sergiandreplace.rarest.engine;

import android.text.TextUtils;
import com.sergiandreplace.rarest.exception.UrlFieldNotFoundInParams;
import com.sergiandreplace.rarest.exception.UrlFieldTypeShouldBeRest;
import com.sergiandreplace.rarest.exception.VerbNotAllowedException;
import com.sergiandreplace.rarest.logger.Logger;
import com.sergiandreplace.rarest.processor.Postprocessor;
import com.sergiandreplace.rarest.processor.Preprocessor;
import com.sergiandreplace.rarest.service.*;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Harvester {
    private static final Pattern urlFieldPattern = Pattern.compile("\\{.*?\\}");
    private static final String X_WWW_FORM_URL_ENCODED_TYPE = "application/x-www-form-urlencoded";

    private Service service;
    private static DefaultHttpClient standardHttpClient = new DefaultHttpClient();


    private String baseUrl;

    private Logger logger;


    public Harvester(String baseUrl, Service service) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    public Response execute() {
        return execute(standardHttpClient);
    }

    public Response execute(DefaultHttpClient httpClient) {
        int count = 0;
        int postResult;
        executePres();
        Response response = null;
        HttpResponse httpResponse;
        HttpRequestBase request = generateRequest();
        generateHeaders(request);
        try {
            request.setURI(new URI(generateUrl()));
            if (service.getVerb().hasBody) {
                ((HttpEntityEnclosingRequestBase) request).setEntity(generateEntity());
            }
            do {
                count++;
                httpResponse = httpClient.execute(request);
                response = new Response(httpResponse);
                postResult = executePosts(service, response, count);
            } while (postResult == Postprocessor.POST_RETRY);
        } catch (Exception e) {
            response = new Response(e);
        }

        return response;

    }

    private int executePosts(Service service, Response response, int count) {
        for (Post post : service.getPosts()) {
            Postprocessor p;
            try {
                p = (Postprocessor) Class.forName(post.getName()).newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int result = p.postprocess(service, response, count);
            if (result != Postprocessor.POST_CONTINUE) {
                return result;
            }
        }
        return Postprocessor.POST_CONTINUE;
    }


    private boolean executePres() {
        for (Pre pre : service.getPres()) {
            Preprocessor p;
            try {
                p = (Preprocessor) Class.forName(pre.getName()).newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (!p.preprocess(service)) return false;
        }
        return true;

    }

    /**
     * Generates a request based on the verb of the service
     *
     * @return An extension of HttpRequestBase (HttpDelete, HttpGet, etc)
     */
    private HttpRequestBase generateRequest() {
        HttpRequestBase request;
        switch (service.getVerb()) {
            case delete:
                request = new HttpDelete();
                break;
            case get:
                request = new HttpGet();
                break;
            case head:
                request = new HttpHead();
                break;
            case post:
                request = new HttpPost();
                break;
            case put:
                request = new HttpPut();
                break;
            default:
                throw new VerbNotAllowedException();
        }
        return request;
    }

    /**
     * Sets the header in an http request from service headers.
     *
     * @param httpRequest the get/post object to get initial headers
     * @return an array of Header objects
     */
    private Header[] generateHeaders(HttpMessage httpRequest) {
        for (com.sergiandreplace.rarest.service.Param param : service.getParams()) {

            String key = param.getName();
            if (param.getValue() != null && param.getType() == ParamType.header) {
                String value = param.getValue();// URLEncoder.encode(e.getValue());
                httpRequest.addHeader(key, value);
            }
        }
        return httpRequest.getAllHeaders();

    }


    private String generateUrl() {
        String url = baseUrl;
        if (!url.endsWith("/")) {
            url = url + "/";
        }
        String path = service.getUrl();
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        url = url + path;
        url = setUrlFields(url);
        url = setRestParams(url);
        return url;

    }

    /**
     * Matches all url fields with corresponding Rest parameters.
     *
     * @param url the String url to perform the replaces
     * @return Url with replaced fields
     */
    private String setUrlFields(String url) {
        String newUrl = url;
        Matcher urlFieldMatcher = urlFieldPattern.matcher(url);
        while (urlFieldMatcher.find()) {
            String field = urlFieldMatcher.group();
            field = field.substring(1, field.length() - 1);
            if (!service.hasParam(field)) {
                throw new UrlFieldNotFoundInParams(field);
            } else {
                Param param = service.getParam(field);
                if (param.getType() != ParamType.rest) {
                    throw new UrlFieldTypeShouldBeRest(field);
                } else {
                    String value = service.getParam(field).getValue();
                    if (value != null && !TextUtils.isEmpty(value)) {
                        value = URLEncoder.encode(value);
                        newUrl = newUrl.replaceAll("\\{" + field + "\\}", service.getParam(field).getValue());
                    }
                }
            }
        }
        return newUrl;
    }

    private String setRestParams(String url) {
        StringBuilder newUrl = new StringBuilder(url);
        boolean first = true;
        if (url.contains("?")) {
            newUrl.append("&");
        } else {
            newUrl.append("?");
        }
        for (Param param : service.getParams()) {
            String value = param.getValue();
            if (param.getType() == ParamType.query && value != null && !TextUtils.isEmpty(value)) {
                if (first) {
                    first = false;
                } else {
                    newUrl.append("&");
                }
                newUrl.append(param.getName());
                newUrl.append("=");
                newUrl.append(URLEncoder.encode(value));
            }
        }

        return newUrl.toString();
    }

    /**
     * return the entities array.
     *
     * @return an Entitiy object with all the parameters
     */
    public HttpEntity generateEntity() {
        HttpEntity ent;
        if (service.getContentType() == null || X_WWW_FORM_URL_ENCODED_TYPE.equals(service.getContentType())) {
            ent = generateXWwwFormUrlEncodedEntity();
        } else {
            ent = generateRawEntity();
        }

        return ent;
    }

    public HttpEntity generateXWwwFormUrlEncodedEntity() {
        List<NameValuePair> bodyParams = new ArrayList<NameValuePair>();
        for (Param param : service.getParams()) {
            if (param.getType() == ParamType.body && param.getValue() != null && !TextUtils.isEmpty(param.getValue())) {
                bodyParams.add(new BasicNameValuePair(param.getAlias(), param.getValue()));
            }
        }

        UrlEncodedFormEntity ent = null;
        try {
            ent = new UrlEncodedFormEntity(bodyParams);
        } catch (UnsupportedEncodingException e) {
            ent = null;
        }
        return ent;
    }

    public HttpEntity generateRawEntity() {
        StringEntity ent;
        String content = "";
        for (Param param : service.getParams()) {
            if (param.getType() == ParamType.body && param.getValue() != null && !TextUtils.isEmpty(param.getValue())) {
                content = param.getValue();
                break;
            }
        }
        try {
            ent = new StringEntity(content);
        } catch (UnsupportedEncodingException e) {
            ent = null;
        }
        return ent;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;

    }
}

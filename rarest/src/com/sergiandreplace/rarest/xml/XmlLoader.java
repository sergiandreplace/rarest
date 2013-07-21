package com.sergiandreplace.rarest.xml;

import android.util.Log;
import com.sergiandreplace.rarest.logger.Logger;
import com.sergiandreplace.rarest.service.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Sergi
 * Date: 21/07/13
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class XmlLoader {

    private static final String API = "API";
    private static final String API_BASEURL = "baseurl";

    private static final String LOGGER = "LOGGER";
    private static final String LOGGER_SHOW = "show";

    private static final String SERVICE = "SERVICE";
    private static final String SERVICE_NAME = "name";
    private static final String SERVICE_URL = "url";
    private static final String SERVICE_VERB = "verb";
    private static final String SERVICE_PARENT = "parent";
    private static final String SERVICE_DEFAULT_PARENT = "default";
    private static final String SERVICE_CONTENT_TYPE = "contentType";

    private static final String PARAM = "PARAM";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ALIAS = "name";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_MANDATORY = "mandatory";

    private static final String PRE = "PRE";
    private static final String PRE_NAME = "name";

    private static final String POST = "PRE";
    private static final String POST_NAME = "name";


    public static Api load(InputStream is) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            RarestHandler handler = new RarestHandler();
            saxParser.parse(is, handler);
            return handler.getApi();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    private static class RarestHandler extends DefaultHandler {

        private Api api;
        boolean inParam = false;

        Service service;
        Logger logger;
        Param param;
        Pre pre;
        Post post;


        public Api getApi() {
            return api;
        }


        @Override
        public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {


            Log.d("Rarest", "Start Element :" + qName);

            if (API.equalsIgnoreCase(qName)) {

                api = new Api();
                api.setServices(new ArrayList<Service>());

                for (int i = 0; i < attributes.getLength(); i++) {
                    if (attributes.getQName(i).equalsIgnoreCase(API_BASEURL)) {
                        api.setBaseUrl(attributes.getValue(API_BASEURL));
                    }
                }
            }
            if (LOGGER.equalsIgnoreCase(qName)) {

                logger = new Logger();

                for (int i = 0; i < attributes.getLength(); i++) {
                    if (attributes.getQName(i).equalsIgnoreCase(LOGGER_SHOW)) {
                        logger.setShow(attributes.getValue(LOGGER_SHOW).equalsIgnoreCase("true"));
                    }
                }
            }
            if (SERVICE.equalsIgnoreCase(qName)) {

                service = new Service();
                service.setParams(new ArrayList<Param>());
                service.setPres(new ArrayList<Pre>());
                service.setPosts(new ArrayList<Post>());

                for (int i = 0; i < attributes.getLength(); i++) {
                    if (attributes.getQName(i).equalsIgnoreCase(SERVICE_NAME)) {
                        service.setName(attributes.getValue(SERVICE_NAME));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(SERVICE_CONTENT_TYPE)) {
                        service.setContentType(attributes.getValue(SERVICE_CONTENT_TYPE));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(SERVICE_DEFAULT_PARENT)) {
                        service.setDefaultParent(attributes.getValue(SERVICE_DEFAULT_PARENT).equalsIgnoreCase("true"));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(SERVICE_PARENT)) {
                        service.setParent(attributes.getValue(SERVICE_PARENT));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(SERVICE_URL)) {
                        service.setUrl(attributes.getValue(SERVICE_URL));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(SERVICE_VERB)) {
                        service.setVerb(HttpVerb.getByName(attributes.getValue(SERVICE_VERB)));
                    }
                }
            }

            if (PARAM.equalsIgnoreCase(qName)) {
                inParam = true;
                param = new Param();

                for (int i = 0; i < attributes.getLength(); i++) {
                    if (attributes.getQName(i).equalsIgnoreCase(PARAM_NAME)) {
                        param.setName(attributes.getValue(PARAM_NAME));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(PARAM_ALIAS)) {
                        param.setAlias(attributes.getValue(PARAM_ALIAS));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(PARAM_TYPE)) {
                        param.setType(ParamType.getByName(attributes.getValue(PARAM_TYPE)));
                    }
                    if (attributes.getQName(i).equalsIgnoreCase(PARAM_MANDATORY)) {
                        param.setMandatory(attributes.getValue(PARAM_MANDATORY).equalsIgnoreCase("true"));
                    }

                }
            }
            if (PRE.equalsIgnoreCase(qName)) {
                pre=new Pre();
                for (int i = 0; i < attributes.getLength(); i++) {
                    if (attributes.getQName(i).equalsIgnoreCase(PRE_NAME)) {
                        pre.setName(attributes.getValue(PRE_NAME));
                    }
                }
            }
            if (POST.equalsIgnoreCase(qName)) {
                pre=new Pre();
                for (int i = 0; i < attributes.getLength(); i++) {
                    if (attributes.getQName(i).equalsIgnoreCase(POST_NAME)) {
                        post.setName(attributes.getValue(POST_NAME));
                    }
                }
            }
        }

        public void endElement(String uri, String localName,
                               String qName) throws SAXException {
            if (LOGGER.equalsIgnoreCase(qName)) {
                api.setLogger(logger);
            }
            if (SERVICE.equalsIgnoreCase(qName)) {
                api.getServices().add(service);
            }

            if (PARAM.equalsIgnoreCase(qName)) {
                inParam = false;
                service.getParams().add(param);
            }
            if (PRE.equalsIgnoreCase(qName)) {
                service.getPres().add(pre);
            }
            if (POST.equalsIgnoreCase(qName)) {
                service.getPosts().add(post);
            }
        }

        public void characters(char ch[], int start, int length) throws SAXException {
            if (inParam) {
                param.setValue(new String(ch));
            }
        }

    }


}

package com.sergiandreplace.rarest;

import com.sergiandreplace.rarest.engine.Harvester;
import com.sergiandreplace.rarest.engine.Response;
import com.sergiandreplace.rarest.exception.ConfigFileException;
import com.sergiandreplace.rarest.exception.ParamAliasNotFoundException;
import com.sergiandreplace.rarest.exception.ServiceNotFoundException;
import com.sergiandreplace.rarest.exception.ServiceNotLoadedException;
import com.sergiandreplace.rarest.logger.Logger;
import com.sergiandreplace.rarest.service.Api;
import com.sergiandreplace.rarest.service.Service;
import com.sergiandreplace.rarest.xml.XmlLoader;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class from the RaREST library. This will be responsible of all interactions with the library components.
 * @author Sergi Martínez
 *
 */
public class Ra {
	/**
	 * List of configuration files loaded. This will be maintained across instances
	 */
	private static Map<String, Api> apis;
    private static boolean loading=false;
	Logger logger;
	
	private Api api;


    /**
	 * The current loaded api for the current api object with its current values
	 * @return the whole api that is currently loaded
	 */
	public Api getApi() {
		return api;
	}

	
	private String serviceName;
	private Service service;


	/**
	 * Generates a new instance of the RA object with the selected api cloned and ready to be modified
	 * @param apiName the name of the api to use. If it it not currently loaded, a runtime error will be thrown
	 */
	public Ra(String apiName) {

		if (!apis.containsKey(apiName)) {
			throw new ConfigFileException();
		}
		api = apis.get(apiName);
		this.logger=api.getLogger();
		logger.log("Api " + apiName + " selected");
	}
	
	/**
	 * Static method that loads a config file and stores it
	 * @param apiName Key used to retrieve the configuration later on
	 * @param isConfig InputStream to the configuration file
	 */
	public static void loadApi(String apiName, InputStream isConfig) {
		if (apis==null) {
			apis=new HashMap<String,Api>();
		}
		Api api = XmlLoader.load(isConfig);
		apis.put(apiName, api);
		api.getLogger().log("Api " + apiName + " loaded");
	}

	/**
	 * Stablished the current service to invoke. From this point, all the set and header 
	 * calls are applied to this service.
	 * If you need to call another service, execute this method again with the new service. This will remove
	 * all the values established, and the new service will contain the values setup in the configuration file
	 * @param serviceName name of the service to load. It will throw a runtime error if service is not in the current config.
	 * @return the same Ra object. 
	 */
	public Ra service(String serviceName) {
		if (api.hasService(serviceName)) {
			this.serviceName=serviceName;
			this.service = api.loadService(serviceName);
			this.service.setLogger(logger);
		} else {
			throw new ServiceNotFoundException(serviceName);
		}
		return this;
	}

	/**
	 * 
	 * @param param
	 * @param value
	 * @return
	 */
	public Ra set(String param, String value) {
		if (service == null) {
			throw new ServiceNotLoadedException();
		}
		if (!service.hasParamByAlias(param)) {
			throw new ParamAliasNotFoundException(param);
		}
		service.getParamByAlias(param).setValue(value).setLogger(logger);
		return this;
	}

    public Ra set(Map<String, String> params) {
        for (Map.Entry<String, String> param:params.entrySet()) {
            set(param.getKey(), param.getValue());
        }
        return this;
    }

	public String getServiceName() {
		return serviceName;
	}
	
//	public Response execute() {
//		if (service == null) {
//			throw new ServiceNotLoadedException();
//		}
//		Harvester harvester=new Harvester(api.getBaseUrl(), service);
//		harvester.setLogger(logger);
//		return harvester.execute();
//	
//	}

	public Response execute(DefaultHttpClient httpClient) {
		if (service == null) {
			throw new ServiceNotLoadedException();
		}
		Harvester harvester=new Harvester(api.getBaseUrl(), service);
		harvester.setLogger(logger);
		return harvester.execute(httpClient);
	}


    public static boolean isLoading() {
        return loading;
    }

    public static void setLoading(boolean loading) {
        Ra.loading = loading;
    }
}

package com.sergiandreplace.rarest.service;

import com.sergiandreplace.rarest.exception.ServiceNotFoundException;
import com.sergiandreplace.rarest.logger.Logger;

import java.util.List;

public class Api {
	private String baseUrl;
	private Logger logger;
	private List<Service> services;


	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public String toString() {
		StringBuilder servicesBuilder=new StringBuilder();
		for (Service service:services) {
			servicesBuilder.append(service.toString());
			servicesBuilder.append("\n");
		}
		return String.format("===================\nApi.start baseUrl=%s \n%s\n%s\nApi.end\n=====================",baseUrl,logger.toString(),servicesBuilder);
	}
	public boolean hasService(String serviceName) {
		boolean found=false;
		for (Service service:services) {
			if (serviceName.equals(service.getName())) {
				found=true;
				break;
			}
		}
		return found;
	}
	
	public Service getService(String serviceName) {
		Service found=null;
		for (Service service:services) {
			if (serviceName.equals(service.getName())) {
				found=service;
				break;
			}
		}
		if (found==null) {
			throw new ServiceNotFoundException(serviceName);
		}
		return found;
	}
	
	public String getDefaultServiceName() {
		String found=null;
		for (Service service:services) {
			if (service.isDefaultParent()) {
				found=service.getName();
				break;
			}
		}
		return found;
	}
	public Service loadService(String serviceName) {
		Service service;
		Service fullService;
		Service parentService;
		
		service=getService(serviceName);
		
		String parentName=service.getParent();
		if (parentName==null) {
			parentName=getDefaultServiceName();
		}
		if (hasService(parentName)) {
			parentService=getService(parentName);
			fullService=Service.merge(parentService, service);
		}else{
			fullService=service.clone();
		}
		logger.log(fullService.toString());
		return fullService;
		
	}
	
}

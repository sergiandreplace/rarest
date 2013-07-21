package com.sergiandreplace.rarest.exception;

public class ServiceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public ServiceNotFoundException(String serviceName) {
		super(String.format("Service %s not found", serviceName));
	}
}

package com.sergiandreplace.rarest.exception;

public class ServiceNotLoadedException extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public ServiceNotLoadedException() {
		super("Service not loaded");
	}
}

package com.sergiandreplace.rarest.exception;

public class HeaderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public HeaderNotFoundException(String paramName) {
		super(String.format("Header with name %s not found", paramName));
	}
}

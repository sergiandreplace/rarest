package com.sergiandreplace.rarest.exception;

public class ParamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public ParamNotFoundException(String paramName) {
		super(String.format("Parameter with name %s not found", paramName));
	}
}

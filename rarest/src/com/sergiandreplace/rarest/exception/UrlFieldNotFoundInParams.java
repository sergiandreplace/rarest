package com.sergiandreplace.rarest.exception;

public class UrlFieldNotFoundInParams extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public UrlFieldNotFoundInParams(String fieldName) {
		super(String.format("Field with name %s not found in params list", fieldName));
	}
}

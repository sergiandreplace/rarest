package com.sergiandreplace.rarest.exception;

public class UrlFieldTypeShouldBeRest extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public UrlFieldTypeShouldBeRest(String fieldName) {
		super(String.format("Field with name %s is not set as rest type", fieldName));
	}
}

package com.sergiandreplace.rarest.exception;

public class NullHeaderException extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public NullHeaderException(String headerName) {
		super(String.format("Value null not valid for header %s", headerName));
	}
}

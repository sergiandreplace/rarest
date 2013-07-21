package com.sergiandreplace.rarest.exception;

public class ParamAliasNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public ParamAliasNotFoundException(String paramAlias) {
		super(String.format("Parameter with alias %s not found", paramAlias));
	}
}

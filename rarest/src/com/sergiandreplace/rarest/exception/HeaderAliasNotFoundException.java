package com.sergiandreplace.rarest.exception;

public class HeaderAliasNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6827877211091538905L;
	
	public HeaderAliasNotFoundException(String headerAlias) {
		super(String.format("Header with alias %s not found", headerAlias));
	}
}

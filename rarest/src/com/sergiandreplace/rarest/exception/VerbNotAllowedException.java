package com.sergiandreplace.rarest.exception;

public class VerbNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 7396254288122293939L;

	public VerbNotAllowedException() {
		super("verb not allowed");
	}
}

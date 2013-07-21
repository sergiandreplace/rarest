package com.sergiandreplace.rarest.exception;

public class ConfigFileException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3386389117652132805L;
	
	public ConfigFileException(Exception e) {
		super(e);
	}
	public ConfigFileException() {

	}

}

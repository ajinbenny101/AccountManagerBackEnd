package com.fdmgroup.springauth.exceptions;

public class ExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExistsException(String message) {
		super(message);
	}

}
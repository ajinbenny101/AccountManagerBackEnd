package com.fdmgroup.springauth.exceptions;

public class ConsultantNotFoundException extends Exception {

	private static final long serialVersionUID = 4778356605665051372L;

	public ConsultantNotFoundException(String message) {
		super(message);
	}

	public ConsultantNotFoundException() {
		super();
	}

}
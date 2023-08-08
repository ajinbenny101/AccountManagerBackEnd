package com.fdmgroup.springauth.exceptions;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 4778356605665051372L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super();
	}

}
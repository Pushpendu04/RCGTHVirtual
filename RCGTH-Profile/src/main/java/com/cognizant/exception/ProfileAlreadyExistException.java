package com.cognizant.exception;

public class ProfileAlreadyExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProfileAlreadyExistException(String string) {
		super(string);
	}
}
